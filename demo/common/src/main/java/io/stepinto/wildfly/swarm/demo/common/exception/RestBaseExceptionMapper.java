package io.stepinto.wildfly.swarm.demo.common.exception;

import io.stepinto.demo.wildfly_swarm.dto.common.common.ErrorResponse;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@JBossLog
@Provider
public class RestBaseExceptionMapper implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException exception) {
        log.error("BaseException", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setExceptionClass(exception.getClass().toString());
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setErrorCode(exception.getErrorCode());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }
}
