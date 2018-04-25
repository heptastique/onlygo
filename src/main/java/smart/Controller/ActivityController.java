package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.DTO.ActivityDTO;
import smart.Entities.Activity;
import smart.Services.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(path="/activity/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivity(@RequestBody @Valid ActivityDTO activityDTO, HttpServletRequest request) {
        try{
            Activity activity = activityService.addActivity(activityDTO);
            return ResponseEntity.ok().body(activity);
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
