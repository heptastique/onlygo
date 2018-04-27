package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Entities.CentreInteret;
import smart.Repositories.CentreInteretRepository;

@Service
public class CentreInteretService {

    @Autowired
    private CentreInteretRepository centreInteretRepository;

    public Iterable <CentreInteret> getCentreInteretAll() {
        return centreInteretRepository.findAll();
    }
}
