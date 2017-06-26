package com.aungmyohtet.pm.service;

public interface SimpleMailService {

    void send(String recipient, String subject, String content);
}
