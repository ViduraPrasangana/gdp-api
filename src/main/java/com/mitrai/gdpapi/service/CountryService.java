package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return new ArrayList<>(countryRepository.findByOrderByNameAsc());
    }
}
