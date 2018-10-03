package io.stepinto.wildfly.swarm.demo.common.model.generator;

import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 20 karakter hosszú azonosító generálása.
 *
 * @author Nandor Holozsnyak
 */
public class IdGenerator implements IdentifierGenerator {

    private static final String DATE_PATTERN = "yyyyMMdd";

    @Override
    public Serializable generate(SessionImplementor session, Object object) {
        String result;
        if (object instanceof AbstractIdentifiedEntity) {
            AbstractIdentifiedEntity entity = (AbstractIdentifiedEntity) object;
            result = StringUtils.isBlank(entity.getId()) ? getGeneratedId() : entity.getId();
        } else {
            result = getGeneratedId();
        }
        return result;
    }

    private String getGeneratedId() {
        String generatedPrefix = UUID.randomUUID().toString().split("-")[4].toUpperCase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return generatedPrefix + simpleDateFormat.format(new Date());
    }

}

