package cryptoTrader.Trading.view;

import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.Trading.model.TradeResultList;
import cryptoTrader.gui.MainUI;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * The DataVisualizationCreator class is a concrete implementation of IDataVisualizationCreator interface.
 * It contains logic for displaying the data in table and chart format. It also contains logic for displaying
 * error messages to users for relevant failed trades.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class DataVisualizationCreator implements IDataVisualizationCreator {

    /**+
     * method to populate table with all the trades executed
     * @param trades list of all the trades made
     */
    @Override
    public void createTableOutput(TradeResultList trades) {
        // Dummy dates for demo purposes. These should come from selection menu
        Object[] columnNames = {"Trader", "Strategy", "CryptoCoin", "Action", "Quantity", "Price", "Date"};


        // generate 2d object array from TradeResultList
        Object[][] data = new Object[trades.length()][7];
        for (int x = 0; x < trades.length(); x++) {
            ITradeResult curr = trades.get(x);
            for (int y = 0; y < 7; y++) {
                data[x][y] = curr.getTrade()[y];
            }
        }


        JTable table = new JTable(data, columnNames);
        //table.setPreferredSize(new Dimension(600, 300));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));


        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);

		MainUI.getInstance().updateStats(scrollPane);
    }

    /**+
     * method to create bar graph for all the trades executed
     * @param trades list of all the trades made
     */
    @Override
    public void createBar(TradeResultList trades) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset = trades.getStats();

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Strategy");
        plot.setDomainAxis(domainAxis);
        LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
        rangeAxis.setRange(new Range(0.1, 20.0));
        plot.setRangeAxis(rangeAxis);

        JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
                true);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);
        MainUI.getInstance().updateStats(chartPanel);
    }

    /**+
     * method to display error popup for failing trades due to unsupported coin type for strategies
     * @param trades list of all the trades made
     */
    @Override
    public void displayError(TradeResultList trades) {
        String msg = "";
        for (int x = 0; x < trades.length(); x++) {
            ITradeResult curr = trades.get(x);
            if (curr.getFlag() == true) {
                curr.setFlag(false);
                String strategy = curr.getStrategy();
                if (strategy == "Strategy-A")
                    msg = strategy + " only relates to coins BTC and ADA. Please ensure " + curr.getBrokerName() + " selects these coins.";
                else if (strategy == "Strategy-B")
                    msg = strategy + " only relates to coins ETH and LTC. Please ensure " + curr.getBrokerName() + " selects these coins.";
                else if (strategy == "Strategy-C")
                    msg = strategy + " only relates to coins LTC and BTC. Please ensure " + curr.getBrokerName() + " selects these coins.";
                else if (strategy == "Strategy-D")
                    msg = strategy + " only relates to coins XMR and SOL. Please ensure " + curr.getBrokerName() + " selects these coins.";
                else
                    msg = "None Strategy does not have any trading logic. Please ensure " + curr.getBrokerName() + " selects a valid strategy.";
            }

        }
        if (msg != "")
            JOptionPane.showMessageDialog(MainUI.getInstance(), msg);

    }
}
