package be.vdab.cheese.controllers;

import be.vdab.cheese.dto.CountryMetCheeses;
import be.vdab.cheese.exceptions.CountryNietGevondenException;
import be.vdab.cheese.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("countries")
class CountryController {
    private final CountryService countryService;

    CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("{id}/withcheeses")
    CountryMetCheeses findByIdMetCheeses(@PathVariable long id) {
        var country = countryService.findById(id).orElseThrow(CountryNietGevondenException::new);
        return new CountryMetCheeses(country);
    }
}
