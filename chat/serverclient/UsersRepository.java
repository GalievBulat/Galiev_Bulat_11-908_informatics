package serverclient;/*
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private static final List<User> users = new ArrayList<>();
    public static List<User> getAll(){
        return users;
    }
    public static User get(String name){
        return users.stream()
                .filter((User u)->u.getName().equals(name)).findFirst().orElse(null);
    }
    public static void add(User user){
        users.add(user);
    }

}
*/
