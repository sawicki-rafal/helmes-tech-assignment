package com.sawraf.helmesassignmentserver.sector.controller;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.service.SectorEntryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SectorEntryControllerTest {

    @Mock
    private SectorEntryService sectorEntryService;

    @InjectMocks
    private SectorEntryController sectorEntryController;

    @Test
    void testDelegateGetAllSectorEntries() {
        sectorEntryController.getAllSectorEntries();
        verify(sectorEntryService, times(1)).getAllSectorEntries();
    }

    @Test
    void testDelegateAddSectorEntry() {
        final SectorEntrySaveOrUpdateDTO saveDTO = new SectorEntrySaveOrUpdateDTO();
        sectorEntryController.addSectorEntry(saveDTO);
        verify(sectorEntryService, times(1)).saveOrUpdateSectorEntry(saveDTO);
    }

    @Test
    void testDelegateUpdateSectorEntry() {
        final SectorEntrySaveOrUpdateDTO updateDTO = new SectorEntrySaveOrUpdateDTO();
        sectorEntryController.updateSectorEntry(updateDTO);
        verify(sectorEntryService, times(1)).saveOrUpdateSectorEntry(updateDTO);
    }

}