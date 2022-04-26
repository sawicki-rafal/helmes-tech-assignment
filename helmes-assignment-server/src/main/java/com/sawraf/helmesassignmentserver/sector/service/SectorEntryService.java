package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.sector.mapper.SectorEntryMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SectorEntryService {

    private final SectorEntryRepository sectorEntryRepository;

    private final SectorEntryMapper sectorEntryMapper;

    public SectorEntryService(SectorEntryRepository sectorEntryRepository, SectorEntryMapper sectorEntryMapper) {
        this.sectorEntryRepository = sectorEntryRepository;
        this.sectorEntryMapper = sectorEntryMapper;
    }

}
