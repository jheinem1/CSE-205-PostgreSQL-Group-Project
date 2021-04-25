import java.text.NumberFormat;

public class Item
{
    protected String name;
    protected double price;
    protected int quantity;

   
    //  Create a new item with the given attributes.
    public Item (String itemName, double itemPrice, int numPurchased)
    {
      name = itemName;
      price = itemPrice;
      quantity = numPurchased;
    }

    //   Return a string with the information about the item
    public String toString ()
    {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      return (name + ":\t" + fmt.format(price) + ", " + quantity);
    }
    
    public void add() {
    	this.quantity++;
    }
    
    public void subtract(int val) {
    	this.quantity = this.quantity-val;
    } 

    //   Returns the unit price of the item
    public double getPrice()
    {
      return price;
    }

    //   Returns the name of the item
    public String getName()
    {
      return name;
    }

    //   Returns the quantity of the item
    public int getQuantity()
    {
      return quantity;
    }
} 