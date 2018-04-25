package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.WeatherData;

public interface WeatherDataRepository extends CrudRepository<WeatherData,Long> {
}
