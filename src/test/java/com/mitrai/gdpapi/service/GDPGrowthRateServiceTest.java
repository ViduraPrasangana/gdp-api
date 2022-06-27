package com.mitrai.gdpapi.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.GDPResponseEntry;
import com.mitrai.gdpapi.model.Year;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GDPGrowthRateServiceTest {
    @Mock
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;
    @InjectMocks
    private GDPGrowthRateService gdpGrowthRateService;
    @Test
    void getGDPGrowthRates() {
        PodamFactory factory = new PodamFactoryImpl();
        @SuppressWarnings("unchecked") List<GDPGrowthRates> rates = (List<GDPGrowthRates>) factory.manufacturePojoWithFullData(ArrayList.class,GDPGrowthRates.class);
        String country = "SL";
        long from = 1961;
        long to = 2010;
        when(gdpGrowthRatesRepository.findByCountry_CodeAndYear_YearGreaterThanEqualAndYear_YearLessThanEqual(country,from,to)).thenReturn(rates);

        assertThat(gdpGrowthRateService.getGDPGrowthRates(country,String.valueOf(from),String.valueOf(to)))
                .hasSize(rates.size())
                .doesNotContainNull()
                .extracting(GDPResponseEntry::getGrowthRate)
                .doesNotContainNull();
    }
}