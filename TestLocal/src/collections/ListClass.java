package collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ListClass {
    //ArrayList
    //LinkedList
    //Vector

    public static void main(String[] args) {
        //use_of_ArrayList();
        use_of_LinkedList();
    }

    private static void use_of_vector() {

    }

    private static void use_of_ArrayList()
    {
        List<String> names = Arrays.asList("JAG","ROOP","BHA","NOT","ROH","ANO","AAR","ISH","MAN","PAR","KAN");

        //print the list
        names.stream().forEach(System.out::println);
    }

    private static void use_of_LinkedList()
    {
        LinkedList<Integer> phoneNumbers = new LinkedList<>();
        phoneNumbers.add(0, 111);
        phoneNumbers.add(1, 333);
        phoneNumbers.add(2,555);
        phoneNumbers.add(3,222);
        phoneNumbers.add(4,444);

      //  phoneNumbers.stream().forEach(System.out::println);


      //  phoneNumbers.stream().forEach(System.out::println);
        phoneNumbers.addFirst(666);
        phoneNumbers.remove(1);
        phoneNumbers.stream().forEach(System.out::println);

    }
}
