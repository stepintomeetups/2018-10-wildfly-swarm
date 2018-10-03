package io.stepinto.demo.wildfly.swarm.world.service.rest;

import org.wildfly.swarm.topology.Advertise;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS aktivátor.
 */
@Advertise("world-service")
@ApplicationPath("/rest")
public class RestInit extends Application {

}
