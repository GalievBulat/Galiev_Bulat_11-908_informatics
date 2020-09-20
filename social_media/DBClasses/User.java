package DBClasses;

import Helpers.CreatableFromList;

import java.util.List;

public class User implements CreatableFromList {
    private final int  author_id;
    private final String name;
    private final String login;
    private final String password;
    private final String registration_date;
    private final String birth_date;
    public User(List<String> list){
        author_id = Integer.parseInt(list.get(0));
        name = list.get(1);
        login = list.get(2);
        password = list.get(3);
        registration_date = list.get(4);
        birth_date = list.get(5);
    }

    public String getName() {
        return name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getRegistration_date() {
        return registration_date;
    }
}
