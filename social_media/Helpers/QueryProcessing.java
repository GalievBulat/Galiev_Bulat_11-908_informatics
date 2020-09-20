package Helpers;

import DBClasses.Message;
import DBClasses.Post;
import DBClasses.User;

import java.io.*;

import static Helpers.FileProcessing.readFrom;
import static Helpers.FileProcessing.writeMe;

public class QueryProcessing {
    private QueryProcessing(){
    }
    private static final int USER_ATTRIBUTES = 6;
    private static final int MESSAGE_ATTRIBUTES = 6;
    private static final int POST_ATTRIBUTES = 5;
    private static final String USERS_FILE = "users.txt";
    private static final String MESSAGES_FILE = "messages.txt";
    private static final String POSTS_FILE = "posts.txt";
    private static final String ENDING_PART = "</main>\n" + "\n" + "</body>\n" + "</html>";
    static public void commandProcessing(String command, Writer stream) throws Exception {
        boolean alrightItsDone = false;
        try {
            if (command.matches("/id/[0-9]*")){
                int number = Integer.parseInt(command.substring(4));
                User user = FindSth.getMe(number,"users.txt",6, User.class);
                if (user!=null) {
                    stream.append(readFrom("initial_part.html"));
                    writeMe(stream, user.getName(), "login: " + user.getLogin() + " ;password: " + user.getPassword(),
                            "reg. date: " + user.getRegistration_date() + " ;birth date: " + user.getBirth_date());
                } else
                    throw new IllegalArgumentException();

                stream.append(ENDING_PART);
            } else if (command.matches("/feed")){
                stream.append(readFrom("initial_part.html"));
                int i = 0;
                Post post = FindSth.getMe(i,POSTS_FILE,POST_ATTRIBUTES, Post.class);
                while (post!=null){
                    User user = FindSth.getMe(post.getAuthor_id(),USERS_FILE,USER_ATTRIBUTES, User.class);
                    if (user!=null)
                        writeMe(stream, user.getName(), user.getLogin(), post.getText() + " at:" + post.getDate() +
                                " on: " + post.getDevice_type());
                    i++;
                    post = FindSth.getMe(i,POSTS_FILE,POST_ATTRIBUTES, Post.class);
                }
                stream.append(ENDING_PART);

            }else if (command.matches("/messages")){
                stream.append(readFrom("initial_part.html"));
                int i = 0;
                Message message = FindSth.getMe(i,MESSAGES_FILE,MESSAGE_ATTRIBUTES, Message.class);
                while (message!=null){
                    User user = FindSth.getMe(message.getAuthor_id(),USERS_FILE,USER_ATTRIBUTES, User.class);
                    User user2 = FindSth.getMe(message.getReceiver_id(),USERS_FILE,USER_ATTRIBUTES, User.class);
                    if  (user != null && user2!=null)
                        writeMe(stream, user.getName(), user.getLogin(),"to " +  user2.getName() +  " text " + message.getText()
                                + " at:" + message.getDate() +
                                " delivered: " + message.is_delivered() + " read: "+ message.is_read());
                    i++;
                    message = FindSth.getMe(i,MESSAGES_FILE,MESSAGE_ATTRIBUTES, Message.class);
                }
                stream.append(ENDING_PART);
            }else {
                stream.write(readFrom("404.html"));
            }
            alrightItsDone = true;
        } finally {
            if (!alrightItsDone){
                stream.write(readFrom("404.html"));
            }
        }
    }

}



























