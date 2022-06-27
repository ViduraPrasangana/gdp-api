package com.mitrai.gdpapi.service;

import com.mitrai.gdpapi.CSVHelper;
import com.mitrai.gdpapi.model.*;
import com.mitrai.gdpapi.repository.CountryRepository;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import com.mitrai.gdpapi.repository.YearRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVService {

    private CountryRepository countryRepository;
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;
    private YearRepository yearRepository;

    public ResponseEntity<ResponseMessage> handleFile(MultipartFile file){
        String message = "";
        try{
            if(CSVHelper.isCorrectType(file)){
                CSVParser csvParser = CSVHelper.extractRecords(file.getInputStream());
                List<CSVRecord> records = csvParser.getRecords();
                List<String> headers = csvParser.getHeaderNames();
                csvParser.close();

                List<Year> years = CSVHelper.extractYears(headers);
                List<Country> countries = CSVHelper.extractCountries(records);

                gdpGrowthRatesRepository.deleteAll();
                countryRepository.deleteAll();
                yearRepository.deleteAll();

                Iterable<Country> savedCountries = countryRepository.saveAll(countries);
                Iterable<Year> savedYears = yearRepository.saveAll(years);

                List<GDPGrowthRates> gdpGrowthRates = CSVHelper.extractGrowthRates(records,savedCountries,savedYears);
                gdpGrowthRatesRepository.saveAll(gdpGrowthRates);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

            }else{
                message = "Invalid file type: " + file.getContentType();
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ResponseMessage(message));

            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
            message = "Error occurred : " + e.getMessage();
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage(message));

    }

}
