package io.stepinto.demo.wildfly.swarm.dashboard.service.action;

import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.HostRibbonService;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.HostType;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.UpgradeHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostInfoType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.OperationSystemType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Gazda szolgáltatást hívó akció osztály.
 */
@JBossLog
@RequestScoped
public class HostAction extends BaseAction {

    @Inject
    private HostRibbonService hostService;

    /**
     * Gazda operációs rendszerének frissítése.
     *
     * @param hostId gazda azonosítója.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen hiba történik.
     */
    public UpgradeHostResponse upgradeHostOs(String hostId) throws BaseException {
        if (StringUtils.isBlank(hostId)) {
            throw new BaseException("hostId is NULL");
        }
        HostResponse hostResponse = unwrapRequest(hostService.getById(hostId), HostResponse.class);
        HostInfoType host = hostResponse.getHost();

        if (!OperationSystemType.FINAL.equals(host.getOperationSystem())) {
            host.setOperationSystem(getNextOperationSystem(host.getOperationSystem()));

            UpdateHostRequest updateHostRequest = new UpdateHostRequest();
            updateHostRequest.setHost(host);
            UpdateHostResponse updateHostResponse = unwrapRequest(hostService.updateHost(updateHostRequest), UpdateHostResponse.class);
            host = updateHostResponse.getHost();
        }
        UpgradeHostResponse response = new UpgradeHostResponse();
        HostType hostType = new HostType();
        hostType.setId(host.getId());
        hostType.setName(host.getName());
        hostType.setOperationSystem(host.getOperationSystem().name());
        response.setHost(hostType);
        succesResponse(response);
        return response;
    }

    private OperationSystemType getNextOperationSystem(OperationSystemType operationSystem) {
        OperationSystemType result;
        switch (operationSystem) {
            case UNKNOWN:
                result = OperationSystemType.ALPHA;
                break;
            case ALPHA:
                result = OperationSystemType.BETA;
                break;
            case BETA:
                result = OperationSystemType.FINAL;
                break;
            case FINAL:
                result = OperationSystemType.FINAL;
                log.debug("Can't upgrade, stays FINAL!");
                break;
            default:
                result = operationSystem;
                break;
        }
        return result;
    }

}
