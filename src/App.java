import javax.swing.JFrame;

import gui.LoginFirstScreen;

public class App {
    public static void main(String[] args) throws Exception {
        var baseFrame = new JFrame();
        baseFrame.setMaximumSize(new java.awt.Dimension(1000, 500));
        baseFrame.setMinimumSize(new java.awt.Dimension(1000, 500));

        var loginFirstScreen = new LoginFirstScreen();
        
        baseFrame.add(loginFirstScreen);
        baseFrame.setVisible(true);
    }
}
