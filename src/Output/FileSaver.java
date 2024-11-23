package Output;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Optional;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Alert.UserAlert;

public class FileSaver extends JPanel{

    private JButton         buttonFile;
    private JTextField      fieldFile;
    private JFileChooser    windowFile;
    private File            file;

    public FileSaver(FileNameExtensionFilter filter){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        createComponents();
        configComponents(filter);
        addComponents();

    }

    private void createComponents(){

        ImageIcon imgIcon = new ImageIcon("img/fileIcon.png");
        Image img = imgIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);

        setButtonFile(new JButton("", new ImageIcon(img)));

        setFieldFile(new JTextField());

        setWindowFile(new JFileChooser());

    }

    private void configComponents(FileNameExtensionFilter filter){

        buttonFile.setBackground(new Color(107, 107, 107));
        buttonFile.setBorder(new LineBorder(new Color(107, 107, 107),0));
        buttonFile.setMaximumSize(new Dimension(50, 50));
        buttonFile.setMinimumSize(new Dimension(80, 50));
        buttonFile.setFocusable(false);

        buttonFile.addActionListener(event -> selectFile(event));

        fieldFile.setFont(new Font(null, Font.BOLD, 12));
        fieldFile.setBackground(new Color(68,68,68));
        fieldFile.setBorder(new LineBorder(new Color(68,68,68),10));
        fieldFile.setMaximumSize(new Dimension(120, 50));
        fieldFile.setEditable(false);
        fieldFile.setFocusable(false);
        fieldFile.setForeground(new Color(204, 204, 204));

        windowFile.setVisible(false);
        windowFile.setAcceptAllFileFilterUsed(false);
        windowFile.addChoosableFileFilter(filter);

    }

    private void addComponents(){

        add(buttonFile, BorderLayout.WEST);     // Botão de Procurar Arquivo
        add(fieldFile, BorderLayout.EAST);      // Campo com a Localização do Arquivo
        add(windowFile);                        // Janela para Procurar Arquivo
    }

    private void selectFile(ActionEvent e){

        windowFile.setVisible(true);
        int windowState = windowFile.showOpenDialog(null);

        if (windowState == JFileChooser.APPROVE_OPTION){
            // Imprime no field relacionado ao seletor o caminho do arquivo selecionado
            String filePath = windowFile.getSelectedFile().getPath();
            if(!getExtension(filePath).equals(Optional.of("mp4"))){
                filePath += ".mp4";
            }
            fieldFile.setText(filePath);
        }
    }

    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public JButton getButtonFile() {
        return buttonFile;
    }
    public void setButtonFile(JButton buttonFile) {
        this.buttonFile = buttonFile;
    }

    public JTextField getFieldFile() {
        return fieldFile;
    }
    public void setFieldFile(JTextField fieldFile) {
        this.fieldFile = fieldFile;
    }

    public JFileChooser getWindowFile() {
        return windowFile;
    }
    public void setWindowFile(JFileChooser windowFile) {
        this.windowFile = windowFile;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

}