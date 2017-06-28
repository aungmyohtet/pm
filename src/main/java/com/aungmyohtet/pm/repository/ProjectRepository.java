package com.aungmyohtet.pm.repository;

import com.aungmyohtet.pm.entity.Project;

public interface ProjectRepository {

    void save(Project project);

    Project findById(int id);
}
