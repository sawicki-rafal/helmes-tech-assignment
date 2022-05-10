package com.sawraf.helmesassignmentserver.sector.service;

import com.sawraf.helmesassignmentserver.exception.ApplicationException;
import com.sawraf.helmesassignmentserver.exception.message.MessageCode;
import com.sawraf.helmesassignmentserver.sector.dto.SectorDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import com.sawraf.helmesassignmentserver.sector.mapper.SectorEntryMapper;
import com.sawraf.helmesassignmentserver.sector.repository.SectorEntryRepository;
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
class SectorEntryServiceTest {

    public static final long SECTOR_1_ID = 1L;

    public static final String SECTOR_1_NAME = "TEST_SECTOR_1";

    public static final long SECTOR_2_ID = 2L;

    public static final long SECTOR_ENTRY_1_ID = 1L;

    public static final String SECTOR_ENTRY_1_NAME = "TEST_ANDRZEJ";

    public static final long SECTOR_ENTRY_2_ID = 2L;

    public static final String SECTOR_ENTRY_2_NAME = "TEST_TOMEK";

    @Mock
    private SectorEntryRepository sectorEntryRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private SectorEntryMapper sectorEntryMapper;

    @InjectMocks
    private SectorEntryService sectorEntryService;

    private List<SectorEntryDTO> sectorEntries;

    private SectorEntry sectorEntry_1;

    private SectorDTO sectorDTO_1;

    private Sector sector_1;

    private SectorEntryDTO sectorEntryDTO_1;

    private SectorEntryDTO sectorEntryDTO_2;


    @BeforeEach
    public void setUp() {
        sectorDTO_1 = new SectorDTO();
        sectorDTO_1.setId(SECTOR_1_ID);
        sectorDTO_1.setName(SECTOR_1_NAME);

        sector_1 = new Sector();
        sector_1.setId(SECTOR_1_ID);
        sector_1.setName(SECTOR_1_NAME);

        sectorEntryDTO_1 = new SectorEntryDTO();
        sectorEntryDTO_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntryDTO_1.setName(SECTOR_ENTRY_1_NAME);
        sectorEntryDTO_1.setAgreedToTerms(true);
        sectorEntryDTO_1.setSectors(Collections.singletonList(sectorDTO_1));

        sectorEntry_1 = new SectorEntry();
        sectorEntry_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntry_1.setName(SECTOR_ENTRY_1_NAME);
        sectorEntry_1.setAgreedToTerms(true);
        sectorEntry_1.setSectors(Collections.singleton(sector_1));

        sectorEntryDTO_2 = new SectorEntryDTO();
        sectorEntryDTO_2.setId(SECTOR_ENTRY_2_ID);
        sectorEntryDTO_2.setName(SECTOR_ENTRY_2_NAME);
        sectorEntryDTO_2.setAgreedToTerms(true);
        sectorEntryDTO_2.setSectors(Collections.singletonList(sectorDTO_1));

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

        String updatedName = "TEST_ANDRZEJ_UPDATE";

        final SectorEntrySaveOrUpdateDTO sectorEntrySaveOrUpdateDTO_1 = new SectorEntrySaveOrUpdateDTO();
        sectorEntrySaveOrUpdateDTO_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntrySaveOrUpdateDTO_1.setName(updatedName);
        sectorEntrySaveOrUpdateDTO_1.setAgreedToTerms(true);
        sectorEntrySaveOrUpdateDTO_1.setSectors(Collections.singletonList(SECTOR_1_ID));

        final SectorEntry updatedSectorEntry_1 = new SectorEntry();
        updatedSectorEntry_1.setId(SECTOR_ENTRY_1_ID);
        updatedSectorEntry_1.setName(updatedName);
        updatedSectorEntry_1.setAgreedToTerms(true);
        updatedSectorEntry_1.setSectors(Collections.singleton(sector_1));

        final SectorEntryDTO updatedSectorEntryDTO_1 = new SectorEntryDTO();
        updatedSectorEntryDTO_1.setId(SECTOR_ENTRY_1_ID);
        updatedSectorEntryDTO_1.setName(updatedName);
        updatedSectorEntryDTO_1.setAgreedToTerms(true);
        updatedSectorEntryDTO_1.setSectors(Collections.singletonList(sectorDTO_1));

        when(sectorEntryRepository.findById(SECTOR_ENTRY_1_ID)).thenReturn(Optional.of(sectorEntry_1));
        when(sectorEntryMapper.map(sectorEntrySaveOrUpdateDTO_1,sectorEntry_1)).thenReturn(updatedSectorEntry_1);

        when(sectorEntryRepository.save(updatedSectorEntry_1)).thenReturn(updatedSectorEntry_1);
        when(sectorRepository.findById(SECTOR_1_ID)).thenReturn(Optional.of(sector_1));
        when(sectorEntryMapper.mapToDto(updatedSectorEntry_1)).thenReturn(updatedSectorEntryDTO_1);

        final SectorEntryDTO sectorEntryDTO = sectorEntryService.saveOrUpdateSectorEntry(sectorEntrySaveOrUpdateDTO_1);

        assertThat(sectorEntryDTO).isEqualTo(updatedSectorEntryDTO_1);
    }

    @Test
    void shouldThrowErrorWhenUpdateWithNotKnownId() {
        final long searchId = 123456L;

        final SectorEntrySaveOrUpdateDTO sectorEntrySaveOrUpdateDTO_1 = new SectorEntrySaveOrUpdateDTO();
        sectorEntrySaveOrUpdateDTO_1.setId(searchId);

        final ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            sectorEntryService.saveOrUpdateSectorEntry(sectorEntrySaveOrUpdateDTO_1);
        });


        final List<Object> messageArgs = exception.getMessageArgs();
        final MessageCode messageCode = exception.getMessageCode();

        assertThat(messageArgs).contains(searchId);
        assertThat(messageCode).isEqualTo(ERROR_ENTITY_NOT_FOUND);
    }

    @Test
    void shouldThrowErrorWhenUnknownSectorId(){
        final long sectorId = 5L;
        final String updatedName = "TEST_ANDRZEJ_UPDATE";

        final SectorEntrySaveOrUpdateDTO sectorEntrySaveOrUpdateDTO_1 = new SectorEntrySaveOrUpdateDTO();
        sectorEntrySaveOrUpdateDTO_1.setId(SECTOR_ENTRY_1_ID);
        sectorEntrySaveOrUpdateDTO_1.setName(updatedName);
        sectorEntrySaveOrUpdateDTO_1.setAgreedToTerms(true);
        sectorEntrySaveOrUpdateDTO_1.setSectors(Collections.singletonList(sectorId));

        final SectorEntry updatedSectorEntry_1 = new SectorEntry();
        updatedSectorEntry_1.setId(SECTOR_ENTRY_1_ID);
        updatedSectorEntry_1.setName(updatedName);
        updatedSectorEntry_1.setAgreedToTerms(true);

        when(sectorEntryRepository.findById(SECTOR_ENTRY_1_ID)).thenReturn(Optional.of(sectorEntry_1));
        when(sectorEntryMapper.map(sectorEntrySaveOrUpdateDTO_1,sectorEntry_1)).thenReturn(updatedSectorEntry_1);
        when(sectorRepository.findById(sectorId)).thenReturn(Optional.empty());

        final ApplicationException exception = assertThrows(ApplicationException.class,()->{
            sectorEntryService.saveOrUpdateSectorEntry(sectorEntrySaveOrUpdateDTO_1);
        });

        final List<Object> messageArgs = exception.getMessageArgs();
        final MessageCode messageCode = exception.getMessageCode();

        assertThat(messageArgs).contains(sectorId);
        assertThat(messageCode).isEqualTo(ERROR_ENTITY_NOT_FOUND);
    }

}