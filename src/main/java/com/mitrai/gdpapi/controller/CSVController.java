package com.mitrai.gdpapi.controller;

import com.mitrai.gdpapi.CSVHelper;
import com.mitrai.gdpapi.repository.CountryRepository;
import com.mitrai.gdpapi.repository.GDPGrowthRatesRepository;
import com.mitrai.gdpapi.repository.YearRepository;
import com.mitrai.gdpapi.model.Country;
import com.mitrai.gdpapi.model.GDPGrowthRates;
import com.mitrai.gdpapi.model.ResponseMessage;
import com.mitrai.gdpapi.model.Year;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GDPGrowthRatesRepository gdpGrowthRatesRepository;

    @Autowired
    private YearRepository yearRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      RedirectAttributes redirectAttributes)  {
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
