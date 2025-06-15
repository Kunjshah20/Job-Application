package com.jobx.firstjobpp.job.controllers;

import com.jobx.firstjobpp.helper.ApiResponse;
import com.jobx.firstjobpp.job.dataobjects.Job;
import com.jobx.firstjobpp.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs") // Class Level
// now I don't have to write jobs/id everywhere since this is defined at class level
// and where only jobs is used, I need to remove it entirely making my base url more maintainable
public class JobRestController {

    private JobService jobService;

    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    //@GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<Job>>> findAll(){
        List<Job> jobs = jobService.findAll();
        if(jobs != null && !jobs.isEmpty()){
            //new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
            return ResponseEntity.ok(new ApiResponse<>("Jobs Found Successfully", jobs)); // another way of doing the same
        }
        return new ResponseEntity<>(new ApiResponse<>("No Jobs Found.", null), HttpStatus.NOT_FOUND);
    }

    //@PostMapping("/jobs")
    @PostMapping
    public ResponseEntity<ApiResponse<Job>> createJob(@RequestBody Job job){
        if(job != null && job.getCompany() != null && job.getCompany().getCompanySeq() != null){
            Job savedJob = jobService.createJob(job);
            if (savedJob != null) {
                return new ResponseEntity<>(new ApiResponse<>("Job Added Successfully", savedJob), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse<>("Company Not Found", null), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Not Added", null), HttpStatus.NOT_FOUND);
    }

    //@GetMapping("/jobs/{jobSeq}")
    @GetMapping("/{jobSeq}")
    public ResponseEntity<ApiResponse<Job>> getJobBySeq(@PathVariable Long jobSeq){

        Job job = jobService.getJobById(jobSeq);
        if(job != null)
        {
            return new ResponseEntity<>(new ApiResponse<>("Job found Successfully", job), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Portal do not have any job with seq: " + jobSeq, null),HttpStatus.NOT_FOUND);
    }

    //@DeleteMapping("/jobs/{jobSeq}")
    @DeleteMapping("/{jobSeq}")
    public ResponseEntity<ApiResponse<String>> deleteJob(@PathVariable Long jobSeq){
        boolean deleted = jobService.deleteJobById(jobSeq);

        if(deleted)
        {
            return new ResponseEntity<>(new ApiResponse<>("Job Deleted Successfully", "Job having sequence: " + jobSeq + " is deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Portal do not have any job with seq: " + jobSeq + " that can be deleted.", null), HttpStatus.NOT_FOUND);
    }

    //@PutMapping("/jobs/{jobSeq}")
    @PutMapping("/{jobSeq}")
    //@RequestMapping(value = "/job/{id}", method = RequestMethod.PUT) // Method level
    public ResponseEntity<ApiResponse<String>> updateJob(@PathVariable Long jobSeq, @RequestBody Job job){
        boolean updated = jobService.updateJob(jobSeq, job);

        if(updated)
        {
            return new ResponseEntity<>(new ApiResponse<>("Job Updated Successfully", ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Portal do not have any job with seq: " + jobSeq + " that can be updated.", ""), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteAllJobs(){
        boolean deleted = jobService.deleteAll();

        if(deleted)
        {
            return new ResponseEntity<>(new ApiResponse<>("All Jobs Deleted Successfully", ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("Job Portal do not have any active jobs for deletion.", ""), HttpStatus.NOT_FOUND);
    }

}
