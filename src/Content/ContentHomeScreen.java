package Content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

import Output.FileSaver;
import VideoProcessing.VideoProcessing;

public class ContentHomeScreen extends JPanel {

    private JLabel labelRecord;
    private JButton buttonRecord;
    private JButton buttonStop;
    private JLabel labelRecordStatus;
    private FileSaver fileSave;

    private JLabel labelOperations;
    private JButton buttonClear;
    private JButton buttonGrayscale;
    private JButton buttonBrightness;
    private JButton buttonContrast;
    private JButton buttonNegative;
    private JButton buttonFlipHorizontal;
    private JButton buttonFlipVertical;
    private JButton buttonCounterclockwiseRotation;
    private JButton buttonClockwiseRotation;
    private JButton buttonZoomOut;

    private JLabel labelFilter;
    private JButton buttonGaussian;
    private JButton buttonCanny;
    private JButton buttonSobel;

    private JLabel labelSlider;
    private JSlider slider;
    private JLabel labelSlider2;

    public ContentHomeScreen(){

        VideoProcessing.setHomeScreen(this);

        setLayout(null);
        setPreferredSize(new Dimension(350, 480));
        setBackground(new Color(76,76,76));

        UIManager.put("ToolTip.background", new ColorUIResource(107, 107, 107));
        UIManager.put("ToolTip.foreground", new ColorUIResource(229,229,229));
        UIManager.put("ToolTip.border", new LineBorder(new Color(68,68,68),2));
        UIManager.put("ToolTip.font", new Font(null, Font.BOLD, 12));
        
        createComponents();
        configComponents();
        addComponents();

    }


    private void createComponents(){

        setLabelRecord(new JLabel("GRAVAR VÍDEO")); 

        ImageIcon recordIcon = new ImageIcon("img/recordIcon.png");
        Image recordIconImg = recordIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonRecord(new JButton("", new ImageIcon(recordIconImg)));

        ImageIcon stopIcon = new ImageIcon("img/stopIcon.png");
        Image stopIconImg = stopIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonStop(new JButton("", new ImageIcon(stopIconImg)));

        setLabelRecordStatus(new JLabel("", SwingConstants.RIGHT)); 

        setFileSave(new FileSaver(new FileNameExtensionFilter(".mp4", ".mp4")));
        
        //---------------------------------------------------------

        setLabelOperations(new JLabel("OPERAÇÕES"));

        //---------------------------------------------------------

        ImageIcon clearIcon = new ImageIcon("img/clearIcon.png");
        Image clearIconImg = clearIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonClear(new JButton("", new ImageIcon(clearIconImg)));

        //---------------------------------------------------------

        ImageIcon grayscaleIcon = new ImageIcon("img/grayscaleIcon.png");
        Image grayscaleIconImg = grayscaleIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonGrayscale(new JButton("", new ImageIcon(grayscaleIconImg)));

        //---------------------------------------------------------

        ImageIcon brightnessIcon = new ImageIcon("img/brightnessIcon.png");
        Image brightnessIconImg = brightnessIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonBrightness(new JButton("", new ImageIcon(brightnessIconImg)));

        //---------------------------------------------------------

        ImageIcon contrastIcon = new ImageIcon("img/contrastIcon.png");
        Image contrastIconImg = contrastIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonContrast(new JButton("", new ImageIcon(contrastIconImg)));

        //---------------------------------------------------------

        ImageIcon negativeIcon = new ImageIcon("img/negativeIcon.png");
        Image negativeIconImg = negativeIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonNegative(new JButton("", new ImageIcon(negativeIconImg)));

        //---------------------------------------------------------

        ImageIcon flipHorizontalIcon = new ImageIcon("img/flipHorizontalIcon.png");
        Image flipHorizontalIconImg = flipHorizontalIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonFlipHorizontal(new JButton("", new ImageIcon(flipHorizontalIconImg)));

        //---------------------------------------------------------

        ImageIcon flipVerticalIcon = new ImageIcon("img/flipVerticalIcon.png");
        Image flipVerticalIconImg = flipVerticalIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonFlipVertical(new JButton("", new ImageIcon(flipVerticalIconImg)));

        //---------------------------------------------------------

        ImageIcon counterclockwiseRotationIcon = new ImageIcon("img/counterclockwiseRotationIcon.png");
        Image counterclockwiseRotationIconImg = counterclockwiseRotationIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonCounterclockwiseRotation(new JButton("", new ImageIcon(counterclockwiseRotationIconImg)));

        //---------------------------------------------------------

        ImageIcon clockwiseRotationIcon = new ImageIcon("img/clockwiseRotationIcon.png");
        Image clockwiseRotationIconImg = clockwiseRotationIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonClockwiseRotation(new JButton("", new ImageIcon(clockwiseRotationIconImg)));

        //---------------------------------------------------------

        ImageIcon zoomOutIcon = new ImageIcon("img/zoomOutIcon.png");
        Image zoomOutIconImg = zoomOutIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        
        setButtonZoomOut(new JButton("", new ImageIcon(zoomOutIconImg)));

        //---------------------------------------------------------

        setLabelFilter(new JLabel("FILTROS"));

        setButtonGaussian(new JButton("GAUSSIANO"));

        setButtonCanny(new JButton("CANNY"));

        setButtonSobel(new JButton("SOBEL"));

        //---------------------------------------------------------

        setLabelSlider(new JLabel("NÍVEL DE APLICAÇÃO"));
        VideoProcessing.setLabelSlider(getLabelSlider());

        setSlider(new JSlider(JSlider.HORIZONTAL));
        VideoProcessing.setSlider(getSlider());

        setLabelSlider2(new JLabel("", SwingConstants.RIGHT));
        VideoProcessing.setLabelSlider2(getLabelSlider2());

    }



    private void configComponents(){
        
        labelRecord.setBounds(20, 0, 200, 50);
        labelRecord.setFont(new Font(null, Font.BOLD, 12));
        labelRecord.setForeground(new Color(229,229,229));
        
        buttonRecord.setBounds(20, 40, 50, 50);
        buttonRecord.setBackground(new Color(107, 107, 107));
        buttonRecord.setFont(new Font(null, Font.BOLD, 18));
        buttonRecord.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonRecord.setForeground(new Color(229,229,229));
        buttonRecord.setToolTipText("Iniciar Gravação de Vídeo");

        buttonRecord.addActionListener(event -> VideoProcessing.recordVideo(getFileSave()));

        buttonStop.setBounds(80, 40, 50, 50);
        buttonStop.setBackground(new Color(107, 107, 107));
        buttonStop.setFont(new Font(null, Font.BOLD, 18));
        buttonStop.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonStop.setForeground(new Color(229,229,229));
        buttonStop.setToolTipText("Parar Gravação de Vídeo");

        buttonStop.addActionListener(event -> VideoProcessing.stopVideo(getFileSave()));

        fileSave.setBounds(140, 40, 500, 50);
        fileSave.setBackground(new Color(76,76,76));

        labelRecordStatus.setBounds(110, 80, 200, 50);
        labelRecordStatus.setFont(new Font(null, Font.BOLD, 12));
        labelRecordStatus.setForeground(new Color(229,229,229));
        labelRecordStatus.setVisible(false);

        //---------------------------------------------------------

        labelOperations.setBounds(20, 90, 200, 50);
        labelOperations.setFont(new Font(null, Font.BOLD, 12));
        labelOperations.setForeground(new Color(229,229,229));

        //---------------------------------------------------------

        buttonClear.setBounds(20, 130, 50, 50);
        buttonClear.setBackground(new Color(107, 107, 107));
        buttonClear.setFont(new Font(null, Font.BOLD, 18));
        buttonClear.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonClear.setForeground(new Color(229,229,229));
        buttonClear.setToolTipText("Descartar Alterações");

        buttonClear.addActionListener(event -> VideoProcessing.setOperation('R'));

        //---------------------------------------------------------

        buttonGrayscale.setBounds(80, 130, 50, 50);
        buttonGrayscale.setBackground(new Color(107, 107, 107));
        buttonGrayscale.setFont(new Font(null, Font.BOLD, 18));
        buttonGrayscale.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonGrayscale.setForeground(new Color(229,229,229));
        buttonGrayscale.setToolTipText("Converter para Escala de Cinza (Luminância)");

        buttonGrayscale.addActionListener(event -> VideoProcessing.setOperation('L'));

        //---------------------------------------------------------

        buttonBrightness.setBounds(140, 130, 50, 50);
        buttonBrightness.setBackground(new Color(107, 107, 107));
        buttonBrightness.setFont(new Font(null, Font.BOLD, 18));
        buttonBrightness.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonBrightness.setForeground(new Color(229,229,229));
        buttonBrightness.setToolTipText("Aplicar Brilho");

        buttonBrightness.addActionListener(event -> VideoProcessing.setOperation('B'));

        //---------------------------------------------------------

        buttonContrast.setBounds(200, 130, 50, 50);
        buttonContrast.setBackground(new Color(107, 107, 107));
        buttonContrast.setFont(new Font(null, Font.BOLD, 18));
        buttonContrast.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonContrast.setForeground(new Color(229,229,229));
        buttonContrast.setToolTipText("Aplicar Contraste");

        buttonContrast.addActionListener(event -> VideoProcessing.setOperation('C'));

        //---------------------------------------------------------

        buttonNegative.setBounds(260, 130, 50, 50);
        buttonNegative.setBackground(new Color(107, 107, 107));
        buttonNegative.setFont(new Font(null, Font.BOLD, 18));
        buttonNegative.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonNegative.setForeground(new Color(229,229,229));
        buttonNegative.setToolTipText("Negativo da Imagem");

        buttonNegative.addActionListener(event -> VideoProcessing.setOperation('N'));
        
        //---------------------------------------------------------

        buttonFlipHorizontal.setBounds(20, 190, 50, 50);
        buttonFlipHorizontal.setBackground(new Color(107, 107, 107));
        buttonFlipHorizontal.setFont(new Font(null, Font.BOLD, 18));
        buttonFlipHorizontal.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonFlipHorizontal.setForeground(new Color(229,229,229));
        buttonFlipHorizontal.setToolTipText("Espelhamento Horizontal");

        buttonFlipHorizontal.addActionListener(event -> VideoProcessing.setFlipH(!VideoProcessing.isFlipH()));

        //---------------------------------------------------------

        buttonFlipVertical.setBounds(80, 190, 50, 50);
        buttonFlipVertical.setBackground(new Color(107, 107, 107));
        buttonFlipVertical.setFont(new Font(null, Font.BOLD, 18));
        buttonFlipVertical.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonFlipVertical.setForeground(new Color(229,229,229));
        buttonFlipVertical.setToolTipText("Espelhamento Vertical");

        buttonFlipVertical.addActionListener(event -> VideoProcessing.setFlipV(!VideoProcessing.isFlipV()));

        //---------------------------------------------------------

        buttonCounterclockwiseRotation.setBounds(140, 190, 50, 50);
        buttonCounterclockwiseRotation.setBackground(new Color(107, 107, 107));
        buttonCounterclockwiseRotation.setFont(new Font(null, Font.BOLD, 18));
        buttonCounterclockwiseRotation.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonCounterclockwiseRotation.setForeground(new Color(229,229,229));
        buttonCounterclockwiseRotation.setToolTipText("Rotacionar 90º no Sentido Anti-Horário");

        buttonCounterclockwiseRotation.addActionListener(event -> VideoProcessing.rotation(false));

        //---------------------------------------------------------

        buttonClockwiseRotation.setBounds(200, 190, 50, 50);
        buttonClockwiseRotation.setBackground(new Color(107, 107, 107));
        buttonClockwiseRotation.setFont(new Font(null, Font.BOLD, 18));
        buttonClockwiseRotation.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonClockwiseRotation.setForeground(new Color(229,229,229));
        buttonClockwiseRotation.setToolTipText("Rotacionar 90º no Sentido Horário");

        buttonClockwiseRotation.addActionListener(event -> VideoProcessing.rotation(true));

        //---------------------------------------------------------

        buttonZoomOut.setBounds(260, 190, 50, 50);
        buttonZoomOut.setBackground(new Color(107, 107, 107));
        buttonZoomOut.setFont(new Font(null, Font.BOLD, 18));
        buttonZoomOut.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonZoomOut.setForeground(new Color(229,229,229));
        buttonZoomOut.setToolTipText("Aplicar Redução (Zoom Out)");

        buttonZoomOut.addActionListener(event -> VideoProcessing.zoomOut());

        //---------------------------------------------------------

        labelFilter.setBounds(20, 240, 200, 50);
        labelFilter.setFont(new Font(null, Font.BOLD, 12));
        labelFilter.setForeground(new Color(229,229,229));

        //---------------------------------------------------------

        buttonGaussian.setBounds(20, 280, 90, 40);
        buttonGaussian.setBackground(new Color(107, 107, 107));
        buttonGaussian.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonGaussian.setFont(new Font(null, Font.BOLD, 12));
        buttonGaussian.setForeground(new Color(229,229,229));
        buttonGaussian.setToolTipText("Filtro Gaussiano (Produz Borramento)");

        buttonGaussian.addActionListener(event -> VideoProcessing.setOperation('G'));

        //---------------------------------------------------------

        buttonCanny.setBounds(120, 280, 90, 40);
        buttonCanny.setBackground(new Color(107, 107, 107));
        buttonCanny.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonCanny.setFont(new Font(null, Font.BOLD, 12));
        buttonCanny.setForeground(new Color(229,229,229));
        buttonCanny.setToolTipText("Filtro Canny (Detector de Bordas)");

        buttonCanny.addActionListener(event -> VideoProcessing.setOperation('E'));

        //---------------------------------------------------------

        buttonSobel.setBounds(220, 280, 90, 40);
        buttonSobel.setBackground(new Color(107, 107, 107));
        buttonSobel.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonSobel.setFont(new Font(null, Font.BOLD, 12));
        buttonSobel.setForeground(new Color(229,229,229));
        buttonSobel.setToolTipText("Filtro Sobel (Estimativa do Gradiente)");

        buttonSobel.addActionListener(event -> VideoProcessing.setOperation('S'));

        //---------------------------------------------------------

        labelSlider.setBounds(20, 320, 200, 50);
        labelSlider.setFont(new Font(null, Font.BOLD, 12));
        labelSlider.setForeground(new Color(229,229,229));
        labelSlider.setVisible(false);

        slider.setBounds(20, 355, 290, 50);
        slider.setBackground(new Color(76,76,76));
        slider.setFont(new Font(null, Font.BOLD, 12));
        slider.setForeground(new Color(229,229,229));
        slider.setVisible(false);

        slider.addChangeListener(event -> VideoProcessing.sliderChange());

        labelSlider2.setBounds(20, 390, 290, 50);
        labelSlider2.setFont(new Font(null, Font.BOLD, 12));
        labelSlider2.setForeground(new Color(229,229,229));


    }

    private void addComponents(){

        add(labelRecord);                       // Label da Gravação de Vídeo
        add(buttonRecord);                      // Botão de Iniciar Gravação de Vídeo
        add(buttonStop);                        // Botão de Parar Gravação de Vídeo
        add(labelRecordStatus);                 // Label do Status da Gravação
        add(fileSave);                          // Campo de escolha de arquivo para salvamento

        add(labelOperations);                   // Label das Operações

        add(buttonClear);                       // Botão de Descartar Alterações
        add(buttonGrayscale);                   // Botão de Conversão para escala de cinza
        add(buttonBrightness);                  // Botão de Aplicar Brilho
        add(buttonContrast);                    // Botão de Aplicar Contraste
        add(buttonNegative);                    // Botão de Exibir Negativo da Imagem

        add(buttonFlipHorizontal);              // Botão de Espelhamento Horizontal
        add(buttonFlipVertical);                // Botão de Espelhamento Vertical
        add(buttonCounterclockwiseRotation);    // Botão de Rotacionar 90º Graus no Sentido Anti-Horário
        add(buttonClockwiseRotation);           // Botão de Rotacionar 90º Graus no Sentido Horário
        add(buttonZoomOut);                     // Botão de Aplicar o Zoom Out

        add(labelFilter);                       // Label dos Filtros
        add(buttonGaussian);                    // Botão de Aplicar Filtro Gaussiano
        add(buttonCanny);                       // Botão de Aplicar Filtro Canny
        add(buttonSobel);                       // Botão de Aplicar Filtro Sobel

        add(labelSlider);                       // Label do Slider
        add(slider);                            // Slider
        add(labelSlider2);                      // Label Abaixo do Slider

    }

    public JLabel getLabelRecord() {
        return labelRecord;
    }
    public void setLabelRecord(JLabel labelRecord) {
        this.labelRecord = labelRecord;
    }

    public JButton getButtonRecord() {
        return buttonRecord;
    }
    public void setButtonRecord(JButton buttonRecord) {
        this.buttonRecord = buttonRecord;
    }

    public JLabel getLabelRecordStatus() {
        return labelRecordStatus;
    }
    public void setLabelRecordStatus(JLabel labelRecordStatus) {
        this.labelRecordStatus = labelRecordStatus;
    }

    public FileSaver getFileSave() {
        return fileSave;
    }
    public void setFileSave(FileSaver fileSave) {
        this.fileSave = fileSave;
    }

    public JLabel getLabelOperations() {
        return labelOperations;
    }
    public void setLabelOperations(JLabel labelOperations) {
        this.labelOperations = labelOperations;
    }

    public JButton getButtonClear() {
        return buttonClear;
    }
    public void setButtonClear(JButton buttonClear) {
        this.buttonClear = buttonClear;
    }

    public JButton getButtonGrayscale() {
        return buttonGrayscale;
    }
    public void setButtonGrayscale(JButton buttonGrayscale) {
        this.buttonGrayscale = buttonGrayscale;
    }

    public JButton getButtonBrightness() {
        return buttonBrightness;
    }
    public void setButtonBrightness(JButton buttonBrightness) {
        this.buttonBrightness = buttonBrightness;
    }

    public JButton getButtonContrast() {
        return buttonContrast;
    }
    public void setButtonContrast(JButton buttonContrast) {
        this.buttonContrast = buttonContrast;
    }

    public JButton getButtonNegative() {
        return buttonNegative;
    }
    public void setButtonNegative(JButton buttonNegative) {
        this.buttonNegative = buttonNegative;
    }

    public JButton getButtonFlipHorizontal() {
        return buttonFlipHorizontal;
    }
    public void setButtonFlipHorizontal(JButton buttonFlipHorizontal) {
        this.buttonFlipHorizontal = buttonFlipHorizontal;
    }

    public JButton getButtonFlipVertical() {
        return buttonFlipVertical;
    }
    public void setButtonFlipVertical(JButton buttonFlipVertical) {
        this.buttonFlipVertical = buttonFlipVertical;
    }

    public JButton getButtonClockwiseRotation() {
        return buttonClockwiseRotation;
    }
    public void setButtonClockwiseRotation(JButton buttonClockwiseRotation) {
        this.buttonClockwiseRotation = buttonClockwiseRotation;
    }

    public JButton getButtonCounterclockwiseRotation() {
        return buttonCounterclockwiseRotation;
    }
    public void setButtonCounterclockwiseRotation(JButton buttonCounterclockwiseRotation) {
        this.buttonCounterclockwiseRotation = buttonCounterclockwiseRotation;
    }

    public JButton getButtonZoomOut() {
        return buttonZoomOut;
    }
    public void setButtonZoomOut(JButton buttonZoomOut) {
        this.buttonZoomOut = buttonZoomOut;
    }

    public JLabel getLabelFilter() {
        return labelFilter;
    }
    public void setLabelFilter(JLabel labelFilter) {
        this.labelFilter = labelFilter;
    }

    public JButton getButtonGaussian() {
        return buttonGaussian;
    }
    public void setButtonGaussian(JButton buttonGaussian) {
        this.buttonGaussian = buttonGaussian;
    }

    public JButton getButtonCanny() {
        return buttonCanny;
    }
    public void setButtonCanny(JButton buttonCanny) {
        this.buttonCanny = buttonCanny;
    }

    public JButton getButtonSobel() {
        return buttonSobel;
    }
    public void setButtonSobel(JButton buttonSobel) {
        this.buttonSobel = buttonSobel;
    }

    public JLabel getLabelSlider() {
        return labelSlider;
    }
    public void setLabelSlider(JLabel labelSlider) {
        this.labelSlider = labelSlider;
    }

    public JSlider getSlider() {
        return slider;
    }
    public void setSlider(JSlider slider) {
        this.slider = slider;
    }

    public JLabel getLabelSlider2() {
        return labelSlider2;
    }
    public void setLabelSlider2(JLabel labelSlider2) {
        this.labelSlider2 = labelSlider2;
    }

    public JButton getButtonStop() {
        return buttonStop;
    }
    public void setButtonStop(JButton buttonStop) {
        this.buttonStop = buttonStop;
    }

}
