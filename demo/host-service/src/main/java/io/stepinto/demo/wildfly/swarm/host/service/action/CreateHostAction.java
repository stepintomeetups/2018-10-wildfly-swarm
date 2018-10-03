package io.stepinto.demo.wildfly.swarm.host.service.action;

import io.stepinto.demo.wildfly.swarm.host.service.converter.HostConverter;
import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import io.stepinto.demo.wildfly.swarm.host.service.service.HostService;
import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostType;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;

/**
 * Hoszt létrehozására szolgáló akció osztály.
 *
 * @author Nandor Holozsnyak
 */
@RequestScoped
public class CreateHostAction extends BaseAction {

    @Inject
    private HostService hostService;

    /**
     * Gazda létrehozása kérés objektum alapján.
     *
     * @param createHostRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException
     */
    @Transactional
    public CreateHostResponse createHost(CreateHostRequest createHostRequest) throws BaseException {
        if (Objects.isNull(createHostRequest)) {
            throw new BaseException("createHostRequest is NULL");
        }
        HostType hostType = createHostRequest.getHost();
        Host host = HostConverter.toEntity(hostType);
        host.setCreated(new Date());

        Host savedHost = hostService.save(host);
        CreateHostResponse response = new CreateHostResponse();
        response.setHost(HostConverter.toHostInfoType(savedHost));
        succesResponse(response);
        return response;
    }

}
