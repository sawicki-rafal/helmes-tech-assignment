package com.sawraf.helmesassignmentserver.sector.entity;


import com.sawraf.helmesassignmentserver.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sectors")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"childrenSectors"})
public class Sector extends AbstractEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector", fetch = FetchType.LAZY)
    private Set<Sector> childrenSectors = new HashSet<>();
}
