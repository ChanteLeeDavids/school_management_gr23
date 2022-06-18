package za.ac.cput.controller.details;

/**
 * Name: Onele Tomson
 * Student number: 219483280
 *CountryController
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.details.Country;
import za.ac.cput.service.details.impl.CountryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("school_management_gr23/country/")
@Log4j2

public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country){
        log.info("Save request: {}",country);
        try {
            Country newCountry = countryService.create(country);
            return ResponseEntity.ok(country);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("readCountry/{countryid}")
    public ResponseEntity<Country> read(@PathVariable String countryId){
        log.info("Read request: {}", countryId);

        try {
            Country readCountry = countryService.read(countryId);
            return ResponseEntity.ok(readCountry);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> list = this.countryService.getAll();
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("deleteCountry/{countryId}")
    public ResponseEntity<Country> deleteCountry(@PathVariable String countryId){
        log.info("Delete Request: {}",countryId);
        this.countryService.delete(countryId);
        return ResponseEntity.noContent().build();
    }


}
