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
public interface WorldRibbonService {

    @TemplateName("getWorldById")
    @Http(method = Http.HttpMethod.GET, uri = "/rest/worldService/world/{id}")
    RibbonRequest<ByteBuf> getWorldById(@Var("id") String id);

}
