package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PollutionDataDto;
import smart.DTO.PollutionDataElemDto;
import smart.Entities.DonneeAthmospherique;
import smart.Repositories.DonneeAthmospheriqueRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DonneeAthmospheriqueService {

    @Autowired
    private DonneeAthmospheriqueRepository donneeAthmospheriqueRepository;

    @Value("${atmoApiKey}")
    private String atmoApiKey;

    public PollutionDataDto UpdateDonneeAthmospheriqueData() {
        RestTemplate restTemplate = new RestTemplate();
        PollutionDataDto response = restTemplate.getForObject("http://api.atmo-aura.fr/communes/69123/indices?api_token=" + atmoApiKey, PollutionDataDto.class);
        for(PollutionDataElemDto elem : response.getIndices().getData()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date elemDate = df.parse(elem.getDate());
                DonneeAthmospherique donnee = new DonneeAthmospherique(Double.parseDouble(elem.getValeur()), elemDate);
                donneeAthmospheriqueRepository.save(donnee);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return response;
    }
    public Iterable <DonneeAthmospherique> getDonneeAthmospheriqueAll() {
        return donneeAthmospheriqueRepository.findAll();
    }
}
