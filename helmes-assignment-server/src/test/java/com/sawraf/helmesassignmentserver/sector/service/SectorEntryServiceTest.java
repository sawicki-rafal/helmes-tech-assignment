package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorEntryMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorEntryServiceTest {

    @Mock
    private SectorEntryRepository sectorEntryRepository;

    @Mock
    private SectorEntryMapper sectorEntryMapper;

    @InjectMocks
    private SectorEntryService sectorEntryService;

    private List<SectorEntryDTO> sectorEntries;

    private SectorEntry sectorEntry_1;

    private SectorEntry sectorEntry_2;

    private SectorDTO sectorDTO_1;

    private SectorDTO sectorDTO_2;

    private Sector sector_1;

    private SectorEntryDTO sectorEntryDTO_1;

    private SectorEntryDTO sectorEntryDTO_2;

    private SectorEntrySaveOrUpdateDTO sectorEntrySaveOrUpdateDTO_1;


    @BeforeEach
    public void setUp() {
        sectorDTO_1 = new SectorDTO();
        sectorDTO_1.setId(1L);
        sectorDTO_1.setName("TEST_SECTOR_1");

        sector_1 = new Sector();
        sector_1.setId(1L);
        sector_1.setName("TEST_SECTOR_1");

        sectorEntryDTO_1 = new SectorEntryDTO();
        sectorEntryDTO_1.setId(1L);
        sectorEntryDTO_1.setName("TEST_ANDRZEJ");
        sectorEntryDTO_1.setAgreedToTerms(true);
        sectorEntryDTO_1.setSectors(Collections.singletonList(sectorDTO_1));

        sectorEntrySaveOrUpdateDTO_1 = new SectorEntrySaveOrUpdateDTO();
        sectorEntrySaveOrUpdateDTO_1.setId(1L);
        sectorEntrySaveOrUpdateDTO_1.setName("TEST_ANDRZEJ");
        sectorEntrySaveOrUpdateDTO_1.setAgreedToTerms(true);
        sectorEntrySaveOrUpdateDTO_1.setSectors(Collections.singletonList(1L));

        sectorEntry_1 = new SectorEntry();
        sectorEntry_1.setId(1L);
        sectorEntry_1.setName("TEST_ANDRZEJ");
        sectorEntry_1.setAgreedToTerms(true);
        sectorEntry_1.setSectors(Collections.singleton(sector_1));

        sectorEntryDTO_2 = new SectorEntryDTO();
        sectorEntryDTO_2.setId(2L);
        sectorEntryDTO_2.setName("TEST_TOMEK");
        sectorEntryDTO_2.setAgreedToTerms(true);
        sectorEntryDTO_2.setSectors(Collections.singletonList(sectorDTO_1));

        sectorEntry_2 = new SectorEntry();
        sectorEntry_2.setId(2L);
        sectorEntry_2.setName("TEST_TOMEK");
        sectorEntry_2.setAgreedToTerms(true);
        sectorEntry_2.setSectors(Collections.singleton(sector_1));

        sectorEntries = new ArrayList<>();
        sectorEntries.add(sectorEntryDTO_1);
        sectorEntries.add(sectorEntryDTO_2);
    }

    @Test
    void getAllShouldReturnAllSectorEntries() {
        List<SectorEntry> repositorySectorEntries = new LinkedList<>();

        when(sectorEntryRepository.findAll()).thenReturn(repositorySectorEntries);
        when(sectorEntryMapper.mapToDto(repositorySectorEntries)).thenReturn(sectorEntries);

        final List<SectorEntryDTO> returnedSectorEntries = sectorEntryService.getAllSectorEntries();
        assertThat(returnedSectorEntries).containsExactlyElementsOf(sectorEntries);
    }

    @Test
    void saveOrUpdateSectorEntryShouldReturnAddedEntry() {

        when(sectorEntryMapper.map(sectorEntrySaveOrUpdateDTO_1)).thenReturn(sectorEntry_1);
        when(sectorEntryRepository.save(sectorEntry_1)).thenReturn(sectorEntry_1);
        when(sectorEntryMapper.mapToDto(sectorEntry_1)).thenReturn(sectorEntryDTO_1);

        final SectorEntryDTO sectorEntryDTO = sectorEntryService.saveOrUpdateSectorEntry(sectorEntrySaveOrUpdateDTO_1);

        assertThat(sectorEntryDTO).isEqualTo(sectorEntryDTO_1);

    }


}