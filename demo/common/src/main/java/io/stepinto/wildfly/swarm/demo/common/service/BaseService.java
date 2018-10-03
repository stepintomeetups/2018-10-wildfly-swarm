package io.stepinto.wildfly.swarm.demo.common.service;

import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.stepinto.wildfly.swarm.demo.common.exception.EntityNotFoundException;
import io.stepinto.wildfly.swarm.demo.common.model.entity.AbstractIdentifiedEntity;
import lombok.extern.jbosslog.JBossLog;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Fő szolgáltatás az entitások kezelésére.
 *
 * @param <T> entitás típusa amelyet a szolgáltatás kezel.
 */
@JBossLog
public class BaseService<T extends AbstractIdentifiedEntity> implements Serializable {

    @Inject
    private EntityManager entityManager;

    /**
     * Entitás mentése.
     *
     * @param entity mentendő entitás.
     * @return mentett entitás.
     * @throws BaseException ha valamilyen hiba történik.
     */
    public T save(T entity) throws BaseException {
        if (Objects.isNull(entity)) {
            throw new BaseException("entity is NULL");
        }
        log.tracev(">> save(entityClass:[{0}])", entity.getClass());
        T saved;
        try {
            saved = entityManager.merge(entity);
        } catch (Exception e) {
            String message = String.format("Error on saving entity with class:[%s]", entity.getClass());
            throw new BaseException(message, e);
        } finally {
            log.tracev("<< save(entityClass:[{0}])", entity.getClass());
        }
        return saved;
    }

    /**
     * Entitás keresése azonosító és osztály alapján.
     *
     * @param id    azonosító.
     * @param clazz osztály.
     * @return entitás objektum.
     * @throws BaseException ha valamilyen kivétel történik.
     */
    public T findById(String id, Class<T> clazz) throws BaseException {
        log.tracev(">> findById(id:[{0}], clazz:[{1}])", id, clazz);
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id is blank");
        }
        if (Objects.isNull(clazz)) {
            throw new BaseException("clazz is NULL");
        }
        T entity;
        try {
            entity = entityManager.find(clazz, id);
        } catch (Exception e) {
            String message = String.format("Error on finding entity with class:[%s] by id:[%s]", clazz, id);
            throw new BaseException(message, e);
        } finally {
            log.tracev("<< findById(id:[{0}], clazz:[{1}])", id, clazz);
        }
        if (Objects.isNull(entity)) {
            String msg = String.format("Entity with class:[%s] and id:[{%s}] not found", clazz, id);
            throw new EntityNotFoundException(msg);
        }
        return entity;
    }

    /**
     * Adott osztályhoz tartozó összes entitás listázása.
     *
     * @param clazz osztály.
     * @return entitások listája.
     * @throws BaseException ha valamilyen kivétel történik.
     */
    public List<T> findAll(Class<T> clazz) throws BaseException {
        log.tracev(">> findAll(clazz:[{0}])", clazz);
        if (Objects.isNull(clazz)) {
            throw new BaseException("clazz is NULL");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> from = query.from(clazz);
        CriteriaQuery<T> select = query.select(from);
        List<T> resultList;
        try {
            resultList = entityManager.createQuery(select).getResultList();
        } catch (Exception e) {
            String message = String.format("Error on finding all entity with class:[%s]", clazz);
            throw new BaseException(message, e);
        } finally {
            log.tracev("<< findAll(clazz:[{0}])", clazz);
        }
        return resultList;
    }
}
