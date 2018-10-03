package io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.producer;

import com.netflix.ribbon.Ribbon;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.story.StoryRibbonService;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world.PlaceRibbonService;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world.WorldRibbonService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 * Ribbon szolgáltatásokat gyártó osztály.
 */
@RequestScoped
public class RibbonServiceProducer {

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

    @Produces
    @RequestScoped
    public StoryRibbonService storyRibbonService() {
        return Ribbon.from(StoryRibbonService.class);
    }


}
