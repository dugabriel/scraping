package com.scraping.service;

import com.scraping.domain.User;
import com.scraping.exception.UserNotFoundException;
import com.scraping.service.olx.OlxSearchService;
import com.scraping.service.vo.ScrapingResultVO;
import com.scraping.service.vo.ScrapingVO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SchedulingService implements Job {

    @Autowired
    private SearchService searchService;
    @Autowired
    private OlxSearchService olxSearchService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("execute job trigger!");

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String searchId = dataMap.get("searchId").toString();

        searchService.findById(searchId)
                .ifPresentOrElse(search -> {

                    switch (search.getSource()) {
                        case OLX:
                            List<ScrapingResultVO> scrapingList = olxSearchService.scrapingList(new ScrapingVO().builder()
                                    .url(search.getSearchExpression())
                                    .source(search.getSource())
                                    .userId(search.getUserId())
                                    .build());
                            log.info("Found {} items to user {}", scrapingList.size(), search.getUserId());

                            if (!scrapingList.isEmpty()) {
                                User user = userService.findById(search.getUserId()).orElseThrow();
                                mailSenderService.sendMail(scrapingList, user);
                            }
                            break;
                        default:
                            log.info("Source not found {}", search.getSource());
                    }

                }, () -> log.info("search id {} not found on schedule", searchId));
    }
}
