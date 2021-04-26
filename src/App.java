import javax.swing.*;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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
        var empScreen = new EmployeeScreen();
        var deleteAccount = new DeleteAccount();
        var processingScreen = new ProcessingScreen();
        //var manScreen = new ManagerScreen();

        baseFrame.add(loginFirstScreen);
        baseFrame.setVisible(true);
        
        DbConnections base = new DbConnections();
        Connection connection = base.getConnection();
        
        //COMMENT THIS OUT THE SECOND TIME YOU RUN THIS PROGRAM
       // base.createTable(connection, DbConnections.generateCreateCommand("USERS", "ID INT PRIMARY KEY NOT NULL, ENCODEDPERSON TEXT NOT NULL, USERNAME TEXT NOT NULL"));

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
            	System.out.println("real beg");
            	String personCoded = base.selectLoginCommand(base.getConnection(), "USERS", loginScreen.getField("username"));
            	Person user = Serializer.deserializePersonObject(personCoded);
            	System.out.println("beg");
            	if (user.getPassword().equals(loginScreen.getField("password"))) {
            		if (user.getPosition().equals("Customer")) {
            		      baseFrame.add(customerScreen);
                          baseFrame.remove(loginScreen);
                          baseFrame.getContentPane().validate();
                          baseFrame.getContentPane().repaint();
                          System.out.println("cus");
            		}
            		else if (user.getPosition().equals("Employee")) {
            		      baseFrame.add(empScreen);
                          baseFrame.remove(loginScreen);
                          baseFrame.getContentPane().validate();
                          baseFrame.getContentPane().repaint();
                          System.out.println("emp");
            		}
            		else if (user.getPosition().equals("Manager")) {
            		      baseFrame.add(empScreen);
                          baseFrame.remove(loginScreen);
                          baseFrame.getContentPane().validate();
                          baseFrame.getContentPane().repaint();
                          System.out.println("real man");
            		}
            		else {
            			System.out.println("ERROR. Position is NOT C, E, or M for " + user);
            		}
            	}
            	else {
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
        
     base.close();   
        
    }

}
