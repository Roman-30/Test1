package Chemistry;

import Chemistry.util.ArrayUtils;
import Chemistry.util.JTableUtils;
import Chemistry.util.SwingUtils;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FrameMain extends JFrame {
    private JPanel mainPanel;
    private JButton openFile;
    private JButton saveDotButton;
    private JButton saveCsvButton;
    private JPanel graphPanel;
    private JRadioButton fileDotRadioButton;
    private JRadioButton fileCsvRadioButton;
    private JButton saveSvgButton;
    private JTable table1;
    private JButton printButton;

    private ChartPanel panel = null;

    private JFileChooser fileChooserTxtOpen;
    private JFileChooser fileChooserDotOpen;
    private JFileChooser fileChooserTxtSave;
    private JFileChooser fileChooserDotSave;
    private JFileChooser fileChooserImgSave;

    public FrameMain() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 400, 400);
        this.setVisible(true);
        this.pack();

        /*saveCsvButton.setVisible(false);
        saveDotButton.setVisible(false);
        saveSvgButton.setVisible(false);
          */
        ButtonGroup group = new ButtonGroup();
        group.add(fileDotRadioButton);
        group.add(fileCsvRadioButton);

        fileChooserTxtOpen = new JFileChooser();
        fileChooserDotOpen = new JFileChooser();
        fileChooserTxtSave = new JFileChooser();
        fileChooserDotSave = new JFileChooser();
        fileChooserImgSave = new JFileChooser();
        fileChooserTxtOpen.setCurrentDirectory(new File("./files/input"));
        fileChooserDotOpen.setCurrentDirectory(new File("./files/input"));
        fileChooserTxtSave.setCurrentDirectory(new File("./files/input"));
        fileChooserDotSave.setCurrentDirectory(new File("./files/input"));
        fileChooserImgSave.setCurrentDirectory(new File("./files/output"));
        FileFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        FileFilter dotFilter = new FileNameExtensionFilter("DOT files (*.dot)", "dot");
        FileFilter svgFilter = new FileNameExtensionFilter("SVG images (*.svg)", "svg");

        fileChooserTxtOpen.addChoosableFileFilter(txtFilter);
        fileChooserDotOpen.addChoosableFileFilter(dotFilter);
        fileChooserTxtSave.addChoosableFileFilter(txtFilter);
        fileChooserDotSave.addChoosableFileFilter(dotFilter);
        fileChooserImgSave.addChoosableFileFilter(svgFilter);

        GraphConstructor constructor = new GraphConstructor(panel, graphPanel, this);

        ArrayList<String> list = new ArrayList<>();

        JTableUtils.initJTableForArray(table1, 50, true, true, true, true);
        table1.setRowHeight(25);

        openFile.addActionListener(e -> {
            if (fileDotRadioButton.isSelected()) {
                if (fileChooserDotOpen.showOpenDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
                    try (Scanner sc = new Scanner(fileChooserDotOpen.getSelectedFile())) {
                        sc.useDelimiter("\\Z");
                        list.add(sc.next());
                    } catch (Exception exc) {
                        SwingUtils.showErrorMessageBox(exc);
                    }
                }
            } else if (fileCsvRadioButton.isSelected()) {
                if (fileChooserTxtOpen.showOpenDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
                    try (Scanner sc = new Scanner(fileChooserTxtOpen.getSelectedFile())) {
                        sc.useDelimiter("\\Z");
                        list.add(sc.next());
                    } catch (Exception exc) {
                        SwingUtils.showErrorMessageBox(exc);
                    }
                }
            }
            JTableUtils.writeArrayToJTable(table1, decriptList(ArrayUtils.toDoubleArray(list.get(0))));
        });

        printButton.addActionListener(e -> {
            constructor.setData(decriptList(ArrayUtils.toDoubleArray(list.get(0))));
            constructor.performanceTestDemo();
        });


        saveCsvButton.addActionListener(e -> {
            if (fileChooserTxtSave.showSaveDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooserTxtSave.getSelectedFile().getPath();
                if (!filename.toLowerCase().endsWith(".txt")) {
                    filename += ".txt";
                }
                try (FileWriter wr = new FileWriter(filename)) {
                    wr.write(constructor.toString());
                } catch (Exception exc) {
                    SwingUtils.showErrorMessageBox(exc);
                }
            }
        });

     /*   saveSvgButton.addActionListener(e -> {
            if (fileChooserImgSave.showSaveDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooserImgSave.getSelectedFile().getPath();
                if (!filename.toLowerCase().endsWith(".svg")) {
                    filename += ".svg";
                }
                try (FileWriter wr = new FileWriter(filename)) {
                    wr.write(panelGraphPainter.svg);
                } catch (Exception exc) {
                    SwingUtils.showErrorMessageBox(exc);
                }
            }
        });
    } */

    }

    private static double[][] decriptList(double[] arr){
        System.out.println(Arrays.toString(arr));
        int n = 0;
        int k = 0;
        double[][] arr1 = new double[2][arr.length / 2];
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length / 2) {
                n++;
                k = 0;
            }
            arr1[n][k] = arr[i];
            k++;
        }
        return arr1;
    }
}
