package io.stepinto.demo.wildfly.swarm.story.service.converter;

import io.stepinto.demo.wildfly.swarm.story.service.entity.Story;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryInfoType;

/**
 * Történetszál konverter.
 */
public final class StoryConverter {

    private StoryConverter() {
    }

    public static StoryInfoType toInfoType(Story story) {
        StoryInfoType storyInfoType = new StoryInfoType();
        storyInfoType.setId(story.getId());
        storyInfoType.setName(story.getName());
        storyInfoType.setDescription(story.getDescription());
        storyInfoType.setAuthor(story.getAuthor());
        storyInfoType.setWorldId(story.getWorldId());
        return storyInfoType;
    }
}
