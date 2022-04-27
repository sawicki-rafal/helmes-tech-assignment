package com.sawraf.helmesassignmentserver.sector.entity;


import com.sawraf.helmesassignmentserver.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "sector_entries")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SectorEntry extends AbstractEntity {

    private String name;

    @ManyToMany
    private List<Sector> sectors;

    private boolean agreedToTerms;
}
