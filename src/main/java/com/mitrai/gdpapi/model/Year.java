package com.mitrai.gdpapi.model;

import javax.persistence.*;

@Entity
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private long year;

    public Year() {
    }

    public Year(long year) {
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
