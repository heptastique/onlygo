package smart.Controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.DTO.DistanceDto;
import smart.DTO.PointDto;
import smart.DTO.UserDto;
import smart.Entities.Point;
import smart.Entities.User;
import smart.Jwt.JwtTokenUtil;
import smart.Jwt.JwtUserFactory;
import smart.Services.ProgrammeService;
import smart.Services.RealisationService;
import smart.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RealisationService realisationService;

    @Autowired
    private ProgrammeService programmeService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try{
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user);
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(path="/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        try{
            User user = userService.addUser(userDto);
            JwtUserFactory.create(user);
            return ResponseEntity.ok().body(user);
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @RequestMapping(path="/user/location",method = RequestMethod.PUT)
    public ResponseEntity<?> setLocation(@RequestBody PointDto pointDto,
                                         HttpServletRequest request) throws NotFoundException{
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Point location = new Point();
        location.setY(pointDto.getY());
        location.setX(pointDto.getX());
        User user = userService.addLocationToUser(username,location);
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/user/location", method = RequestMethod.GET)
    public ResponseEntity<?> getUserLocation(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user.getLocation());
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @RequestMapping(path="/user/objectif", method = RequestMethod.PUT)
    public ResponseEntity<?> addObjectifHebdo(@RequestBody DistanceDto distance, HttpServletRequest request) {
          String token = request.getHeader(tokenHeader).substring(7);
          String username = jwtTokenUtil.getUsernameFromToken(token);
          userService.putObjectifHebdo(username,distance.getDistance());
          return ResponseEntity.ok().body(userService.getObjectifHebdo(username));
    }

    @RequestMapping(value = "/user/objectif", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserObjectif(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user.getObjectifs());
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/user/progression", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPourcentage(HttpServletRequest  request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try {
            return ResponseEntity.ok().body("{\"progression\":" + userService.getPourcentageCourant(username) + "}");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/user/realisation", method = RequestMethod.GET)
    public ResponseEntity<?> getUserRealisations(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try{
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(realisationService.getUserRealisations(user));
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @RequestMapping(path="/user/maxDistance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDistanceMax(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body("{\"distance\":" +  user.getDistanceMax() + "}");
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @RequestMapping(path="/user/maxDistance", method = RequestMethod.PUT)
    public ResponseEntity<?> setDistanceMax(@RequestBody DistanceDto distance, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        userService.setDistanceMax(username,distance.getDistance());
        DistanceDto updatedDistance = new DistanceDto();
        updatedDistance.setDistance(userService.getDistanceMax(username));
        return ResponseEntity.ok().body(updatedDistance);
    }
}
