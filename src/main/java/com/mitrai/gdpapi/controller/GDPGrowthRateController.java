package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.service.GDPGrowthRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gdp")
public class GDPGrowthRateController {
    @Autowired
    private GDPGrowthRateService gdpGrowthRateService;

    @GetMapping()
    public List<GDPResponseEntry> findGdpByCountryAndYears(@RequestParam("country") String code, @RequestParam("from") String from,@RequestParam("to") String to){
        return gdpGrowthRateService.getGDPGrowthRates(code,from,to);
    }


}
