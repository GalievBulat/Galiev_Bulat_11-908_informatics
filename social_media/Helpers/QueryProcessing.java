package Helpers;

import DBClasses.Message;
import DBClasses.Post;
import DBClasses.User;
import Helpers.FindSth;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import File.FileProcessing;
import File.FileHandler;
import File.OutputHandler;


public final class QueryProcessing {
    private static final int USER_ATTRIBUTES = 6;
    private static final int MESSAGE_ATTRIBUTES = 6;
    private static final int POST_ATTRIBUTES = 5;
    private static final String USERS_FILE = "users.txt";
    private static final String MESSAGES_FILE = "messages.txt";
    private static final String POSTS_FILE = "posts.txt";
    private static final String ENDING_PART = "</main>\n" + "\n" + "</body>\n" + "</html>";
    private void idCommand(InstanceCreating iC,OutputHandler fP, int number) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        User user = iC.getMe(number, "users.txt", 6, User.class);
        if (user != null) {
            fP.writeMe(new FileHandler("initial_part.html").getLines());
            fP.writeMeEntity(user.getName(), "login: " + user.getLogin() + " ;password: " + user.getPassword(),
                    "reg. date: " + user.getRegistration_date() + " ;birth date: " + user.getBirth_date());
        } else
            throw new IllegalArgumentException();
    }
    private void feedCommand(InstanceCreating iC,OutputHandler fP) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        fP.writeMe(new FileHandler("initial_part.html").getLines());
        int i = 0;
        Post post = iC.getMe(i, POSTS_FILE, POST_ATTRIBUTES, Post.class);
        while (post != null) {
            User user = iC.getMe(post.getAuthor_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            if (user != null)
                fP.writeMeEntity(user.getName(), user.getLogin(), post.getText() + " at:" + post.getDate() +
                        " on: " + post.getDevice_type());
            i++;
            post = iC.getMe(i, POSTS_FILE, POST_ATTRIBUTES, Post.class);
        }
    }
    private void messagesCommand(InstanceCreating iC,OutputHandler fP) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        fP.writeMe(new FileHandler("initial_part.html").getLines());
        int i = 0;
        Message message = iC.getMe(i, MESSAGES_FILE, MESSAGE_ATTRIBUTES, Message.class);
        while (message != null) {
            User user = iC.getMe(message.getAuthor_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            User user2 = iC.getMe(message.getReceiver_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            if (user != null && user2 != null)
                fP.writeMeEntity(user.getName(), user.getLogin(), "to " + user2.getName() + " text " + message.getText()
                        + " at:" + message.getDate() +
                        " delivered: " + message.is_delivered() + " read: " + message.is_read());
            i++;
            message = iC.getMe(i, MESSAGES_FILE, MESSAGE_ATTRIBUTES, Message.class);
        }
    }
    public QueryProcessing(String command, OutputHandler fP) throws Exception {
        InstanceCreating instanceCreating = new FindSth();
        if (command.matches("/id/[0-9]*")) {
            int number = Integer.parseInt(command.substring(4));
            idCommand(instanceCreating,fP,number);
            fP.writeMe(ENDING_PART);
        } else if (command.matches("/feed")) {
            feedCommand(instanceCreating,fP);
            fP.writeMe(ENDING_PART);

        } else if (command.matches("/messages")) {
            messagesCommand(instanceCreating,fP);
            fP.writeMe(ENDING_PART);
        } else {
            fP.writeMe(new FileHandler("404.html").getLines());
        }
    }

}



























