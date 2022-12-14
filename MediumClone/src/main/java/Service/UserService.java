package Service;

import Dao.UserDao;
import Model.Story;
import Model.Tag;
import Model.User;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final UserDao daoUser = new UserDao();

    private static final Scanner scan = new Scanner(System.in);

    public void createUsers(User user){
        if (user.getPassword().length() > 5&&user.getPassword().length() < 15 )
            System.out.println("Parolanız 5 karakterden fazla 15 karakterden kısa olmadır");
        else
            daoUser.createUser(user);
    }

    private List<User> getAllUser(){
        return daoUser.getAllUsers();
    }

    public void printAllUsersBlogList(){
        getAllUser().forEach(user -> user.getStoryList().
                    forEach(blog -> System.out.println("Yazar: " + blog.getAuthor() + " | Hikaye Başlığı: " + blog.getTitle() + " | Durum: " + blog.getBlogStatus())));
    }

    public void getUsersBlogList(User user){
        daoUser.getAllUsers().stream().
                filter(user1 -> user1.getName().equalsIgnoreCase(user.getName())).
                forEach(user2 -> user2.getStoryList().forEach(blog -> System.out.println("Blog Başlığı: " + blog.getTitle())));
    }

    public void followerUsers(User user, User followedUser){
        daoUser.setFollowers(user, followedUser);
    }

    public void removesFollowing(User user, User unfollowedUser){
        daoUser.removeFollow(user, unfollowedUser);
    }

    public void followsTag(User user, Tag tag){
        daoUser.followTag(user, tag);
    }

    public void writeADraft(Story story, User user){
        System.out.print(user.getName() + " Taslağınızı yayınlamak ister misiniz? (e/a): ");
        String answer = scan.nextLine();
        daoUser.writeADraft(story, user, answer);
        System.out.println();
    }

    public void deleteAStory(Story story, User user){
        System.out.print(user.getName() + "");
        String answer = scan.nextLine();
        daoUser.deleteAStory(story, user, answer);
        System.out.println();
    }

    public void publishAStory(Story story, User user){
        System.out.print(user.getName() + " Hikayenizi silmek ister misiniz? (e/a): ");
        String answer = scan.nextLine();
        daoUser.publishAStory(story, user, answer);
    }
}
