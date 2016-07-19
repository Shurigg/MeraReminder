package com.yandrim.reminder.dto;

import java.util.Date;

public class RemindDTO {

    private long id;
    private String title = "";
    private String description = "";
    private Date remindDate;

    public RemindDTO(String title) {
        this.title = title;
    }

    public RemindDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title!=null)
            this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description!=null)
            this.description = description;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

}