package selfLearning;
import java.util.Stack;

public class StackExample {
	
	public static void main(String[] args) {
		
		Stack<Integer> s = new Stack<Integer>();
		pushData(s);
		popData(s);
		
		//System.out.println(s.pop());
	}
	
	public static void pushData(Stack s)
	{
		System.out.println("Data is pushed to Stack");
		for(int i =1;i<10;i++)
		{
			s.push(i);
			System.out.println(i);
		}
	}
	
	public static void popData(Stack s)
	{
		System.out.println("Data is popped from Stack");

		for(int i=1;i<s.capacity(); i++)
		{
			int k = (int) s.pop();
			System.out.println(k);

		}
	}

}
