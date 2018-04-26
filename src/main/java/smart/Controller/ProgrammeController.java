package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Jwt.JwtTokenUtil;
import smart.Repositories.UserRepository;
import smart.Services.ProgrammeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(path="/programme/active", method = RequestMethod.GET)
    public ResponseEntity<?> getActiveProgrammeOfUser(HttpServletRequest request) {
        // This returns a JSON or XML with the active program of the user (current week and all the activities it contains)
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userRepository.findByUsername("admin");
        Programme programme = programmeService.getActiveProgrammeOfUser(user);
        return ResponseEntity.ok().body(programme);
    }
}
