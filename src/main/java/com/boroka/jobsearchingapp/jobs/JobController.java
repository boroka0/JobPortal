package com.boroka.jobsearchingapp.jobs;

import com.boroka.jobsearchingapp.user.UserDto;
import com.boroka.jobsearchingapp.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/jobs")
    public String jobs(Model model) {
        return jobs();
    }

    private String jobs() {
        return "/jobs";
    }


    @PostMapping("/jobs")
    public String createJob(
            @ModelAttribute("formJob") @Valid JobEntity formJob,
            BindingResult bindingResult,
            Model model
    ) {
        if (!bindingResult.hasErrors()) {
            jobService.createJob(formJob);
        }
        return jobs();
    }

    @PostMapping("/jobs/{id}")
    public String save(
            @PathVariable Integer id,
            @ModelAttribute("formJob") @Valid JobEntity formJob,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            jobService.save(formJob);
        }
        return jobs();
    }

    @GetMapping("/jobs/search")
    public String findJob(
            Model model,
            String keyword
    ) {
        if (keyword != null) {
            List<JobDto> list = jobService.findJobByKeyword(keyword);
            model.addAttribute("list", list);
        } else {
            List<JobDto> list = jobService.findAllJobs();
            model.addAttribute("list", list);
        }
        return "jobs";
    }

    @ModelAttribute("allJob")
    List<JobDto> allJob() {
        return jobService.findAllJobs();
    }

    @ModelAttribute("allJobsWithKeyword")
    List<JobDto> allJobsWithKeyword(String keyword) {
        return jobService.findJobByKeyword(keyword);
    }

    @ModelAttribute("formJob")
    public JobEntity formJob() {
        return new JobEntity();
    }

    @ModelAttribute("formNewJob")
    public Boolean formNewJob() {
        return true;
    }

}
