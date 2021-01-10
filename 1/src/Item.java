public class Item {
    public String title;
    public double price;
    public int quantity;
    public Type type;
    
    public Item(String title, double price, Type type, int quantity){
        this.title = title;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }
    public Item() {

    }

}