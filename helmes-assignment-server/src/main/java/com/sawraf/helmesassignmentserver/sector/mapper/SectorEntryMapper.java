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

    private final SectorMapper sectorMapper;

    public SectorEntryMapper(SectorMapper sectorMapper) {
        this.sectorMapper = sectorMapper;
    }

    public SectorEntryDTO mapToDto(SectorEntry entry) {
        SectorEntryDTO entryDTO = new SectorEntryDTO();
        entryDTO.setId(entry.getId());
        entryDTO.setName(entry.getName());
        entryDTO.setSectors(sectorMapper.mapToDto(entry.getSectors()));
        entryDTO.setAgreedToTerms(entry.isAgreedToTerms());
        return entryDTO;
    }

    public List<SectorEntryDTO> mapToDto(Collection<SectorEntry> entries) {
        return entries.stream()
                .map(this::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public SectorEntry map(SectorEntryDTO entryDTO) {
        SectorEntry entry = new SectorEntry();
        entry.setId(entryDTO.getId());
        entry.setName(entryDTO.getName());
        entry.setSectors(sectorMapper.map(entryDTO.getSectors()));
        entry.setAgreedToTerms(entryDTO.isAgreedToTerms());
        return entry;
    }
}
