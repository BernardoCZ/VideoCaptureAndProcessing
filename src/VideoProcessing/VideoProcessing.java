package VideoProcessing;

import javax.swing.JLabel;
import javax.swing.JSlider;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import Alert.UserAlert;
import Content.ContentHomeScreen;
import Output.FileSaver;
import Screen.CameraScreen;
import Screen.HomeScreen;

public class VideoProcessing {

    // Char => Operação
    // R => Limpar (Reverter)
    // L => Grayscale (Luminância)
    // B => Brilho
    // C => Contraste
    // N => Negativo
    // G => Filtro Gaussiano
    // E => Filtro Canny (Edge Detector)
    // S => Filtro Sobel

    private static char operation = ' ';

    private static boolean flipH = false;
    private static boolean flipV = false;
    private static int rotationID = 0;
    private static double downscalingFactor = 1.0;

    private static JLabel labelSlider;
    private static JSlider slider;
    private static JLabel labelSlider2;

    private static ContentHomeScreen homeScreen;
    private static CameraScreen cameraScreen;

    public static Mat processFrame(Mat frame)
    {
        switch(getOperation()) {
            case 'L':
                frame = grayscale(frame);
                break;
            case 'B':
                frame = brightness(frame);
                break;
            case 'C':
                frame = contrast(frame);
                break;
            case 'N':
                frame = negative(frame);
                break;
            case 'G':
                frame = gaussianFilter(frame);
                break;
            case 'E':
                frame = cannyFilter(frame);
                break;
            case 'S':
                frame = sobelFilter(frame);
                break;
            default:
                break;
        }

        if(getOperation() == 'R')
        {
            setOperation(' ');
            setFlipH(false);
            setFlipV(false);
            setRotationID(0);
            setDownscalingFactor(1.0);
        }
        else{
            if(isFlipH())
            {
                frame = flipHorizontal(frame);
            }

            if(isFlipV())
            {
                frame = flipVertical(frame);
            }

            frame = downscaleFrame(rotateFrame(frame));
        }

        return frame;
    }

    public static void sliderChange()
    {
        switch(getOperation()) {
            case 'L':
                getLabelSlider2().setText("TONS DE CINZA");
                break;
            case 'B':
                getLabelSlider2().setText("BRILHO: " + getSlider().getValue());
                break;
            case 'C':
                getLabelSlider2().setText("CONTRASTE: " + getSlider().getValue() + "%");
                break;
            case 'N':
                getLabelSlider2().setText("NEGATIVO");
                break;
            case 'G':
                getLabelSlider2().setText("FILTRO GAUSSIANO: KERNEL " + ((getSlider().getValue()*2) + 1) + "x" + ((getSlider().getValue()*2) + 1));
                break;
            case 'E':
                getLabelSlider2().setText("FILTRO CANNY");
                break;
            case 'S':
                getLabelSlider2().setText("FILTRO SOBEL");
                break;
            default:
                getLabelSlider2().setText("");
                break;
        }
    }

    public static Mat grayscale(Mat frame)
    {
        Mat newFrame = new Mat();
        Imgproc.cvtColor(frame, newFrame, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(newFrame, newFrame, Imgproc.COLOR_GRAY2RGB);
        return newFrame;
    }

    public static Mat brightness(Mat frame)
    {
        frame.convertTo(frame, -1, 1, getSlider().getValue());
        return frame;
    }

    public static Mat contrast(Mat frame)
    {
        double alpha = 1;
        if(getSlider().getValue() >= 50)
        {
            // Valores de 50% a 100% variam de 1 a 3, onde 50% = 1 e 100% = 3
            alpha = ( (getSlider().getValue() - 50.0) / 25.0) + 1;
        }
        else{
            // Valores de 0% a 50% variam de 0 a 1, onde 0% = 0 e 50% = 1
            alpha = getSlider().getValue() / 50.0;
        }
        frame.convertTo(frame, -1, alpha, 0);
        return frame;
    }

    public static Mat negative(Mat frame)
    {
        frame.convertTo(frame, -1, -1, 255);
        return frame;
    }

    public static Mat flipHorizontal(Mat frame)
    {
        Mat newFrame = new Mat();
        Core.flip(frame, newFrame, 1);
        return newFrame;
    }

    public static Mat flipVertical(Mat frame)
    {
        Mat newFrame = new Mat();
        Core.flip(frame, newFrame, 0);
        return newFrame;
    }

    public static Mat gaussianFilter(Mat frame)
    {
        Mat newFrame = new Mat();
        Imgproc.GaussianBlur(frame, newFrame, new Size(((getSlider().getValue()*2) + 1), ((getSlider().getValue()*2) + 1)), 0);
        return newFrame;
    }

    public static Mat cannyFilter(Mat frame)
    {
        Mat newFrame = new Mat();
        Imgproc.Canny(frame, newFrame, 50, 50 * 3, 3);
        Imgproc.cvtColor(newFrame, newFrame, Imgproc.COLOR_GRAY2RGB);
        return newFrame;
    }

    public static Mat sobelFilter(Mat frame)
    {
        frame = grayscale(frame);
        Mat grad_x = new Mat();
        Imgproc.Sobel(frame, grad_x, -1, 1, 0);
        Mat grad_y = new Mat();
        Imgproc.Sobel(frame, grad_y, -1, 0, 1);

 
        Mat grad = new Mat();
        Core.addWeighted( grad_x, 0.5, grad_y, 0.5, 0, grad );

        
        return grad;
    }

    public static Mat rotateFrame(Mat frame) {

        if(getRotationID() != 0)
        {
            if(getRotationID() > 0)
            {
                for(int i = 0; i < getRotationID(); i++)
                {
                    Core.rotate(frame, frame, Core.ROTATE_90_CLOCKWISE);
                }
            }
            else{
                for(int i = 0; i < (getRotationID() * (-1)); i++)
                {
                    Core.rotate(frame, frame, Core.ROTATE_90_COUNTERCLOCKWISE);
                }
            }
        }

        return frame;
    }

    public static Mat downscaleFrame(Mat frame)
    {
        Mat newFrame = new Mat();
        Imgproc.resize(frame, newFrame, new Size(0, 0), 1.0/getDownscalingFactor(), 1.0/getDownscalingFactor(), Imgproc.INTER_AREA);
        return newFrame;
    }

    public static char getOperation() {
        return operation;
    }
    public static void setOperation(char op) {
        if(VideoProcessing.operation != op)
        {
            if(op == 'B')
            {
                getSlider().setMaximum(255);
                getSlider().setMinimum(-255);
                getSlider().setValue(0);
                getLabelSlider().setVisible(true);
                getSlider().setVisible(true);
            }
            else if(op == 'C')
            {
                getSlider().setMaximum(100);
                getSlider().setMinimum(0);
                getSlider().setValue(50);
                getLabelSlider().setVisible(true);
                getSlider().setVisible(true);
            }
            else if(op == 'G')
            {
                getSlider().setMaximum(50);
                getSlider().setMinimum(0);
                getSlider().setValue(0);
                getLabelSlider().setVisible(true);
                getSlider().setVisible(true);
            }
            else{
                getLabelSlider().setVisible(false);
                getSlider().setVisible(false);
            }
        }
        
        VideoProcessing.operation = op;
        sliderChange();
    }

    public static void rotation(boolean clockwise) {

        if(clockwise)
        {
            if(getRotationID() + 1 == 4)
            {
                setRotationID(0);
            }
            else{
                setRotationID(getRotationID() + 1);
            }
        }
        else{
            if(getRotationID() - 1 == -4)
            {
                setRotationID(0);
            }
            else{
                setRotationID(getRotationID() - 1);
            }
        }
    }

    public static void zoomOut()
    {
        setDownscalingFactor(getDownscalingFactor()*2.0);
    }

    public static void recordVideo(FileSaver fileSaver)
    {
        if(fileSaver.getFieldFile().getText().length() != 0)
        {
            try{
                getCameraScreen().setRecordVideo(true);

                getCameraScreen().setVideoWriter(
                new VideoWriter(fileSaver.getFieldFile().getText(), VideoWriter.fourcc('m', 'p', '4', 'v'), 30,
                    new Size(getCameraScreen().getCap().get(Videoio.CAP_PROP_FRAME_WIDTH), getCameraScreen().getCap().get(Videoio.CAP_PROP_FRAME_HEIGHT))));

                getHomeScreen().getLabelRecordStatus().setText("GRAVANDO...");
                getHomeScreen().getLabelRecordStatus().setVisible(true);
                getHomeScreen().getButtonClockwiseRotation().setEnabled(false);
                getHomeScreen().getButtonCounterclockwiseRotation().setEnabled(false);
                getHomeScreen().getButtonZoomOut().setEnabled(false);
                
                setRotationID(0);
                setDownscalingFactor(1.0);

            } catch (Exception e) {
                UserAlert userAlert = new UserAlert("Erro na Gravação de Vídeo"); 
            }

        }
        else{
            UserAlert userAlert = new UserAlert("Você não selecionou um destino!");
        }
    }

    public static void stopVideo(FileSaver fileSaver)
    {
        if(getCameraScreen().isRecordVideo())
        {
            getCameraScreen().setRecordVideo(false);
            getCameraScreen().getVideoWriter().release();
            getCameraScreen().setVideoWriter(null);

            getHomeScreen().getLabelRecordStatus().setText("VÍDEO FINALIZADO E SALVO");
            getHomeScreen().getButtonClockwiseRotation().setEnabled(true);
            getHomeScreen().getButtonCounterclockwiseRotation().setEnabled(true);
            getHomeScreen().getButtonZoomOut().setEnabled(true);

        }
    }

    public static boolean isFlipH() {
        return flipH;
    }
    public static void setFlipH(boolean flipH) {
        VideoProcessing.flipH = flipH;
    }

    public static boolean isFlipV() {
        return flipV;
    }
    public static void setFlipV(boolean flipV) {
        VideoProcessing.flipV = flipV;
    }

    public static int getRotationID() {
        return rotationID;
    }
    public static void setRotationID(int rotationID) {
        VideoProcessing.rotationID = rotationID;
    }

    public static double getDownscalingFactor() {
        return downscalingFactor;
    }
    public static void setDownscalingFactor(double downscalingFactor) {
        VideoProcessing.downscalingFactor = downscalingFactor;
    }

    public static JLabel getLabelSlider() {
        return labelSlider;
    }
    public static void setLabelSlider(JLabel labelSlider) {
        VideoProcessing.labelSlider = labelSlider;
    }

    public static JSlider getSlider() {
        return slider;
    }
    public static void setSlider(JSlider slider) {
        VideoProcessing.slider = slider;
    }

    public static JLabel getLabelSlider2() {
        return labelSlider2;
    }
    public static void setLabelSlider2(JLabel labelSlider2) {
        VideoProcessing.labelSlider2 = labelSlider2;
    }

    public static ContentHomeScreen getHomeScreen() {
        return homeScreen;
    }
    public static void setHomeScreen(ContentHomeScreen homeScreen) {
        VideoProcessing.homeScreen = homeScreen;
    }

    public static CameraScreen getCameraScreen() {
        return cameraScreen;
    }
    public static void setCameraScreen(CameraScreen cameraScreen) {
        VideoProcessing.cameraScreen = cameraScreen;
    }

}
