package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> findAllCountry(){
        return countryService.getAllCountries();
    }

}
