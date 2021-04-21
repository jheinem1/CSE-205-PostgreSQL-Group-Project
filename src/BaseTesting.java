import java.sql.*;
import java.util.LinkedHashMap;

public class BaseTesting {

	public static void main(String[] args) {
		
		DbConnections base = new DbConnections();
		
		Connection connection = base.getConnection();
		
		//base.createTable(connection, DbConnections.generateCreateCommand("test3", "ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL"));
		
		
		LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" , "7");
		toInsert.put("NAME" , "Juan");
		base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "test3"));
		
		base.selectAllCommandForTest(connection, "test3");
		
	
		base.updateCommand(connection, DbConnections.generateUpdateCommand("test3", "NAME", "'Pablo'", "NAME = 'Juan'"), "test3");
		
		base.selectAllCommandForTest(connection, "test3");
		
		base.deleteCommand(connection, DbConnections.generateDeleteCommand("test3", "ID = 7"));
		
		base.selectAllCommandForTest(connection, "test3");
		
		base.close();
		
	

	}

}
