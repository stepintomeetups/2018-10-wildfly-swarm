package io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.producers;

import com.netflix.ribbon.Ribbon;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.HostRibbonService;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.PlaceRibbonService;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.WorldRibbonService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 * Kliens oldali load-balancing szolgáltatásokat gyártó osztály.
 */
@RequestScoped
public class RibbonServiceProducers {

    @Produces
    @RequestScoped
    public HostRibbonService hostRibbonService() {
        return Ribbon.from(HostRibbonService.class);
    }

    @Produces
    @RequestScoped
    public WorldRibbonService worldRibbonService() {
        return Ribbon.from(WorldRibbonService.class);
    }

    @Produces
    @RequestScoped
    public PlaceRibbonService placeRibbonService() {
        return Ribbon.from(PlaceRibbonService.class);
    }
}
