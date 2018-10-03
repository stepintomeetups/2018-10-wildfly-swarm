package io.stepinto.demo.wildfly.swarm.story.service.action.story;

import com.netflix.ribbon.RibbonRequest;
import io.netty.buffer.ByteBuf;
import io.stepinto.demo.wildfly.swarm.story.service.entity.Story;
import io.stepinto.demo.wildfly.swarm.story.service.ribbon.service.WorldRibbonService;
import io.stepinto.demo.wildfly.swarm.story.service.converter.StoryConverter;
import io.stepinto.demo.wildfly.swarm.story.service.service.StoryService;
import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryRequest;
import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryType;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Történetszál létrehozását kezelő akció osztály.
 */
@RequestScoped
public class CreateStoryAction extends BaseAction {

    @Inject
    private StoryService storyService;

    @Inject
    private WorldRibbonService worldRibbonService;

    /**
     * Történetszál létrehozása kérés objektum alapján.
     *
     * @param createStoryRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen hiba történik.
     */
    @Transactional
    public CreateStoryResponse createStory(CreateStoryRequest createStoryRequest) throws BaseException {
        if (Objects.isNull(createStoryRequest)) {
            throw new BaseException("createStoryRequest is NULL");
        }
        StoryType storyType = createStoryRequest.getStory();
        RibbonRequest<ByteBuf> world = worldRibbonService.getWorldById(storyType.getWorldId());
        //Validáljuk a világ meglétét
        unwrapRequest(world, WorldResponse.class);

        Story story = new Story();
        story.setName(storyType.getName());
        story.setDescription(storyType.getDescription());
        story.setAuthor(storyType.getAuthor());
        story.setActive(storyType.isActive());
        story.setWorldId(storyType.getWorldId());
        Story savedStory = storyService.save(story);

        CreateStoryResponse response = new CreateStoryResponse();
        response.setStory(StoryConverter.toInfoType(savedStory));
        succesResponse(response);
        return response;
    }

}
