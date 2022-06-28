package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.status(HttpStatus.OK).body(countryRepository.findByOrderByNameAsc());
    }
}
