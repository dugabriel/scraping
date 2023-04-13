package com.scraping.service.olx;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
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

            if (items.isEmpty()) {
                items = htmlPage.getByXPath("//li[@class='sc-1mburcf-1 kNvXDP']");

                items.stream().forEach(item -> {
                    HtmlAnchor linkItem = (HtmlAnchor) item.getFirstChild();

                    HtmlDivision titleDivision = item.getFirstByXPath("//div[@class='sc-gRnDUn jYrXQv']");
                    String title = titleDivision.getFirstChild().getTextContent();

                    HtmlSpan spanPrice = (HtmlSpan) item.getFirstByXPath(".//span[@class='main-price sc-ifAKCX bytyxL']");
                    HtmlImage image = (HtmlImage) item.getFirstByXPath(".//img");

                    log.info("OLX --> Found item {}. Price {}. Link {}", title, spanPrice.getTextContent() == null ? "" : spanPrice.getTextContent(), linkItem.getHrefAttribute());

                    scrapingResultList.add(new ScrapingResultVO().builder()
                            .name(title)
                            .price(spanPrice.getTextContent() == null ? "" : spanPrice.getTextContent())
                            .link(linkItem.getHrefAttribute())
                            .imageUrl(image.getSrc() == null ? "" : image.getSrc())
                            .build());
                });

            } else {
                items.stream().forEach(item -> {
                    HtmlAnchor linkItem = (HtmlAnchor) item.getFirstChild();
                    String title = linkItem.getAttribute("title");
                    HtmlSpan spanPrice = (HtmlSpan) item.getFirstByXPath(".//span[@class='m7nrfa-0 eJCbzj sc-ifAKCX jViSDP']");
                    HtmlImage image = (HtmlImage) item.getFirstByXPath(".//img");

                    log.info("OLX --> Found item {}. Price {}. Link {}", title, spanPrice.getTextContent() == null ? "" : spanPrice.getTextContent(), linkItem.getHrefAttribute());

                    scrapingResultList.add(new ScrapingResultVO().builder()
                            .name(title)
                            .price(spanPrice.getTextContent() == null ? "" : spanPrice.getTextContent())
                            .link(linkItem.getHrefAttribute())
                            .imageUrl(image.getSrc() == null ? "" : image.getSrc())
                            .build());
                });
            }
        } catch (IOException e) {
            throw new ScrapingException(e.getMessage());
        }

        return scrapingResultList;
    }

}
