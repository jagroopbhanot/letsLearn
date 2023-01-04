package DesignPatterns.StrategyDesignPattern;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {


    //List of items
    List<Item> items;
    PaymentStrategy paymentStrategy;
    public ShoppingCart(PaymentStrategy paymentStrategy){
        this.items=new ArrayList<Item>();
        this.paymentStrategy = paymentStrategy;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public int calculateTotal(){
        int sum = 0;
        for(Item item : items){
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(){
        int amount = calculateTotal();
        paymentStrategy.pay(amount);
    }
}
