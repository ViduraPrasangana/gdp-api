package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    Logger logger = LoggerFactory.getLogger(CountryController.class);
    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> findAllCountry(HttpServletRequest request){
        String msg = String.format("GET request to country endpoint from %s",request.getRemoteAddr());
        logger.info(msg);
        return countryService.getAllCountries();
    }

}
