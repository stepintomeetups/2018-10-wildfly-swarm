package io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Content;
import com.netflix.ribbon.proxy.annotation.ContentTransformerClass;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.hystrix.HostListResponseFallbackHandler;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.transformer.BaseContentTransformer;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostRequest;

/**
 * Gazda szolgáltatást hívó interfész.
 */
@ResourceGroup(name = "host-service")
public interface HostRibbonService {

    @TemplateName("getById")
    @Http(method = HttpMethod.GET, uri = "/rest/hostService/host/{id}")
    RibbonRequest<ByteBuf> getById(@Var("id") String id);

    @TemplateName("getByWorldAndPlace")
    @Http(method = HttpMethod.GET, uri = "/rest/hostService/host/byWorldAndPlace/{worldId}/{placeId}")
    @Hystrix(fallbackHandler = HostListResponseFallbackHandler.class)
    RibbonRequest<ByteBuf> getByWorldAndPlace(@Var("worldId") String worldId, @Var("placeId") String placeId);

    @TemplateName("upgradeHostOs")
    @Http(method = HttpMethod.PUT, uri = "/rest/hostService/host", headers = {
        @Http.Header(name = "Content-Type", value = "application/json")
    })
    @ContentTransformerClass(BaseContentTransformer.class)
    RibbonRequest<ByteBuf> updateHost(@Content UpdateHostRequest updateHostRequest);

}
