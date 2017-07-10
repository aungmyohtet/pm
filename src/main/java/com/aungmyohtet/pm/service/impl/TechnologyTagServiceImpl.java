package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.repository.TechnologyTagRepository;
import com.aungmyohtet.pm.service.TechnologyTagService;

@Service
public class TechnologyTagServiceImpl implements TechnologyTagService {

    @Autowired
    private TechnologyTagRepository technologyTagRepository;

    @Override
    @Transactional
    public void save(TechnologyTag technologyTag) {
        technologyTagRepository.save(technologyTag);
    }

    @Override
    @Transactional
    public TechnologyTag findById(int id) {
        return technologyTagRepository.findById(id);
    }

    @Override
    @Transactional
    public List<TechnologyTag> findAll() {
        return technologyTagRepository.findAll();
    }

    @Override
    @Transactional
    public Set<TechnologyTag> find(Task task) {
        return technologyTagRepository.find(task);
    }
}
