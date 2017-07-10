package com.aungmyohtet.pm.service;

import java.util.List;
import java.util.Set;

import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;

public interface StatusService {

    void save(Status status);

    Status findById(int id);

    List<Status> findAll();
    
    Set<Status> find(Task task);
}
