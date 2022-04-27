package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SectorEntryDTO {

    private Long id;

    private String name;

    private List<SectorDTO> sectors;

    private boolean isAgreedToTerms;
}
