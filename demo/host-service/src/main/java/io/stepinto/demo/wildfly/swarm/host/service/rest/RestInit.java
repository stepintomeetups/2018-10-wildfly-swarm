package io.stepinto.demo.wildfly.swarm.host.service.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.wildfly.swarm.topology.Advertise;

/**
 * JAX-RS aktivátor.
 */
@Advertise("host-service")
@ApplicationPath("/rest")
public class RestInit extends Application {

}
