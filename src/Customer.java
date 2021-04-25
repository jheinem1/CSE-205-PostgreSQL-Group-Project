import java.util.LinkedHashMap;

public class Customer extends Person {

	public Customer(String fName, String lName, String address, String emailAddress, String username,
			String password) {
		super(fName, lName, address, emailAddress, "Customer", username, password);
	}
	
	//CONTAINS PLACEHOLDER METHODS FOR SHOPPING CART

	public shoppingCart getShoppingCart(DbConnections base, String databaseName) {
		
		String base.selectStoredCart(base.getConnection(), databaseName, this.getID()).decode();
		
	}
	
	//CONTAINS PLACEHOLDER METHODS FOR SHOPPING CART
	public void storeShoppingCart(DbConnections base, ShoppingCart cart, String databaseName) {

		String encodedCart = cart.encode();
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		String SID = this.getID() + "";
		toInsert.put(encodedCart , SID);
		base.insertCommand(base.getConnection(), DbConnections.generateInsertCommand(toInsert, databaseName));
	}
	
	//REMOVE SHOPPING CART METHOD
	public void removeShoppingCart(DbConnections base, String databaseName) {
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(databaseName, "ID = " + this.getID()));
	}

	
}
