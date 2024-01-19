package GUI.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ScrollPaneWin11 extends JScrollPane {

    public ScrollPaneWin11() {
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        EventQueue.invokeLater(() -> {
            setComponentZOrder(getVerticalScrollBar(), 0);
            setComponentZOrder(getHorizontalScrollBar(), 1);
            setComponentZOrder(getViewport(), 2);
            getVerticalScrollBar().setOpaque(false);
            getHorizontalScrollBar().setOpaque(false);
        });
    }

    private class ScrollLayout extends ScrollPaneLayout {
        @Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane scroll) {
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }
        }
    }
}

