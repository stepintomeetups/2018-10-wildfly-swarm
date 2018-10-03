package io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.story;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

/**
 * Történetszál szolgáltatást hívó interfész.
 */
@ResourceGroup(name = "story-service")
public interface StoryRibbonService {

    /**
     * Történetszál lekérdezése azonosító alapján.
     *
     * @param id történetszál azonosítója.
     * @return válasz objektum.
     */
    @TemplateName("getStoryById")
    @Http(method = Http.HttpMethod.GET, uri = "/rest/storyService/story/{id}")
    RibbonRequest<ByteBuf> getStoryById(@Var("id") String id);


}
