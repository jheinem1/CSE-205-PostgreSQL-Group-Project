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
                    "cowocow0"
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

    /*public static void selectCommand(Connection connection, String sqlCommand, String[] items)
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
    }*/
    
    public static void selectAllCommandForTest(Connection connection, String table)
    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlCommand = "Select * FROM " + table + ";";
            
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
            	 int id = resultSet.getInt("id");
              	String name = resultSet.getString("name");
               
                
                System.out.println("Name: " + name + " ID: " + id);
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
            String sqlCommand = "Select * FROM test3;";
            
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
    
    private static void selectCommandFromDelete(Connection connection, String table)

    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlCommand = "Select * FROM " + table + ";";
            
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

    public static String selectStoredCartCommand(Connection connection, String table, int custID)
    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlCommand = "Select * FROM " + table + ";";
            
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            int id = -1;
            String cart = "ERROR";
            while(resultSet.next()){
                id = resultSet.getInt("id");
                cart = resultSet.getString("cart");
                System.out.println("ID: " + id + "Cart: " + cart); 
                if (id == custID) {
                	break;
                }
            }
            resultSet.close();
            statement.close();
            System.out.println("Data Selected...");
            if (id == custID) {
            	return cart;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Catch all Exception occurred: "+e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return "Cart with ID " +  custID + "not found";
    }
    
    
    public static void selectTestsCommand(Connection connection, String table)
    {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sqlCommand = "Select * FROM " + table + ";";
            
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
            	
            	String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                
                System.out.println("Name: " + name + " ID: " + id);
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


           // selectCommand(connection);
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
            // String sqlCommand = "DELETE FROM table WHERE condition;"
            System.out.println(sqlCommand + "!!!!");
            statement.executeUpdate(sqlCommand);
            connection.commit();
            statement.close();
            System.out.println("Data Deleted...");


            //selectCommandFromDelete(connection, table);
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
    	return "DELETE FROM " + from + " WHERE " + where + ";";
    }
    
    
    public static String generateSelectCommand(String table) {
    	return "SELECT * FROM " + table + ";";
    }
    
    
    public static String generateCreateCommand(String table, String commaSeperatedValues) {
    	return "CREATE TABLE " + table + "(" + commaSeperatedValues + ");";
    }
    
    //overloaded
    public static String generateUpdateCommand(String table, String toSet, String value) {
    	return "UPDATE " + table + " SET " + toSet + " = " + value + ";";
    }
 
    //overloaded
    public static String generateUpdateCommand(String table, String toSet, String value, String condition) {
    	return " UPDATE " + table + " SET " + toSet + " = " + value + " WHERE " + condition + ";";
    }
    
    public static String generateInsertCommand(LinkedHashMap<String, String> changes, String table) {
    	String[] dict = depackLinkedHash(changes);
    	String keys = dict[0];
    	String values = dict[1];
    	
    	String sqlCommand = "INSERT INTO " + table + " (" +  keys  + ") ";
        sqlCommand += "VALUES(" + values + ");";
        //System.out.println(sqlCommand + "!!!!");
        return sqlCommand;
    }
    
    public Connection getConnection() {
    	return connection;
    }
    
    public static String[] depackLinkedHash(LinkedHashMap<String, String> hash) {
    	
    	String[] dictionary = new String[2];
    	
    	String keys = "";
    	String values = "";
    	for (Map.Entry<String, String> entries : hash.entrySet()) {
    		keys += entries.getKey() + ", ";
    		values += "\'" + entries.getValue() + "\', ";
    	}
    	
    	keys = keys.substring(0, keys.length() - 2); 
    	values = values.substring(0, values.length() - 2); 
    	
    	dictionary[0] = keys;
    	dictionary[1] = values;
    	return dictionary;
    	
    }


    public String selectLoginCommand(Connection connection, String users, String username) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}