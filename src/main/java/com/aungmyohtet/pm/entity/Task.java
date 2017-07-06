package com.aungmyohtet.pm.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

@Entity
@Table
public class Task extends ScheduledEntity {

    private String title;

    private int no;

    @ManyToOne
    private Project project;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_assignee", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> assignees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_status", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "status_id", referencedColumnName = "id"))
    private Set<Status> status;

    private int weight;

    private int score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<User> assignees) {
        this.assignees = assignees;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }
}
