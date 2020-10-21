package selfLearning;

class Print
{
	synchronized void printDocuments(int numOfCopies, String docName)
	//void printDocuments(int numOfCopies, String docName)
	{
		for (int i = 1; i <= numOfCopies; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(">> Printing "+docName + " "+i );
		}
	}
}

class firstThread extends Thread
{
	Print pref;
	
	firstThread(Print p)
	{
		pref = p;
	}
	@Override
	public void run()
	{
		pref.printDocuments(10, "Jagroop.pdf");
	}
}

class secondThread extends Thread
{
	Print pref;
	
	secondThread(Print p)
	{
		pref = p;
	}
	@Override
	public void run()
	{
			pref.printDocuments(10, "Isha.pdf");
	}
}
public class ThreadSynchronization {

	public static void main(String[] args) {
		System.out.println("==== Application Started");
	
		Print p = new Print();
		firstThread first = new firstThread(p);
		secondThread second = new secondThread(p);
		second.start();

		first.start();
		
		System.out.println("==== Application Finished");

	}
}
