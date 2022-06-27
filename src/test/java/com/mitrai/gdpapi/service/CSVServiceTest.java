package com.mitrai.gdpapi.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.Year;
import com.mitrai.gdpapi.repository.CountryRepository;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import com.mitrai.gdpapi.repository.YearRepository;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.number.LongRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CSVServiceTest {
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;
    @Mock
    private YearRepository yearRepository;

    @InjectMocks
    private CSVService csvService;

    @Test
    void handleFile() throws IOException {

        PodamFactory factory = new PodamFactoryImpl();

        @SuppressWarnings("unchecked") List<Country> countries = (List<Country>) factory.manufacturePojoWithFullData(ArrayList.class,Country.class);
        @SuppressWarnings("unchecked") List<Year> years = (List<Year>) factory.manufacturePojoWithFullData(ArrayList.class,Year.class);
        @SuppressWarnings("unchecked") List<GDPGrowthRates> rates = (List<GDPGrowthRates>) factory.manufacturePojoWithFullData(ArrayList.class,GDPGrowthRates.class);

        when(countryRepository.saveAll(any())).thenReturn(countries);
        when(yearRepository.saveAll(any())).thenReturn(years);
        when(gdpGrowthRatesRepository.saveAll(any())).thenReturn(rates);


        FileInputStream inputFile = new FileInputStream( "src/main/resources/gdpGrowthRates.csv");
        MockMultipartFile file = new MockMultipartFile("file", "gdpGrowthRates.csv", "text/csv", inputFile);

        assertThat(csvService.handleFile(file)).matches(r -> r.getStatusCode().equals(HttpStatus.CREATED));
    }
}