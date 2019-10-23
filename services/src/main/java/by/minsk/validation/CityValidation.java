package by.minsk.validation;

import by.minsk.dto.CityDTO;
import by.minsk.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityValidation {
    private CityRepository cityRepository;

    @Autowired
    public CityValidation(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void validateCityToUpdate(CityDTO cityDTO) {
        long id = cityDTO.getId();
        validateIdExists(id);

        String name = cityDTO.getName();
        validateName(name);

        String description = cityDTO.getDescription();
        validateDescription(description);
    }

    public void validateCityToCreate(CityDTO cityDTO) {
        long id = cityDTO.getId();
        validateIdToCreate(id);

        String name = cityDTO.getName();
        validateName(name);

        String description = cityDTO.getDescription();
        validateDescription(description);
    }

    public void validateCityToDelete(long id) {
        validateIdExists(id);
    }

    public void validateCityExists(long id) {
        validateIdExists(id);
    }


    private void validateIdToCreate(long id) {
        if (cityRepository.findOne(id) != null) {
            throw new RuntimeException("city with such id already exists");
        }
    }

    private void validateIdExists(long id) {
        if (cityRepository.findOne(id) == null) {
            throw new RuntimeException("no city with such id ");
        }
    }

    private void validateName(String name) {
        if (name == null) {
            throw new RuntimeException("field Name can not be empty");
        }
        if (!name.matches("[А-Яа-я]{3,}")) {
            throw new RuntimeException("wrong input, check the spelling of field Name");
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            throw new RuntimeException("field Description can not be empty");
        }
    }
}


