package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.DTO.ProgrammeDTO;
import smart.Entities.Programme;
import smart.Entities.Sport;
import smart.Entities.User;
import smart.Jwt.JwtTokenUtil;
import smart.Repositories.SportRepository;
import smart.Repositories.UserRepository;
import smart.Services.ProgrammeService;
import smart.Services.SportService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class ProgrammeController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private UserRepository userRepository;

    /*@RequestMapping(path="/program/active", method = RequestMethod.GET)
    public ResponseEntity<?> addActivity(@RequestBody @Valid ActivityDTO activityDTO, HttpServletRequest request) {
        try{
            Activity activity = activityService.addActivity(activityDTO);
            return ResponseEntity.ok().body(activity);
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }*/



    @RequestMapping(path="/programme/active", method = RequestMethod.GET)
    public ResponseEntity<?> getActiveProgrammeOfUser(@RequestBody @Valid ProgrammeDTO programmeDTO, HttpServletRequest request) {
        // This returns a JSON or XML with the active program of the user (current week and all the activities it contains)
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Long userId = userRepository.findByUsername(username).getId();
        Programme programme = programmeService.getActiveProgrammeOfUser(userId);
        return ResponseEntity.ok().body(programme);
    }
}
