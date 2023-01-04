package inheritance;
public class Dog extends Animal{
    Dog()
    {
        System.out.println("Dog Constructor");
    }
    public void run()
    {
        super.run();
        System.out.println("Dog is running {}...");
    }
}
