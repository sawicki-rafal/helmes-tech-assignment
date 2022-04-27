package com.sawraf.helmesassignmentserver.sector.controller;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.service.SectorEntryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("/sector-entries")
public class SectorEntryController {

    private final SectorEntryService sectorEntryService;

    public SectorEntryController(SectorEntryService sectorEntryService) {
        this.sectorEntryService = sectorEntryService;
    }

    @GetMapping
    public List<SectorEntryDTO> getAllSectorEntries() {
        return sectorEntryService.getAllSectorEntries();
    }

    @PostMapping
    public SectorEntryDTO addSectorEntry(@RequestBody @Valid SectorEntryDTO sectorEntryDTO){
        return sectorEntryService.saveSectorEntry(sectorEntryDTO);
    }

}
