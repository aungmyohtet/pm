package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;

public interface TaskRepository {

    void save(Task task);

    List<Task> findAll();

    void delete(Task task);

    Task findById(int id);

    List<Task> findByProject(Project project);

    Task findByTitleAndProject(String title, Project project);

    Task findByNoAndProject(int no, Project project);

    int findMaxTaskNoByProject(Project project);

}
