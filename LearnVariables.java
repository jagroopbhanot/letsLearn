package selfLearning;

import java.util.ArrayList;
import java.util.List;

public class LearnVariables {

	int i=10;
	
	
	static int j ;
	public static void main(String[] args) { 
		int k=1;
		LearnVariables l1 = new LearnVariables();
		LearnVariables l2 = new LearnVariables();
	System.out.println("Instance Variable --> "+(l1.i+10));	
	System.out.println("Another Instance Variable -->"+l2.i);
	System.out.println("Static Variable --> "+ LearnVariables.j);
	System.out.println("Local Variable -->"+k);
	
	gInt(l1.i);
	System.out.println(l1.i);
	
	
	List<Integer> slist = new ArrayList<Integer>();
	for (int i = 0; i < 10; i++) {
		slist.add(i);
	}
	slist.parallelStream().forEach(ab -> {
		System.out.println(ab);
		});
	}
	
	public static void gInt(int m)
	{
		m = 20;
		
	}
}
