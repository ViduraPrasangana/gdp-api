package com.mitrai.gdpapi.model;

public class GDPResponseEntry {
    private long year;
    private double growthRate;

    public GDPResponseEntry(long year, double growthRate) {
        this.year = year;
        this.growthRate = growthRate;
    }

    public long getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }
}
