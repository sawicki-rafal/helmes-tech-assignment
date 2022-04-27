package com.sawraf.helmesassignmentserver.sector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class SectorDTO {

    private Long id;

    private String name;

    private Set<SectorDTO> childrenSectors;

}
