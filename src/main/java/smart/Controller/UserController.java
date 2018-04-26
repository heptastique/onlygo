package smart.Controller;

import javassist.NotFoundException;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import smart.DTO.PointDto;
import smart.DTO.DistanceDto;
import smart.DTO.UserDto;
import smart.Entities.Point;
import smart.Entities.User;
import smart.Exceptions.EmailExistsException;
import smart.Exceptions.UsernameExistsException;
import smart.Jwt.JwtAuthenticationResponse;
import smart.Jwt.JwtTokenUtil;
import smart.Jwt.JwtUser;
import smart.Jwt.JwtUserFactory;
import smart.Repositories.UserRepository;
import smart.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.lang.reflect.TypeVariable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

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

    @RequestMapping(path="/user/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() throws ParseException {
        // This returns a JSON or XML with the users
        List<JwtUser> jwtUsers = new ArrayList<JwtUser>();
        for(User user: userService.getAllUsers()){
            jwtUsers.add(JwtUserFactory.create(user));
        }
        return ResponseEntity.ok().body(jwtUsers);
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
          DistanceDto updatedDistance = new DistanceDto();
          updatedDistance.setDistance(userService.getObjectifHebdo(username));
          return ResponseEntity.ok().body(updatedDistance);
    }

    @RequestMapping(value = "/user/objectif", method = RequestMethod.GET)
    public ResponseEntity<?> getUserObjectif(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body("{\"objectif\":" +  user.getObjectifHebdo() + "}");
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
