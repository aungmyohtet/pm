package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.repository.update.TechnologyTagRepository;
import com.aungmyohtet.pm.service.update.TechnologyTagService;

@Service
public class TechnologyTagServiceImplUpdate implements TechnologyTagService {

    @Autowired
    private TechnologyTagRepository techTagRepo;

    @Override
    public void save(TechnologyTag technologyTag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(TechnologyTag technologyTag) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public List<TechnologyTag> findAll() {
        return this.techTagRepo.findAll();
    }

    @Override
    public List<TechnologyTag> findByTask(Task task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public TechnologyTag findByName(String name) {
        return this.techTagRepo.findByName(name);
    }

    @Override
    public TechnologyTag findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TechnologyTag> filterByNameContainingText(String text) {
        // TODO Auto-generated method stub
        return null;
    }

}
