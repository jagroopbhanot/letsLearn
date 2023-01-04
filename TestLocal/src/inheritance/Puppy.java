package inheritance;
public class Puppy extends Dog {
    Puppy()
    {
        System.out.println("Puppy Constructor");
    }

    public void run()
    {
        super.run();
        System.out.println("Puppy is running{}...");
    }

}
