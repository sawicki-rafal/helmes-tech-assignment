package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SectorEntrySaveOrUpdateDTO {
    private Long id;

    private String name;

    private List<Long> sectors;

    private boolean isAgreedToTerms;
}
