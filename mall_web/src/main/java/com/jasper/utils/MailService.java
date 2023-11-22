package com.jasper.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author com
 * 6/22/2023  6:14 PM
 */
@Component
@RequiredArgsConstructor
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSenderImpl  mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private void checkMail(String to,String subject,String content){
        if(StrUtil.isEmpty(to)){
            throw new RuntimeException("to address is empty");
        }
        if(StrUtil.isEmpty(subject)){
            throw new RuntimeException("subject is empty");
        }
        if(StrUtil.isEmpty(content)){
            throw new RuntimeException("content is empty");
        }
    }

    public void sendMail(String to,String subject,String content){
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.setSentDate(new Date());
            mailSender.send(mimeMessageHelper.getMimeMessage());
            logger.info("send mail success"+sender+"->"+to);
        } catch (MessagingException e) {
            logger.warn("send mail fail"+sender+"->"+to);
        }
    }
}
