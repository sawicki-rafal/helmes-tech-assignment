package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class SectorEntrySaveOrUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private List<Long> sectors;

    @NotNull
    private boolean isAgreedToTerms;
}
