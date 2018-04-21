package smart.Services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import smart.Entities.Authority;
import smart.Repositories.AuthoRepository;


public class AuthorityService {

    @Autowired
    private AuthoRepository authoRepository;

    public Authority getAuthority(long id) throws NotFoundException {
         Authority a = authoRepository.findById(id);

        if (a == null) {
            throw new NotFoundException(String.format("No authority found with id '%d'.", id));
        } else {
            return a;
        }
    }
}
