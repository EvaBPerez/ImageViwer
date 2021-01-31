package imageviwer.apps.mock;

import imageviwer.model.Image;
import imageviwer.view.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class MockImageLoader implements ImageLoader {

    @Override
    public List<Image> load() {        
        List<Image> imageList = new ArrayList<Image>();
        imageList.add(new Image("Hola"));
        imageList.add(new Image("amig@"));
        imageList.add(new Image("bienvenido"));
        return imageList;
    }
    
}
