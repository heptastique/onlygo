package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PollutionDataDto;
import smart.Repositories.DonneeAthmospheriqueRepository;

public class DonneeAthmospheriqueService {

    @Autowired
    private DonneeAthmospheriqueRepository donneeAthmospheriqueRepository;

    @Value("${openweather.apikey}")
    private String openWeatherApiKey;

    public PollutionDataDto UpdateDonneeAthmospheriqueData() {
        RestTemplate restTemplate = new RestTemplate();
        PollutionDataDto response = restTemplate.getForObject("http://api.atmo-aura.fr/communes/69123/indices?api_token=" + openWeatherApiKey, PollutionDataDto.class);
        return response;
    }

}
