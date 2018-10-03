package io.stepinto.wildfly.swarm.demo.common.action;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.netflix.ribbon.RibbonRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.stepinto.demo.wildfly_swarm.dto.common.common.BaseResponseType;
import io.stepinto.demo.wildfly_swarm.dto.common.common.ErrorResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.stepinto.wildfly.swarm.demo.common.exception.EntityNotFoundException;
import io.stepinto.wildfly.swarm.demo.common.instance.ServiceInstance;
import lombok.extern.jbosslog.JBossLog;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Fő akció osztály.
 *
 * @author Nandor Holozsnyak
 */
@JBossLog
public class BaseAction implements Serializable {

    @Inject
    private ServiceInstance serviceInstance;

    /**
     * Válasz objektum fejléceinek beállítása.
     *
     * @param baseResponseType válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    protected void succesResponse(BaseResponseType baseResponseType) throws BaseException {
        baseResponseType.setHystrixResponse(false);
        baseResponseType.setServiceInstanceId(serviceInstance.getIntanceId());
        baseResponseType.setResponseId(UUID.randomUUID().toString());
    }

    /**
     * Kérés objektum meghívása majd az eredmény kicsomagolása.
     *
     * @param request kérés objektum.
     * @param clazz   válasz objektum típusa.
     * @param <T>     generikus típus.
     * @return kicsomagolt válasz objektum
     * @throws BaseException ha valamilyen hiba történik.
     */
    protected <T> T unwrapRequest(RibbonRequest<ByteBuf> request, Class<T> clazz) throws BaseException {
        ByteBuf byteBuf = request.execute();
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader reader = mapper.reader();
        JsonFactory factory = new JsonFactory();
        T value;
        try {
            InputStream inputStream = new ByteBufInputStream(byteBuf.retainedDuplicate());
            JsonParser parser = factory.createParser(inputStream);
            value = reader.readValue(parser, clazz);
        } catch (Exception e) {
            handleException(byteBuf.retainedDuplicate());
            throw new BaseException("Error on unwrapping RibbonRequest.", e);
        }
        log.tracev("Response arrived from instanceId:[{0}]", ((BaseResponseType) value).getServiceInstanceId());
        return value;
    }

    private void handleException(ByteBuf execute) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader reader = mapper.reader();
        JsonFactory factory = new JsonFactory();
        ErrorResponse errorResponse;
        try {
            InputStream inputStream = new ByteBufInputStream(execute);
            JsonParser parser = factory.createParser(inputStream);
            errorResponse = reader.readValue(parser, ErrorResponse.class);
        } catch (IOException e1) {
            throw new BaseException("Error on handling error.", e1);
        }
        if (errorResponse.getErrorCode().equals("ENTITY_NOT_FOUND")) {
            throw new EntityNotFoundException(errorResponse.getMsg());
        }
    }

}
