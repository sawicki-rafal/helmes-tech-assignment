package com.sawraf.helmestechassignment.sector.entity;

import com.sawraf.helmestechassignment.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class SectorEntry extends AbstractEntity {

    @Id
    private Long id;

    private String name;

    //ManyToMany
    @OneToMany
    private List<Sector> sectors;

    private boolean agreedToTerms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public boolean isAgreedToTerms() {
        return agreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        this.agreedToTerms = agreedToTerms;
    }
}
