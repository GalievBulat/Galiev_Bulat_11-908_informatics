import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SocialMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        File resultingFile = new File("result.html");
        try {
            BufferedWriter fOS = new BufferedWriter(new FileWriter(resultingFile));
            try(fOS){
                commandProcessing(command,fOS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
    private static final int USER_ATTRIBUTES = 6;
    private static final int MESSAGE_ATTRIBUTES = 6;
    private static final int POST_ATTRIBUTES = 5;
    static private User findUser(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"users.txt",USER_ATTRIBUTES);
        if (lines!=null){
            return new User(lines);
        } else
            return null;
    }
    static private Post getPost(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"posts.txt",POST_ATTRIBUTES);
        if (lines!=null){
            return new Post(lines);
        } else
            return null;
    }
    static private Message getMessage(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"messages.txt",MESSAGE_ATTRIBUTES);
        if (lines!=null){
            return new Message(lines);
        } else
            return null;
    }
    static private List<String> getLines(int id,String filename,int attributes) throws FileNotFoundException {
        BufferedReader fIS = new BufferedReader(new FileReader(filename));
        List<String> lines = fIS.lines().skip((attributes+1)*id+1).limit(attributes).collect(Collectors.toList());
        if (lines.size()<attributes)
            return null;
        return lines;
    }
    static private void commandProcessing(String command, Writer stream) throws IllegalArgumentException, IOException {
        try {
            if (command.matches("/id/[0-9]*")){
                System.out.println("looking for it...");
                    int number = Integer.parseInt(command.substring(4));
                    User user = findUser(number);
                    if (user!=null) {
                        stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                        writeMe(stream, user.getName(), "login: " + user.getLogin() + " ;password: " + user.getPassword(),
                                "reg. date: " + user.getRegistration_date() + " ;birth date: " + user.getBirth_date());
                    } else
                        throw new IllegalArgumentException();
                    stream.append("</main>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>");
            } else if (command.matches("/feed")){
                stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                int i = 0;
                Post post = getPost(i);
                while (post!=null){
                    User user = findUser(post.getAuthor_id());
                    writeMe(stream, user.getName(), user.getLogin(), post.getText() + " at:" + post.getDate() +
                            " on: " + post.getDevice_type());
                    i++;
                    post = getPost(i);
                }
                stream.append("</main>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

            }else if (command.matches("/messages")){
                stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                int i = 0;
                Message message = getMessage(i);
                while (message!=null){
                    User user = findUser(message.getAuthor_id());
                    User user2 = findUser(message.getReceiver_id());
                    if  (user != null && user2!=null)
                        writeMe(stream, user.getName(), user.getLogin(),"to " +  user2.getName() +  " text " + message.getText()
                            + " at:" + message.getDate() +
                            " delivered: " + message.is_delivered() + " read: "+ message.is_read());
                    i++;
                    message = getMessage(i);
                }
                stream.append("</main>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

            }else {
                stream.write("<!DOCTYPE html>\n" +
                        "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/html\" xmlns=\"http://www.w3.org/1999/html\" style=\"height: 100%;\">" +
                        "<body>404</body>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            stream.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/html\" xmlns=\"http://www.w3.org/1999/html\" style=\"height: 100%;\">" +
                    "<body>404</body>");
        }
    }
    static private void writeMe(Writer stream,String arg1, String arg2, String arg3){
        try {
            stream.append(new BufferedReader(new FileReader("first_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg1);
            stream.append(new BufferedReader(new FileReader("second_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg2);
            stream.append(new BufferedReader(new FileReader("third_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg3);
            stream.append(new BufferedReader(new FileReader("fourth_part_of_the_page.html")).lines().collect(Collectors.joining()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
