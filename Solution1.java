package selfLearning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



class Result {

    /*
     * Complete the 'longestSubarray' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

	/*
	 * public static int longestSubarray(List<Integer> arr) { // Write your code
	 * here
	 * 
	 * }
	 */

}

public class Solution1 {
    public static void main(String[] args) throws IOException 
    {
		/*
		 * int[] arr = new int[] {0,1,2,1,2,3}; for (int i : arr) { //
		 * System.out.println(arr[i]); } int[] b = Arrays.copyOfRange(arr, 0, 4); for
		 * (int i : b) { System.out.println(b[i]); }
		 */
       //System.out.println(arr.length);
    	
    	findSubstring("jagroop",5);
    }
    
    public static void findSubstring(String s, int k) {

    	String str;
    	int c1=0,c2;
    	List<String> subList = new ArrayList<String>();
    	Map<Integer, String> newsubList = new HashMap<Integer, String>();
    	for(int i=0; i<s.length(); i++)
    	{
    		if((i+k)<=s.length())
    		{
    			subList.add(s.substring(i,i+k));
    		}
    	}
		
		  for (String string : subList) 
		  { 
			  String s1 = string ,s2,s3 = null ;
			  System.out.println(string); 
			  c1=0;
			  for (int i = 0; i< string.length(); i++) 
			  { 
				 // System.out.println("the value is = "+string.charAt(i));
				  String c = Character.toString(string.charAt(i));
				  if((c.equals("u") ||c.equals("e")||c.equals("i")||c.equals("o")||c.equals("a")))
				  {
					  c1++;
				  }
				 // System.out.println(string.charAt(i));
		  
			  } 
			  //System.out.println("string is = "+string + " and count is ="+c1);
			  newsubList.put(c1, string);

		  }
		  Integer max=newsubList.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

		 System.out.println(max);	  
    }
}
