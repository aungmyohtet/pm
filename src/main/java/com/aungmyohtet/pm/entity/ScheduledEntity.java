package com.aungmyohtet.pm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ScheduledEntity extends BaseEntity {

    @Column(name = "scheduled_startdate")
    private Date scheduledStartDate;

    @Column(name = "scheduled_enddate")
    private Date scheduledFinishedDate;

    @Column(name = "actual_startdate")
    private Date actualStartDate;

    @Column(name = "actual_enddate")
    private Date actualFinishedDate;

    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public Date getScheduledFinishedDate() {
        return scheduledFinishedDate;
    }

    public void setScheduledFinishedDate(Date scheduledFinishedDate) {
        this.scheduledFinishedDate = scheduledFinishedDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualFinishedDate() {
        return actualFinishedDate;
    }

    public void setActualFinishedDate(Date actualFinishedDate) {
        this.actualFinishedDate = actualFinishedDate;
    }

}
