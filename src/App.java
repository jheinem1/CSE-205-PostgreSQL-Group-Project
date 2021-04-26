import javax.swing.*;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        baseFrame.add(loginFirstScreen);
        baseFrame.setVisible(true);

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
    }
}
