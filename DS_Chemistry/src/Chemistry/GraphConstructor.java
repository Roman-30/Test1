package Chemistry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class GraphConstructor {

    private final JPanel graphPanel;
    private final JFrame frame;
    private double[][] data;
    private ChartPanel chartPanel;

    public GraphConstructor(double[][] data, ChartPanel chartPanel, JPanel graphPanel, JFrame frame) {
        this.data = data;
        this.chartPanel = chartPanel;
        this.graphPanel = graphPanel;
        this.frame = frame;
    }

    public GraphConstructor(ChartPanel chartPanel, JPanel graphPanel, JFrame frame) {
        this(null, chartPanel, graphPanel, frame);
    }

    private void customizeChartDefault(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYDataset ds = plot.getDataset();
        for (int i = 0; i < ds.getSeriesCount(); i++) {
            chart.getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(2));
        }
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void performanceTestDemo() {
        DefaultXYDataset ds = new DefaultXYDataset();
        ds.addSeries("Зависимость", data);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Зависимость",
                "First",
                "Second",
                ds
        );
        customizeChartDefault(chart);

        /*if (frame.getHeight() != 600) {
            final int height = frame.getHeight() + 400;
            frame.setSize(frame.getWidth(), height);
        } */
        chartPanel = new ChartPanel(chart);
        chartPanel.setSize(graphPanel.getSize());
        graphPanel.removeAll();
        graphPanel.revalidate();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.updateUI();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] datum : data) {
            for (double v : datum) {
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
