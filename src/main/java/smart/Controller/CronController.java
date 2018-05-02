package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import smart.Algorithms.JsonToSQL;
import smart.Services.DonneeAthmospheriqueService;
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
    JsonToSQL jsonToSQL;

    @RequestMapping(path="/update", method = RequestMethod.GET)
    public ResponseEntity<?> updateTimeFrame(){
        weatherDataService.UpdateWeatherData();
        donneeAthmospheriqueService.UpdateDonneeAthmospheriqueData();
        timeFrameService.updateEvaluation(weatherDataService.getWeatherDataAll(),
            donneeAthmospheriqueService.getDonneeAthmospheriqueAll());
        return ResponseEntity.ok("Ok");

    }

    @RequestMapping(path="/pointToSQL", method = RequestMethod.GET)
    public ResponseEntity<?> updatePoint(){
        jsonToSQL.generate();
        return ResponseEntity.ok("Ok");
    }

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Ok");
    }



}
