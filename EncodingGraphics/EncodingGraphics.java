
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

class EncodingGraph extends JPanel {
    ArrayList<Integer> byteStream;
    int MARGIN = 50;
    int GRID_BLOCK_SIZE = 50;

    EncodingGraph(ArrayList<Integer> byteStream) {
        this.byteStream = byteStream;
    }

    void drawGraphAxis(Graphics g, int panelHeight, int panelWidth) {
        int x1 = 0 + MARGIN;
        int y1 = 0 + MARGIN;
        int x2 = panelWidth - MARGIN;
        int y2 = panelHeight - MARGIN;

        // draw axis lines
        g.drawLine(x1, y1, x1, y2 + (int) (MARGIN / 2));
        g.drawLine(x1 - (int) (MARGIN / 2), y2, x2, y2);

        // draw vertical grid lines
        g.setColor(Color.LIGHT_GRAY);
        for (int i = MARGIN + GRID_BLOCK_SIZE; i < panelWidth - (MARGIN); i += GRID_BLOCK_SIZE) {
            g.drawLine(i, y1, i, y2);
        }
        // draw horizontal grid lines
        for (int i = panelHeight - (MARGIN + GRID_BLOCK_SIZE); i > y1; i -= GRID_BLOCK_SIZE) {
            g.drawLine(x1, i, x2, i);
        }

        g.setColor(Color.BLACK);
    }

    void plotGraph(Graphics g, ArrayList<Integer> byteStream, int panelHeight, int panelWidth) {
        Graphics2D g2d = (Graphics2D) g;
        int high = (panelHeight - GRID_BLOCK_SIZE) - ((panelHeight - GRID_BLOCK_SIZE) % GRID_BLOCK_SIZE);
        high = panelHeight - high;
        int low = panelHeight - MARGIN;

        g.setColor(Color.BLUE);

        g2d.setStroke(new BasicStroke(3));
        int x = 0 + MARGIN;
        int last = byteStream.get(0);

        for (int i : byteStream) {
            if (i != last) {
                g2d.drawLine(x, high, x, panelHeight - MARGIN);
            }
            if (i == 0) {
                g2d.drawLine(x, low, x + GRID_BLOCK_SIZE, low);
            } else {
                g2d.drawLine(x, high, x + GRID_BLOCK_SIZE, high);
            }
            x += GRID_BLOCK_SIZE;
            last = i;
        }
        g2d.setStroke(new BasicStroke(1));

        g.setColor(Color.BLUE);
        g.drawLine(0 + MARGIN - 10, high, 0 + MARGIN, high);
        g.drawLine(0 + MARGIN - 10, low, 0 + MARGIN, low);

        g.setColor(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        drawGraphAxis(g, panelHeight, panelWidth);
        plotGraph(g, this.byteStream, panelHeight, panelWidth);
    }

}

public class EncodingGraphics {
    public EncodingGraphics(ArrayList<Integer> byteStream) {
        var panel = new EncodingGraph(byteStream);
        // panel.setBackground(Color.GREEN.darker());
        var frame = new JFrame("ENCODING GRAPH");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
