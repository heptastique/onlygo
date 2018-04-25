package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smart.DTO.UserDto;
import smart.Entities.User;
import smart.Exceptions.EmailExistsException;
import smart.Exceptions.UsernameExistsException;
import smart.Repositories.AuthoRepository;
import smart.Repositories.UserRepository;

import java.util.Arrays;
import java.util.Date;


@Service
public class UserService {

    @Autowired
    private AuthoRepository authoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(UserDto userDto) throws EmailExistsException, UsernameExistsException {
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
        user.setAuthorities(Arrays.asList(authoRepository.findById(1)));
        return userRepository.save(user);
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
}
