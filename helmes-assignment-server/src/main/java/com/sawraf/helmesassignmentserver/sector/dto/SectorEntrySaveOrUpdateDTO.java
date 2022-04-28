package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class SectorEntrySaveOrUpdateDTO {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotEmpty
    private List<Long> sectors;

    @NotNull
    private boolean isAgreedToTerms;
}
