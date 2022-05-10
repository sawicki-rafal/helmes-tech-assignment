package com.sawraf.helmesassignmentserver.sector.mapper;

import com.sawraf.helmesassignmentserver.exception.ApplicationException;
import com.sawraf.helmesassignmentserver.exception.message.MessageCode;
import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.repository.SectorRepository;
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
class SectorMapperTest {

    public static final long PARENT_SECTOR_ID = 1L;
    public static final long SINGLE_SECTOR_ID = 4L;
    @Mock
    private SectorRepository sectorRepository;

    @InjectMocks
    private SectorMapper sectorMapper;

    private Sector parentSector;

    private Sector childSector_1;

    private Sector childSector_2;

    private Sector singleSector;

    @BeforeEach
    public void setup() {
        parentSector = new Sector();
        childSector_1 = new Sector();
        childSector_2 = new Sector();
        singleSector = new Sector();

        Set<Sector> children = new HashSet<>();
        children.add(childSector_1);
        children.add(childSector_2);

        parentSector.setId(PARENT_SECTOR_ID);
        parentSector.setParentSector(null);
        parentSector.setName("PARENT_SECTOR_NAME");
        parentSector.setChildrenSectors(children);

        childSector_1.setId(2L);
        childSector_1.setParentSector(parentSector);
        childSector_1.setName("CHILD_SECTOR_1");

        childSector_2.setId(3L);
        childSector_2.setParentSector(parentSector);
        childSector_2.setName("CHILD_SECTOR_2");

        singleSector.setId(SINGLE_SECTOR_ID);
        singleSector.setParentSector(null);
        singleSector.setName("SINGLE_SECTOR");
    }

    @Test
    void shouldMapSingle() {
        final SectorDTO singleSectorDTO = sectorMapper.mapToDto(singleSector);
        assertThat(singleSectorDTO.getId()).isEqualTo(singleSector.getId());
        assertThat(singleSectorDTO.getName()).isEqualTo(singleSector.getName());
        assertThat(singleSectorDTO.getChildrenSectors().size())
                .isEqualTo(singleSector.getChildrenSectors().size());
    }

    @Test
    void shouldMapParentAndChildrenSectors() {
        final SectorDTO parentSectorDTO = sectorMapper.mapToDto(parentSector);
        assertThat(parentSectorDTO.getId()).isEqualTo(parentSector.getId());
        assertThat(parentSectorDTO.getName()).isEqualTo(parentSector.getName());
        assertThat(parentSectorDTO.getChildrenSectors().size())
                .isEqualTo(parentSector.getChildrenSectors().size());

        parentSectorDTO.getChildrenSectors().forEach(sectorDTO -> {
            Sector sector = parentSector.getChildrenSectors().stream()
                    .filter(s -> s.getId().equals(sectorDTO.getId()))
                    .findFirst().get();

            assertThat(sectorDTO.getId()).isEqualTo(sector.getId());
            assertThat(sectorDTO.getName()).isEqualTo(sector.getName());
            assertThat(sectorDTO.getChildrenSectors().size())
                    .isEqualTo(sector.getChildrenSectors().size());
        });


    }

    @Test
    void shouldMapCollection() {
        final List<Sector> sectors = new LinkedList<>();
        sectors.add(parentSector);
        sectors.add(singleSector);

        final List<SectorDTO> sectorDTOs = sectorMapper.mapToDto(sectors);

        assertThat(sectorDTOs.size()).isEqualTo(sectors.size());

        sectorDTOs.forEach(sectorDTO -> {
            Sector sector = sectors.stream()
                    .filter(s -> s.getId().equals(sectorDTO.getId()))
                    .findFirst().get();

            assertThat(sectorDTO.getId()).isEqualTo(sector.getId());
            assertThat(sectorDTO.getName()).isEqualTo(sector.getName());
            assertThat(sectorDTO.getChildrenSectors().size())
                    .isEqualTo(sector.getChildrenSectors().size());
        });
    }
}