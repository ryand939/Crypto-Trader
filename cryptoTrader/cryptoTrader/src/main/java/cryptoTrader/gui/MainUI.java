package cryptoTrader.gui;

import cryptoTrader.Trading.model.TradeResultList;
import cryptoTrader.Trading.view.VisualizeErrorScreen;
import cryptoTrader.Trading.view.VisualizeHistogram;
import cryptoTrader.Trading.view.VisualizeTable;
import cryptoTrader.UiOperations.UIProxy;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

/**
 * The MainUI class is used for creating UI for user to do crypto trading. Main UI of application
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class MainUI extends JFrame implements ActionListener, TableModelListener {

    private static final long serialVersionUID = 1L;

    private static MainUI instance;
    private final JPanel stats;
    private JPanel chartPanel;
    private JPanel tablePanel;

    // Should be a reference to a separate object in actual implementation
    private List<String> selectedList;

    private JTextArea selectedTickerList;
    //	private JTextArea tickerList;
    private JTextArea tickerText;
    private JTextArea BrokerText;
    private JComboBox<String> strategyList;
    private final Map<String, List<String>> brokersTickers = new HashMap<>();
    private final Map<String, String> brokersStrategies = new HashMap<>();
    private final List<String> selectedTickers = new ArrayList<>();
    private final String selectedStrategy = "";
    private final DefaultTableModel dtm;
    private final JTable table;

    private MainUI() {

        // Set window title
        super("Crypto Trading Tool");

        JPanel north = new JPanel();

        JButton trade = new JButton("Perform Trade");
        trade.setActionCommand("refresh");
        trade.addActionListener(this);


        JPanel south = new JPanel();

        south.add(trade);
        dtm = new DefaultTableModel(new Object[]{"Trading Client", "Coin List", "Strategy Name"}, 1);
        table = new JTable(dtm);
        // table.setPreferredSize(new Dimension(600, 300));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
                TitledBorder.CENTER, TitledBorder.TOP));
        TableColumn clientColumn = table.getColumnModel().getColumn(0);
        TableColumn coinColumn = table.getColumnModel().getColumn(1);
        clientColumn.getPropertyChangeListeners();
        Vector<String> strategyNames = new Vector<String>();
        strategyNames.add("None");
        strategyNames.add("Strategy-A");
        strategyNames.add("Strategy-B");
        strategyNames.add("Strategy-C");
        strategyNames.add("Strategy-D");
        TableColumn strategyColumn = table.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox(strategyNames);
        comboBox.addActionListener(this);
        strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
        JButton addRow = new JButton("Add Row");
        JButton remRow = new JButton("Remove Row");
        addRow.setActionCommand("addTableRow");
        addRow.addActionListener(this);
        remRow.setActionCommand("remTableRow");
        remRow.addActionListener(this);

        scrollPane.setPreferredSize(new Dimension(600, 300));
        table.setFillsViewportHeight(true);


        JPanel east = new JPanel();
//		east.setLayout();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
        east.add(scrollPane);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(addRow);
        buttons.add(remRow);
        east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

        // Set charts region
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(1250, 650));
        stats = new JPanel();
        stats.setLayout(new GridLayout(2, 2));

        west.add(stats);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(east, BorderLayout.EAST);
        getContentPane().add(west, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);

        VisualizeTable table = new VisualizeTable(TradeResultList.getInstance());
        VisualizeHistogram histogram = new VisualizeHistogram(TradeResultList.getInstance());
        VisualizeErrorScreen errorScreen = new VisualizeErrorScreen(TradeResultList.getInstance());
    }

    /**
     * Getter method for MainUI instance. Ensures same object is always returned.
     * @return	instance of MainUI
     */
    public static MainUI getInstance() {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    /**+
     * starts the initial rendering of the program
     */
    public static void initiateTrading() {

        JFrame frame = MainUI.getInstance();
        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }

    /**+
     * method to update stats
     * @param component instance of JComponent class
     */
    public void updateStats(JComponent component) {
        stats.add(component);
        stats.revalidate();
    }

    /**+
     * handler to handle different actions like "add row", "remove row" performed by user
     * @param e event of ActionEvent type
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        String msg = UIProxy.getInstance().commandMapper(command, dtm, stats, table);

        if (msg != "") {
            JOptionPane.showMessageDialog(this, msg);
        }
    }

    /**+
     * handler to handle updates in table values
     * @param e event of TableModelEvent type
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        dtm.fireTableDataChanged();
    }
}
