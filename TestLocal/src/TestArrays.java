import aop.logging.TrackElapsedTime;
import comparator.Student;
import entity.Laptop;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestArrays {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // useOfGroupingUsingStreamApi();
       // Get_count_min_max_sum_average_for_numbers();
       // useOf_reduce();
        //sample();
        //flatMap();
       // flatMap_CompletableFuture();
      //  get_second_higherst_number();
      //  search_for_name();
       // function();
      //  MaxAgefromList();
      //  play_with_groupingby();
      //  Hashmap();
      //  treemap();
     //   linkedHashmap();
      //  numberdivisibleby3Or5();
        use_of_predicate();
    }

    private static void use_of_predicate()
    {
        Predicate<Integer> noGreaterThanFive = x-> x>5;
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        integerList.stream().filter(noGreaterThanFive).forEach(System.out::println);
    }
    private static void numberdivisibleby3Or5()
    {
        IntStream.rangeClosed(1,1000).filter(a-> a%3==0 || a%5==0).forEach(System.out::println);
    }
    private static void linkedHashmap()
    {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("3" , "aaa");
        linkedHashMap.put(null, "bbbb");
        linkedHashMap.put(null,"zzz");
        linkedHashMap.put("2", "cccc");
        linkedHashMap.put("1", "dddd");
        System.out.println(linkedHashMap);
    }
    private static  void treemap()
    {
        TreeMap treeMap = new TreeMap();
      //  treeMap.put(null, "1");
        treeMap.put("1", "j");
        treeMap.put("3", "k");
        treeMap.put("2", "8");
        System.out.println(treeMap);
    }
    private static void Hashmap()
    {
        HashMap hashMap = new HashMap();
        hashMap.put(null, "1");
        hashMap.put(null, "2");
        hashMap.put("1", "Jagroop");
        hashMap.put("2", "Isha");
        hashMap.put("3", "Aarav");
        hashMap.put("3", "Aarav bhanot");

        System.out.print(hashMap);
    }
    private static void play_with_groupingby()
    {
        List<Student> myStudents = Arrays.asList(
                new Student("A", 23,1,1),
                new Student("B",24,1,2),
                new Student("C",25,1,3),
                new Student("D",21,2,4),
                new Student("E",19,2,5));

        Comparator<Student> compareByAge = Comparator.comparing(Student::getAge);
        Map<Integer, Optional<Student>> groupByResult = myStudents.stream().collect(Collectors.groupingBy(
                Student::getDept,
                Collectors.reducing(BinaryOperator.maxBy(compareByAge))));

        System.out.println(groupByResult);

        List<Integer> myInteger = Arrays.asList(1,2,3,4,5,6,11,34,11,66,88,33,2,5,1);
      System.out.println("Second highest element in the list is :" + myInteger.stream()
              .sorted(Collections.reverseOrder())
              .skip(1)
              .distinct()
              .findFirst()
              .get());

      IntSummaryStatistics summaryStatistics = myInteger.stream().mapToInt(a-> a).summaryStatistics();
      System.out.println("max is ::" + summaryStatistics.getMax() + "count is ::" + summaryStatistics.getCount() + " Avg is ::"+ summaryStatistics.getAverage()
              + "min is ::"+summaryStatistics.getMin() + "Sum is :: "+summaryStatistics.getSum());

        Map<Integer, Long> groupByExample = myInteger.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
        System.out.println("groupByExample::" + groupByExample);
    }
    private static void MaxAgefromList()
    {
        //second highest number in the list
        List<Integer> agelist = Arrays.asList(3,5,7,12,78,34,67,14,9);

        System.out.println(agelist.stream().sorted(Collections.reverseOrder()).skip(7).findFirst().get());

    // find max salary from the object
        List<Student> studentList = Stream.of(
                new Student("J",12,12,1),
                new Student("A",34,12,2),
                new Student("K",33,12,3),
                new Student("K1",31,13,4),
                new Student("K2",32,13,5),
                new Student("K3",33,13,6)
        ).collect(Collectors.toList());

        Comparator<Student> compareByAge = Comparator.comparing(Student::getAge);

        Map<Integer, Optional<Student>> collect = studentList.stream()
                .collect(Collectors.groupingBy(
                        Student::getDept,
                        Collectors.reducing(BinaryOperator.maxBy(compareByAge))));


        System.out.println(collect);

    }



    private static void function()
    {
        Function<Integer, String> function = (t) -> String.valueOf(t+10);
        System.out.println(function.apply(88));
    }
    private static void search_for_name() {
        List<String> users = Arrays.asList("Jagroop", "Raj", "Ram", "Ravan", "jagroop");

        users.stream().filter(a -> a.equalsIgnoreCase("jagroop")).forEach(System.out::println);

    }


    private static void sample()
    {
       // ConvertStringtouppercaseandJointhemwithcoma
        List<Integer> myList = Arrays.asList(1,2,3,4,5,6,7,8,9);

       System.out.println(myList.stream().count());

       LongSummaryStatistics myStats = myList.stream().mapToLong(a -> a).summaryStatistics();
       System.out.println("Sum is -> " + myStats.getSum() + " Avg is -> " + myStats.getAverage() + " Min is -> "+ myStats.getMin() + " Max is -> "+ myStats.getMax() + " Count is -> "+ myStats.getCount() );

    }
    @TrackElapsedTime
    private static void useOfGroupingUsingStreamApi()
    {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);

        System.out.println("------------------------");
        List<Student> data = new ArrayList<Student>();
        data.add(new Student("d1", 12,12,1));
        data.add(new Student("d2", 13,14,2));
        data.add(new Student("d1", 14,15,3));
        data.add(new Student("d3", 13,16,4));
        data.add(new Student("d3", 15,14,5));
        data.add(new Student("d3", 17,12,6));
        final Map<String, List<Integer>> optionsByName = data.stream().collect(
                Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getAge, Collectors.toList())));
        System.out.println(optionsByName);

        Comparator<Student> compareByAge = Comparator.comparing(Student::getAge);

        Map<String, Optional<Student>> stringOptionalMap = data.stream().collect(Collectors.groupingBy(
                Student::getName,
                Collectors.reducing(BinaryOperator.maxBy(compareByAge))));

        System.out.println("==============");
        stringOptionalMap.entrySet().forEach(a -> System.out.println("key : " + a.getKey() + " " + " value : "+ a.getValue().get()));
    }
    private static void testArraysIncrement(){

        int arrays []= {3,5,78,12,55,77,11,25,23,7,3,82,78};

        System.out.println("array = " + Arrays.toString(arrays));
        Arrays.parallelSetAll(arrays, x -> arrays[x] + 1);
        System.out.println("array = " + Arrays.toString(arrays));

        //printList();
        //removeEmptyStringFromList();
        //countElementsInList();
        //doubleEachElementInList();
        //CountStringwhoselengthismorethanthree();
        //CountingEmptyString();
        //ConvertStringtouppercaseandJointhemwithcoma();
        //Get_count_min_max_sum_average_for_numbers();
        //flatMap();
       // useOf_reduce();

    }

    private static void useOf_reduce()
    {
        List<Integer> myList = Arrays.asList(1,2,3,4);
        int additionResult = myList.stream().reduce(1, Integer:: sum);
        System.out.println("All elements are added using stream --> "+ additionResult +  " " + Thread.currentThread().getName());

        int additionResult1 = myList.parallelStream().reduce(5, Integer:: sum);
        System.out.println("All elements are added using parallel stream --> "+ additionResult1 + " " + Thread.currentThread().getName());

        int multiplyResult = myList.stream().reduce(1, (subtotal, element) -> subtotal*element);
      //  System.out.println("All elements are mutliplied -->" + multiplyResult);

        List<String> myList1 = Arrays.asList("J","A","G","R","O","O","P");
        String result = myList1.stream().reduce("",(subtotal,element) -> subtotal.concat(element));
        System.out.println(" Hello = " + result);
//        myList.stream().forEach(a->  System.out.println(a + " " + Thread.currentThread().getName()));
//
//        myList.parallelStream().forEach(a->  System.out.println(a + " " + Thread.currentThread().getName()));


        //  System.out.println("Concatenate the string -->" + result);
        

    }

    private static void flatMap_CompletableFuture() throws ExecutionException, InterruptedException {
        List<Student> myList = new ArrayList<>();
        myList.add(new Student("J",1,1,1));
        myList.add(new Student("A",2,2,2));
        myList.add(new Student("G",3,3,3));
        myList.add(new Student("R",4,4,4));

        List<Student> myList1 = new ArrayList<>();
        myList1.add(new Student("O",5,5,5));
        myList1.add(new Student("O",6,6,6));
        myList1.add(new Student("P",7,7,7));

        List<List<Student>> finalList = new ArrayList<>();
        finalList.add(myList);
        finalList.add(myList1);

        List<Student> flat = finalList.stream().flatMap(a-> a.stream()).collect(Collectors.toList());
        flat.forEach(System.out::println);

        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(myList.toString());
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(myList1.toString());

        BiFunction<String, String, String> stringStringBiFunction =
                (res1, res2) -> res1.concat(res2);

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, stringStringBiFunction);

        String a = cf1.get();
        String b = cf2.get();
        CompletableFuture.allOf(cf1, cf2).join();
       // System.out.println("Output of thenCombine - " + cf3.join());
    }
    private static void flatMap()
    {
        List<String> myList = Arrays.asList("1233","","23455","333","43333", "JAG");
        List<String> myList1 = Arrays.asList("JAG","","REG","OOP","NOT");
        List<List<String>> allproducts = new ArrayList<List<String>>();
        allproducts.add(myList);
        allproducts.add(myList1);

            List<String> newList = allproducts.stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        System.out.println(newList);
    }
    private static void printList()
    {
        List<String> myList = Arrays.asList("1233","","23455","333","43333");
        myList.forEach(a-> System.out.println(a));
        //another way to print list
        myList.forEach(System.out::println);
    }

    private static void removeEmptyStringFromList()
    {
        List<String> myList = Arrays.asList("1","","2","3","4");
        List<String> mynewList = myList.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
        System.out.println("Removing empty string from the list");
        mynewList.forEach(a -> System.out.println(a));
    }

    private static void countElementsInList()
    {
        List<String> myList = Arrays.asList("1","","2","3","4");
        System.out.println("count in List => " + myList.stream().count());
    }

    private static void doubleEachElementInList()
    {
        List<Integer> myList = Arrays.asList(1,2,34,5,6,7);
        List<Integer> myNewList = myList.stream().map(a-> a*a).collect(Collectors.toList());
        System.out.println("Before Doubling each element of list");
        myNewList.forEach(System.out::println);
    }

    private static void CountStringwhoselengthismorethanthree()
    {
        List<String> myList = Arrays.asList("1233","","23455","333","43333");
        System.out.println("Count String whose length is more than three ==>"+myList.stream().filter(a-> a.length()>3).count());
    }

    private static void CountingEmptyString()
    {
        List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
        System.out.println("Counting Empty String ==>"+strList.stream().filter(a-> a.isEmpty()).count());
    }

    private static void ConvertStringtouppercaseandJointhemwithcoma()
    {
        List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
        System.out.println("Convert String to uppercase and Join them with coma ==>"+strList.stream().filter(a-> !a.isEmpty()).map(a-> a.toUpperCase()).collect(Collectors.joining(",")));
    }

    private static void Get_count_min_max_sum_average_for_numbers()
    {
        List<Integer> myList = Arrays.asList(1,2,34,5,6,7);
        List<Student> st = Stream.of(
                new Student("Jagroop", 37, 121,1),
                new Student("Aarav",10,1 ,2),
                new Student("Isha", 37,12,3))
                .collect(Collectors.toList());

        DoubleSummaryStatistics mySatistics = st.stream().mapToDouble(a->a.getAge()).summaryStatistics();

        System.out.println("Get Max Number => " + mySatistics.getMax());
        System.out.println("Get Avg Number => " + mySatistics.getAverage());
        System.out.println("Get Total Count of Numbers => " + mySatistics.getCount());
        System.out.println("Get Min Number => " + mySatistics.getMin());
        System.out.println("Get Sum of all Number => " + mySatistics.getSum());
    }

    private static void get_second_higherst_number()
    {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(12,44,76,1,8,9,9));
        System.out.println(arr.stream().sorted(Collections.reverseOrder())
                .distinct()
                .skip(5)
                .findFirst().get());


    }
}
