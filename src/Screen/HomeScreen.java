package Screen;

import java.awt.*;
import javax.swing.*;
import Content.ContentHomeScreen;
import VideoProcessing.VideoProcessing;

public class HomeScreen extends Screen{

    private JScrollPane scrollPane;
    private ContentHomeScreen content;
    private CameraScreen baseCam;

    public HomeScreen(){

        super("Home Screen");

        setLocation(0, 0);
        setSize(350, 480);
        
        createComponents();
        configComponents();
        addComponents();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setBaseCam(new CameraScreen(350));
    }

    private void createComponents(){
        setTitle("Processador de Vídeo");
        setContent(new ContentHomeScreen());
        setScrollPane(new JScrollPane(content));
    }

    private void configComponents(){
        scrollPane.setBounds(0, 0, 345, 685);
        scrollPane.setBackground(new Color(76,76,76));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void addComponents() {
        add(scrollPane);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public ContentHomeScreen getContent() {
        return content;
    }
    public void setContent(ContentHomeScreen content) {
        this.content = content;
    }

    public CameraScreen getBaseCam() {
        return baseCam;
    }
    public void setBaseCam(CameraScreen baseCam) {
        this.baseCam = baseCam;
    }

}
