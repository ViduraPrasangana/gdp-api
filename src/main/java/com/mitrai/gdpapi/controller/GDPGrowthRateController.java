package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gdp")
public class GDPGrowthRateController {
    @Autowired
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;

    @GetMapping()
    public List<GDPResponseEntry> findGdpByCountryAndYears(@RequestParam("country") String code, @RequestParam("from") String from,@RequestParam("to") String to){
        List<GDPGrowthRates> rates = gdpGrowthRatesRepository.findByCountry_CodeAndYear_YearGreaterThanEqualAndYear_YearLessThanEqual(code,Integer.parseInt(from),Integer.parseInt(to));
        List<GDPResponseEntry> responseEntries = new ArrayList<>();
        rates.forEach(gdpGrowthRates -> {
            responseEntries.add(new GDPResponseEntry(gdpGrowthRates.getYear().getYear(),gdpGrowthRates.getGrowthRate()));
        });
        return responseEntries;
    }


}
