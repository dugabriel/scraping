package com.scraping.service;

import com.scraping.configuration.MailConfig;
import com.scraping.domain.User;
import com.scraping.exception.FreemarkerTemplateErrorException;
import com.scraping.service.vo.ScrapingResultVO;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private Configuration freemarkerConfiguration;

    public void sendMail(List<ScrapingResultVO> scrapingList, User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Encontramos alguns itens que você está procurando");
            mimeMessageHelper.setFrom(mailConfig.getFrom());
            mimeMessageHelper.setTo(user.getEmail());

            Map<String, Object> model = new HashMap<>();
            model.put("scrapingList", scrapingList);
            model.put("name", user.getName());

            mimeMessageHelper.setText(getFreemarkerTemplate(model), true);

            log.info("sending mail to {} with {} items", user.getEmail(), scrapingList.size());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getFreemarkerTemplate(Map<String, Object> model) {
        StringWriter stringWriter = new StringWriter();
        try {
            freemarkerConfiguration.getTemplate("emailOlxList.flth").process(model, stringWriter);
        } catch (TemplateException | RuntimeException | IOException e) {
            throw new FreemarkerTemplateErrorException(e.getMessage(), e.getCause());
        }
        return stringWriter.getBuffer().toString();
    }
}
