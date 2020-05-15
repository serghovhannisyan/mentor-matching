package com.mentorcliq.mentormatching.service;

import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    /**
     * Service method for employee import via csv file
     * @param file
     * @return response containing highest set(s)
     */
    EmployeeCSVImportResponseDto upload(MultipartFile file);
}
