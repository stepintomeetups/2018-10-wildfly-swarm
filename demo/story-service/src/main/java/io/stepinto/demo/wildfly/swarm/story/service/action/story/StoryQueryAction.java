package io.stepinto.demo.wildfly.swarm.story.service.action.story;

import io.stepinto.demo.wildfly.swarm.story.service.entity.Story;
import io.stepinto.demo.wildfly.swarm.story.service.service.StoryService;
import io.stepinto.demo.wildfly.swarm.story.service.converter.StoryConverter;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryInfoType;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryListResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Történet szálakat listázó akció osztály.
 */
@RequestScoped
public class StoryQueryAction extends BaseAction {

    @Inject
    private StoryService storyService;

    /**
     * Történetszál lekérdezése azonosító alapján.
     *
     * @param id történetszál azonosítója.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public StoryResponse findById(String id) throws BaseException {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id is NULL");
        }
        Story story = storyService.findById(id, Story.class);

        StoryResponse response = new StoryResponse();
        response.setStory(StoryConverter.toInfoType(story));
        succesResponse(response);
        return response;
    }

    /**
     * Az összes történetszál lekérdezése.
     *
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public StoryListResponse findAll() throws BaseException {
        List<Story> worldList = storyService.findAll(Story.class);

        List<StoryInfoType> worldInfoTypes = worldList.stream().map(StoryConverter::toInfoType).collect(Collectors.toList());
        StoryListResponse response = new StoryListResponse();
        response.getList().addAll(worldInfoTypes);
        succesResponse(response);
        return response;
    }

}
