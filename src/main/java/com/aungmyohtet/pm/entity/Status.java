package com.aungmyohtet.pm.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Status extends UniqueNamedEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_status", joinColumns = @JoinColumn(name = "status_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Task> task;

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
}
