package com.mitrai.gdpapi.model;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.Year;

import javax.persistence.*;

@Entity
public class GDPGrowthRates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Year year;

    @Column(nullable = false)
    private double growthRate;

    public GDPGrowthRates() {
    }

    public GDPGrowthRates(Country country, Year year, double growthRate) {
        this.country = country;
        this.year = year;
        this.growthRate = growthRate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }
}
