package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectorMapper {

    public SectorDTO mapToDto(Sector sector) {
        final SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(sector.getId());
        sectorDTO.setName(sector.getName());
        return sectorDTO;
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
