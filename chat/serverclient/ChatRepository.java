package serverclient;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    private final List<Chat> chats = new ArrayList<>();
    {
        chats.add(new Chat(0));
    }

    public  List<Chat> getChats() {
        return chats;
    }
    public  Chat getChat(int num){
        return chats.get(num);
    }

}
