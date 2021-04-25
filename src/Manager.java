import java.util.LinkedHashMap;

public class Manager extends Person {

	public Manager(String fName, String lName, String address, String emailAddress, String username,
			String password) {//Needed info for Manager
		super(fName, lName, address, emailAddress, "Manager", username, password);
	}
	
	public void addCustomer(DbConnections base, String dbName, String fName, String lName, String address, String emailAddress, String position, String username,
			String password) {//Same function used in Employee to Add a customer to the database by creating the nessasary items for Customer.java
		
		Customer cust = makeCustomer(fName, lName, address, emailAddress, username, password);
		
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" , cust.getID() + "");
		toInsert.put("Person" , cust.encode());
		
		base.insertCommand(base.getConnection(), DbConnections.generateInsertCommand(toInsert, dbName));
	}
	
	private Customer makeCustomer(String fName, String lName, String address, String emailAddress, String username, String password) {//Same function used in Employee to Creates a customer based off needed information
		Customer cust = new Customer(fName, lName, address, emailAddress, username, password);
		return cust;
	}
	
	public void removeCustomer(DbConnections base, String dbName, Customer cust) {//Same function used in Employee to remove a customer from the database
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + cust.getID()));
	}
	
	public void updateCustomer(DbConnections base, String dbName, Customer cust, String toSet, String value) {//Sane function used in Employee to update a customer's info within the database
		base.updateCommand(base.getConnection(), DbConnections.generateUpdateCommand(dbName, toSet, value), dbName);
	}
	
	public void addEmployee(DbConnections base, String dbName, String fName, String lName, String address, String emailAddress, String position, String username,
			String password) {//Adds Employee to database based on needed information
		
		Employee emp = new Employee(fName, lName, address, emailAddress, username, password);
		
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" , emp.getID() + "");
		toInsert.put("Person" , emp.encode());
		
		base.insertCommand(base.getConnection(), DbConnections.generateInsertCommand(toInsert, dbName));
	}
	
	private Employee makeEmployee(String fName, String lName, String address, String emailAddress, String username, String password) {//Creates a Employee based on needed information
		Employee emp = new Employee(fName, lName, address, emailAddress, username, password);
		return emp;
	}
	
	public void removeEmployee(DbConnections base, String dbName, Employee emp) {//Removes a Employee from the database
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + emp.getID()));
	}
	
	public void updateEmployee(DbConnections base, String dbName, Employee emp, String toSet, String value) {//Used to update a Employee in the database
		base.updateCommand(base.getConnection(), DbConnections.generateUpdateCommand(dbName, toSet, value), dbName);
	}
	
	public void removePendingOrder(DbConnections base, String dbName, String orderID) {//Used to remove a pending order from the database
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + orderID));
	}
	

	//For bellow you should import Inventory.java and use some of its functions to help in creating these
	
	
	//makeitem, should use createNewItem from Inventory.java
	//additem, should use increase quantity from Inventory.java
	//removeitem, hopefully a removeItem will be added to Inventory.java for you to implement
	//updateitem, would use some of the commands from Inventory.java

}
