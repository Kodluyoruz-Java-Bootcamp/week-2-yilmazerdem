package Dao;

import Model.BlogStatus;
import Model.Story;
import Model.Tag;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final List<User> USER_LIST = new ArrayList<>();
    private final TagDao tagDao = new TagDao();
    public void createUser(User user){
        USER_LIST.add(user);
    }

    public List<User> getAllUsers(){
        return USER_LIST;
    }

    public void setFollowers(User user, User followedUser){
        if(USER_LIST.contains(user) && USER_LIST.contains(followedUser)) {
            user.getFollowingUsersList().add(followedUser);
            followedUser.getFollowedUsersList().add(user);
        }
        else {
            if (!USER_LIST.contains(user))
                System.out.println(" " + user.getName());

            if (!USER_LIST.contains(followedUser))
                System.out.println("Kullanıcı listesinde " + followedUser.getName() + " isimli kullanıcı bulunamamıştır!!!!");
        }
    }

    public void removeFollow(User user, User unfollowedUser){
        if(USER_LIST.contains(user) && USER_LIST.contains(unfollowedUser)) {
            user.getFollowedUsersList().remove(unfollowedUser);
            unfollowedUser.getFollowingUsersList().remove(user);
            System.out.println(user.getName() + " kullanıcısı takip etme işlemi durdurulmuştur." + unfollowedUser.getName());
        }
        else {
            if (!USER_LIST.contains(user))
                    System.out.println(user.getName() + "Belirtilen kullanıcıya erişilememiştir. ");

            if (!USER_LIST.contains(unfollowedUser))
                System.out.println("Malesef kullanıcıya erişilemedi" + unfollowedUser.getName());
        }
    }

    public void followTag(User user, Tag tag){
        List<Tag> tagList = tagDao.getAllTags();

        if (!tagList.contains(tag))
            System.out.println("Etiket bulunamadı. Bu etkileşime giremezsiniz" + tag.getTagName() + ").");
        else {
            user.getFollowingTagList().add(tag);
            System.out.println(user.getName() + "Takip ediliyor" + tag.getTagName());
        }
    }

    public void writeADraft(Story story, User user, String answer){
        user.getStoryList().add(story);

        if (answer.equalsIgnoreCase("y")) {
            story.setBlogStatus(BlogStatus.PUBLISHED);
            System.out.println("Hikayeniz yayınlanmıştır. ( " + story.getTitle()+")");
        }
    }

    public void deleteAStory(Story story, User user, String answer){
        if (answer.equalsIgnoreCase("y")) {
            if (user.getStoryList().contains(story)) {
                user.getStoryList().remove(story);
                System.out.println("Hikayeniz başarılı bir şekilde silinmiştir. " + story.getTitle());
            }
            else
                System.out.println(story.getTitle() + " Bulunamadı. ");
        }
    }

    public void publishAStory(Story story, User user, String answer){
        if (answer.equalsIgnoreCase("y")){
            if (user.getStoryList().contains(story)) {
                if (story.getBlogStatus() != BlogStatus.PUBLISHED) {
                    story.setBlogStatus(BlogStatus.PUBLISHED);
                    System.out.println("Hikaye başarılı bir şekilde yayınlanmıştır." + story.getTitle());
                }
                else
                    System.out.println(story.getTitle() + " daha önce yayınlanmıştır. Lütfen başlığı değiştirin !!");
            }
            else
                System.out.println(story.getTitle() + " bulunamadı");
        }
    }
}
