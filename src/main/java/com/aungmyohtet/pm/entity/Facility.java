package com.aungmyohtet.pm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Facility extends UniqueNamedEntity {

    private String description;

    private int capacity;

    private int others;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getCapacity() {
	return capacity;
    }

    public void setCapacity(int capacity) {
	this.capacity = capacity;
    }

    public int getOthers() {
	return others;
    }

    public void setOthers(int others) {
	this.others = others;
    }

}
