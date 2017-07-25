package com.aungmyohtet.pm.service.impl;

import java.util.Set;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.ProjectService2;

public class ProjectServiceImpl2 implements ProjectService2 {

    @Override
    public Set<Task> findTasksByProject(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<User> findMembersByProject(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTaskToProject(Task task, Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addMemberToProject(User user, Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeTaskFromProject(Task task, Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeMemberFromProject(User user, Project project) {
        // TODO Auto-generated method stub

    }

}
