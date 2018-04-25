package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smart.Entities.Sport;
import smart.Repositories.SportRepository;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

}
