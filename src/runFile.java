import GUI.AccountMenu.loginMenu;

import javax.swing.*;

public class runFile {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new loginMenu().setVisible(true);
            }
        });
    }
}
