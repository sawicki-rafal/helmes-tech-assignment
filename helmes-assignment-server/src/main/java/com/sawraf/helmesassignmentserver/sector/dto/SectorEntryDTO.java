package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class SectorEntryDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    private List<SectorDTO> sectors;

    @NotNull
    private boolean isAgreedToTerms;
}
