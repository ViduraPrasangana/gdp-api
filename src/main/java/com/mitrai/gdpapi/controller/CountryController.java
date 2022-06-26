package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.repository.CountryRepository;
import com.mitrai.gdpapi.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<Country> findAllCountry(){
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return countries;
    }

    @PostMapping
    public Country saveCountry() {
        Country c = new Country("Sri lanka","SL");
        return countryRepository.save(c);
    }

}
