package comparator;
import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getName().length() < o2.getName().length())
            return -1;
        else if(o1.getName().length() > o2.getName().length())
            return 1;
        else
            return 0;
    }
}
