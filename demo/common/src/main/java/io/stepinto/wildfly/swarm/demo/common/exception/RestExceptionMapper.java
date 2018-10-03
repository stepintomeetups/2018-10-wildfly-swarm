package io.stepinto.wildfly.swarm.demo.common.exception;

import io.stepinto.demo.wildfly_swarm.dto.common.common.ErrorResponse;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@JBossLog
@Provider
public class RestExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("Exception", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setExceptionClass(exception.getClass().toString());
        errorResponse.setErrorCode("UNKNOWN");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).build();
    }
}
