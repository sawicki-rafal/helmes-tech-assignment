package com.sawraf.helmesassignmentserver.sector.controller;

import com.sawraf.helmesassignmentserver.sector.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SectorControllerTest {

    @Mock
    private SectorService sectorService;

    @InjectMocks
    private SectorController sectorController;

    @Test
    void testDelegateGetAllSectors() {
        sectorController.getAllSectors();
        verify(sectorService, times(1)).getAllSectors();
    }
}