package com.sawraf.helmestechassignment.sector.entity;

import com.sawraf.helmestechassignment.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Sector extends AbstractEntity {

    private String name;

    private Integer value;

    private Integer parentValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getParentValue() {
        return parentValue;
    }

    public void setParentValue(Integer parentValue) {
        this.parentValue = parentValue;
    }
}
