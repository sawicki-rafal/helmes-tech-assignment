package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectorEntryMapper {

    public SectorEntryDTO mapToDto(SectorEntry entry) {
        SectorEntryDTO entryDTO = new SectorEntryDTO();
        entryDTO.setName(entry.getName());
        entryDTO.setSectors(entry.getSectors().stream()
                .map(Sector::getName)
                .collect(Collectors.toList()));
        entryDTO.setAgreedToTerms(entry.isAgreedToTerms());
        return entryDTO;
    }

    public List<SectorEntryDTO> mapToDto(Collection<SectorEntry> entries) {
        return entries.stream()
                .map(this::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
