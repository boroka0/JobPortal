package com.boroka.jobsearchingapp.jobs;

import com.boroka.jobsearchingapp.user.UserDto;
import com.boroka.jobsearchingapp.user.UserEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class JobService implements InitializingBean {
    private final List<JobEntity> initJobs = List.of(
            JobEntity.builder().jobName("Junior software engineer").location("Budapest").build(),
            JobEntity.builder().jobName("Junior software developer").location("Budapest").build(),
            JobEntity.builder().jobName("Junior software developer").location("Eger").build(),
            JobEntity.builder().jobName("Medior software developer").location("Budapest").build(),
            JobEntity.builder().jobName("Senior software developer").location("Szeged").build()
    );

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jobRepository.count() == 0) {
            jobRepository.saveAll(
                    initJobs.stream()
                            .map(dto -> JobEntity.builder()
                                    .jobName(dto.getJobName())
                                    .location(dto.getLocation())
                                    .build())
                            .collect(Collectors.toList())
            );
        }
    }

    private JobDto buildDto(JobEntity entity) {
        return JobDto.builder()
                .jobName(entity.getJobName())
                .location(entity.getLocation())
                .build();
    }

    @Autowired
    private JobRepository jobRepository;

    public void createJob(JobEntity job) {
        jobRepository.save(
                JobEntity.builder()
                        .jobName(job.getJobName())
                        .location(job.getLocation())
                        .build());
    }

    public void save(JobEntity job) {
        jobRepository.save(job);
    }

    public List<JobDto> findAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::buildDto)
                .collect(Collectors.toList());
    }

    public List<JobDto> findJobByKeyword(String keyword) {
        return jobRepository.findAllByJobNameContains(keyword).stream()
                .map(this::buildDto)
                .collect(Collectors.toList());
    }

}
