package com.aungmyohtet.pm.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "technology_tag")
public class TechnologyTag extends UniqueNamedEntity{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_technologytag", joinColumns = @JoinColumn(name = "technologytag_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Task> task;

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
}