package by.minsk.controllers;

import by.minsk.dto.CityDTO;
import by.minsk.impl.CityServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/t_bot")
@Api
public class CityController {
    private CityServiceImpl cityService;

    @Autowired
    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll() {
        List<CityDTO> cityDTOList = cityService.getAll();
        return new ResponseEntity<List<CityDTO>>(cityDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable long id) {
        CityDTO cityDTO = cityService.getById(id);
        return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO cityDTO) {
        CityDTO cityDTO1 = cityService.create(cityDTO);
        return new ResponseEntity<CityDTO>(cityDTO1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO) {
        CityDTO cityDTO1 = cityService.update(cityDTO);
        return new ResponseEntity<CityDTO>(cityDTO1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        cityService.delete(id);
    }

}