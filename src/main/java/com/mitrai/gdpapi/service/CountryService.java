package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.repository.CountryRepository;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return new ArrayList<>(countryRepository.findByOrderByNameAsc());
    }
}
