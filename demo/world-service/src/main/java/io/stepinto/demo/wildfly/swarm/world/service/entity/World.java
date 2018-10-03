package io.stepinto.demo.wildfly.swarm.world.service.entity;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Világot reprezentáló entitás.
 */
@Data
@Entity
@Table(name = "world")
public class World extends AbstractIdentifiedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

}
