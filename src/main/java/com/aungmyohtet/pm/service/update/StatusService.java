package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Status;

public interface StatusService {

    // wrap normal repository methods
    void save(Status status);

    void delete(Status status);

    List<Status> findAll();

    Status findByName(String name);

    List<Status> filterByNameContainingText(String text);
}
