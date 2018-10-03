package io.stepinto.demo.wildfly.swarm.guest.service.entity;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Vendéget reprezentáló entitás.
 */
@Data
@Entity
@Table(name = "guest")
public class Guest extends AbstractIdentifiedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "place_id", nullable = false)
    private String placeId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "first_visit_date", nullable = false)
    private Date firstVisitDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_visit_date")
    private Date lastVisitDate;

}
