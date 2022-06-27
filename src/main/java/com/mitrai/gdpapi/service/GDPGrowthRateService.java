package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GDPGrowthRateService {

    @Autowired
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;

    public List<GDPResponseEntry> getGDPGrowthRates(String code, String from, String to){
        List<GDPGrowthRates> rates = gdpGrowthRatesRepository.findByCountry_CodeAndYear_YearGreaterThanEqualAndYear_YearLessThanEqual(code,Integer.parseInt(from),Integer.parseInt(to));
        List<GDPResponseEntry> responseEntries = new ArrayList<>();
        rates.forEach(gdpGrowthRates -> responseEntries.add(new GDPResponseEntry(gdpGrowthRates.getYear().getYear(),gdpGrowthRates.getGrowthRate())));
        return responseEntries;
    }

}
