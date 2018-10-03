package io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

/**
 * Világ helyeit szolgáltatást hívó interfész.
 */
@ResourceGroup(name = "world-service")
public interface PlaceRibbonService {

    /**
     * Világban lévő hely lekérdezése azonosító alapján.
     *
     * @param id hely azonosítója.
     * @return válasz objektum.
     */
    @TemplateName("getPlaceById")
    @Http(method = Http.HttpMethod.GET, uri = "/rest/worldService/place/{id}")
    RibbonRequest<ByteBuf> getPlaceById(@Var("id") String id);


}
