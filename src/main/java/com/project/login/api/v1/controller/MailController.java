package com.project.login.api.v1.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.login.api.jwt.JwtTokenProvider;
import com.project.login.api.lib.Helper;
import com.project.login.api.v1.dto.Response;
import com.project.login.api.v1.dto.request.MailDto;
import com.project.login.api.v1.dto.request.UserRequestDto;
import com.project.login.api.v1.service.MailService;
import com.project.login.api.v1.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
@RestController
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public String send(@RequestBody MailDto mailDto) throws MessagingException, UnsupportedEncodingException {
        log.info("MailDto mailDto : {}", mailDto);
        mailService.sendMail2(mailDto);

        return "하이";
    }
}
