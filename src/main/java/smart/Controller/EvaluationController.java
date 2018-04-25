package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import smart.Services.EvaluationService;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping(path="/evaluation/now", method = RequestMethod.GET)
    public ResponseEntity<?> getEvaluationNow() {
        return ResponseEntity.ok(evaluationService.getEvaluationNow());
    }
}
