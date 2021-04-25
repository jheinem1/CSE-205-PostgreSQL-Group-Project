import java.util.LinkedHashMap;

public class Customer extends Person {

	public Customer(String fName, String lName, String address, String emailAddress, String username,
			String password) {//Needed Customer info
		super(fName, lName, address, emailAddress, "Customer", username, password);
	}
	
	//CONTAINS PLACEHOLDER METHODS FOR SHOPPING CART

	public ShoppingCart getShoppingCart(DbConnections base, String databaseName) {//Obtains shopping cart from database
//		TODO: Resolve syntax error in below line
//		String base.selectStoredCart(base.getConnection(), databaseName, this.getID()).decode();
		throw new UnsupportedOperationException("Method not implemented yet");
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
	public void removeShoppingCart(DbConnections base, String databaseName) {//Removes item from database via ID
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(databaseName, "ID = " + this.getID()));
	}

	
}
