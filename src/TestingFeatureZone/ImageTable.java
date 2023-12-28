package TestingFeatureZone;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ImageTable extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public ImageTable() {
        setTitle("Image Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create a table model with two columns
        model = new DefaultTableModel(new Object[]{"Image", "Description"}, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : String.class;
            }
        };

        // Add rows to the table model
        try {
            BufferedImage image1 = ImageIO.read(new File("image1.jpg"));
            BufferedImage image2 = ImageIO.read(new File("image2.jpg"));
            BufferedImage image3 = ImageIO.read(new File("image3.jpg"));

            model.addRow(new Object[]{new ImageIcon(image1.getScaledInstance(100, 100, Image.SCALE_SMOOTH)), "Image 1"});
            model.addRow(new Object[]{new ImageIcon(image2.getScaledInstance(100, 100, Image.SCALE_SMOOTH)), "Image 2"});
            model.addRow(new Object[]{new ImageIcon(image3.getScaledInstance(100, 100, Image.SCALE_SMOOTH)), "Image 3"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a table with the model
        table = new JTable(model);
        table.setRowHeight(100);
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

        // Add the table to a scroll pane
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageTable();
    }

    // Custom cell renderer for displaying images
    private class ImageRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            label.setIcon((ImageIcon) value);
            return label;
        }
    }
}

