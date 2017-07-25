package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Status;

public interface StatusRepository {

    void save(Status status);

    void delete(Status status);

    List<Status> findAll();

    Status findByName(String name);

    List<Status> filterByNameContainingText(String text);
}
