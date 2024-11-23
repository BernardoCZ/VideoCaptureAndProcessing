package Screen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.videoio.VideoCapture;

import java.awt.*;

public class ModifiedCameraScreen extends Screen{

    private JLabel cameraLabel;
    private int camID = 0;
    private VideoCapture cap;

    public ModifiedCameraScreen(CameraScreen baseCam){

        super("Vídeo Modificado");

        setLocation(baseCam.getWidth() + baseCam.getX(), 0);
        setLayout(new BorderLayout());

        createComponents(baseCam);
        configComponents();
        addComponents();
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createComponents(CameraScreen baseCam){
        setTitle("Vídeo Modificado");   
        setCameraLabel(new JLabel());
        setCap(baseCam.getCap());
        getCameraLabel().setIcon(new ImageIcon(baseCam.getFstImgData()));
    }

    private void configComponents(){
        cameraLabel.setBounds(0, 0, cameraLabel.getIcon().getIconWidth()+16, cameraLabel.getIcon().getIconHeight()+39); 
    }

    public void updateWindowSize(){
        setSize(cameraLabel.getIcon().getIconWidth()+16, cameraLabel.getIcon().getIconHeight()+39);
    }

    private void addComponents(){
        add(cameraLabel);
    }

    public JLabel getCameraLabel() {
        return cameraLabel;
    }
    public void setCameraLabel(JLabel cameraLabel) {
        this.cameraLabel = cameraLabel;
    }

    public int getCamID() {
        return camID;
    }
    public void setCamID(int camID) {
        this.camID = camID;
    }

    public VideoCapture getCap() {
        return cap;
    }
    public void setCap(VideoCapture cap) {
        this.cap = cap;
    }
    
}
