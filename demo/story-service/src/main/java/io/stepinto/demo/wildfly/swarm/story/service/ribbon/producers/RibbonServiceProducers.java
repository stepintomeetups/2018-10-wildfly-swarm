package io.stepinto.demo.wildfly.swarm.story.service.ribbon.producers;

import com.netflix.ribbon.Ribbon;
import io.stepinto.demo.wildfly.swarm.story.service.ribbon.service.WorldRibbonService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 * Ribbon szolgáltatásokat gyártó osztály.
 */
@RequestScoped
public class RibbonServiceProducers {

    @Produces
    @RequestScoped
    public WorldRibbonService worldRibbonService() {
        return Ribbon.from(WorldRibbonService.class);
    }
}
