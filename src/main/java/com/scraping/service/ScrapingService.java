package com.scraping.service;

import com.scraping.service.vo.ScrapingResultVO;
import com.scraping.service.vo.ScrapingVO;

import java.util.List;

public interface ScrapingService {

    List<ScrapingResultVO> scrapingList(ScrapingVO scrapingVO);
}
