package com.jobx.firstjobpp.company.controllers;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.service.CompanyService;
import com.jobx.firstjobpp.helper.ApiResponse;
import com.jobx.firstjobpp.job.dataobjects.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{companySeq}")
    public ResponseEntity<ApiResponse<String>> updateJob(@PathVariable Long companySeq, @RequestBody Company company){
        boolean updated = companyService.updateCompany(companySeq, company);

        if(updated)
        {
            return new ResponseEntity<>(new ApiResponse<>("Company Updated Successfully", ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Company doesn't exist with seq: " + companySeq + " that can be updated.", ""), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Company>> createJob(@RequestBody Company company){
        if(company != null){
            companyService.createCompany(company);
            return new ResponseEntity<>(new ApiResponse<>("Company Added Successfully", company), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse<>("Company Not Added", null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companySeq}")
    public ResponseEntity<ApiResponse<String>> deleteJob(@PathVariable Long companySeq){
        boolean deleted = companyService.deleteCompanyById(companySeq);

        if(deleted)
        {
            return new ResponseEntity<>(new ApiResponse<>("Company Deleted Successfully", "Company having sequence: " + companySeq + " is deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Company do not exist with seq: " + companySeq + " that can be deleted.", null), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{companySeq}")
    public ResponseEntity<ApiResponse<Company>> getJobBySeq(@PathVariable Long companySeq){

        Company company = companyService.getCompanyById(companySeq);
        if(company != null)
        {
            return new ResponseEntity<>(new ApiResponse<>("Job found Successfully", company), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Portal do not have any job with seq: " + companySeq, null),HttpStatus.NOT_FOUND);
    }

}
