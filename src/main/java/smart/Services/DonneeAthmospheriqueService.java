package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PollutionDataDto;
import smart.DTO.PollutionDataElemDto;
import smart.Entities.DonneeAthmospherique;
import smart.Entities.Jour;
import smart.Repositories.DonneeAthmospheriqueRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static smart.Algorithms.FindByJour.findDay;

@Service
public class DonneeAthmospheriqueService {

    @Autowired
    private DonneeAthmospheriqueRepository donneeAthmospheriqueRepository;

    @Value("${atmoApiKey}")
    private String atmoApiKey;

    public PollutionDataDto UpdateDonneeAthmospheriqueData() {
        RestTemplate restTemplate = new RestTemplate();
        PollutionDataDto response = restTemplate.getForObject("http://api.atmo-aura.fr/communes/69123/indices?api_token=" + atmoApiKey, PollutionDataDto.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate;
        DonneeAthmospherique lastDonneeAthmospherique = null;
        for(PollutionDataElemDto elem : response.getIndices().getData()) {
            try {
                Date elemDate = df.parse(elem.getDate());
                DonneeAthmospherique donnee = new DonneeAthmospherique(Double.parseDouble(elem.getValeur()), elemDate);
                donneeAthmospheriqueRepository.save(donnee);
                lastDonneeAthmospherique = donnee;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        try {
            lastDate = df.parse(response.getIndices().getData().get(0).getDate());
        }catch (Exception e) {
            System.err.println(e.getMessage());
            lastDate = new Date();
        }
        // set today date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int currentDay = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(lastDate);
        do
        {
            calendar.add(Calendar.DAY_OF_YEAR,1);
            Date date = calendar.getTime();
            double lastIndice = lastDonneeAthmospherique.getIndice();
            DonneeAthmospherique donnee;
            if  ( findDay(lastDonneeAthmospherique.getDate()) == Jour.DIMANCHE){
                donnee = new DonneeAthmospherique(lastIndice + (Math.random()-.7)*20, date);
            }
            else
            {
                donnee = new DonneeAthmospherique(lastIndice + (Math.random()-0.3)*15, date);
            }

            donneeAthmospheriqueRepository.save(donnee);
            lastDonneeAthmospherique = donnee;
        }while ( calendar.get(Calendar.DAY_OF_YEAR) != (currentDay + 7) );

        return response;
    }
    public Iterable <DonneeAthmospherique> getDonneeAthmospheriqueAll() {
        return donneeAthmospheriqueRepository.findAll();
    }
}
