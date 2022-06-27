package com.mitrai.gdpapi.model;

import com.mitrai.gdpapi.YearStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;

import javax.persistence.*;

@Entity
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,name = "year")
    @PodamStrategyValue(YearStrategy.class)
    private long yearVal;

    public Year() {
    }

    public Year(long year) {
        this.yearVal = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getYear() {
        return yearVal;
    }

    public void setYear(long yearVal) {
        this.yearVal = yearVal;
    }
}
