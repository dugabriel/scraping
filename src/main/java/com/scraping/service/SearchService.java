package com.scraping.service;

import com.scraping.configuration.QuartzConfig;
import com.scraping.domain.Search;
import com.scraping.exception.SchedulerErrorException;
import com.scraping.exception.SearchNotFoundException;
import com.scraping.exception.UserNotFoundException;
import com.scraping.repository.SearchRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuartzConfig quartzConfig;

    @Transactional
    public Search create(Search search) {
        checkValidUser(search.getUserId());
        Search createdSearch = searchRepository.insert(search);
        createJob(createdSearch.getId());
        return createdSearch;
    }

    @Transactional
    public Optional<Search> findById(String searchId) {
        return searchRepository.findById(searchId);
    }

    @Transactional
    public List<Search> findResearches(String userId) {
        return searchRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteSearch(String searchId) {
        checkValidSearch(searchId);
        removeJob(searchId);
        searchRepository.deleteById(searchId);
    }

    private void checkValidUser(String userId) {
        if (userService.findById(userId).isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
    }

    private void checkValidSearch(String searchId) {
        if (searchRepository.findById(searchId).isEmpty()) {
            throw new SearchNotFoundException("search not found");
        }
    }

    private void createJob(String searchId) {
        JobDetail jobDetail = JobBuilder.newJob(SchedulingService.class).withIdentity(searchId).build();
        jobDetail.getJobDataMap().put("searchId", searchId);

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(searchId)
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .build();

        try {
            Scheduler scheduler = quartzConfig.schedulerFactoryBean().getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (IOException | SchedulerException e) {
            throw new SchedulerErrorException(e.getMessage());
        }
    }

    private void removeJob(String searchId) {
        try {
            Scheduler scheduler = quartzConfig.schedulerFactoryBean().getScheduler();
            scheduler.deleteJob(new JobKey(searchId));
            TriggerKey triggerKey = new TriggerKey(searchId);
            scheduler.unscheduleJob(triggerKey);
        } catch (IOException | SchedulerException e) {
            throw new SchedulerErrorException(e.getMessage());
        }
    }
}
