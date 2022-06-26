package com.mitrai.gdpapi.repository;

import com.mitrai.gdpapi.model.GDPGrowthRates;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GDPGrowthRatesRepository extends CrudRepository<GDPGrowthRates, Long> {
    List<GDPGrowthRates> findByCountry_CodeAndYear_YearGreaterThanEqualAndYear_YearLessThanEqual(String code, long year, long year1);
}
