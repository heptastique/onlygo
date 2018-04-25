package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smart.DTO.UserDto;
import smart.Entities.User;
import smart.Exceptions.EmailExistsException;
import smart.Repositories.AuthoRepository;
import smart.Repositories.UserRepository;

import java.util.Arrays;
import java.util.Date;


@Service
public class EvaluationService {

    @Autowired
    private AuthoRepository authoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public double getEvaluationNow() {
        return 0.5;
    }
}
