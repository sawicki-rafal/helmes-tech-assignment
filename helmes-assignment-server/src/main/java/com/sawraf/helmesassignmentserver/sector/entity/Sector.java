package com.sawraf.helmesassignmentserver.sector.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sawraf.helmesassignmentserver.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"childrenSectors"})
public class Sector extends AbstractEntity {

    @NotNull
    @Size(min = 3)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_sector_id")
    @JsonManagedReference("self-reference")
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Sector> childrenSectors = new HashSet<>();

}
