package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.repository.StatusRepository;
import com.aungmyohtet.pm.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    @Transactional
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    @Transactional
    public Status findById(int id) {
        return statusRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Status> findAll() {

        return statusRepository.findAll();
    }

    @Override
    @Transactional
    public Set<Status> find(Task task) {

        return statusRepository.find(task);
    }
}
