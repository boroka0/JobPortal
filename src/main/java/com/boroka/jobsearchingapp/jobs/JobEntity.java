package com.boroka.jobsearchingapp.jobs;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Please enter a valid job name (should be under 50 characters)")
    @Size(min=2, max=50)
    private String jobName;
    @NotEmpty(message = "Please enter a valid location (should be under 50 characters)")
    @Size(min=2, max=50)
    private String location;
}
