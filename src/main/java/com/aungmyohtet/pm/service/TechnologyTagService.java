package com.aungmyohtet.pm.service;

import java.util.List;
import java.util.Set;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;

public interface TechnologyTagService {
    void save(TechnologyTag technologyTag);

    TechnologyTag findById(int id);

    List<TechnologyTag> findAll();
    
    Set<TechnologyTag> find(Task task);
}
