package io.stepinto.demo.wildfly.swarm.host.service.entity;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Hosztokat reprezentáló entitás.
 *
 * @author Nandor Holozsnyak
 */
@Data
@Entity
@Table(name = "host")
public class Host extends AbstractIdentifiedEntity {

    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_system", nullable = false)
    private OperationSystem operationSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Column(name = "consciousness_level", nullable = false)
    private BigDecimal consciousnessLevel;

    @NotNull
    @Column(name = "story_thread_id", nullable = false)
    private String storyThreadId;

    @NotNull
    @Column(name = "world_id", nullable = false)
    private String worldId;

    @NotNull
    @Column(name = "place_id", nullable = false)
    private String placeId;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

}
