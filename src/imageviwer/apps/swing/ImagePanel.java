package imageviwer.apps.swing;

import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay{
    private Image image;
    private BufferedImage bufferedImage;
    private Shift shift;
    private int x;
    private Image image2;

    public ImagePanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g) {
        BufferedImage image = load(this.image.getName());
        Box box = new Box(image);
        g.drawImage(image, box.x, box.y, box.width, box.height, null);
        if (x == 0) return;
        BufferedImage image2 = load(this.image2.getName());
        int offest = x > 0 ? - (image2.getWidth()-x) : x + image.getWidth();
        g.drawImage(image2, offest, 0, box.width, box.height, null);
    }
    
    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void display(Image image) {
        this.image = image;
        bufferedImage = load(image.getName());
        repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }
    
    private class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private final int px;
        private final int py;
        
        private Box(BufferedImage image) {
            this.px = getWidth();
            this.py = getHeight();
            this.width = calcWidth(image.getWidth(), image.getHeight());
            this.height = calcHeight(image.getWidth(), image.getHeight());
            this.x = (px - width)/2;
            this.y = (py - height)/2;
        }

        private int calcWidth(double ix, double iy) {
            double pr = 1.0 * px/py;
            double ir = 1.0 * ix/iy;
            return ir > pr ? px : (int) (ix * 1.0 * py/iy);
        }

        private int calcHeight(double ix, double iy){
            double pr = 1.0 * px/py;
            double ir = 1.0 * ix/iy;
            return ir > pr ? (int) (iy * 1.0 * px/ix) : py; 
        }   
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener{
        private int initial;
        
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            this.initial = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x = 0;
            if (Math.abs(e.getX()-initial) > getWidth()/2) image = image2;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            x = e.getX() - initial;
            if (x > 0) image2 = shift.right();
            if (x < 0) image2 = shift.left();
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
    
    
}
