package com.scraping.service.olx;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OlxSearchService {


    public static void main(String[] args) {
        String searchQuery = "https://pr.olx.com.br/?q=capacete%20de%20moto";

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        try {
            HtmlPage htmlPage = client.getPage(searchQuery);

            List<HtmlElement> items = htmlPage.getByXPath("//div[@class='sc-12rk7z2-0 bjnzhV']");

            if (!items.isEmpty()) {
                for (HtmlElement item : items) {


                    HtmlAnchor linkItem = (HtmlAnchor) item.getFirstChild();
                    String title = linkItem.getAttribute("title");


                    HtmlSpan spanPrice = (HtmlSpan) item.getFirstByXPath(".//span[@class='m7nrfa-0 eJCbzj sc-fzsDOv kHeyHD']");


                    System.out.println(title +" Valor:"+ spanPrice.getTextContent() + ". link: " +  linkItem.getHrefAttribute());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
