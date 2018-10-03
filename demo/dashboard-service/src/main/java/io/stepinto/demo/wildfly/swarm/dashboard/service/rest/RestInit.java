package io.stepinto.demo.wildfly.swarm.dashboard.service.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.wildfly.swarm.topology.Advertise;

/**
 * Vezérlőpanel REST végpontokat aktiváló és Consul-ba bejegyző osztály.
 *
 * @author Nandor Holozsnyak
 */
@Advertise("dashboard-service")
@ApplicationPath("/rest/dashboardService")
public class RestInit extends Application {

}
