package Dao;

import Model.Story;
import Model.Tag;

import java.util.ArrayList;
import java.util.List;

public class StoryDao {
    private final static List<Story> STORY_LIST = new ArrayList<>();
    private final TagDao tagDao = new TagDao();

    public void createStory(Story story){
        STORY_LIST.add(story);
    }

    public List<Story> getAllStories(){
        return STORY_LIST;
    }

    public void addStoryToTag(Story story, Tag tag){
        List<Tag> tagList = tagDao.getAllTags();

        if (!tagList.contains(tag))
            System.out.println("TagList'te böyle bir etiket yok." + tag.getTagName());
        else{
            tag.getStoryList().add(story);
            System.out.println(story.getTag() + "Başarılı bir şekilde eklenmiştir. " + tag.getTagName());
        }
    }

    public void removeStoryFromTag(Story story, Tag tag){
        List<Tag> tagList = tagDao.getAllTags();

        if (!tagList.contains(tag)){
            System.out.println("Etiket Adı " + tag.getTagName() + " listede bulunamamıştır !!! ");
            return;
        }

        if (!STORY_LIST.contains(story)){
            System.out.println("Hikaye listesinde belirtilen hikaye bulunamamıştır. " + story.getTitle() + " | " + story.getAuthor());
            return;
        }

        tagList.remove(story.getTag());
    }
}
