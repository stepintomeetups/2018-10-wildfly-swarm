package io.stepinto.wildfly.swarm.demo.common.exception;

import io.stepinto.demo.wildfly_swarm.dto.common.common.ErrorResponse;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@JBossLog
@Provider
public class RestEntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        log.error("EntityNotFoundException", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setExceptionClass(exception.getClass().toString());
        errorResponse.setErrorCode(exception.getErrorCode());
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}
