package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.DTO.RealisationDTO;
import smart.Entities.Programme;
import smart.Entities.Realisation;
import smart.Entities.User;
import smart.Jwt.JwtTokenUtil;
import smart.Repositories.UserRepository;
import smart.Services.ProgrammeService;
import smart.Services.RealisationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class RealisationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RealisationService realisationService;

    @RequestMapping(path="/realisation/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRealisation(@RequestBody @Valid RealisationDTO realisationDTO, HttpServletRequest request) {
        // This returns a JSON or XML with the active program of the user (current week and all the activities it contains)
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        try{
            Realisation realisation = realisationService.addRealisation(realisationDTO, username);
            return ResponseEntity.ok().body(realisation);
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

