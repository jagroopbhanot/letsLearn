package selfLearning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JavaComparator {

	public static void main(String[] args) {
		List<String> data = new ArrayList<String>();
		data.add("1");
		data.add("2");
		data.add("3");
		data.add("4");
		data.add("5");
		data.add("6");
		data.add("7");
		data.add("8");
		data.add("9");
		data.add("10");
		
		data.sort(Comparator.reverseOrder());
		data.forEach(System.out::println);
	}
}
