package com.example.mediumclone;

import Model.Story;
import Model.Tag;
import Model.User;
import Service.StoryService;
import Service.TagService;
import Service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediumCloneMain {

    public static void main(String[] args) {

        StoryService serviceStory = new StoryService();
        TagService serviceTag = new TagService();
        UserService serviceUser= new UserService();

        User erdem = new User("Erdem",
                            "erdmylmz0@gmail.com",
                            "121314");

        User erman = new User("Erman",
                            "ermandeney@hotmail.com",
                            "959987");
        
            Story story01 = new Story("Başlık-01", "Başarılarımız, başarısızlıklarımızdan öğrendiklerimizdir.", erdem);
        Story story02 = new Story("Başlık-02", "Başarılı olmak için sürekli çalışmaya ihtiyacımız var, şansa değil.", erman);

        serviceStory.createStory(story01);
        serviceStory.createStory(story02);

        Tag tag = new Tag("Öz gelişim");

        serviceUser.createUsers(erdem);
        serviceUser.createUsers(erman);

        story01.setTagList(tag);
        story02.setTagList(tag);

        serviceTag.createTag(tag);
        serviceTag.printAllTags();

        serviceUser.followerUsers(erdem, erman);
        serviceUser.followerUsers(erman, erdem);

        serviceUser.followsTag(erdem, tag);
        serviceUser.followsTag(erman, tag);

        serviceUser.writeADraft(story01, erdem);
        serviceUser.writeADraft(story02, erman);

        serviceUser.printAllUsersBlogList();

        serviceUser.removesFollowing(erman, erdem);

        serviceUser.publishAStory(story01, erdem);

        serviceUser.deleteAStory(story02, erman);

        serviceUser.printAllUsersBlogList();

        Story story3 = new Story("Başlık-03", "Her başarı başarısızlıklardan öğrenebildiklerimizdir", erdem);
        serviceStory.createStory(story3);
        erdem.getStoryList().add(story3);

        serviceUser.getUsersBlogList(erdem);
    }

}
