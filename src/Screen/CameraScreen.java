package Screen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.awt.*;

public class CameraScreen extends Screen{

    private JLabel cameraLabel;

    public CameraScreen(){

        super("Câmera");

        setLocation(200, 200);
        setLayout(null);

        createComponents();
        configComponents();
        addComponents();
        pack();

        setSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createComponents(){
        setTitle("Câmera");   
        setCameraLabel(new JLabel());
    }

    private void configComponents(){
        cameraLabel.setBounds(0, 0, 640, 480); 
    }

    private void addComponents(){
        add(cameraLabel);
    }

    // Inicia câmera
    public void startCamera() 
    { 
        int camID = 0;
        VideoCapture cap = new VideoCapture(0);
        Mat frame = new Mat();
        byte[] imgData;

        if(cap.open(camID))
        {
            while(true)
            {
                cap.read(frame);
                if(frame.empty())
                    break;

                //converte o frame em uma matriz de bytes
                final MatOfByte buf = new MatOfByte(); 
                Imgcodecs.imencode(".jpg", frame, buf); 
                imgData = buf.toArray(); 
    
                // Add to JLabel 
                getCameraLabel().setIcon(new ImageIcon(imgData)); 
                
            }
        }
    }

    public JLabel getCameraLabel() {
        return cameraLabel;
    }
    public void setCameraLabel(JLabel cameraLabel) {
        this.cameraLabel = cameraLabel;
    }
    
}
