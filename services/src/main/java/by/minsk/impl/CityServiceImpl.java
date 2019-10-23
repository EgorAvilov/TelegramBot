package by.minsk.impl;

import by.minsk.converters.CityConverter;
import by.minsk.dto.CityDTO;
import by.minsk.entity.City;
import by.minsk.interfaces.CityService;
import by.minsk.repository.CityRepository;
import by.minsk.validation.CityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private CityValidation cityValidation;
    private CityConverter cityConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository,
                           CityValidation cityValidation,
                           CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityValidation = cityValidation;
        this.cityConverter = cityConverter;
    }

    @Override
    public CityDTO getById(long id) {
        cityValidation.validateCityExists(id);
        City city = cityRepository.findOne(id);
        return cityConverter.convertToDTO(city);
    }

    @Override
    public List<CityDTO> getAll() {
        return cityRepository.findAll()
                             .stream()
                             .map(cityConverter::convertToDTO)
                             .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        cityValidation.validateCityToDelete(id);
        cityRepository.delete(id);
    }

    @Override
    public CityDTO update(CityDTO cityDTO) {
        cityValidation.validateCityToUpdate(cityDTO);
        City city = cityConverter.convertToEntity(cityDTO);
        city = cityRepository.save(city);
        return cityConverter.convertToDTO(city);
    }

    @Override
    public CityDTO create(CityDTO cityDTO) {
        cityValidation.validateCityToCreate(cityDTO);
        City city = cityConverter.convertToEntity(cityDTO);
        city = cityRepository.save(city);
        return cityConverter.convertToDTO(city);
    }

    @Override
    public CityDTO getByName(String name) {
        try {
            City city = cityRepository.findCityByName(name);
            return cityConverter.convertToDTO(city);
        }catch(RuntimeException e){
            return null;
        }
    }
}
