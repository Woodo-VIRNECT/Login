package com.project.login.api.v1.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.login.api.lib.MailUtil;
import com.project.login.api.v1.dto.request.AttachFileDto;
import com.project.login.api.v1.dto.request.MailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    public String sendMail() throws MessagingException, UnsupportedEncodingException {
        String to = "dnejdzlr2@gmail.com";
        String from = "dnejdzlr2@virnect.com";
        String subject = "test";

        StringBuilder body = new StringBuilder();
        body.append("<html> <body><h1>Hello </h1>");
        body.append("<div>테스트 입니다2. </div>");
        // body.append("<div>테스트 입니다2. <img src=\"cid:flower.jpg\"> </div> </body></html>");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

        mimeMessageHelper.setFrom(new InternetAddress(from, from, "UTF-8"));
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);

        // FileSystemResource fileSystemResource = new FileSystemResource(new File("C:/Users/HOME/Desktop/test.txt"));
        // mimeMessageHelper.addAttachment("또르르.txt", fileSystemResource);

        // FileSystemResource file = new FileSystemResource(new File("C:/Users/HOME/Desktop/flower.jpg"));
        // mimeMessageHelper.addInline("flower.jpg", file);

        javaMailSender.send(message);

        return "하이";
    }

    public String sendMail2(MailDto mailDto) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // use multipart (true)

            InternetAddress[] toAddress = MailUtil.listToArray(mailDto.getToAddressList());
            InternetAddress[] ccAddress = MailUtil.listToArray(mailDto.getCcAddressList());
            InternetAddress[] bccAddress = MailUtil.listToArray(mailDto.getBccAddressList());

            mimeMessageHelper.setSubject(MimeUtility.encodeText(mailDto.getSubject(), "UTF-8", "B")); // Base64 encoding
            mimeMessageHelper.setText(mailDto.getContent(), mailDto.isUseHtmlYn());
            mimeMessageHelper.setFrom(new InternetAddress(mailDto.getFromAddress(), mailDto.getFromAddress(), "UTF-8"));
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setCc(ccAddress);
            mimeMessageHelper.setBcc(bccAddress);

            if(!CollectionUtils.isEmpty(mailDto.getAttachFileList())) {
                for(AttachFileDto attachFileDto: mailDto.getAttachFileList()) {
                    FileSystemResource fileSystemResource = new FileSystemResource(new File(attachFileDto.getRealFileNm()));
                    mimeMessageHelper.addAttachment(MimeUtility.encodeText(attachFileDto.getAttachFileNm(), "UTF-8", "B"), fileSystemResource);
                }
            }

            javaMailSender.send(mimeMessage);

            log.info("MailServiceImpl.sendMail() :: SUCCESS");
        } catch (Exception e) {
            log.info("MailServiceImpl.sendMail() :: FAILED");
            e.printStackTrace();
        }

        return "하이";
    }


}
