public class Student {
    private int age;
    private int course;
    private String name;
    {
        name = "Steve";
        age = 13;
        course = 1;
    }
    public Student(){
    }
    public Student(String _name){
        name = _name;
    }
    public Student(String _name,int _course){
        name = _name;
        course = _course;
    }
    public Student(String _name,int _course,int _age){
        name = _name;
        course = _course;
        age = _age;
    }

    public int getAge() {
        return age;
    }

    public int getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", course=" + course +
                ", name='" + name + '\'' +
                '}';
    }
}
