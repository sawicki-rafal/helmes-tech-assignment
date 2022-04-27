package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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
            return;
        }
        Set<SectorDTO> childrenSectors = sector.getChildrenSectors().stream()
                .map(this::mapToDto)
                .collect(Collectors.toUnmodifiableSet());
        sectorDTO.setChildrenSectors(childrenSectors);
    }

    public List<SectorDTO> mapToDto(Collection<Sector> sectors) {
        return sectors.stream()
                .map(this::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public Sector map(SectorDTO sectorDTO) {
        final Sector sector = new Sector();
        sector.setId(sectorDTO.getId());
        sector.setName(sectorDTO.getName());
        return sector;
    }

    public List<Sector> map(Collection<SectorDTO> sectorDTOs) {
        return sectorDTOs.stream()
                .map(this::map)
                .collect(Collectors.toUnmodifiableList());
    }
}
