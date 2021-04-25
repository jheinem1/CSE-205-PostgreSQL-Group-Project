import java.util.LinkedHashMap;

public class Employee extends Person {

	public Employee(String fName, String lName, String address, String emailAddress, String username,
			String password) {
		super(fName, lName, address, emailAddress, "Employee", username, password);
	}
	
	public void addCustomer(DbConnections base, String dbName, String fName, String lName, String address, String emailAddress, String position, String username,
			String password) {
		
		Customer cust = makeCustomer(fName, lName, address, emailAddress, username, password);
		
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" , cust.getID() + "");
		toInsert.put("Person" , cust.encode());
		
		base.insertCommand(base.getConnection(), DbConnections.generateInsertCommand(toInsert, dbName));
	}
	
	private Customer makeCustomer(String fName, String lName, String address, String emailAddress, String username, String password) {
		Customer cust = new Customer(fName, lName, address, emailAddress, username, password);
		return cust;
	}
	
	public void removeCustomer(DbConnections base, String dbName, Customer cust) {
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + cust.getID()));
	}
	
	public void updateCustomer(DbConnections base, String dbName, Customer cust, String toSet, String value) {
		base.updateCommand(base.getConnection(), DbConnections.generateUpdateCommand(dbName, toSet, value), dbName);
	}
	
	
	public void removePendingOrder(DbConnections base, String dbName, String orderID) {
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + orderID));
	}

}
