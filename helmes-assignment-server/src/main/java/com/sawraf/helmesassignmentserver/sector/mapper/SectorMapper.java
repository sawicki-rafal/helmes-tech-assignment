package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SectorMapper {

    private final SectorRepository sectorRepository;

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
                .collect(Collectors.toSet());
        sectorDTO.setChildrenSectors(childrenSectors);
    }

    public List<SectorDTO> mapToDto(Collection<Sector> sectors) {
        return sectors.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Sector map(SectorDTO sectorDTO) {
        final Sector sector = new Sector();
        sector.setId(sectorDTO.getId());
        sector.setName(sectorDTO.getName());
        return sector;
    }

    public Set<Sector> map(Collection<SectorDTO> sectorDTOs) {
        return sectorDTOs.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    public Set<Sector> map(List<Long> sectorIds) {
        return sectorIds.stream()
                //TODO throw Exception
                .map(id -> sectorRepository.findById(id).get())
                .collect(Collectors.toSet());
    }
}
