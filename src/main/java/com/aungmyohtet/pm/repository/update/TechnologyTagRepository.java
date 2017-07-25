package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;

public interface TechnologyTagRepository {

    void save(TechnologyTag technologyTag);

    void delete(TechnologyTag technologyTag);

    List<TechnologyTag> findAll();

    List<TechnologyTag> findByTask(Task task);

    TechnologyTag findByName(String name);

    TechnologyTag findById(int id);

    List<TechnologyTag> filterByNameContainingText(String text);
}
