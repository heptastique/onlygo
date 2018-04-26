package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import smart.Entities.TimeFrame;
import smart.Services.TimeFrameService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class TimeFrameController {

    @Autowired
    private TimeFrameService timeFrameService;

    @RequestMapping(path="/timeFrame/all", method = RequestMethod.GET)
    public ResponseEntity<?> getTimeFrameAll() {
        List <TimeFrame> timeFrames = new ArrayList <TimeFrame> ();
        for(TimeFrame timeFrame : timeFrameService.getTimeFrameAll()) {
            timeFrames.add(timeFrame);
        }
        return ResponseEntity.ok().body(timeFrames);
    }

    @RequestMapping(path="/timeFrame/now", method = RequestMethod.GET)
    public ResponseEntity<?> getTimeFrameNow() {
        TimeFrame timeFrame = timeFrameService.getTimeFrame("now");
        return ResponseEntity.ok().body(timeFrame);
    }

    @RequestMapping(path="/timeFrame/time", method = RequestMethod.GET)
    public ResponseEntity<?> getTimeFrameDate(@RequestParam(value = "time", defaultValue = "now") String time) {
        TimeFrame timeFrame = timeFrameService.getTimeFrame(time);
        return ResponseEntity.ok().body(timeFrame);
    }

    @RequestMapping(path="/timeFrame/evaluation/now", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationNow() {
        double evaluation = timeFrameService.getEvaluation("now");
        return ResponseEntity.ok().body(evaluation);
    }

    @RequestMapping(path="/timeFrame/evaluation/time", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationDate(@RequestParam(value = "time", defaultValue = "now") String time) {
        double evaluation = timeFrameService.getEvaluation(time);
        return ResponseEntity.ok().body(evaluation);
    }
}
