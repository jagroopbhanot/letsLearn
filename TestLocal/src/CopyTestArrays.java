import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

public class CopyTestArrays {

    public static void printList()
    {
        List<String> names = Arrays.asList("J","A","G","R","O","O","P");
        System.out.println("#### printList ####");
        names.stream().forEach(System.out::println);
    }

    public static void removeEmptyStringFromList()
    {
        List<String> names = Arrays.asList("J","","A","","G","","R","O","","O","P");
        System.out.println("#### removeEmptyStringFromList ####");
        names.stream().filter(a-> !a.isEmpty()).forEach(System.out::println);

    }

    public static void countElementsInList()
    {
        List<String> names = Arrays.asList("J","","A","","G","","R","O","","O","P");
        System.out.println("#### countElementsInList ####");

        System.out.println(names.stream().filter(a-> !a.isEmpty()).count());
    }

    public static void doubleEachElementInList()
    {
        List<Integer> names = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        System.out.println("#### doubleEachElementInList ####");

        names.stream().map(a-> a+a).forEach(System.out::println);
    }

    public static void CountStringwhoselengthismorethanthree()
    {
        List<String> names = Arrays.asList("JAGROOP","","ANOOP","","GUNJAN","","ROHIT","Oly","","Om","PUNEET");
        System.out.println("#### CountStringwhoselengthismorethanthree ####");

        System.out.println(names.stream().filter(a-> a.length()>=3).count());
    }

    public static void ConvertStringtolowercaseandJointhemwithcoma()
    {
        List<String> names = Arrays.asList("J","A","G","R","O","O","P");
        System.out.println("#### ConvertStringtolowercaseandJointhemwithcoma ####");

        System.out.println(names.stream().map(a-> a.toLowerCase()).collect(Collectors.joining(",")));
    }

    public static void Get_count_min_max_sum_average_for_numbers()
    {
        List<Integer> myList = Arrays.asList(1,2,34,5,6,7);
        LongSummaryStatistics mySatistics = myList.stream().mapToLong(a->a).summaryStatistics();
        System.out.println("#### Get_count_min_max_sum_average_for_numbers ####");

        System.out.println("Get Max Number => " + mySatistics.getMax());
        System.out.println("Get Avg Number => " + mySatistics.getAverage());
        System.out.println("Get Total Count of Numbers => " + mySatistics.getCount());
        System.out.println("Get Min Number => " + mySatistics.getMin());
        System.out.println("Get Sum of all Number => " + mySatistics.getSum());
    }
    public static void main(String[] args) {
        printList();
        removeEmptyStringFromList();
        countElementsInList();
        doubleEachElementInList();
        CountStringwhoselengthismorethanthree();
        //CountingEmptyString();
        ConvertStringtolowercaseandJointhemwithcoma();
        Get_count_min_max_sum_average_for_numbers();
        //flatMap();
        //useOf_reduce();
    }
}
