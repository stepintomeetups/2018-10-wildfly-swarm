package io.stepinto.demo.wildfly.swarm.host.service.action;

import io.stepinto.demo.wildfly.swarm.host.service.converter.HostConverter;
import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import io.stepinto.demo.wildfly.swarm.host.service.service.HostService;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostInfoType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostListResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hosztok listázását végő akció osztály.
 *
 * @author Nandor Holozsnyak
 */
@RequestScoped
public class HostListAction extends BaseAction {

    @Inject
    private HostService hostService;

    /**
     * Hoszt lekérdezése azonosító alapján.
     *
     * @param id azonosító.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public HostResponse findById(String id) throws BaseException {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id is NULL");
        }
        Host host = hostService.findById(id, Host.class);

        HostResponse response = new HostResponse();
        response.setHost(HostConverter.toHostInfoType(host));
        succesResponse(response);
        return response;
    }


    /**
     * Az összes hoszt listázása.
     *
     * @return minden host egy listában.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public HostListResponse findAll() throws BaseException {
        List<Host> hostList = hostService.findAll(Host.class);
        HostListResponse response = new HostListResponse();
        List<HostInfoType> hostTypeList = hostList.stream().map(HostConverter::toHostInfoType).collect(Collectors.toList());
        response.getList().addAll(hostTypeList);
        succesResponse(response);
        return response;
    }

    /**
     * Gazdák világ és hely azonosító alapján való listázása.
     *
     * @return minden host egy listában.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public HostListResponse findByWorldAndPlaceId(String worldId, String placeId) throws BaseException {
        if (StringUtils.isAnyBlank(worldId, placeId)) {
            throw new BaseException("worldId and/or placeId are NULL");
        }
        List<Host> hostList = hostService.findHostListByWorldAndPlaceId(worldId, placeId);
        HostListResponse response = new HostListResponse();
        List<HostInfoType> hostTypeList = hostList.stream().map(HostConverter::toHostInfoType).collect(Collectors.toList());
        response.getList().addAll(hostTypeList);
        succesResponse(response);
        return response;
    }

}
