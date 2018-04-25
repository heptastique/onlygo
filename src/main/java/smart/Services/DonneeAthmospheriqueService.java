package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PollutionDataDto;
import smart.Repositories.DonneeAthmospheriqueRepository;

@Service
public class DonneeAthmospheriqueService {

    @Autowired
    private DonneeAthmospheriqueRepository donneeAthmospheriqueRepository;

    @Value("${atmoApiKey}")
    private String atmoApiKey;

    public PollutionDataDto UpdateDonneeAthmospheriqueData() {
        RestTemplate restTemplate = new RestTemplate();
        PollutionDataDto response = restTemplate.getForObject("http://api.atmo-aura.fr/communes/69123/indices?api_token=" + atmoApiKey, PollutionDataDto.class);
        return response;
    }

}
