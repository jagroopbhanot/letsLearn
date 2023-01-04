package collections.ListToMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConnverListToMap {
    public static void main(String[] args) {
        List<Person> mylist = new ArrayList<>();
        mylist.add(new Person(1,"J", 37));
        mylist.add(new Person(2,"A", 36));
        mylist.add(new Person(3,"g", 57));
        mylist.add(new Person(4,"r", 38));
        mylist.add(new Person(5,"p", 32));

       Map<Integer, String> maplist = mylist
               .stream()
               .collect(Collectors.toMap(Person::getId, Person::getName));

        System.out.println(maplist);
    }
}
