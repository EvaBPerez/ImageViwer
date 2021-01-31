package imageviwer.apps.swing;

import imageviwer.model.Image;
import imageviwer.view.ImageLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileImageLoader implements ImageLoader{
    private final File root;

    public FileImageLoader(File root) {
        this.root = root;
    }

    public List<Image> load() {
        List<Image> listImage = new ArrayList<>();
        for (File file : root.listFiles(imageFilter())) {
            listImage.add(new Image(file.getAbsolutePath()));
        }
        
        return listImage;
    }

    private static final String[] imageExtension = new String[] {".png", ".jpg", ".JPG", ".PNG"};
    
    private FilenameFilter imageFilter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File parent, String name) {
                for (String imageExtension : imageExtension) {
                    if (name.endsWith(imageExtension)) return true;
                }
                
                return false;
            }
            
        };
    }    
}
