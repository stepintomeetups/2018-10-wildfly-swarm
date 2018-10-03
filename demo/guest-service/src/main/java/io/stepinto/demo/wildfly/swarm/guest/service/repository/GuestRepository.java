package io.stepinto.demo.wildfly.swarm.guest.service.repository;

import io.stepinto.demo.wildfly.swarm.guest.service.entity.Guest;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface GuestRepository extends EntityRepository<Guest, String> {
}
