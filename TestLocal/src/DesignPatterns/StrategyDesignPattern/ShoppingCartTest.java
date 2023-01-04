package DesignPatterns.StrategyDesignPattern;
public class ShoppingCartTest {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new PaypalStrategy("myemail@example.com", "mypwd"));

        Item item1 = new Item("1234",10);
        Item item2 = new Item("5678",40);

        cart.addItem(item1);
        cart.addItem(item2);

        //pay by paypal
        cart.pay();
        ShoppingCart cart1 = new ShoppingCart(new CreditCardStrategy("Aarav", "456123", "456", "01-03-2022"));

        Item item11 = new Item("1234",10);
        Item item21 = new Item("5678",40);
        Item item3 = new Item("9012",40);
        cart1.addItem(item11);
        cart1.addItem(item21);
        cart1.addItem(item3);
        //pay by credit card
        cart1.pay();
    }

}
