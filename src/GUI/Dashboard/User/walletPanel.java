package GUI.Dashboard.User;

import DTO.Account;

import javax.swing.*;

public class walletPanel extends JPanel {
    private Account account;
    private JPanel mainPanel;

    public walletPanel(Account account) {
        this.account = account;
        this.initPanel();
    }
    public void initPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Wallet"));
    }
}
