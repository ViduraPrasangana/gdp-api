package com.mitrai.gdpapi.service;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.repository.CountryRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    void getAllCountries_notContainsNull_sortedByName() {
        PodamFactory factory = new PodamFactoryImpl();

        @SuppressWarnings("unchecked")
        List<Country> countries = (List<Country>) factory.manufacturePojoWithFullData(ArrayList.class,Country.class).stream().sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());

//        List<Country> countries = generator.objects(Country.class,5).sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());

        when(countryRepository.findByOrderByNameAsc()).thenReturn(countries);

        assertThat(countryService.getAllCountries().getBody())
                .containsAll(countries)
                .hasSize(5)
                .doesNotContainNull()
                .extracting(Country::getName)
                .isSorted();
    }
}