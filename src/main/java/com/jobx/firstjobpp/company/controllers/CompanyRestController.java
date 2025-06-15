package com.jobx.firstjobpp.company.controllers;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.service.CompanyService;
import com.jobx.firstjobpp.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyRestController {

    private CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getAllJobs(){
        List<Company> companies = companyService.getAllCompanies();
        if(companies != null && !companies.isEmpty()){
            return new ResponseEntity<>(new ApiResponse<>("Companies Found Successfully.", companies), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("No Companies Found.", null), HttpStatus.NOT_FOUND);
    }

}
