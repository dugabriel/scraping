package com.scraping.service.olx;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.scraping.exception.ScrapingException;
import com.scraping.service.ScrapingService;
import com.scraping.service.vo.ScrapingResultVO;
import com.scraping.service.vo.ScrapingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OlxSearchService implements ScrapingService {

    @Override
    public List<ScrapingResultVO> scrapingList(ScrapingVO scrapingVO) {
        List<ScrapingResultVO> scrapingResultList = new ArrayList<>();

        log.info("Initiate scraping for user {}", scrapingVO.getUserId());

        try (WebClient client = new WebClient()) {
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage htmlPage = client.getPage(scrapingVO.getUrl());
            List<HtmlElement> items = htmlPage.getByXPath("//div[@class='sc-12rk7z2-0 bjnzhV']");

            items.stream().forEach(item -> {
                HtmlAnchor linkItem = (HtmlAnchor) item.getFirstChild();
                String title = linkItem.getAttribute("title");
                HtmlSpan spanPrice = (HtmlSpan) item.getFirstByXPath(".//span[@class='m7nrfa-0 eJCbzj sc-fzsDOv kHeyHD']");

                log.info("OLX --> Found item {}. Price {}. Link {}", title, spanPrice.getTextContent(), linkItem.getHrefAttribute());

                scrapingResultList.add(new ScrapingResultVO().builder()
                        .name(title)
                        .price(spanPrice.getTextContent())
                        .link(linkItem.getHrefAttribute())
                        .build());
            });
        } catch (IOException e) {
            throw new ScrapingException(e.getMessage());
        }

        return scrapingResultList;
    }

}
