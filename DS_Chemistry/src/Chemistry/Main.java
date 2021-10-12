package Chemistry;

import Chemistry.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
      /*  Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //SwingUtils.setDefaultFont(null, 20);
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtils.setDefaultFont("Arial", 20);
                                                  */
        EventQueue.invokeLater(() -> {
            try {
                JFrame frameMain = new FrameMain();
                frameMain.setVisible(true);
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });


    }
}
