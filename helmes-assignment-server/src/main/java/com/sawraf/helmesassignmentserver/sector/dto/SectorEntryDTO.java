package com.sawraf.helmesassignmentserver.sector.dto;

import java.util.List;

public class SectorEntryDTO {

    private Long id;

    private String name;

    private List<SectorDTO> sectors;

    private boolean isAgreedToTerms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectorDTO> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorDTO> sectors) {
        this.sectors = sectors;
    }

    public boolean isAgreedToTerms() {
        return isAgreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        isAgreedToTerms = agreedToTerms;
    }
}
