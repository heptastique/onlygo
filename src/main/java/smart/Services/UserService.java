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
import smart.Repositories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


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
    private ActivityRepository activityRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private SportService sportService;

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

        Objectif objectif = new Objectif();
        objectif.setObjectif(userDto.getObjectifHebdo());
        objectif.setSport(sportService.getSport("Course"));

        List<Objectif> objectifList = new ArrayList<Objectif>();
        objectifList.add(objectif);

        user.setObjectifs(objectifList);

        user.setDistanceMax(userDto.getDistanceMax());

        try{
            user.setAuthorities(Arrays.asList(authorityService.getAuthority(1)));
        }catch (Exception e){
            throw new NotFoundException("Authority not found");
        }

        Point localisation = new Point();
        localisation.setX(userDto.getLocation().getX());
        localisation.setY(userDto.getLocation().getY());

        localisation = pointRepository.save(localisation);

        user.setLocation(localisation);

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

        // Updating active program goal
        List<Objectif> objectifs = activeProgramme.getObjectifs();
        objectifs.get(0).setObjectif(distance);

        programmeRepository.save(activeProgramme);

        // Updating user goal
        List<Objectif> objectifsUtilisateur = user.getObjectifs();
        objectifsUtilisateur.get(0).setObjectif(distance);
        user.setObjectifs(objectifsUtilisateur);
        userRepository.save(user);

        return user.getObjectifs().get(0).getObjectif();
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

    public List<Objectif> getObjectifHebdo(String username) {
        User user = userRepository.findByUsername(username) ;
        return user.getObjectifs();
    }
    public double getDistanceMax(String username) {
        User user = userRepository.findByUsername(username) ;
        return user.getDistanceMax();
    }

    public double getPourcentageCourant(String username) {
        User user = userRepository.findByUsername(username);
        double realisations=0;
        Programme activeProgram = programmeService.getActiveProgrammeOfUser(username);
        Iterable<Activity> realList = activityRepository.findByProgrammeAndEstRealisee(activeProgram, true);
        for(Activity activity : realList)
        {
            realisations += activity.getDistanceRealisee();
        }

        return 100*realisations/activeProgram.getObjectifs().get(0).getObjectif();
    }
}
