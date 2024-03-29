package by.minsk.repository;

import by.minsk.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByName(String name);
}
