import java.util.Comparator;
import java.util.PriorityQueue;

public class Queueing {
    public static void main(String[] args) {
        Student[] sL = {
                new Student("Vasya", 1, 18), new Student(),
                new Student("Oleg", 3), new Student("Lena", 2),
                new Student("Shreka", 4, 21),
                new Student("Aleg", 4, 14),
        };
        PriorityQueue<Student> ageQueue = new PriorityQueue<>(6, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        });
        PriorityQueue<Student> courseQueue = new PriorityQueue<>(6, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getCourse(),o2.getCourse());
            }
        });
        PriorityQueue<Student> nameQueue = new PriorityQueue<>(6, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getName().length()!= o2.getName().length()){
                    return Integer.compare(o2.getName().length(),o1.getName().length());
                } else {
                    for (int i = 0; i < o1.getName().length(); i++) {
                        if (o1.getName().charAt(i)> o2.getName().charAt(i)){
                            return -1;
                        } else if (o1.getName().charAt(i)< o2.getName().charAt(i)){
                            return 1;
                        }
                    }
                    return 0;
                }
            }
        });
        for (Student s: sL){
            ageQueue.add(s);
            courseQueue.add(s);
            nameQueue.add(s);
        }

        System.out.println("age queue");
        for (int i = 0; i < sL.length; i++) {
            System.out.println(ageQueue.poll());
        }
        System.out.println("course queue");
        for (int i = 0; i < sL.length; i++) {
            System.out.println(courseQueue.poll());
        }
        System.out.println("name queue");
        for (int i = 0; i < sL.length; i++) {
            System.out.println(nameQueue.poll());
        }
    }
}
