package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorServiceTest {

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private SectorMapper sectorMapper;

    @InjectMocks
    private SectorService sectorService;

    private List<SectorDTO> sectors;

    @BeforeEach
    public void setUp() {
        SectorDTO sector_1 = new SectorDTO();
        sector_1.setId(1L);
        sector_1.setName("TEST_SECTOR_1");

        SectorDTO sector_2 = new SectorDTO();
        sector_2.setId(2L);
        sector_2.setName("TEST_SECTOR_2");

        sectors = new ArrayList<>();
        sectors.add(sector_1);
        sectors.add(sector_2);
    }

    @Test
    void getAllShouldReturnAllSectors() {
        List<Sector> repositorySectors = new LinkedList<>();

        when(sectorRepository.findAllByParentSectorNull()).thenReturn(repositorySectors);
        when(sectorMapper.mapToDto(repositorySectors)).thenReturn(sectors);

        final List<SectorDTO> returnedSectors = sectorService.getAllSectors();
        assertThat(returnedSectors).containsExactlyElementsOf(sectors);
    }

}