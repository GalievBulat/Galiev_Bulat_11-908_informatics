import DBClasses.User;
import DBClasses.UserTC;
import Servants.FileOutputHandler;
import Servants.OutputHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/reg2")
public class RegServlet2 extends HttpServlet{
    private final String USERS_FILE = "C:\\Users\\Kakad\\Documents\\webb\\src\\main\\java\\users.txt";
    private final int USERS_ATTRIBUTES = 6;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String phoneNum = req.getParameter("phone_num");
            String tc = req.getParameter("tc");
            String password = req.getParameter("password");
            String birthDay = req.getParameter("birth_day");

            if (!(name.equals("") ||surname.equals("") || email.equals("") || phoneNum.equals("")
                    || tc.equals("")|| password.equals("")|| birthDay.equals(""))){
                if ( phoneNum.matches("\\+\\d{11}")){
                    resp.getWriter().write("ok");
                    UserTC user = new UserTC(name,surname,email,phoneNum,Integer.parseInt(tc),password,birthDay);
                    FileOutputHandler fH = new FileOutputHandler("C:\\Users\\Kakad\\Documents\\webb\\src\\main\\java\\users_tc.txt");
                    fH.writeTheUserToTheFile(user);
                } else{
                    resp.getWriter().write("wrong num");
                }
            }else {
                resp.getWriter().write("no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Kakad\\Documents\\webb\\src\\main\\webapp\\WEB-INF\\template"));
            cfg.setDefaultEncoding("UTF-8");
            //
            Template temp = cfg.getTemplate("reg2.ftl");
            Map<String, Object> root = Collections.emptyMap();
            temp.process(root, resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}