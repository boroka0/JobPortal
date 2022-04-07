package com.boroka.jobsearchingapp.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;


}
