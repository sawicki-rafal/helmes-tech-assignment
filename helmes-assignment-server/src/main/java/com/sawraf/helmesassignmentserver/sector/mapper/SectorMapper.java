package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SectorMapper {

    public SectorDTO mapToDto(Sector sector) {
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(sector.getId());
        sectorDTO.setName(sector.getName());
        mapChildrenSectors(sector, sectorDTO);
        return sectorDTO;
    }

    private void mapChildrenSectors(Sector sector, SectorDTO sectorDTO) {
        if (sector.getChildrenSectors().isEmpty()) {
            sectorDTO.setChildrenSectors(new HashSet<>());
            return;
        }
        Set<SectorDTO> childrenSectors = sector.getChildrenSectors().stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
        sectorDTO.setChildrenSectors(childrenSectors);
    }

    public List<SectorDTO> mapToDto(Collection<Sector> sectors) {
        return sectors.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
