package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import smart.Entities.Evaluation;
import smart.Services.EvaluationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping(path="/evaluation/all", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationAll() {
        List <Evaluation> evaluations = new ArrayList <Evaluation> ();
        for(Evaluation evaluation : evaluationService.getEvaluationAll()) {
            evaluations.add(evaluation);
        }
        return ResponseEntity.ok().body(evaluations);
    }

    @RequestMapping(path="/evaluation/now", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationNow() {
        return ResponseEntity.ok(evaluationService.getEvaluation("now"));
    }

    @RequestMapping(path="/evaluation/time", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationDate(@RequestParam(value = "time", defaultValue = "now") String time) {
        return ResponseEntity.ok(evaluationService.getEvaluation(time));
    }
}
