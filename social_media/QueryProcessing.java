import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collectors;

public class QueryProcessing {
    private QueryProcessing(){
    }
    final static public void commandProcessing(String command, Writer stream) throws IllegalArgumentException, IOException {
        try {
            if (command.matches("/id/[0-9]*")){
                System.out.println("looking for it...");
                int number = Integer.parseInt(command.substring(4));
                User user = FindSth.findUser(number);
                if (user!=null) {
                    stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                    writeMe(stream, user.getName(), "login: " + user.getLogin() + " ;password: " + user.getPassword(),
                            "reg. date: " + user.getRegistration_date() + " ;birth date: " + user.getBirth_date());
                } else
                    throw new IllegalArgumentException();
                stream.append("</main>\n" + "\n" + "</body>\n" + "</html>");
            } else if (command.matches("/feed")){
                stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                int i = 0;
                Post post = FindSth.getPost(i);
                while (post!=null){
                    User user = FindSth.findUser(post.getAuthor_id());
                    if (user!=null)
                        writeMe(stream, user.getName(), user.getLogin(), post.getText() + " at:" + post.getDate() +
                                " on: " + post.getDevice_type());
                    i++;
                    post = FindSth.getPost(i);
                }
                stream.append("</main>\n" + "\n" + "</body>\n" + "</html>");

            }else if (command.matches("/messages")){
                stream.append(new BufferedReader(new FileReader("initial_part.html")).lines().collect(Collectors.joining()));
                int i = 0;
                Message message = FindSth.getMessage(i);
                while (message!=null){
                    User user = FindSth.findUser(message.getAuthor_id());
                    User user2 = FindSth.findUser(message.getReceiver_id());
                    if  (user != null && user2!=null)
                        writeMe(stream, user.getName(), user.getLogin(),"to " +  user2.getName() +  " text " + message.getText()
                                + " at:" + message.getDate() +
                                " delivered: " + message.is_delivered() + " read: "+ message.is_read());
                    i++;
                    message = FindSth.getMessage(i);
                }
                stream.append("</main>\n" + "\n" + "</body>\n" + "</html>");

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
    final static public void writeMe(Writer stream,String arg1, String arg2, String arg3){
        try {
            stream.append(new BufferedReader(new FileReader("first_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg1);
            stream.append(new BufferedReader(new FileReader("second_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg2);
            stream.append(new BufferedReader(new FileReader("third_part_of_the_page.html")).lines().collect(Collectors.joining()));
            stream.append(arg3);
            stream.append(new BufferedReader(new FileReader("fourth_part_of_the_page.html")).lines().collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
