package DBClasses;

import java.util.List;

public class UserTC implements CreatableFromList, WritableToDB {
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNum;
    private final int tc;
    private final String password;
    private final String birthDay;
    public UserTC(List<String> list){
        name = list.get(0);
        surname = list.get(1);
        email = list.get(2);
        phoneNum = list.get(3);
        tc = Integer.parseInt(list.get(4));
        password = list.get(5);
        birthDay = list.get(6);
    }
    public UserTC(String _name, String _surname, String _email, String _phoneNum, int _tc,
                  String _password, String _birthDay){
        name = _name;
        surname = _surname;
        email = _email;
        phoneNum = _phoneNum;
        tc = _tc;
        password = _password;
        birthDay = _birthDay;
    }

    public int getTc() {
        return tc;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDay() {
        return birthDay;
    }

    @Override
    public String toString() {
        return "UserTC{ "  + name + "; " +
                  surname +  "; "
                + email + "; "
                + phoneNum + "; "
                 + tc + "; "
                 + password  + "; "
                 + birthDay  +
                '}';
    }

    @Override
    public String getDBRepresentation() {
        return "UserTC{ "  + name + "; " +
                surname +  "; "
                + email + "; "
                + phoneNum + "; "
                + tc + "; "
                + password  + "; "
                + birthDay  +
                '}';
    }
}
