package DBClasses;

import Helpers.CreatableFromList;

import java.util.List;

public class Post implements CreatableFromList {
    private final int post_id;
    private final int author_id;
    private final String text;
    private final String date;
    private final String device_type;


    public Post(List<String> list){
        this.post_id = Integer.parseInt(list.get(0));
        this.author_id = Integer.parseInt(list.get(1));
        this.text = list.get(2);
        this.date = list.get(3);
        this.device_type = list.get(4);
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getDate() {
        return date;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getText() {
        return text;
    }
}
