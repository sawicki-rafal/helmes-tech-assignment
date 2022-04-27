package com.sawraf.helmesassignmentserver.sector.controller;

import com.sawraf.helmesassignmentserver.sector.dto.SectorEntryDTO;
import com.sawraf.helmesassignmentserver.sector.dto.SectorEntrySaveOrUpdateDTO;
import com.sawraf.helmesassignmentserver.sector.service.SectorEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sector-entries")
@RequiredArgsConstructor
public class SectorEntryController {

    private final SectorEntryService sectorEntryService;

    @GetMapping
    public List<SectorEntryDTO> getAllSectorEntries() {
        return sectorEntryService.getAllSectorEntries();
    }

    @PostMapping
    public SectorEntryDTO addSectorEntry(@RequestBody @Valid SectorEntrySaveOrUpdateDTO sectorEntryDTO){
        return sectorEntryService.saveOrUpdateSectorEntry(sectorEntryDTO);
    }

    @PutMapping
    public SectorEntryDTO updateSectorEntry(@RequestBody @Valid SectorEntrySaveOrUpdateDTO sectorEntryDTO){
        return sectorEntryService.saveOrUpdateSectorEntry(sectorEntryDTO);
    }

}
