package GUI.CustomComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class customImageTable extends JTable {
    private String[] columnNames;
    private Object[][] data;
    public customImageTable() {
        super();
        initTable();
        setTableColumnImage();
        setTableColumnTextCenter();
    }
    public customImageTable(String[] columnNames, Object[][] data) {
        super();
        this.columnNames = columnNames;
        this.data = data;
        initTable();
        setTableColumnImage();
        setTableColumnTextCenter();
        setFont(new Font("Inter Semi Bold", Font.PLAIN, 16));
    }
    public void initTable() {
        this.setModel(new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

        });
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setRowHeight(250);
        this.setBackground(Color.decode("#ECECEC"));
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.showVerticalLines = false;
        this.showHorizontalLines = false;
    }

    @Override
    protected JTableHeader createDefaultTableHeader() {
        return null; // Disable the header
    }
    public void setTableColumnImage() {
        this.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
        this.getColumnModel().getColumn(0).setMaxWidth(250);
    }
    public void setTableColumnTextCenter() {
        this.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setText((String) value);
                label.setHorizontalAlignment(JLabel.LEADING);
                label.setVerticalAlignment(JLabel.TOP);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                return label;
            }
        });
        this.getColumnModel().getColumn(1).setMaxWidth(400);
    }
    public class ImageRenderer extends DefaultTableCellRenderer {
        public ImageRenderer() {
            super();
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            setHorizontalAlignment(JLabel.CENTER);
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
}
