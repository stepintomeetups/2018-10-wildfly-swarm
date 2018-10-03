package io.stepinto.wildfly.swarm.demo.common.instance;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Szolgáltatás azonosító példány.
 */
@ApplicationScoped
public class ServiceInstance {

    private static final String SERVICE_INSTANCE_ID = UUID.randomUUID().toString().split("-")[0];

    @Inject
    @ConfigurationValue("service.name")
    private String serviceName;


    public String getIntanceId() {
        return serviceName.concat("-service-").concat(SERVICE_INSTANCE_ID);
    }

}
