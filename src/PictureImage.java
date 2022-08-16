import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class PictureImage implements Image {
    private JFrame frame;
    private ImageComponent component;

    class ImageComponent extends JComponent {
        public BufferedImage bim;

        public ImageComponent(String filename) {
            try {
                bim = ImageIO.read(new File(filename));
            } catch (IOException e) {
            }
        }

        public void paint(Graphics g) {
            g.drawImage(bim, 0, 0, null);
        }

        public Dimension getPreferredSize() {
            if (bim == null) {
                return new Dimension(100, 100);
            } else {
                return new Dimension(bim.getWidth(), bim.getHeight());
            }
        }
    }

    public PictureImage(String filename) {
        component = new ImageComponent(filename);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(component);
        frame.pack();
        frame.setVisible(true);
    }

    public int sizeX() {
        return component.bim.getWidth();
    }

    public int sizeY() {
        return component.bim.getHeight();
    }

    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // This is a very simple contrast based on Euclidean distance of RGB values.
        // Better use CIE*Lab color models, see formulas on http://www.easyrgb.com/en/math.php
        if (p0.x < 0 || p0.x >= sizeX() || p0.y < 0 || p0.y >= sizeY() ||
                p1.x < 0 || p1.x >= sizeX() || p1.y < 0 || p1.y >= sizeY()) {
            throw new InputMismatchException();
        }
        int rgb1 = component.bim.getRGB(p0.x, p0.y) & 0xffffff;
        int rgb2 = component.bim.getRGB(p1.x, p1.y) & 0xffffff;
        double rd = rgb1 / 65536.0 - rgb2 / 65536.0;
        double gd = ((rgb1 / 256) % 256) - ((rgb2 / 256) % 256);
        double bd = (rgb1 % 256) - (rgb2 % 256);
        return Math.sqrt(rd * rd + gd * gd + bd * bd);
    }

    public void removeVPath(int[] path) {
        // show yellow flashes along the path
        for (int y = 0; y < sizeY(); y++) {
            component.bim.setRGB(path[y], y, 0xffffff00);
        }
        frame.repaint();
        // delete path from image
        BufferedImage newimage = new BufferedImage(sizeX() - 1, sizeY(), TYPE_INT_ARGB);
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX() - 1; x++) {
                int xo = x + ((x >= path[y]) ? 1 : 0);
                newimage.setRGB(x, y, component.bim.getRGB(xo, y));
            }
        }
        component.bim = newimage;
    }

    public void render() {
        frame.pack();
        frame.repaint();
    }

}

