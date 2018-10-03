package io.stepinto.demo.wildfly.swarm.world.service.entity;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Világon (parkon) belüli helyeket reprezentáló entitás.
 */
@Data
@Entity
@Table(name = "place")
public class Place extends AbstractIdentifiedEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="world_id")
    private World world;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "sector", nullable = false)
    private String sector;

}
