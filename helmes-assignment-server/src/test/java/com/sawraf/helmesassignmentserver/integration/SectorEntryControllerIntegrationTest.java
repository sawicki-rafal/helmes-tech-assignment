package com.sawraf.helmesassignmentserver.integration;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SectorEntryControllerIntegrationTest {

    @Autowired
    private SectorEntryRepository sectorEntryRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private static final long TEST_DB_SECTOR_ENTRIES_COUNT = 2L;

    private static final long SECTOR_ENTRY_1_ID = 1L;

    private static final long SECTOR_ENTRY_2_ID = 2L;

    private Sector sector_1;

    @BeforeEach
    void setUp() {
        sectorEntryRepository.deleteAll();
        sectorRepository.deleteAll();

        SectorEntry sectorEntry_1 = new SectorEntry();
        sectorEntry_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntry_1.setName("TEST_SECTOR_1");
        sectorEntry_1.setVersion(0L);

        SectorEntry sectorEntry_2 = new SectorEntry();
        sectorEntry_2.setId(SECTOR_ENTRY_2_ID);
        sectorEntry_2.setName("TEST_SECTOR_2");
        sectorEntry_2.setVersion(0L);

        sectorEntryRepository.save(sectorEntry_1);
        sectorEntryRepository.save(sectorEntry_2);

        sector_1 = new Sector();
        sector_1.setName("TEST_SECTOR_1");
        sector_1.setVersion(0L);

        sectorRepository.save(sector_1);
    }

    @Test
    void testGetAllSectorEntries() {
        ResponseEntity<SectorEntryDTO[]> sectorDTOsResponse = testRestTemplate
                .getForEntity("http://localhost:" + port + "/api/sector-entries", SectorEntryDTO[].class);
        assertThat(sectorDTOsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        final List<SectorEntryDTO> sectorEntryDTOs = Arrays.asList(Objects.requireNonNull(sectorDTOsResponse.getBody()));

        assertThat(sectorEntryDTOs.size()).isEqualTo(TEST_DB_SECTOR_ENTRIES_COUNT);
    }

    @Test
    void testAddSectorEntry() {
        final Sector sector = sectorRepository.findAll().stream().findAny().get();

        final SectorEntrySaveOrUpdateDTO saveDTO = new SectorEntrySaveOrUpdateDTO();
        saveDTO.setId(null);
        saveDTO.setName("CHANGED_NAME" + Math.random());
        saveDTO.setSectors(Collections.singletonList(sector.getId()));
        saveDTO.setAgreedToTerms(true);

        ResponseEntity<SectorEntryDTO> sectorDTOsResponse = testRestTemplate
                .postForEntity("http://localhost:" + port + "/api/sector-entries", saveDTO, SectorEntryDTO.class);
        assertThat(sectorDTOsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        final SectorEntryDTO sectorEntryDTO = Objects.requireNonNull(sectorDTOsResponse.getBody());

        assertThat(sectorEntryDTO.getName()).isEqualTo(saveDTO.getName());
        assertThat(sectorEntryDTO.getSectors().size()).isEqualTo(1);

        final SectorEntry dbSectorEntry = sectorEntryRepository.findById(sectorEntryDTO.getId()).get();

        assertThat(dbSectorEntry.getId()).isEqualTo(sectorEntryDTO.getId());
        assertThat(dbSectorEntry.getName()).isEqualTo(saveDTO.getName());
    }

    @Test
    void testUpdateSectorEntry() {
        final Sector sector = sectorRepository.findAll().stream().findAny().get();

        final SectorEntrySaveOrUpdateDTO updateDTO = new SectorEntrySaveOrUpdateDTO();
        updateDTO.setId(SECTOR_ENTRY_1_ID);
        updateDTO.setName("CHANGED_NAME" + Math.random());
        updateDTO.setSectors(Collections.singletonList(sector.getId()));
        updateDTO.setAgreedToTerms(true);

        testRestTemplate.put("http://localhost:" + port + "/api/sector-entries", updateDTO, SectorEntryDTO.class);

        final SectorEntry dbSectorEntry = sectorEntryRepository.findById(updateDTO.getId()).get();

        assertThat(dbSectorEntry.getId()).isEqualTo(updateDTO.getId());
        assertThat(dbSectorEntry.getName()).isEqualTo(updateDTO.getName());
    }
}
