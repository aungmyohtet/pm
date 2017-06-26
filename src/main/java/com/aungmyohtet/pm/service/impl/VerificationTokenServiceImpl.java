package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.entity.VerificationToken;
import com.aungmyohtet.pm.repository.VerificationTokenRepository;
import com.aungmyohtet.pm.service.VerificationTokenService;

@Service("verificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public boolean isValidToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        return verificationToken != null? true : false;
    }
}
