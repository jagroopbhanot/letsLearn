package DesignPatterns.FactoryDesignPattern;
public class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu){
        if("PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
        else if("Server".equalsIgnoreCase(type)) return new Server1(ram, hdd, cpu);

        return null;
    }
}
