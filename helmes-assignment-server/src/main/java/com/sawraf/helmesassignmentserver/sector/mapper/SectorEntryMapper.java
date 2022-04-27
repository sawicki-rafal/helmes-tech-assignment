package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class SectorEntryMapper {

    private final SectorMapper sectorMapper;

    private final SectorEntryRepository sectorEntryRepository;

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
                .collect(Collectors.toList());
    }

    public SectorEntry map(SectorEntrySaveOrUpdateDTO entryDTO) {
        SectorEntry entry = acquireEntity(entryDTO);
        entry.setName(entryDTO.getName());
        entry.setSectors(sectorMapper.map(entryDTO.getSectors()));
        entry.setAgreedToTerms(entryDTO.isAgreedToTerms());
        return entry;
    }

    private SectorEntry acquireEntity(SectorEntrySaveOrUpdateDTO entryDTO) {
        SectorEntry sectorEntry;
        if (isNew(entryDTO)) {
            sectorEntry = new SectorEntry();
        } else {
            //TODO check if entry exists
            sectorEntry = sectorEntryRepository.findById(entryDTO.getId()).get();
        }
        return sectorEntry;
    }

    private boolean isNew(SectorEntrySaveOrUpdateDTO entryDTO) {
        return entryDTO.getId() == null;
    }
}
