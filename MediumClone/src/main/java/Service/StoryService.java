package Service;

import Dao.StoryDao;
import Model.Story;
import Model.Tag;

import java.util.List;

public class StoryService {
    private final StoryDao daoStory = new StoryDao();

    public void createStory(Story story){
        daoStory.createStory(story);
    }

    public List<Story> getAllStories(){
        return daoStory.getAllStories();
    }

    public void addStoryToTag(Story story, Tag tag){
        daoStory.addStoryToTag(story, tag);
    }

    public void removeStoryFromTag(Story story, Tag tag){
        daoStory.removeStoryFromTag(story, tag);
    }
}
