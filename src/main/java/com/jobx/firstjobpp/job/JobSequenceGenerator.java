package com.jobx.firstjobpp.job;

public class JobSequenceGenerator {

    private static JobSequenceGenerator instance;
    private Long seq = 100L;

    private JobSequenceGenerator(){}

    public static JobSequenceGenerator getInstance() {
        // Check if the instance is null (first time access)
        if (instance == null) {
            // If null, create the instance
            instance = new JobSequenceGenerator();
        }
        return instance;
    }

    public Long getNextSeq() {
        return ++seq;
    }
}
