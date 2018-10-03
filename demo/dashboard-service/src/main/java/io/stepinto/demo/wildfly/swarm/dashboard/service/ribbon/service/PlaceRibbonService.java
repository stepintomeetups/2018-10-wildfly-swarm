package io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.ClientProperties;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

/**
 * Világokat hívó belső szolgáltatás.
 */
@ClientProperties(properties = {
    @ClientProperties.Property(name = "ConnectTimeout", value = "1000"),
    @ClientProperties.Property(name = "MaxAutoRetriesNextServer", value = "1")
})
@ResourceGroup(name = "world-service")
public interface PlaceRibbonService {

    @TemplateName("findByWorldId")
    @Http(method = Http.HttpMethod.GET, uri = "/rest/worldService/place/byWorldId/{worldId}")
    RibbonRequest<ByteBuf> findByWorldId(@Var("worldId") String worldId);

}
