import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

class FindSth {
    private FindSth(){
    }
    private static final int USER_ATTRIBUTES = 6;
    private static final int MESSAGE_ATTRIBUTES = 6;
    private static final int POST_ATTRIBUTES = 5;

    final static public  User findUser(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"users.txt",USER_ATTRIBUTES);
        if (lines!=null){
            return new User(lines);
        } else
            return null;
    }
    final static public   Post getPost(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"posts.txt",POST_ATTRIBUTES);
        if (lines!=null){
            return new Post(lines);
        } else
            return null;
    }
    final static public  Message getMessage(int id) throws FileNotFoundException {
        List<String> lines = getLines(id,"messages.txt",MESSAGE_ATTRIBUTES);
        if (lines!=null){
            return new Message(lines);
        } else
            return null;
    }
    final static public List<String> getLines(int id,String filename,int attributes) throws FileNotFoundException {
        BufferedReader fIS = new BufferedReader(new FileReader(filename));
        List<String> lines = fIS.lines().skip((attributes+1)*id+1).limit(attributes).collect(Collectors.toList());
        if (lines.size()<attributes)
            return null;
        return lines;
    }
}
