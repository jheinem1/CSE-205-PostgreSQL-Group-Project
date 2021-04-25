package Project;
import java.util.ArrayList;

public class Inventory {
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Inventory() {
		init();
	}
	
	public void init() {
		items.add(new Item("Bread", 3.99, 100));
		items.add(new Item("Milk", 2.99,100));
		items.add(new Item("Eggs", 4.49, 200));
		items.add(new Item("Juice", 3.49, 50));
		items.add(new Item("Cereal", 4.99, 150));
	}
	
	public void increaseQuantity(String name) {
		
		for (Item item : this.items) {
			
			if (name.equals(item.getName())) {
				item.add();
			}
		}
		
		return;
	}
	
	public void decreaseQuantity(String name, int val) {
		
		for (Item item : this.items) {
			
			if (name.equals(item.getName())) {
				if (item.getQuantity() >= 0) {
					
					item.subtract(val);
				}
			}
		}
		
		return;
	}
	
	public void createNewItem(String name, int price, int quantity) {
		
		items.add(new Item(name, price, quantity));
	}
	
	//Should add a removeItem so as to help in thing like Manager.java
	
	public String toString() {
		
		String s = "";
		s += "Current Inventory:\n";
		
		for (Item item : this.items) {
			s += '\t' + item.toString();
		}
		
		return s;
	}
	
	public void printInventory() {
		
		System.out.println(this.toString());
	}
	
	public Item addToCart(String name, int quantity) {
		decreaseQuantity(name, quantity);
		for (Item item : this.items) {
			
			if (name.equals(item.getName())) {
				if (item.getQuantity() >= 0) {
					Item temp = new Item(name, item.getPrice(), quantity);
					return temp;
				}
			}
		}
		return null;
	}
	
}
