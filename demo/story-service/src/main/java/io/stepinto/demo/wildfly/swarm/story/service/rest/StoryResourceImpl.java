package io.stepinto.demo.wildfly.swarm.story.service.rest;

import io.stepinto.demo.wildfly.swarm.story.service.action.story.CreateStoryAction;
import io.stepinto.demo.wildfly.swarm.story.service.action.story.StoryQueryAction;
import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryRequest;
import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryListResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
public class StoryResourceImpl implements StoryResource {

    @Inject
    private CreateStoryAction createStoryAction;

    @Inject
    private StoryQueryAction storyQueryAction;

    @Override
    public StoryResponse getById(String id) throws BaseException {
        log.tracev(">> getById(id:[{0}])", id);
        try {
            return storyQueryAction.findById(id);
        } finally {
            log.tracev("<< getById(id:[{0}])", id);
        }
    }

    @Override
    public StoryListResponse findAll() throws BaseException {
        log.tracev(">> findAll()");
        try {
            return storyQueryAction.findAll();
        } finally {
            log.tracev("<< findAll()");
        }
    }

    @Override
    public CreateStoryResponse createStory(CreateStoryRequest createStoryRequest) throws BaseException {
        log.tracev(">> createStory(createStoryRequest:[{0}])", createStoryRequest);
        try {
            return createStoryAction.createStory(createStoryRequest);
        } finally {
            log.tracev("<< createStory(createStoryRequest:[{0}])", createStoryRequest);
        }
    }
}
