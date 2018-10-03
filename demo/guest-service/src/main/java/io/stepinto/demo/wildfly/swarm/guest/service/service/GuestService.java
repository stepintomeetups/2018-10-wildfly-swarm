package io.stepinto.demo.wildfly.swarm.guest.service.service;

import io.stepinto.demo.wildfly.swarm.guest.service.entity.Guest;
import io.stepinto.wildfly.swarm.demo.common.service.BaseService;

import javax.enterprise.context.RequestScoped;

/**
 * {@link Guest} entitást kezelő szolgáltatás.
 */
@RequestScoped
public class GuestService extends BaseService<Guest> {


}
