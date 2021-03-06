package view.models;


import serverclient.User;

public class Message {
    String userName;
    public String text;


    @Override
    public String toString() {
        String text = "";
        return text + "Name: " + this.userName + "\n Text: " + this.text ;
    }


    public Message(String name, String text) {
        userName = name;
        this.text = text;
    }


    public String getUser() {
        return userName;
    }

    public void setUser(String name) {
        this.userName = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
