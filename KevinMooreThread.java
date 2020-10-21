package selfLearning;

public class KevinMooreThread extends Thread{
	
	public KevinMooreThread (String s) { 
	    super(s); 
	  }
	static Integer count =0;
	int fixed = 6;
	
	@Override
	public void run() {

		for(int j = 0; j<3; j++)
		{
			System.out.println(Thread.currentThread().getName() + " " +calcualte(fixed));
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<5; i++)
		{
			KevinMooreThread t = new KevinMooreThread("Thread "+i);
			t.start();
		}
		
	}
	
	private synchronized int calcualte(int x)
	{
	//	fixed = x + ++count;
	//	count =0;
		//fixed= count + ++x;
		return x + ++count;
		/*
		 * 7
		 * 8
		 * 21
		 * */
	}
}
