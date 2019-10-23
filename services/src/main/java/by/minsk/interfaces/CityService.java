package by.minsk.interfaces;

import by.minsk.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO getById(long id);

    List<CityDTO> getAll();

    void delete(long id);

    CityDTO update(CityDTO cityDTO);

    CityDTO create(CityDTO cityDTO);

    CityDTO getByName(String capital);
}
