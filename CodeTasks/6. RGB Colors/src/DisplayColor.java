import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Pre-defined class. Nothing is graded here!!!
 * <p>
 * Modifications are not accounted for.
 */
public class DisplayColor {

    public static void displayColor(RgbColor c) {
        JFrame jf = new JFrame("Color Test") {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue()));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jf.setLocation(300, 300);
        jf.setSize(400, 350);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new RgbColor(10, 0b0011111111, 0b0000000000, 0b0010100001);
        new RgbColor(3, 0b011, 0b000, 0b001);
    }
}
