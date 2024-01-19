package GUI.CustomComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class gameEditorImageTable extends JTable {
    private String[] columnNames;
    private Object[][] data;

    public gameEditorImageTable() {
        super();
        initTable();
        setTableColumnImage();
        setTableColumnTextFormat();
    }

    public gameEditorImageTable(String[] columnNames, Object[][] data) {
        super();
        this.columnNames = columnNames;
        this.data = data;
        initTable();
        setTableColumnImage();
        setTableColumnTextFormat();
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
        return null;
    }

    public void setTableColumnImage() {
        this.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
        this.getColumnModel().getColumn(0).setMaxWidth(180);
    }

    public void setTableColumnTextFormat() {
        this.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setText((String) value);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setFont(new Font("Inter", Font.BOLD, 16));
                label.setBorder(null);
                return label;
            }
        });
        this.getColumnModel().getColumn(1).setMaxWidth(220);
    }

    public class ImageRenderer extends DefaultTableCellRenderer {
        public ImageRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(null);
            return label;
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
