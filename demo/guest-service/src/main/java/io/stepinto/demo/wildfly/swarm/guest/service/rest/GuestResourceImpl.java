package io.stepinto.demo.wildfly.swarm.guest.service.rest;

import io.stepinto.demo.wildfly.swarm.guest.service.action.CreateGuestAction;
import io.stepinto.demo.wildfly.swarm.guest.service.action.GuestQueryAction;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestRequest;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestListResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
public class GuestResourceImpl implements GuestResource {

    @Inject
    private CreateGuestAction createGuestAction;

    @Inject
    private GuestQueryAction guestQueryAction;

    @Override
    public GuestResponse getById(String id) throws BaseException {
        log.tracev(">> getById(id:[{0}])", id);
        try {
            return guestQueryAction.findById(id);
        } finally {
            log.tracev("<< getById(id:[{0}])", id);

        }
    }

    @Override
    public GuestListResponse findAll() throws BaseException {
        log.tracev(">> findAll()");
        try {
            return guestQueryAction.findAll();
        } finally {
            log.tracev("<< findAll()");
        }
    }

    @Override
    public CreateGuestResponse createGuest(CreateGuestRequest createGuestRequest) throws BaseException {
        log.tracev(">> createGuest(createGuestRequest:[{0}])", createGuestRequest);
        try {
            return createGuestAction.createGuest(createGuestRequest);
        } finally {
            log.tracev("<< createGuest(createGuestRequest:[{0}])", createGuestRequest);
        }
    }
}
