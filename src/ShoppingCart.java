import java.util.ArrayList;

public class ShoppingCart extends ItemHolder
{
//	private ArrayList<Item> items = new ArrayList<Item>();
//
//	public ShoppingCart () {
//
//	}
//
//	public void addToCart(Item i) {
//		items.add(i);
//	}
//
//	public void removeFromCart(Item i) {
//		items.remove(i);
//	}
//
//	public String toString() {
//
//		String s = "";
//		s += "Current Cart:\n";
//
//		for (Item item : this.items) {
//			s += '\t' + item.toString();
//		}
//
//		return s;
//	}
//
//	public void printCart() {
//
//		System.out.println(this.toString());
//	}
//
//    public String encode() {
//		throw new UnsupportedOperationException("Method not implemented");
//    }
    public double getTotalCost() {
        var cost = 0;
        for (var item : getValues())
            cost += item.getPrice();
        return cost;
    }

    public String encode() {
        return Serializer.serialize(getValues());
    }
}
