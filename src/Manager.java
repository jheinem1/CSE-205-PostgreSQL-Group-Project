import java.util.LinkedHashMap;

public class Manager extends Person {

	public Manager(String fName, String lName, String address, String emailAddress, String username,
			String password) {
		super(fName, lName, address, emailAddress, "Manager", username, password);
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
	
	public void addEmployee(DbConnections base, String dbName, String fName, String lName, String address, String emailAddress, String position, String username,
			String password) {
		
		Employee emp = new Employee(fName, lName, address, emailAddress, username, password);
		
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" , emp.getID() + "");
		toInsert.put("Person" , emp.encode());
		
		base.insertCommand(base.getConnection(), DbConnections.generateInsertCommand(toInsert, dbName));
	}
	
	private Employee makeEmployee(String fName, String lName, String address, String emailAddress, String username, String password) {
		Employee emp = new Employee(fName, lName, address, emailAddress, username, password);
		return emp;
	}
	
	public void removeEmployee(DbConnections base, String dbName, Employee emp) {
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + emp.getID()));
	}
	
	public void updateEmployee(DbConnections base, String dbName, Employee emp, String toSet, String value) {
		base.updateCommand(base.getConnection(), DbConnections.generateUpdateCommand(dbName, toSet, value), dbName);
	}
	
	public void removePendingOrder(DbConnections base, String dbName, String orderID) {
		base.deleteCommand(base.getConnection(), DbConnections.generateDeleteCommand(dbName, "ID = " + orderID));
	}
	

	
	//makeitem
	//additem
	//removeitem
	//updateitem

}
