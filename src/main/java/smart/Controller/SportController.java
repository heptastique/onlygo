package smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smart.Entities.Sport;
import smart.Repositories.SportRepository;
import smart.Services.SportService;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "http://localhost:8100" )
public class SportController {

    @Autowired
    private SportService sportService;

    @RequestMapping(path="/sport/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSports() throws ParseException {
        // This returns a JSON or XML with the sports
        Iterable<Sport> sports = sportService.getAllSports();
        return ResponseEntity.ok().body(sports);
    }
}
