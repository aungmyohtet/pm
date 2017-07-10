package com.aungmyohtet.pm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Board extends BaseEntity {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    @Column(name = "startShownDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date startShownDate;

    @Column(name = "lastShownDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date lastShownDate;

    private int no;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartShownDate() {
        return startShownDate;
    }

    public void setStartShownDate(Date startShownDate) {
        this.startShownDate = startShownDate;
    }

    public Date getLastShownDate() {
        return lastShownDate;
    }

    public void setLastShownDate(Date lastShownDate) {
        this.lastShownDate = lastShownDate;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Board() {
        super();
    }

    public Board(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    public Board(String title, String content, int no, Organization organization) {
        super();
        this.title = title;
        this.content = content;
        this.no = no;
        this.organization = organization;
    }

    public Board(String title, String content, Date createdDate, Date startShownDate, Date lastShownDate) {
        super();
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.startShownDate = startShownDate;
        this.lastShownDate = lastShownDate;
    }

    public Board(String title, String content, Date createdDate, Date startShownDate, Date lastShownDate, int no, Organization organization) {
        super();
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.startShownDate = startShownDate;
        this.lastShownDate = lastShownDate;
        this.no = no;
        this.organization = organization;
    }
}
