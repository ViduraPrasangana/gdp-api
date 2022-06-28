package com.mitrai.gdpapi.controller;


import com.mitrai.gdpapi.model.ResponseMessage;
import com.mitrai.gdpapi.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/csv")
public class CSVController {
    Logger logger = LoggerFactory.getLogger(CSVController.class);

    @Autowired
    private CSVService csvService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      RedirectAttributes redirectAttributes, HttpServletRequest request)  {
        String msg = String.format("POST request to CSV endpoint from %s",request.getRemoteAddr());
        logger.info(msg);
        return csvService.handleFile(file);
    }
}
