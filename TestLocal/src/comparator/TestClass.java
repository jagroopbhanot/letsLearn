package comparator;
import comparable.Laptop;

import java.util.ArrayList;
import java.util.Collections;

public class TestClass {

    public static void main(String[] args) {
        Student l1 = new Student("Jagroop",30,16,1);
        Student l2 = new Student("Anoop",28,20,2);
        Student l3 =new Student("Isha",29,18,3);
        Student l4 = new Student("Manisha",27,17,4);

        ArrayList<Student> mylist = new ArrayList<>();
        mylist.add(l1);
        mylist.add(l2);
        mylist.add(l3);
        mylist.add(l4);

        Collections.sort(mylist, new AgeComparator());
        System.out.println("============After comparing by Age ===========");
        mylist.stream().forEach(System.out::println);
        Collections.sort(mylist, new NameComparator());
        System.out.println("============After comparing by Name  ===========");
        mylist.stream().forEach(System.out::println);
        Collections.sort(mylist, new rollNumberComparator());
        System.out.println("============After comparing by Rollnumber ===========");
        mylist.stream().forEach(System.out::println);
    }
}
