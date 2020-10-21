package selfLearning;

import java.util.*;
import java.util.regex.*;


// Write your code here. DO NOT use an access modifier in your class declaration.
class Parser
{
     private static final Map<Character, Character> brackets = new HashMap<Character,Character>();
    static {
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('(', ')');
    }  

    boolean isBalanced(String str)
    {
    	final Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++)
        {
	        if (brackets.containsKey(str.charAt(i))) 
	        {
	        	stack.push(str.charAt(i));
	        } 
	        else if (stack.empty() || (str.charAt(i) != brackets.get(stack.pop()))) 
	        {
	        	return false;
	        } 
        }
        	return stack.empty();
        }
}
class JavaStack {
	
	public static void main(String[] args) {
		Parser parser = new Parser();
        
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			System.out.println(parser.isBalanced(in.next()));
		}

        
		in.close();
	}
}
