package test.Services;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import smart.DTO.PollutionDataDto;
import smart.Entities.DonneeAthmospherique;
import smart.Repositories.DonneeAthmospheriqueRepository;
import smart.Services.DonneeAthmospheriqueService;

import static org.junit.Assert.assertNotNull;

public class DonneeAthmospheriqueServiceTest {

    @Autowired
    private DonneeAthmospheriqueService donneeAthmospheriqueService;

    @Before
    public void InitServices() {
        donneeAthmospheriqueService = new DonneeAthmospheriqueService();
    }

    @Test
    public void DataIsCorrectlyFetched() {
        PollutionDataDto data = donneeAthmospheriqueService.UpdateDonneeAthmospheriqueData();
        assertNotNull(data.getCommune());
    }

}
