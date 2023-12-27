package GUI.Dashboard.User;

import DTO.Account;

import javax.swing.*;

public class gameStorePanel extends JPanel {
    private JPanel mainPanel;
    private JPanel bestSeller;
    private JLabel header;
    private JPanel gameListPanel;
    private JButton button1;
    private Account account;
    public gameStorePanel(Account account) {
        this.add(mainPanel);
    }
}
