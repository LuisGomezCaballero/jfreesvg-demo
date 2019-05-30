package com.luisgomezcaballero.jfreesvgdemo;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

public class Main {

  private static CategoryDataset createDataset() {
    DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
    dataset.add(30.0, null, "Row Key 1", "Column Key 1");
    dataset.add(50.0, null, "Row Key 2", "Column Key 2");
    dataset.add(40.0, null, "Row Key 3", "Column Key 3");
    dataset.add(80.0, null, "Row Key 4", "Column Key 4");
    dataset.add(60.0, null, "Row Key 5", "Column Key 5");
    return dataset;
  }

  private static JFreeChart createChart(CategoryDataset dataset) {
    JFreeChart chart = ChartFactory.createLineChart("Title", "Category Axis Label",
        "Value Axis Label", dataset);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    StatisticalBarRenderer renderer = new StatisticalBarRenderer();
    plot.setRenderer(renderer);
    ChartUtils.applyCurrentTheme(chart);
    renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    renderer.setDefaultItemLabelsVisible(true);

    return chart;
  }

  public static void main(String[] args) {
    JFreeChart chart = createChart(createDataset());
    SVGGraphics2D g2 = new SVGGraphics2D(800, 600);
    Rectangle r = new Rectangle(0, 0, 800, 600);
    chart.draw(g2, r);
    File f = new File("Result.svg");
    try {
      SVGUtils.writeToSVG(f, g2.getSVGElement());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
