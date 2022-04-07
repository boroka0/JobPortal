package com.boroka.jobsearchingapp.jobs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {
    List<JobEntity> findAllByJobNameContains(String name);
}
