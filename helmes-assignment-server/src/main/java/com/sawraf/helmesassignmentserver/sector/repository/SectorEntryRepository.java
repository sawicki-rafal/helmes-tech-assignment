package com.sawraf.helmesassignmentserver.sector.repository;

import com.sawraf.helmesassignmentserver.sector.entity.SectorEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorEntryRepository extends JpaRepository<SectorEntry, Long> {
}
