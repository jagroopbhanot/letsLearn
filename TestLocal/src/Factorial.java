import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Factorial {

    public static void main(String[] args) {
        //learn_reduce();
        learn_flatmap();
    }

    public static void learn_reduce()
    {
        List<Integer> mylist = Arrays.asList(1,2,3,4,5,6,7);
       int result= mylist.parallelStream().reduce(0, (a,b)-> a+b);
       System.out.println(result);
    }
    public static void learn_flatmap()
    {
        List<String> mylist1 = Arrays.asList("1","2","3","4");
        List<String> mylist2 = Arrays.asList("11","22","3","4");
        List<List<String>> myResult = new ArrayList<>();
        myResult.add(mylist1);
        myResult.add(mylist2);

        List<String> newList =myResult.stream().flatMap(a-> a.stream().map(b-> b)).collect(Collectors.toList());
        System.out.println(newList);
    }
}
