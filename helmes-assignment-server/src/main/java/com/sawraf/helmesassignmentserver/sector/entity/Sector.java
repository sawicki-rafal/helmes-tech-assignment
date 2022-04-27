package com.sawraf.helmesassignmentserver.sector.entity;


import com.sawraf.helmesassignmentserver.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Sector extends AbstractEntity {

    private String name;

    private Integer parentValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentValue() {
        return parentValue;
    }

    public void setParentValue(Integer parentValue) {
        this.parentValue = parentValue;
    }
}
