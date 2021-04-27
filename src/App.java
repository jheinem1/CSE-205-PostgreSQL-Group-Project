import javax.swing.*;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.LinkedHashMap;

public class App {
    public static void main(String[] args) throws Exception {
        var baseFrame = new JFrame();
        baseFrame.setMaximumSize(new java.awt.Dimension(1000, 500));
        baseFrame.setMinimumSize(new java.awt.Dimension(1000, 500));
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var loginFirstScreen = new LoginFirstScreen();
        var loginScreen = new LoginScreen();
        var accountTypeSelection = new AccountTypeSelection();
        var customerCreateAccount = new CustomerCreateAccount();
        var staffCreateAccount = new StaffCreateAccount();
        var customerScreen = new CustomerScreen();
        var checkout = new Checkout();
        var empScreen = new EmployeeScreen();
        var deleteAccount = new DeleteAccount();
        var processingScreen = new ProcessingScreen();
        var employeeAccountScreen = new EmployeeAccountScreen();
        //var manScreen = new ManagerScreen();

        baseFrame.add(loginFirstScreen);
        baseFrame.setVisible(true);

        DbConnections base = new DbConnections();
        Connection connection = base.getConnection();
        var processing = new Processing();
        var shoppingCart = new ShoppingCart();

        //COMMENT THIS OUT THE SECOND TIME YOU RUN THIS PROGRAM
        // base.createTable(connection, DbConnections.generateCreateCommand("USERS", "ID INT PRIMARY KEY NOT NULL, ENCODEDPERSON TEXT NOT NULL, USERNAME TEXT NOT NULL"));
       /* base.createTable(connection, DbConnections.generateCreateCommand("LASTID", "ID INT PRIMARY KEY NOT NULL"));
    	LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
		toInsert.put("ID" ,"9");
        base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "LASTID"));*/
        
        loginFirstScreen.onLoginClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginScreen);
                baseFrame.remove(loginFirstScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        loginFirstScreen.onCreateAccountClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(accountTypeSelection);
                baseFrame.remove(loginFirstScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        accountTypeSelection.onCustomerClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(customerCreateAccount);
                baseFrame.remove(accountTypeSelection);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        accountTypeSelection.onEmployeeClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(staffCreateAccount);
                baseFrame.remove(accountTypeSelection);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        loginScreen.onCancelClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(loginScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        loginScreen.onCreateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(accountTypeSelection);
                baseFrame.remove(loginScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        loginScreen.onLoginClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	/*Customer test = new Customer("Test", "McTestFace", "3 E. Road", "lololol@testing.com", "Username", "Password");
            	LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
        		toInsert.put("ID" , "7");
        		toInsert.put("ENCODEDPERSON" , Serializer.serialize(test));
            	toInsert.put("USERNAME", "Username");
        		//base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "USERS"));
            	*/

                System.out.println(loginScreen.getField("username"));

                String personCoded = base.selectLoginCommand(base.getConnection(), "USERS", loginScreen.getField("username"));
                System.out.println("Serialized Person: " + personCoded + "\n");
                if (personCoded.equals("Could not find person with username " + loginScreen.getField("username"))) {
                    System.out.println("Incorrect username. Try again :P");
                    return;
                }
                Person user = Serializer.deserializePersonObject(personCoded);
                System.out.println("beg");
                if (user.getPassword().equals(loginScreen.getField("password"))) {
                    if (user.getPosition().equals("customer")) {
                        baseFrame.add(customerScreen);
                        baseFrame.remove(loginScreen);
                        baseFrame.getContentPane().validate();
                        baseFrame.getContentPane().repaint();
                        System.out.println("cus");
                    } else if (user.getPosition().equals("employee")) {
                        baseFrame.add(empScreen);
                        baseFrame.remove(loginScreen);
                        baseFrame.getContentPane().validate();
                        baseFrame.getContentPane().repaint();
                        System.out.println("emp");
                    } else if (user.getPosition().equals("manager")) {
                        baseFrame.add(empScreen);
                        baseFrame.remove(loginScreen);
                        baseFrame.getContentPane().validate();
                        baseFrame.getContentPane().repaint();
                        System.out.println("real man");
                    } else {
                        System.out.println("ERROR. Position is NOT C, E, or M for " + user);
                    }
                } else {
                    System.out.println("Incorrect username or password");
                }


            }
        });
        deleteAccount.onCancelClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(empScreen);
                baseFrame.remove(deleteAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        deleteAccount.onDeleteClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: add delete account functionality
                baseFrame.add(empScreen);
                baseFrame.remove(deleteAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        deleteAccount.onLogoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(deleteAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        processingScreen.onUpdateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processing.updateItems(processingScreen.getSelectedItems(), Double.parseDouble(processingScreen.getField("price")), Integer.parseInt(processingScreen.getField("quantity")));
                processingScreen.refreshItems(processing.getNames().toArray(new String[0]));
            }
        });
        processingScreen.onDeleteClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processing.removeItems(processingScreen.getSelectedItems());
                processingScreen.refreshItems(processing.getNames().toArray(new String[0]));
            }
        });
        processingScreen.onLogoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(processingScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        processingScreen.onShipClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processing.shipItems(processingScreen.getSelectedItems());
                processingScreen.refreshItems(processing.getNames().toArray(new String[0]));
            }
        });
        processingScreen.onAccountsClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(employeeAccountScreen);
                baseFrame.remove(processingScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        processingScreen.onStoreClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(empScreen);
                baseFrame.remove(processingScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        empScreen.onAddClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        empScreen.onUpdateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        empScreen.onDeleteClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        empScreen.onLogoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginScreen);
                baseFrame.remove(empScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        empScreen.onProcessingClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(processingScreen);
                baseFrame.remove(empScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        empScreen.onAccountsClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(employeeAccountScreen);
                baseFrame.remove(empScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        staffCreateAccount.onCancelClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(staffCreateAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        staffCreateAccount.onLoginClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginScreen);
                baseFrame.remove(staffCreateAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        staffCreateAccount.onCreateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (staffCreateAccount.getField("position").toLowerCase().equals("employee")) {
                	String fname = staffCreateAccount.getField("firstName");
                	String lname = staffCreateAccount.getField("lastName");
                	String add = "N/A";
                	String eAdd = staffCreateAccount.getField("email");
                	String username = staffCreateAccount.getField("username");
                	String password = staffCreateAccount.getField("password");
                	
                	Employee emp = new Employee(fname, lname, add, eAdd, username, password);
                	String serialEmp = Serializer.serialize(emp);
                	
                	LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
            		toInsert.put("ID" , emp.getID()+ "");
            		toInsert.put("ENCODEDPERSON" , serialEmp);
                	toInsert.put("USERNAME", username);
                	
                	base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "USERS"));
                }
                else if (staffCreateAccount.getField("position").toLowerCase().equals("manager")) {
                	String fname = staffCreateAccount.getField("firstName");
                	String lname = staffCreateAccount.getField("lastName");
                	String add = "N/A";
                	String eAdd = staffCreateAccount.getField("email");
                	String username = staffCreateAccount.getField("username");
                	String password = staffCreateAccount.getField("password");
                	
                	Manager man = new Manager(fname, lname, add, eAdd, username, password);
                	
                	String serialMan = Serializer.serialize(man);
                	
                	LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
            		toInsert.put("ID" , man.getID()+ "");
            		toInsert.put("ENCODEDPERSON" , serialMan);
                	toInsert.put("USERNAME", username);
                	
                	base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "USERS"));
                	
                }
                baseFrame.add(empScreen);
                baseFrame.remove(staffCreateAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
                
            }
        });
        
        customerCreateAccount.onCancelClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(customerCreateAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        
        customerCreateAccount.onLoginClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginScreen);
                baseFrame.remove(customerCreateAccount);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        
        customerCreateAccount.onCreateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String fname = customerCreateAccount.getField("firstName");
            	String lname = customerCreateAccount.getField("lastName");
            	String add = customerCreateAccount.getField("address");
            	String eAdd = customerCreateAccount.getField("email");
            	String username = customerCreateAccount.getField("username");
            	String password = customerCreateAccount.getField("password");
            	
            	Customer cust = new Customer(fname, lname, add, eAdd, username, password);
            	String serialCust = Serializer.serialize(cust);
            	
            	LinkedHashMap<String, String> toInsert = new LinkedHashMap<String, String>();
        		toInsert.put("ID" , cust.getID()+ "");
        		toInsert.put("ENCODEDPERSON" , serialCust);
            	toInsert.put("USERNAME", username);
            	
            	 base.insertCommand(connection, DbConnections.generateInsertCommand(toInsert, "USERS"));
            	 baseFrame.add(customerScreen);
                 baseFrame.remove(customerCreateAccount);
                 baseFrame.getContentPane().validate();
                 baseFrame.getContentPane().repaint();
            }
           });

        customerScreen.onLogoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(customerScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        customerScreen.onAddClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO implement
            }
        });
        customerScreen.onDeleteClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO implement
            }
        });
        customerScreen.onUpdateClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO implement
            }
        });
        customerScreen.onCheckoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(checkout);
                baseFrame.remove(customerScreen);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        checkout.onCancelClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(checkout);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        checkout.onCheckoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (var item : shoppingCart.getValues())
                    processing.addItem(item);
                shoppingCart.clear();
                baseFrame.add(customerScreen);
                baseFrame.remove(checkout);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });
        checkout.onLogoutClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.add(loginFirstScreen);
                baseFrame.remove(checkout);
                baseFrame.getContentPane().validate();
                baseFrame.getContentPane().repaint();
            }
        });

        //base.close();

    }

}
