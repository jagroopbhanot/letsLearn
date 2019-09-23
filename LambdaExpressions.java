interface FuncInterface 
{ 
    // An abstract function 
    void abstractFun(int x, int y); 
  
    // A non-abstract (or default) function 
    default void normalFun() 
    { 
       System.out.println("Hello"); 
    } 
} 
public class LambdaExpressions 
{
	public static void main(String[] args) 
	{
		//1. Way to invoke functional interface by passing arguments
		FuncInterface fobj = (int x, int y)->System.out.println(x*y);
		
		 // This calls above lambda expression and prints 10. 
        fobj.abstractFun(5,7); 
        
        
        
	}
}
