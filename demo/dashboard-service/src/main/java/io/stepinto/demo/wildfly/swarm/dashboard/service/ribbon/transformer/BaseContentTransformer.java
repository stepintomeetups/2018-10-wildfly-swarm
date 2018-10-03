package io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.reactivex.netty.channel.ContentTransformer;
import io.stepinto.demo.wildfly_swarm.dto.common.common.BaseRequestType;
import lombok.extern.jbosslog.JBossLog;

import java.util.UUID;

/**
 * Általános kérés objektum alakító osztály.
 */
@JBossLog
public class BaseContentTransformer implements ContentTransformer<BaseRequestType> {

    @Override
    public ByteBuf call(BaseRequestType baseRequestType, ByteBufAllocator byteBufAllocator) {
        baseRequestType.setRequestId(UUID.randomUUID().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        ByteBuf byteBuf;
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(baseRequestType);
            byteBuf = byteBufAllocator.buffer(bytes.length);
            byteBuf.writeBytes(bytes);
        } catch (JsonProcessingException e) {
            log.warn("Error on converting request to JSON.");
            byteBuf = new EmptyByteBuf(PooledByteBufAllocator.DEFAULT);
        }
        return byteBuf;
    }
}
