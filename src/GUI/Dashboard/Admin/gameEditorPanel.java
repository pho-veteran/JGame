package GUI.Dashboard.Admin;

import BUS.GameService;
import BUS.ImageHandler;
import DTO.Game;
import GUI.CustomComponents.ScrollPaneWin11;
import GUI.CustomComponents.gameEditorImageTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class gameEditorPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel gameListAndButtonPanel;
    private JPanel infoEditorPanel;
    private JScrollPane gameListScrollPane;
    private JTable gameList;
    private JButton addGame;
    private JTextField searchByNameField;
    private JButton removeGame;
    private JButton confirmSearchButton;
    private JPanel changeBannerPanel;
    private JButton removeBanner;
    private JButton editBanner;
    private JLabel gameLogo;
    private JTextField gameNameField;
    private JPanel changeTextRelatedInfo;
    private JTextArea descField;
    private JTextField genreField;
    private JScrollPane descScrollPane;
    private JTextField priceField;
    private JPanel pricePanel;
    private JButton confirmButton;
    private JButton discardButton;
    private JPanel decisionPanel;
    private JPanel filled;
    private ArrayList<Game> gameListData;
    private Game selectedGame;
    private String currentBannerURL;
    private String changedBannerURL;

    public gameEditorPanel() {
        this.initPanel();
    }

    public void initPanel() {
        fetchGameListData();
        initGameListTable();
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        infoEditorPanel.setVisible(false);
        initListeners();
    }

    public void initListeners() {
        confirmSearchButton.addActionListener(e -> {
            fetchSearchedGameListData(searchByNameField.getText());
            initGameListTable();
            clearSelection();
        });
        editBanner.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Choose a game banner image");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String sourceBannerURL = fileChooser.getSelectedFile().getAbsolutePath();
                if (currentBannerURL == null)
                    currentBannerURL = selectedGame.getBannerURL();
                changedBannerURL = sourceBannerURL;
                gameLogo.setIcon(new ImageIcon(sourceBannerURL));
            }
        });
        removeBanner.addActionListener(e -> {
            if (currentBannerURL == null)
                currentBannerURL = selectedGame.getBannerURL();
            changedBannerURL = "src/icon/game/default.png";
            gameLogo.setIcon(new ImageIcon("src/icon/game/default.png"));
        });
        removeGame.addActionListener(e -> {
            if (gameList.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a game to remove", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this game?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                new GameService().removeGame(selectedGame);
                new ImageHandler().removeImage(selectedGame.getBannerURL());
                refreshGameList();
                removeButtonPanelListener();
                clearSelection();
            }
        });
        addGame.addActionListener(e -> {
            resetBannerState();
            clearSelection();
            initAddGamesFunction();
            removeButtonPanelListener();
            addGameButtonListener();
        });
    }

    public void initAddGamesFunction() {
        gameNameField.setText("");
        descField.setText("");
        genreField.setText("");
        priceField.setText("");
        infoEditorPanel.setVisible(true);
        changedBannerURL = "src/icon/game/default.png";
        gameLogo.setIcon(new ImageIcon("src/icon/game/default.png"));
    }

    public void editGameButtonListener() {
        discardButton.addActionListener(e -> {
            clearSelection();
            resetBannerState();
            removeButtonPanelListener();
        });
        confirmButton.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this game?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                clearSelection();
                executeGameInfoUpdate();
                resetBannerState();
                refreshGameList();
            }
            removeButtonPanelListener();
        });
    }

    public void addGameButtonListener() {
        confirmButton.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this game?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                clearSelection();
                executeAddNewGame();
                refreshGameList();
            }
            removeButtonPanelListener();
        });
        discardButton.addActionListener(e -> {
            clearSelection();
            resetBannerState();
            removeButtonPanelListener();
        });
    }

    public void removeButtonPanelListener() {
        removeButtonListener(confirmButton);
        removeButtonListener(discardButton);
    }

    public void removeButtonListener(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    public void resetBannerState() {
        currentBannerURL = null;
        changedBannerURL = null;
    }

    public void clearSelection() {
        gameList.getSelectionModel().clearSelection();
        infoEditorPanel.setVisible(false);
    }

    public void refreshGameList() {
        if (searchByNameField.getText().equals(""))
            fetchGameListData();
        else
            fetchSearchedGameListData(searchByNameField.getText());
        initGameListTable();
    }

    public void executeAddNewGame() {
        if (checkFieldConstraints()) {
            String projectBannerURL = new ImageHandler().copyImage(changedBannerURL);

            new GameService().addGame(new Game(0, Double.parseDouble(priceField.getText()),
                    gameNameField.getText(), descField.getText(), genreField.getText(),
                    projectBannerURL));
        }
    }

    public void executeGameInfoUpdate() {
        if (checkFieldConstraints()) {
            if (currentBannerURL != null) {
                try {
                    String projectBannerURL =
                            new ImageHandler().setBannerURL(changedBannerURL, currentBannerURL);
                    selectedGame.setBannerURL(projectBannerURL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            new GameService().updateGame(new Game(selectedGame.getGameId(), Double.parseDouble(priceField.getText()),
                    gameNameField.getText(), descField.getText(), genreField.getText(),
                    selectedGame.getBannerURL()));
        }
    }

    public boolean checkFieldConstraints() {
        if (priceField.getText().equals("") || gameNameField.getText().equals("")
                || descField.getText().equals("") || genreField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(priceField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Price must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (gameNameField.getText().length() > 255) {
            JOptionPane.showMessageDialog(null, "Name must not exceed 255 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (genreField.getText().length() > 255) {
            JOptionPane.showMessageDialog(null, "Genre must not exceed 255 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void updateGameInfoPanel() {
        infoEditorPanel.setVisible(true);
        gameNameField.setText(selectedGame.getName());
        descField.setText(selectedGame.getDescription());
        genreField.setText(selectedGame.getGenre());
        priceField.setText(selectedGame.getPrice() + "");
        gameLogo.setIcon(new ImageIcon(selectedGame.getBannerURL()));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        gameListScrollPane = new ScrollPaneWin11();
        gameListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descScrollPane = new ScrollPaneWin11();
    }

    public void fetchGameListData() {
        gameListData = new GameService().getAllGamesList();
    }

    public void fetchSearchedGameListData(String name) {
        gameListData = new GameService().searchGame(name, "");
    }

    public void initGameListTable() {
        String[] columnNames = {"Image", "Name"};
        Object[][] data = new Object[gameListData.size()][2];
        for (int i = 0; i < gameListData.size(); i++) {
            data[i][0] = new ImageIcon(gameListData.get(i).getBannerURL());
            data[i][1] = gameListData.get(i).getName();
        }
        gameList = new gameEditorImageTable(columnNames, data);
        gameListScrollPane.setViewportView(gameList);

        gameList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = gameList.getSelectedRow();
                if (selectedRow != -1) {
                    selectedGame = gameListData.get(selectedRow);
                    updateGameInfoPanel();
                }
            }
            removeButtonPanelListener();
            editGameButtonListener();
        });
    }
}
