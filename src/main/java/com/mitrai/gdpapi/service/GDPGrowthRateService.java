package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GDPGrowthRateService {
    @Autowired
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;

    public ResponseEntity<List<GDPResponseEntry>> getGDPGrowthRates(String code, String from, String to){
        List<GDPGrowthRates> rates = gdpGrowthRatesRepository.findByCountry_CodeAndYear_YearGreaterThanEqualAndYear_YearLessThanEqual(code,Integer.parseInt(from),Integer.parseInt(to));
        List<GDPResponseEntry> responseEntries = new ArrayList<>();
        rates.forEach(gdpGrowthRates -> responseEntries.add(new GDPResponseEntry(gdpGrowthRates.getYear().getYear(),gdpGrowthRates.getGrowthRate())));
        return ResponseEntity.status(HttpStatus.OK).body(responseEntries);
    }

}
