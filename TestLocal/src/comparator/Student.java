package comparator;
import java.util.Comparator;

public class Student {
    private String name;
    private int age;
    private int dept;
    private int rollNumber;

    public Student(String name, int age, int dept, int rollNumber) {
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                ", rollNumber=" + rollNumber +
                '}';
    }
}
