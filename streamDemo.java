package streamFilterinJava8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//https://jagroopbhanot.wordpress.com/2019/04/29/stream-in-java8/
public class streamDemo 
{
	public static void main(String[] args) 
	{
		List<Integer> number = Arrays.asList(2,3,4,5,2,3,3,2);
		
		//print list as it is
		System.out.println(number); 
		
		//filter the list based on specific value. Here I am fecthing a new list based on particular value
		List<Integer> a = number.stream().filter(s-> s.equals(2)).collect(Collectors.toList());
		
		System.out.println(a); // it will print new list with all values as 2 as mentioned in above step
	
		// check count of the number of elements in the list
		long count = number.stream().count();
		System.out.println(count); // it will count and print the total elements in the list number
		
		//print the distinct elements in the list
		List<Integer> c = number.stream().distinct().collect(Collectors.toList());
		System.out.println(c);
		
		//use of map method
		List<Integer> d = number.stream().map(x-> x*x).collect(Collectors.toList());
		System.out.println(d);
		
		//create a list of string
		List<String> s = Arrays.asList("Jagroop","Isha","Aarav","Bhanot", "Sahil", "Pankaj", "Anoop", "Neha","Payal","Golu","Kanu","Gori", "Paru", "Papa", "Mummy");
		System.out.println(s);
		
		//filter the list of string based on specific character like "A"
		List<String> s2 = s.stream().filter(s1-> s1.startsWith("A")).collect(Collectors.toList());
		System.out.println(s2);
		
		//sort the list now
		System.out.println(s.stream().sorted().collect(Collectors.toList()));
		
		//return set from the list number, since set do not allow duplicates so all duplicates will be eliminated
		System.out.println(number.stream().collect(Collectors.toSet()));
		
		// demonstration of forEach method
		number.stream().forEach(y-> System.out.println(y));
		
		System.out.println("Just to differentiate demo of Foreach without set and demo of Foreach with set ");
		//for Each along with set
		number.stream().sorted().collect(Collectors.toSet()).forEach(y -> System.out.println(y));
	
		long countParallelStream = number.parallelStream().count();
		System.out.println("Testing with ParallelStream " + countParallelStream);
		
		//another way of using foreach on the list of string s
		s.forEach(System.out::println);
	}
}
