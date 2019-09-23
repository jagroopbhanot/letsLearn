import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Stream {
	
	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1,1,1,7,12,87,3,7,34,5);
		// demonstration of map method
		List<Integer> newNumbers = numbers.stream().map(x -> x*x).collect(Collectors.toList());
		System.out.println(newNumbers);
		
		List<String> value = Arrays.asList("Jagroop","Anoop","Isha","Aarav","Pari","Kanu","Manisha");
		
		// demonstration of filter method
		List<String> newValue = value.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
		System.out.println(newValue);
		
		// demonstration of sorted method
		List<Integer> newValue1 = numbers.stream().sorted().collect(Collectors.toList());
		System.out.println(newValue1);
		
		// collect method returns a set 
	    Set<Integer> squareSet = 
	         numbers.stream().map(x->x).collect(Collectors.toSet()); 
	    System.out.println("duplicate removed:"+squareSet); 
	    
	 // demonstration of forEach method 
	    numbers.stream().map(x -> x+x).forEach(y -> System.out.println(y));
	}

}
