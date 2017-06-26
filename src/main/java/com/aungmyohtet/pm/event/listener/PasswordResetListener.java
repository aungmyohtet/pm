package com.aungmyohtet.pm.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.aungmyohtet.pm.event.PasswordResetEvent;
import com.aungmyohtet.pm.service.SimpleMailService;

@Component
public class PasswordResetListener implements ApplicationListener<PasswordResetEvent> {

    @Autowired
    private SimpleMailService mailService;

    @Override
    public void onApplicationEvent(PasswordResetEvent event) {
        this.sendPasswordResetEmail(event);

    }

    private void sendPasswordResetEmail(PasswordResetEvent event) {
        String email = event.getEmail();
        String token = event.getToken();
        String subject = "Password Reset Email";
        String confirmationUrl = event.getAppUrl() + "/account/reset-password?token=" + token;
        String content = "Please confirm your email by clicking the link " + "http://localhost:8080" + confirmationUrl;

        mailService.send(email, subject, content);
    }

}
