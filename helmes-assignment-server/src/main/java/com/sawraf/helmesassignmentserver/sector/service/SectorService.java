package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public List<SectorDTO> getAllSectors() {
        return sectorMapper.mapToDto(sectorRepository.findAllByParentSectorNull());
    }
}
