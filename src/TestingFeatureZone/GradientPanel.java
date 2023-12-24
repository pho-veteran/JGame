package TestingFeatureZone;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Define the start and end points for the gradient
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, height);

        // Define the colors for the gradient
        Color color1 = Color.RED;
        Color color2 = Color.BLUE;

        // Create a LinearGradientPaint object for the gradient
        LinearGradientPaint gradient = new LinearGradientPaint(start, end, new float[]{0.0f, 1.0f}, new Color[]{color1, color2});

        // Set the paint for the graphics context to the gradient
        g2d.setPaint(gradient);

        // Fill the entire panel with the gradient
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gradient Panel Example");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create an instance of the GradientPanel and add it to the frame
            GradientPanel gradientPanel = new GradientPanel();
            frame.add(gradientPanel);

            frame.setVisible(true);
        });
    }
}
