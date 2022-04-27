package com.sawraf.helmesassignmentserver.sector.entity;


import com.sawraf.helmesassignmentserver.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
