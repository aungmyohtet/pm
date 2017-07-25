package com.aungmyohtet.pm.service.update.impl;

import java.io.Serializable;

import org.springframework.security.core.Authentication;

import com.aungmyohtet.pm.service.update.CustomPermissionEvaluatorService;

public class CustomPermissionEvaluatorServiceImplUpdate implements CustomPermissionEvaluatorService {

    @Override
    public boolean hasPermission(Authentication arg0, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
        // TODO Auto-generated method stub
        return false;
    }

}
