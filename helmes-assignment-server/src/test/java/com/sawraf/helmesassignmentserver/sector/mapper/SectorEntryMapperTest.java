package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.exception.ApplicationException;
import com.sawraf.helmesassignmentserver.exception.message.MessageCode;
import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.sawraf.helmesassignmentserver.exception.message.MessageCode.ERROR_ENTITY_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorEntryMapperTest {

    public static final long SECTOR_ENTRY_1_ID = 1L;
    @Mock
    private SectorMapper sectorMapper;

    @Mock
    private SectorEntryRepository sectorEntryRepository;

    @InjectMocks
    private SectorEntryMapper sectorEntryMapper;

    private SectorEntry sectorEntry_1;

    private SectorEntry sectorEntry_2;

    private Set<Sector> sectors;

    private List<SectorDTO> sectorDTOs;

    @BeforeEach
    public void setup() {
        sectors = new HashSet<>();
        sectorDTOs = new LinkedList<>();

        sectorEntry_1 = new SectorEntry();
        sectorEntry_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntry_1.setName("SECTOR_ENTRY_NAME");
        sectorEntry_1.setSectors(sectors);
        sectorEntry_1.setAgreedToTerms(true);

        sectorEntry_2 = new SectorEntry();
        sectorEntry_2.setId(2L);
        sectorEntry_2.setName("SECTOR_ENTRY_NAME");
        sectorEntry_2.setSectors(sectors);
        sectorEntry_2.setAgreedToTerms(true);

    }

    @Test
    void shouldMapSingle() {
        when(sectorMapper.mapToDto(sectors)).thenReturn(sectorDTOs);
        final SectorEntryDTO sectorEntryDTO = sectorEntryMapper.mapToDto(sectorEntry_1);
        assertThat(sectorEntryDTO.getId()).isEqualTo(sectorEntry_1.getId());
        assertThat(sectorEntryDTO.getName()).isEqualTo(sectorEntry_1.getName());
        assertThat(sectorEntryDTO.getSectors()).isEqualTo(sectorDTOs);
        assertThat(sectorEntryDTO.isAgreedToTerms()).isEqualTo(sectorEntry_1.isAgreedToTerms());
    }

    @Test
    void shouldMapCollection() {
        when(sectorMapper.mapToDto(sectors)).thenReturn(sectorDTOs);

        final List<SectorEntry> sectorEntries = List.of(sectorEntry_1, sectorEntry_2);

        final List<SectorEntryDTO> sectorEntryDTOS = sectorEntryMapper.mapToDto(sectorEntries);

        assertThat(sectorEntryDTOS.size()).isEqualTo(sectorEntries.size());

        sectorEntries.forEach(sectorEntry -> {
            SectorEntryDTO sectorEntryDTO = sectorEntryDTOS.stream()
                    .filter(seDTO -> seDTO.getId().equals(sectorEntry.getId()))
                    .findFirst().get();

            assertThat(sectorEntryDTO.getId()).isEqualTo(sectorEntry.getId());
            assertThat(sectorEntryDTO.getName()).isEqualTo(sectorEntry.getName());
            assertThat(sectorEntryDTO.getSectors()).isEqualTo(sectorDTOs);
            assertThat(sectorEntryDTO.isAgreedToTerms()).isEqualTo(sectorEntry.isAgreedToTerms());
        });
    }

    @Test
    void shouldMapFromUpdateDTO() {
        final long sectorId = 1L;
        final List<Long> sectorIds = List.of(sectorId);
        Sector sector = new Sector();
        sector.setId(sectorId);
        final Set<Sector> sectors = Set.of(sector);

        when(sectorMapper.map(sectorIds)).thenReturn(sectors);

        final SectorEntrySaveOrUpdateDTO updateDTO = new SectorEntrySaveOrUpdateDTO();
        updateDTO.setId(SECTOR_ENTRY_1_ID);
        updateDTO.setName("CHANGED_NAME");
        updateDTO.setSectors(sectorIds);
        updateDTO.setAgreedToTerms(false);

        SectorEntry toBeUpdatedSectorEntry = new SectorEntry();
        toBeUpdatedSectorEntry = sectorEntryMapper.map(updateDTO, toBeUpdatedSectorEntry);

        assertThat(toBeUpdatedSectorEntry.getId()).isEqualTo(updateDTO.getId());
        assertThat(toBeUpdatedSectorEntry.getName()).isEqualTo(updateDTO.getName());
        assertThat(toBeUpdatedSectorEntry.getSectors()).isEqualTo(sectors);
        assertThat(toBeUpdatedSectorEntry.isAgreedToTerms()).isEqualTo(updateDTO.isAgreedToTerms());

    }

    @Test
    void shouldMapFromSaveDTO() {
        final long sectorId = 1L;
        final List<Long> sectorIds = List.of(sectorId);
        Sector sector = new Sector();
        sector.setId(sectorId);
        final Set<Sector> sectors = Set.of(sector);

        when(sectorMapper.map(sectorIds)).thenReturn(sectors);

        final SectorEntrySaveOrUpdateDTO saveDTO = new SectorEntrySaveOrUpdateDTO();
        saveDTO.setId(null);
        saveDTO.setName("CHANGED_NAME");
        saveDTO.setSectors(sectorIds);
        saveDTO.setAgreedToTerms(false);

        SectorEntry toBeSavedSectorEntry = new SectorEntry();
        toBeSavedSectorEntry = sectorEntryMapper.map(saveDTO, toBeSavedSectorEntry);

        assertThat(toBeSavedSectorEntry.getId()).isEqualTo(saveDTO.getId());
        assertThat(toBeSavedSectorEntry.getName()).isEqualTo(saveDTO.getName());
        assertThat(toBeSavedSectorEntry.getSectors()).isEqualTo(sectors);
        assertThat(toBeSavedSectorEntry.isAgreedToTerms()).isEqualTo(saveDTO.isAgreedToTerms());

    }

}