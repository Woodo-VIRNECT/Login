package com.project.login.api.v1.controller;

import java.io.UnsupportedEncodingException;

import com.project.login.api.jwt.JwtTokenProvider;
import com.project.login.api.lib.Helper;
import com.project.login.api.v1.dto.Response;
import com.project.login.api.v1.dto.request.UserRequestDto;
import com.project.login.api.v1.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UsersController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UsersService usersService;
    private final Response response;

    private final JavaMailSender javaMailSender;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated UserRequestDto.SignUp signUp, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated UserRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Validated UserRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Validated UserRequestDto.Logout logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.logout(logout);
    }

    @GetMapping("/authority/addAdminRole")
    public ResponseEntity<?> authority(HttpServletRequest request) {
        log.info("ADD ROLE_ADMIN");

        return usersService.authority();
    }

    @GetMapping("/userTest")
    public ResponseEntity<?> userTest() {
        log.info("ROLE_USER TEST");
        return response.success();
    }

    @GetMapping("/adminTest")
    public ResponseEntity<?> adminTest() {
        log.info("ROLE_ADMIN TEST");
        return response.success();
    }


    @PostMapping("/sendMail")
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
}
