package com.kashyap.starterproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private int id;
    private String name;
    private boolean isActive;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
