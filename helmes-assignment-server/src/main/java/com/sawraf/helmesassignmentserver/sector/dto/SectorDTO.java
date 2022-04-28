package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
public class SectorDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    private Set<SectorDTO> childrenSectors;

}
