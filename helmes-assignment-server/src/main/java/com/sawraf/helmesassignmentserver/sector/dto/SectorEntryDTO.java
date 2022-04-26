package com.sawraf.helmesassignmentserver.sector.dto;

import java.util.List;

public class SectorEntryDTO {

    private String name;

    private List<String> sectors;

    private boolean isAgreedToTerms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSectors() {
        return sectors;
    }

    public void setSectors(List<String> sectors) {
        this.sectors = sectors;
    }

    public boolean isAgreedToTerms() {
        return isAgreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        isAgreedToTerms = agreedToTerms;
    }
}
