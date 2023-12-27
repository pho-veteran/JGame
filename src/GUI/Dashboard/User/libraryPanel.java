package GUI.Dashboard.User;

import DTO.Account;

import javax.swing.*;

public class libraryPanel extends JPanel {
    private Account account;
    public libraryPanel(Account account) {
        this.account = account;
        this.add(new JLabel("Library"));
    }
}
