package com.mitrai.gdpapi.repository;

import com.mitrai.gdpapi.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {
    List<Country> findByOrderByNameAsc();

}
