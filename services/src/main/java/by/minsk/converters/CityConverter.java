package by.minsk.converters;

import by.minsk.dto.CityDTO;
import by.minsk.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {
    public CityDTO convertToDTO(City city) {
        long id = city.getId();
        String name = city.getName();
        String description = city.getDescription();

        return CityDTO.builder()
                      .id(id)
                      .name(name)
                      .description(description)
                      .build();
    }

    public City convertToEntity(CityDTO cityDTO) {
        long id = cityDTO.getId();
        String name= cityDTO.getName();
        String description = cityDTO.getDescription();

        return City.builder()
                   .id(id)
                   .name(name)
                   .description(description)
                   .build();
    }
}
