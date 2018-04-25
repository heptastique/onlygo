package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import smart.Services.EvaluationService;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping(path="/evaluation/now", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationNow() {
        return ResponseEntity.ok(evaluationService.getEvaluation());
    }

    @RequestMapping(path="/evaluation/date", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationDate(@RequestParam(value = "date", defaultValue = "now") String date) {
        return ResponseEntity.ok(evaluationService.getEvaluation(date));
    }
}
