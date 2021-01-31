package imageviwer.view;

import imageviwer.model.Image;

public interface ImageDisplay {

    public void display(Image image);
    public Image image();
    
    void on(Shift shift);
    interface Shift {
        Image left();
        Image right();
    }
}
