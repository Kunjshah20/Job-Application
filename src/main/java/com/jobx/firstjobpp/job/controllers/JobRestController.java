package com.jobx.firstjobpp.job.controllers;

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
    public ResponseEntity<List<Job>> findAll(){
        //new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
        return ResponseEntity.ok(jobService.findAll()); // another way of doing the same
    }

    //@PostMapping("/jobs")
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    //@GetMapping("/jobs/{jobSeq}")
    @GetMapping("/{jobSeq}")
    public ResponseEntity<Job> getJobBySeq(@PathVariable Long jobSeq){

        Job job = jobService.getJobById(jobSeq);
        if(job != null)
        {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@DeleteMapping("/jobs/{jobSeq}")
    @DeleteMapping("/{jobSeq}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobSeq){
        boolean deleted = jobService.deleteJobById(jobSeq);

        if(deleted)
        {
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Portal do not have any job with seq: " + jobSeq + " that can be deleted.", HttpStatus.NOT_FOUND);
    }

    //@PutMapping("/jobs/{jobSeq}")
    @PutMapping("/{jobSeq}")
    //@RequestMapping(value = "/job/{id}", method = RequestMethod.PUT) // Method level
    public ResponseEntity<String> updateJob(@PathVariable Long jobSeq, @RequestBody Job job){
        boolean updated = jobService.updateJob(jobSeq, job);

        if(updated)
        {
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Portal do not have any job with seq: " + jobSeq + " that can be updated.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllJobs(){
        boolean deleted = jobService.deleteAll();

        if(deleted)
        {
            return new ResponseEntity<>("All Jobs Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Portal do not have any active jobs for deletion.", HttpStatus.NOT_FOUND);
    }

}
