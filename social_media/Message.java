import java.util.List;

public class Message{
    private final int author_id;
    private final int receiver_id;
    private final String text;
    private final String  date;
    private final boolean is_read;
    private final boolean is_delivered;
    public Message(List<String> list){
        this.author_id = Integer.parseInt(list.get(0));
        receiver_id = Integer.parseInt(list.get(1));
        this.text = list.get(2);
        this.date = list.get(3);
        is_read = list.get(4).equals("true");
        is_delivered = list.get(5).equals("true");
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public boolean is_delivered() {
        return is_delivered;
    }

    public boolean is_read() {
        return is_read;
    }
}
