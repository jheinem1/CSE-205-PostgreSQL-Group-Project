//Code from lecture by S. Osburn
//Organized and recorded by the MVP H. Spragg
//Modifications and OOP-ification by D. Wallace

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DbConnections {
	private Connection connection;
	
	
    public DbConnections()
    {
        connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/testdb",
                    "postgres",
                    "Password"
            );
            System.out.println("Opened Database Successfully!");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: " + e.getClass().getName() + ": " + e.getMessage());
        }

        // Create Table
        //createTable(connection);

        // INSERT
        //insert(connection,1,"Paul",23,"Alaska",5645.65);
        //insert(connection,2,"Bob",65,"Michigan",12935.65);
        //insert(connection,3,"Jacob",25,"Arizona",6236.65);
        //insert(connection,4,"George",21,"Arizona",736.65);



        // SELECT
        //select(connection);

        // UPDATE
        //update(connection);

        // DELETE
        //delete(connection);
    }

      public void close() {
        try {
            if(this.connection != null)
                this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
      
    public static void createTable(Connection connection, String sqlCommand)
    {
        Statement statement = null;
        try{
            statement = connection.createStatement();
           /* String sqlCommand =
                    "CREATE TABLE company("+
                        "ID INT PRIMARY KEY NOT NULL, "+
                        "NAME TEXT NOT NULL, "+
                        "AGE INT NOT NULL, "+
                        "ADDRESS CHAR(50), "+
                        "SALARY REAL"+
                    ")";*/
            statement.executeUpdate(sqlCommand);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table Created...");
    }

    
    public static void insertCommand(Connection connection, String sqlCommand)
    {
    	
        Statement statement = null;
        try{
        	connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            statement.executeUpdate(sqlCommand);
            statement.close();
            connection.commit();
            System.out.println("Data Inserted...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void selectCommand(Connection connection, String sqlCommand, String[] items)
    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                float salary = resultSet.getFloat("salary");

                System.out.println(String.format("ID = %d\n NAME = %s\n AGE = %d\n ADDRESS = %s\n SALARY = %.2f \n",id,name,age,address,salary));
            }
            resultSet.close();
            statement.close();
            System.out.println("Data Selected...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    
    public static void selectCommand(Connection connection)
    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlCommand = "Select * FROM company;";
            
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                float salary = resultSet.getFloat("salary");

                System.out.println(String.format("ID = %d\n NAME = %s\n AGE = %d\n ADDRESS = %s\n SALARY = %.2f \n",id,name,age,address,salary));
            }
            resultSet.close();
            statement.close();
            System.out.println("Data Selected...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void updateCommand(Connection connection, String sqlCommand, String table)
    {
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            //String sqlCommand = "UPDATE company set SALARY = 30000.00 where ID = 1;";
            statement.executeUpdate(sqlCommand);
            connection.commit();
            statement.close();
            System.out.println("Data Updated...");


            selectCommand(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void deleteCommand(Connection connection, String sqlCommand)
    {
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            // String sqlCommand = "DELETE FROM table WHERE condition;";
            statement.executeUpdate(sqlCommand);
            connection.commit();
            statement.close();
            System.out.println("Data Deleted...");


            selectCommand(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    
    public static String generateDeleteCommand(String from, String where) {
    	return "DELETE FROM " + from + "WHERE" + where + ";";
    }
    
    
    public static String generateSelectCommand(String table) {
    	return "SELECT * FROM " + table + ";";
    }
    
    
    public static String generateCreateCommand(String table, String commaSeperatedValues) {
    	return "CREATE TABLE " + table + "(" + commaSeperatedValues + ");";
    }
    
    //overloaded
    public static String generateUpdateCommand(String table, String toSet, String value) {
    	return "UPDATE" + table + "SET" + toSet + "=" + value + ";";
    }
 
    //overloaded
    public static String generateUpdateCommand(String table, String toSet, String value, String condition) {
    	return "UPDATE" + table + "SET" + toSet + "=" + value + "WHERE" + condition + ";";
    }
    
    public static String generateInsertCommand(LinkedHashMap<String, String> changes) {
    	String[] dict = depackLinkedHash(changes);
    	String keys = dict[0];
    	String values = dict[1];
    	
    	String sqlCommand = "INSERT INTO company(" +  keys  + ")";
        sqlCommand += "VALUES(" + values + ");";
        return sqlCommand;
    }
    
    public static String[] depackLinkedHash(LinkedHashMap<String, String> hash) {
    	
    	String[] dictionary = new String[2];
    	
    	String keys = "";
    	String values = "";
    	for (Map.Entry<String, String> entries : hash.entrySet()) {
    		keys += entries.getKey() + ", ";
    		values += entries.getValue() + ", ";
    	}
    	
    	dictionary[0] = keys;
    	dictionary[1] = values;
    	return dictionary;
    	
    }
 
    
}