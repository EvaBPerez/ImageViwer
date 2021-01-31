package imageviwer.control;

import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;
import java.util.List;

public class PrevImageCommand implements Command{
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public PrevImageCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(images.get(next()));
    }

    private int next() {
        int index = images.indexOf(imageDisplay.image());
        return (index -1 + images.size()) % images.size();
    }
    
}
