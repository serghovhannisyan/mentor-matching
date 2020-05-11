package com.mentorcliq.mentormatching.controller;

import com.mentorcliq.mentormatching.exception.InvalidFileException;
import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import com.mentorcliq.mentormatching.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.MethodHandles;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class EmployeeApiController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final EmployeeService employeeService;

    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/upload-csv")
    public EmployeeCSVImportResponseDto upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            log.error("File is missing {}", file.getName());
            throw new InvalidFileException("File is empty");
        }

        return employeeService.readFromCSV(file);
    }
}
