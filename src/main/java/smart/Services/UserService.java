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
    private SportRepository sportRepository;

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

        Iterable<Sport> sports = sportRepository.findAll();
        List<Objectif> objectifList = new ArrayList<Objectif>();
        for(Sport sport : sports)
        {
            Objectif objectif = new Objectif(userDto.getObjectifHebdo(), sport);
            objectifList.add(objectif);
        }
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

    public double putObjectifHebdo(String username, Long sportId, double distance){
        User user = userRepository.findByUsername(username) ;
        Programme activeProgramme = programmeService.getActiveProgrammeOfUser(username);
        Sport sport = sportRepository.findById(sportId).get();
        double updatedObjectif = 0d;
        boolean foundInProgram = false;
        boolean foundInUser = false;

        // Updating active program goal
        List<Objectif> objectifs = activeProgramme.getObjectifs();
        for(Objectif objectif : objectifs)
        {
            if((long)objectif.getSport().getId()==(long)sport.getId())
            {
                objectif.setObjectif(distance);
                foundInProgram = true;
            }
        }
        if(!foundInProgram)
        {
            Objectif newObjectif = new Objectif(distance, sport);
            activeProgramme.addObjectif(newObjectif);
        }
        programmeRepository.save(activeProgramme);

        // Updating user goal
        List<Objectif> objectifsUtilisateur = user.getObjectifs();
        for(Objectif objectifUser : objectifsUtilisateur)
        {
            if((long)objectifUser.getSport().getId()==(long)sport.getId())
            {
                objectifUser.setObjectif(distance);
                updatedObjectif = objectifUser.getObjectif();
                foundInUser = true;
            }
        }
        if(!foundInUser)
        {
            Objectif newObjectif = new Objectif(distance, sport);
            user.addObjectif(newObjectif);
        }
        userRepository.save(user);

        return updatedObjectif;
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
