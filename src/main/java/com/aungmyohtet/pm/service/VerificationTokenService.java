package com.aungmyohtet.pm.service;

public interface VerificationTokenService {

    boolean isValidToken(String token);
}
