package Service;

import Dao.TagDao;
import Model.Tag;

import java.util.List;

public class TagService {
    private final TagDao daoTag = new TagDao();

    public void createTag(Tag tag){
        daoTag.createTag(tag);
    }

    public List<Tag> getAllTag(){
        return daoTag.getAllTags();
    }

    public void removeTag(Tag tag){
        daoTag.removeTag(tag);
    }

    public void printAllTags(){
        daoTag.printAllTags();
    }
}
