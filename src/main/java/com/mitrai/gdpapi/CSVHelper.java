package com.mitrai.gdpapi;

import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.Year;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHelper {
    public static final String TYPE = "text/csv";
    private static final String[] columnNames = {"Country Name", "Country Code"};

    public static boolean isCorrectType(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    public static CSVParser extractRecords(InputStream is) throws ParseException {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             return new CSVParser(fileReader,
                     CSVFormat.Builder.create()
                             .setHeader()
                             .setSkipHeaderRecord(true)
                             .setTrim(true)
                             .build());

        } catch (IOException e) {
            throw new ParseException("Failed to parse of CSV file: " + e.getMessage(),0);
        }
    }

    public static List<Year> extractYears(List<String> years){
        List<Year> yearsObjects = new ArrayList<>();

        years.forEach(s -> {
            try{
                yearsObjects.add(new Year(Integer.parseInt(s)));
            }catch (NumberFormatException ignore){}
        });

        return yearsObjects;
    }

    public static List<GDPGrowthRates> extractGrowthRates(List<CSVRecord> csvRecords, Iterable<Country> countries, Iterable<Year> years) throws ParseException {
        List<GDPGrowthRates> gdpGrowthRates = new ArrayList<>();
        Map<String,Country> countryMap = new HashMap<>();

        countries.forEach(country -> {
            countryMap.put(country.getCode(),country);
        });

        for (CSVRecord csvRecord : csvRecords) {
            Country country = countryMap.get(csvRecord.get(columnNames[1]));

            years.forEach(year -> {
                try{
                    double rate = Double.parseDouble(csvRecord.get(String.valueOf(year.getYear())));
                    gdpGrowthRates.add(new GDPGrowthRates(country,year,rate));
                }catch (NullPointerException | NumberFormatException ignore){}

            });

        }
        return gdpGrowthRates;
    }

    public static List<Country> extractCountries(List<CSVRecord> csvRecords) {
        List<Country> countries = new ArrayList<>();

        csvRecords.forEach(csvRecord -> {
            countries.add(new Country(csvRecord.get(0),csvRecord.get(1)));
        });

        return countries;
    }
}
