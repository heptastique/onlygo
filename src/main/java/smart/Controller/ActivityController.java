package smart.Controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.DTO.PolylinePointsDTO;
import smart.Entities.*;
import smart.Jwt.JwtTokenUtil;
import smart.Services.ActivityService;
import smart.Services.ProgrammeService;
import smart.Services.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class ActivityController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/activity/nextPlanned", method = RequestMethod.GET)
    public ResponseEntity<?> getNextPlannedActivity(HttpServletRequest request) {
        // This returns a JSON or XML with the next planned activity of the current program (week) from today onwards.
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Programme programme;

        try{
            programme = programmeService.getActiveProgrammeOfUser(username);
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        Activity activity = activityService.getNextActivity(programme);
        if(activity==null)
        {
            return ResponseEntity.status(204).body("Plus d'activité prévue.\nVous avez terminé votre programme de la semaine !");
        }
        return ResponseEntity.ok().body(activity);
    }

    @RequestMapping(path="/activity/itinary", method = RequestMethod.GET)
    public ResponseEntity<?> getItinary(@RequestParam(value = "user", defaultValue = "user1") String userName, @RequestParam(value = "activite", defaultValue = "10008") long id) {
        User user = null;
        try {
            user = userService.getUserByUsername(userName);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        Activity activity = activityService.getActivity(id);
        PolylinePointsDTO itinerary = activityService.findItinary(user, activity, activity.getCentreInteret());
        return ResponseEntity.ok().body(itinerary);
    }
}
