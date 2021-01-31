package imageviwer.apps.mock;

import imageviwer.control.Command;
import imageviwer.control.ExitCommand;
import imageviwer.control.InitCommand;
import imageviwer.control.NextImageCommand;
import imageviwer.control.NullCommand;
import imageviwer.control.PrevImageCommand;
import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;
import imageviwer.view.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = initCommands(createImageDisplay());
        
        while (true) {
            commands.getOrDefault(scanner.next(), new NullCommand()).execute();
        }
    }
    
    private static Map<String, Command> initCommands(ImageDisplay imageDisplay) {
        List<Image> listImage = new ArrayList<Image>();
        Map<String, Command> commands = new HashMap<>();
        ImageLoader imageLoader = new MockImageLoader();
        commands.put("q", new ExitCommand());
        commands.put("n", new NextImageCommand(listImage, imageDisplay));
        commands.put("p", new PrevImageCommand(listImage, imageDisplay));
        commands.put("i", new InitCommand(imageLoader, listImage, imageDisplay));
        return commands;        
    }
    
    private static ImageDisplay createImageDisplay() {
        ImageDisplay imageDisplay = new MockImageDisplay();
        return imageDisplay;
    }
    
}
