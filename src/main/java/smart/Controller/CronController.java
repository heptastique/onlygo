package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import smart.Algorithms.JsonToSQL;
import smart.Services.DonneeAthmospheriqueService;
import smart.Services.PointService;
import smart.Services.TimeFrameService;
import smart.Services.WeatherDataService;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class CronController {

    @Autowired
    TimeFrameService timeFrameService;
    @Autowired
    WeatherDataService weatherDataService;
    @Autowired
    DonneeAthmospheriqueService donneeAthmospheriqueService;
    @Autowired
    PointService pointService;

    @RequestMapping(path="/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTimeFrame(){
        weatherDataService.UpdateWeatherData();
        donneeAthmospheriqueService.UpdateDonneeAthmospheriqueData();
        timeFrameService.updateEvaluation(weatherDataService.getWeatherDataAll(),
            donneeAthmospheriqueService.getDonneeAthmospheriqueAll());
        return ResponseEntity.ok("{\"status\": \"Ok\"}");

    }

    @RequestMapping(path="/importStravaPoints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePoint(){
        pointService.generate();
        return ResponseEntity.ok("{\"status\": \"Ok\"}");
    }

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Ok");
    }



}
