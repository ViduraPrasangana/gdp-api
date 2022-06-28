package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.service.GDPGrowthRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/gdp")
public class GDPGrowthRateController {
    Logger logger = LoggerFactory.getLogger(GDPGrowthRateController.class);

    @Autowired
    private GDPGrowthRateService gdpGrowthRateService;

    @GetMapping()
    public ResponseEntity<List<GDPResponseEntry>> findGdpByCountryAndYears(@RequestParam("country") String code, @RequestParam("from") String from, @RequestParam("to") String to, HttpServletRequest request){
        String msg = String.format("GET request to GDP Growth Rate endpoint from %s",request.getRemoteAddr());
        logger.info(msg);
        return gdpGrowthRateService.getGDPGrowthRates(code,from,to);
    }


}
