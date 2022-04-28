package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorEntryMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SectorEntryService {

    private final SectorEntryRepository sectorEntryRepository;

    private final SectorEntryMapper sectorEntryMapper;

    public SectorEntryService(SectorEntryRepository sectorEntryRepository, SectorEntryMapper sectorEntryMapper) {
        this.sectorEntryRepository = sectorEntryRepository;
        this.sectorEntryMapper = sectorEntryMapper;
    }

    public List<SectorEntryDTO> getAllSectorEntries() {
        final List<SectorEntry> all = sectorEntryRepository.findAll();
        return sectorEntryMapper.mapToDto(all);
    }

    public SectorEntryDTO saveOrUpdateSectorEntry(SectorEntrySaveOrUpdateDTO sectorEntryDTO) {
        final SectorEntry sectorEntry = sectorEntryRepository.save(sectorEntryMapper.map(sectorEntryDTO));
        return sectorEntryMapper.mapToDto(sectorEntry);
    }
}
