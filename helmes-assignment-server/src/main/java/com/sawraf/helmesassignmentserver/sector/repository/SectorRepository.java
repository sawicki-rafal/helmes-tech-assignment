package com.sawraf.helmesassignmentserver.sector.repository;

import com.sawraf.helmesassignmentserver.sector.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}