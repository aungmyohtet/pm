package com.aungmyohtet.pm.dto;

public class TaskDto {

    private String title;

    private String scheduledStartDate;

    private String scheduledFinishedDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(String scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public String getScheduledFinishedDate() {
        return scheduledFinishedDate;
    }

    public void setScheduledFinishedDate(String scheduledFinishedDate) {
        this.scheduledFinishedDate = scheduledFinishedDate;
    }
}
