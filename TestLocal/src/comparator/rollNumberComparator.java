package comparator;
import java.util.Comparator;

public class rollNumberComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getRollNumber() > o2.getRollNumber())
            return 1;
        else if(o1.getRollNumber() < o2.getRollNumber())
            return  -1;
        else
            return 0;
    }
}
