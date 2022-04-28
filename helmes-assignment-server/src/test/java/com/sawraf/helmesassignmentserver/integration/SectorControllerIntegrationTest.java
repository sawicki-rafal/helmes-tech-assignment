package com.sawraf.helmesassignmentserver.integration;

import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
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
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SectorControllerIntegrationTest {

    public static final long TEST_DB_SECTORS_COUNT = 2L;
    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private  int port;

    @BeforeEach
    void setUp(){
        sectorRepository.deleteAll();

        Sector sector_1 = new Sector();
        sector_1.setId(1L);
        sector_1.setName("TEST_SECTOR_1");
        sector_1.setVersion(0L);

        Sector sector_2 = new Sector();
        sector_2.setId(2L);
        sector_2.setName("TEST_SECTOR_2");
        sector_2.setVersion(0L);

        sectorRepository.save(sector_1);
        sectorRepository.save(sector_2);
    }

    @Test
    void testGetAllSectors(){
        ResponseEntity<SectorDTO[]> sectorDTOsResponse = testRestTemplate
                .getForEntity("http://localhost:"+port+"/api/sectors",SectorDTO[].class);
        assertThat(sectorDTOsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        final List<SectorDTO> sectorDTOs = Arrays.asList(Objects.requireNonNull(sectorDTOsResponse.getBody()));

        assertThat(sectorDTOs.size()).isEqualTo(TEST_DB_SECTORS_COUNT);
    }
}
