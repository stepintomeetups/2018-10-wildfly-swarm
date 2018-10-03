package io.stepinto.wildfly.swarm.demo.common.model.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entitások ősosztálya egy azonosító mezővel.
 *
 * @author Nandor Holozsnyak
 */
@Data
@MappedSuperclass
public class AbstractIdentifiedEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "IdGenerator")
    @GenericGenerator(name = "IdGenerator", strategy = "io.stepinto.wildfly.swarm.demo.common.model.generator.IdGenerator")
    private String id;

}
