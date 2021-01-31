package imageviwer.apps.swing;

import imageviwer.control.Command;
import imageviwer.control.ImagePresenter;
import imageviwer.control.NextImageCommand;
import imageviwer.control.PrevImageCommand;
import imageviwer.model.Image;
import imageviwer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Main extends JFrame {
    private List<Image> images;
    private ImageDisplay imageDisplay;
    private Map<String, Command> commands = new HashMap<>();
    private ImagePresenter imagePresenter;
    

    public static void main(String[] args) {
        new Main().execute();
    }   
    
    public Main() {
        this.setTitle("Image Viwer");
        this.setSize(800, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private void execute() {
        images = new FileImageLoader(new File("fotos")).load();
        imagePresenter = new ImagePresenter(images, imageDisplay);
        imageDisplay.display(images.get(0));
        newCommands();
        setVisible(true);
    }
    
    private void newCommands() {
        commands.put("<--", new PrevImageCommand(images, imageDisplay));
        commands.put("-->", new NextImageCommand(images, imageDisplay));
    }

    private ImagePanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        imageDisplay = imagePanel;
        return imagePanel;
    }

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.add(button("<--"));
        toolbar.add(button("-->"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        
        return button;
    }    
}
