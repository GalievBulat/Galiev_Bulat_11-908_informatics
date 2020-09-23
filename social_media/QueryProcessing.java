import DBClasses.Message;
import DBClasses.Post;
import DBClasses.User;
import Helpers.FindSth;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static FileProcessing.readFrom;

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
    private static void idCommand(FileProcessing fP, int number) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        User user = FindSth.getMe(number, "users.txt", 6, User.class);
        if (user != null) {
            fP.writeMe(readFrom("initial_part.html"));
            fP.writeMeEntity(user.getName(), "login: " + user.getLogin() + " ;password: " + user.getPassword(),
                    "reg. date: " + user.getRegistration_date() + " ;birth date: " + user.getBirth_date());
        } else
            throw new IllegalArgumentException();
    }
    private static void feedCommand(FileProcessing fP) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        fP.writeMe(readFrom("initial_part.html"));
        int i = 0;
        Post post = FindSth.getMe(i, POSTS_FILE, POST_ATTRIBUTES, Post.class);
        while (post != null) {
            User user = FindSth.getMe(post.getAuthor_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            if (user != null)
                fP.writeMeEntity(user.getName(), user.getLogin(), post.getText() + " at:" + post.getDate() +
                        " on: " + post.getDevice_type());
            i++;
            post = FindSth.getMe(i, POSTS_FILE, POST_ATTRIBUTES, Post.class);
        }
    }
    private static void messagesCommand(FileProcessing fP) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        fP.writeMe(readFrom("initial_part.html"));
        int i = 0;
        Message message = FindSth.getMe(i, MESSAGES_FILE, MESSAGE_ATTRIBUTES, Message.class);
        while (message != null) {
            User user = FindSth.getMe(message.getAuthor_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            User user2 = FindSth.getMe(message.getReceiver_id(), USERS_FILE, USER_ATTRIBUTES, User.class);
            if (user != null && user2 != null)
                fP.writeMeEntity(user.getName(), user.getLogin(), "to " + user2.getName() + " text " + message.getText()
                        + " at:" + message.getDate() +
                        " delivered: " + message.is_delivered() + " read: " + message.is_read());
            i++;
            message = FindSth.getMe(i, MESSAGES_FILE, MESSAGE_ATTRIBUTES, Message.class);
        }
    }
    private static void commandProcessing(String command, FileProcessing fP) throws Exception {
        if (command.matches("/id/[0-9]*")) {
            int number = Integer.parseInt(command.substring(4));
            idCommand(fP,number);
            fP.writeMe(ENDING_PART);
        } else if (command.matches("/feed")) {
            feedCommand(fP);
            fP.writeMe(ENDING_PART);

        } else if (command.matches("/messages")) {
            messagesCommand(fP);
            fP.writeMe(ENDING_PART);
        } else {
            fP.writeMe(readFrom("404.html"));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        File resultingFile = new File("result.html");
        try {
            FileProcessing fP = new FileProcessing(resultingFile);
            try(fP){
                commandProcessing(command,fP);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument!");
        } catch (Exception e) {
            System.out.println("Exception!");
        }
        sc.close();
    }

}



























