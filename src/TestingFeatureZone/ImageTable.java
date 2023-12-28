package TestingFeatureZone;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ImageTable extends JFrame {
    private JTable table;
    private JPanel mainPanel = new JPanel();

    public ImageTable() {
        this.setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        this.initTable();
        setTableColumnImage();
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void initTable() {
        String[] columnNames = {"Name", "Image"};
        Object[][] data = {
                {"1", new ImageIcon("src/icon/logo.png")},
                {"2", new ImageIcon("src/icon/dashboard/user/libraryLogo.png")},
                {"3", new ImageIcon("src/icon/dashboard/user/storeLogo.png")},
                {"4", new ImageIcon("src/icon/dashboard/user/walletLogo.png")},
                {"5", new ImageIcon("src/icon/dashboard/user/libraryLogo.png")}
        };

        table = new JTable(new DefaultTableModel(data, columnNames)) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(512);
        table.setShowGrid(false);
        table.setBackground(Color.decode("#303952"));

    }

    // Set table column renderer as image
    public void setTableColumnImage() {
        table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
    }


    // Image renderer
    public class ImageRenderer extends DefaultTableCellRenderer {
        public ImageRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER); // Center the image in the cell
        }
        @Override
        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                if (value != null) {
                    ImageIcon imageIcon = (ImageIcon) value;
                    setIcon(imageIcon);
                    setText("");
                } else {
                    setIcon(null);
                    setText("");
                }
            } else {
                super.setValue(value);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageTable().setVisible(true));
    }
}
