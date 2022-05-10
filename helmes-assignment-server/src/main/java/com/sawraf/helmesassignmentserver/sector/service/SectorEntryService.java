package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.exception.ApplicationException;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorEntryMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sawraf.helmesassignmentserver.exception.message.MessageCode.ERROR_ENTITY_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class SectorEntryService {

    private final SectorEntryRepository sectorEntryRepository;

    private final SectorRepository sectorRepository;

    private final SectorEntryMapper sectorEntryMapper;

    public List<SectorEntryDTO> getAllSectorEntries() {
        final List<SectorEntry> all = sectorEntryRepository.findAll();
        return sectorEntryMapper.mapToDto(all);
    }

    public SectorEntryDTO saveOrUpdateSectorEntry(SectorEntrySaveOrUpdateDTO sectorEntryDTO) {
        SectorEntry sectorEntry;
        if (isNew(sectorEntryDTO)) {
            sectorEntry = new SectorEntry();
        } else {
            sectorEntry = sectorEntryRepository.findById(sectorEntryDTO.getId())
                    .orElseThrow(() ->
                            new ApplicationException(ERROR_ENTITY_NOT_FOUND, SectorEntry.class, sectorEntryDTO.getId()));
        }
        sectorEntry = sectorEntryMapper.map(sectorEntryDTO, sectorEntry);

        Set<Sector> sectors = map(sectorEntryDTO.getSectors());

        sectorEntry.setSectors(sectors);

        sectorEntry = sectorEntryRepository.save(sectorEntry);
        return sectorEntryMapper.mapToDto(sectorEntry);
    }

    public Set<Sector> map(List<Long> sectorIds) {
        return sectorIds.stream()
                .map(id -> sectorRepository.findById(id)
                        .orElseThrow(() ->
                                new ApplicationException(ERROR_ENTITY_NOT_FOUND, Sector.class.getName(), id)))
                .collect(Collectors.toSet());
    }

    private boolean isNew(SectorEntrySaveOrUpdateDTO entryDTO) {
        return entryDTO.getId() == null;
    }
}
