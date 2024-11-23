package Screen;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import VideoProcessing.VideoProcessing;

public class CameraScreen extends Screen{

    private JLabel cameraLabel;
    private int camID = 0;
    private VideoCapture cap;
    private ModifiedCameraScreen modifiedCam = null;
    private byte[] fstImgData;
    private VideoWriter videoWriter = null;
    private boolean recordVideo = false;

    public CameraScreen(int posx){

        super("Vídeo Base");

        VideoProcessing.setCameraScreen(this);

        setLocation(posx, 0);
        setLayout(new BorderLayout());

        createComponents();
        configComponents();
        addComponents();
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setModifiedCam(new ModifiedCameraScreen(this));
        showFrames();

    }

    private void createComponents(){
        setTitle("Vídeo Base");   
        setCameraLabel(new JLabel());
        setCap(new VideoCapture(camID));

        // Captura um frame para saber o tamanho inicial do vídeo
        Mat frame = new Mat();
        cap.read(frame);

        // Converte o frame em uma matriz de bytes
        final MatOfByte buf = new MatOfByte(); 
        Imgcodecs.imencode(".jpg", frame, buf); 
        setFstImgData(buf.toArray());

        // Adiciona ao JLabel 
        getCameraLabel().setIcon(new ImageIcon(getFstImgData()));

    }

    private void configComponents(){
        cameraLabel.setBounds(0, 0, cameraLabel.getIcon().getIconWidth()+16, cameraLabel.getIcon().getIconHeight()+39);  
    }

    private void addComponents(){
        add(cameraLabel);
    }

    // Mostra imagens da câmera
    private void showFrames() 
    { 
        Mat frame = new Mat();
        byte[] imgData;

        if(cap.open(camID))
        {
            while(true)
            {
                cap.read(frame);
                if(frame.empty())
                    break;

                // Converte o frame base em uma matriz de bytes
                final MatOfByte buf1 = new MatOfByte(); 
                Imgcodecs.imencode(".jpg", frame, buf1); 
                imgData = buf1.toArray(); 
    
                // Adiciona ao JLabel na janela do vídeo base
                getCameraLabel().setIcon(new ImageIcon(imgData));

                // Converte o frame modificado em uma matriz de bytes
                Mat newFrame = new Mat();
                newFrame = VideoProcessing.processFrame(frame);
                final MatOfByte buf2 = new MatOfByte(); 
                Imgcodecs.imencode(".jpg", newFrame, buf2); 
                imgData = buf2.toArray(); 

                // Adiciona ao JLabel na janela do vídeo modificado
                getModifiedCam().getCameraLabel().setIcon(new ImageIcon(imgData));
                getModifiedCam().updateWindowSize();

                // Verifica se está gravando um vídeo
                if(isRecordVideo())
                {
                    getVideoWriter().write(newFrame);
                }
            }
        }
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

    public ModifiedCameraScreen getModifiedCam() {
        return modifiedCam;
    }
    public void setModifiedCam(ModifiedCameraScreen modifiedCam) {
        this.modifiedCam = modifiedCam;
    }

    public byte[] getFstImgData() {
        return fstImgData;
    }
    public void setFstImgData(byte[] fstImgData) {
        this.fstImgData = fstImgData;
    }

    public VideoWriter getVideoWriter() {
        return videoWriter;
    }
    public void setVideoWriter(VideoWriter videoWriter) {
        this.videoWriter = videoWriter;
    }

    public boolean isRecordVideo() {
        return recordVideo;
    }
    public void setRecordVideo(boolean recordVideo) {
        this.recordVideo = recordVideo;
    }
    
}
