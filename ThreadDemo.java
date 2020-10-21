package selfLearning;

class MyThread extends Thread
{
//	public void printDocuments()
	@Override
	public void run()
	{
		for (int i = 0; i <10; i++) {
			System.out.println("^^^^MyThread "+i);
		}
	}
}

class YourThread implements Runnable
{
//	public void printDocuments()
	@Override
	public void run()
	{
		for (int i = 0; i <10; i++) {
			System.out.println("@@@@YourThread "+i);
		}
	}
}

public class ThreadDemo {

	public static void main(String[] args) {
		MyThread t = new MyThread();
		Runnable r = new YourThread();
		Thread t1 = new Thread(r);
		System.out.println("=== Main thread Started");

		t.start();
		t1.start();

		for (int i = 0; i <10; i++) {
			System.out.println("Main thread  "+ i);
		}		System.out.println("=== Main thread Finished");

	}
}
