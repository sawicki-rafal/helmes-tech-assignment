package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class SectorDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private Set<SectorDTO> childrenSectors;

}
