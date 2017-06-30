package com.aungmyohtet.pm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class TaskNote extends BaseEntity {

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commented_by", referencedColumnName = "id")
    private User commentedBy;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getCommentedby() {
        return commentedBy;
    }

    public void setCommentedBy(User user) {
        this.commentedBy = user;
    }

}
