package io.stepinto.demo.wildfly.swarm.story.service.repository;

import io.stepinto.demo.wildfly.swarm.story.service.entity.Story;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * {@link Story} entitást kezelő interfész.
 */
@Repository
public interface StoryRepository extends EntityRepository<Story, String> {
}
