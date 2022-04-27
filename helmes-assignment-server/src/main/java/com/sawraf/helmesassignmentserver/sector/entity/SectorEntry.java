package com.sawraf.helmesassignmentserver.sector.entity;


import com.sawraf.helmesassignmentserver.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sector_entries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SectorEntry extends AbstractEntity {

    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "sector_entry_sectors",
            joinColumns = {@JoinColumn(name = "sector_entry_id")},
            inverseJoinColumns = {@JoinColumn(name = "sector_id")}
    )
    private Set<Sector> sectors = new HashSet<>();

    @NotNull
    private boolean agreedToTerms;
}
