import org.opencv.core.Core;

import Alert.UserAlert;
import Screen.HomeScreen;

public class App {
    public static void main(String[] args){

      // Carrega a biblioteca core do OpenCV
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

      try {
         new HomeScreen();
      } catch (Exception e) {
         UserAlert userAlert = new UserAlert("ERRO - Erro ao criar Tela Inicial"); 
      }

    }
}
