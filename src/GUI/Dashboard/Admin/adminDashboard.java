package GUI.Dashboard.Admin;

import javax.swing.*;

public class adminDashboard extends JFrame {
    private JPanel panel1;
    public adminDashboard() {
        this.initFrame();
    }
    public void initFrame(){
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
