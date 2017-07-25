package com.aungmyohtet.pm.service;

import java.util.Set;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;

public interface ProjectService2 {

    Set<Task> findTasksByProject(Project project);

    Set<User> findMembersByProject(Project project);

    void addTaskToProject(Task task, Project project);

    void addMemberToProject(User user, Project project);

    void removeTaskFromProject(Task task, Project project);

    void removeMemberFromProject(User user, Project project);

}
