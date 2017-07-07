package com.aungmyohtet.pm.repository;

import java.util.List;
import java.util.Set;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;

public interface TechnologyTagRepository {

    void save(TechnologyTag technologyTag);

    TechnologyTag findById(int id);

    List<TechnologyTag> findAll();
    
    Set<TechnologyTag> find(Task task);
}
