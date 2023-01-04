package comparable;

import java.util.ArrayList;
import java.util.Collections;

public class TestClass{

    public static void main(String[] args) {
        Laptop l1 = new Laptop("Dell",800,16);
        Laptop l2 = new Laptop("HP",1200,32);
        Laptop l3 =new Laptop("Apple",1500,8);
        Laptop l4 = new Laptop("Acer",500,16);

        ArrayList<Laptop> mylist = new ArrayList<>();
        mylist.add(l1);
        mylist.add(l2);
        mylist.add(l3);
        mylist.add(l4);

        Collections.sort(mylist);

        mylist.stream().forEach(System.out::println);
    }
}
