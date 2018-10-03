package io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.hystrix;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostListResponse;

import java.util.Map;
import java.util.UUID;

import rx.Observable;

/**
 * Tartalék kezelő gazda lista objektumra.
 */
public class HostListResponseFallbackHandler implements FallbackHandler<HostListResponse> {

    public Observable<HostListResponse> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
        HostListResponse hostListResponse = new HostListResponse();
        hostListResponse.setResponseId(UUID.randomUUID().toString());
        hostListResponse.setHystrixResponse(true);
        return Observable.just(hostListResponse);
    }
}
