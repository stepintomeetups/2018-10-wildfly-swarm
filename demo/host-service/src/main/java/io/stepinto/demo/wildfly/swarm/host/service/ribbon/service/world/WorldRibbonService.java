package io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

/**
 * Világ szolgáltatást hívó interfész.
 */
@ResourceGroup(name = "world-service")
public interface WorldRibbonService {

    /**
     * Világ lekérdezése azonosító alapján.
     *
     * @param id világ azonosítója.
     * @return válasz objektum.
     */
    @TemplateName("getWorldById")
    @Http(method = Http.HttpMethod.GET, uri = "/rest/worldService/world/{id}")
    RibbonRequest<ByteBuf> getWorldById(@Var("id") String id);


}
