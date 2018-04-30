package smart.Services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smart.Algorithms.ProgramActivities;
import smart.DTO.PointDto;
import smart.DTO.UserDto;
import smart.Entities.*;
import smart.Exceptions.EmailExistsException;
import smart.Exceptions.UsernameExistsException;
import smart.Jwt.JwtUser;
import smart.Repositories.AuthoRepository;
import smart.Repositories.ProgrammeRepository;
import smart.Repositories.UserRepository;

import java.util.Arrays;
import java.util.Date;


@Service
public class UserService {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(UserDto userDto) throws EmailExistsException, UsernameExistsException, NotFoundException {
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(
                    "Cette adresse email est déjà utilisée par un autre compte");
        }
        if (userExist(userDto.getUsername())) {
            throw new UsernameExistsException(
                    "Ce nom d'utilisateur est indisponible car pris par un autre compte ");

        }
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        Date date = java.util.Calendar.getInstance().getTime();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLastPasswordResetDate(date);
        user.setEnabled(true);
        user.setObjectifHebdo(userDto.getObjectifHebdo());
        try{
            user.setAuthorities(Arrays.asList(authorityService.getAuthority(1)));
        }catch (Exception e){
            throw new NotFoundException("Authority not found");
        }

        return userRepository.save(user);
    }

    public User getUserByUsername(String username)throws NotFoundException{
        return userRepository.findByUsername(username);
    }

    public User addLocationToUser(String username, Point location) throws NotFoundException{
        User user = userRepository.findByUsername(username);
        user.setLocation(location);
        return userRepository.save(user);
    }

    public double putObjectifHebdo(String username, double distance){
        User user = userRepository.findByUsername(username) ;
        Programme activeProgramme = programmeService.getActiveProgrammeOfUser(username);
        activeProgramme.setObjectifDistance(distance);
        programmeRepository.save(activeProgramme);
        user.setObjectifHebdo(distance);
        userRepository.save(user);
        return user.getObjectifHebdo();
    }

    public double setDistanceMax(String username, double distance){
        User user = userRepository.findByUsername(username) ;
        user.setDistanceMax(distance);
        userRepository.save(user);
        return user.getDistanceMax();
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean userExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public double getObjectifHebdo(String username) {
        User user = userRepository.findByUsername(username) ;
        return user.getObjectifHebdo();
    }
    public double getDistanceMax(String username) {
        User user = userRepository.findByUsername(username) ;
        return user.getDistanceMax();
    }


    public double getPourcentageCourant(String username) {
        User user = userRepository.findByUsername(username);
        double realisations=0;
        Programme activeProgram = programmeService.getActiveProgrammeOfUser(username);
        for(Realisation r:activeProgram.getRealisations()){
            realisations +=r.getDistance();
        }

        return 100*realisations/activeProgram.getObjectifDistance();
    }
}
