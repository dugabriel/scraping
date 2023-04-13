package com.scraping.service;

import com.scraping.configuration.QuartzConfig;
import com.scraping.domain.Search;
import com.scraping.domain.User;
import com.scraping.exception.SchedulerErrorException;
import com.scraping.exception.SearchNotFoundException;
import com.scraping.exception.UserNotFoundException;
import com.scraping.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuartzConfig quartzConfig;

    @Transactional
    public Search create(Search search, String username) {
        String userId = checkValidUser(username);
        search.setUserId(userId);
        Search createdSearch = searchRepository.insert(search);
        createJob(createdSearch.getId(), createdSearch.getFrequency().getValue());
        return createdSearch;
    }

    @Transactional
    public Optional<Search> findById(String searchId) {
        return searchRepository.findById(searchId);
    }

    @Transactional
    public List<Search> findResearches(String username) {
        User user = userService.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User not found!"));
        return searchRepository.findByUserId(user.getId());
    }

    @Transactional
    public void deleteSearch(String searchId, String username) {
        checkValidSearch(searchId, username);
        removeJob(searchId);
        searchRepository.deleteById(searchId);
    }

    private String checkValidUser(String username) {
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        return userOptional.get().getId();
    }

    private void checkValidSearch(String searchId, String username) {
        searchRepository.findByUserId()
        if (searchRepository.findById(searchId).isEmpty()) {
            throw new SearchNotFoundException("search not found");
        }
    }

    private void createJob(String searchId, int frequency) {
        JobDetail jobDetail = JobBuilder.newJob(SchedulingService.class).withIdentity(searchId).build();
        jobDetail.getJobDataMap().put("searchId", searchId);

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(searchId)
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow()
                        .withIntervalInMinutes(frequency)
                        .repeatForever())
                .build();

        try {
            Scheduler scheduler = quartzConfig.schedulerFactoryBean().getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (IOException | SchedulerException e) {
            log.error("An error occurred. Removing search with id {}", searchId);
            deleteSearch(searchId);
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
