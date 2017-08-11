package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.dto.TaskDto;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;

public interface TaskService {

    void assign(Task task, User user);

    void unassign(Task task, User user);

    List<TaskNote> getTaskNotes(Task task);

    // wrap normal repository methods
    void save(Task task);

    List<Task> findAll();

    void delete(Task task);

    Task findById(int id);

    List<Task> findByProject(Project project);

    Task findByTitleAndProject(String title, Project project);

    Task findByNoAndProject(int no, Project project);

    TaskDto convertToDto(Task task);

    void addTech(Task task, TechnologyTag tech);
}
