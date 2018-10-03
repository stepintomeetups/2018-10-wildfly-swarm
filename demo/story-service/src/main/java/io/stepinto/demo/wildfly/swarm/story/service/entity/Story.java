package io.stepinto.demo.wildfly.swarm.story.service.entity;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Történetszálat reprezentáló entitás.
 *
 */
@Data
@Entity
@Table(name = "story")
public class Story extends AbstractIdentifiedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Column(name = "worldId", nullable = false)
    private String worldId;
}
