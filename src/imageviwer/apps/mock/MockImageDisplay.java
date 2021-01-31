package imageviwer.apps.mock;

import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;

public class MockImageDisplay implements ImageDisplay{
    private Image image;

    @Override
    public void display(Image image) {
        this.image = image;
        System.out.println(image.getName());
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void on(Shift shift) {}
    
    
}
