package cryptoTrader.UiOperations;

import cryptoTrader.UiOperations.broker.Broker;
import cryptoTrader.UiOperations.broker.BrokerList;
import cryptoTrader.UiOperations.tradeinitiator.TradeInitiator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The UIProxy class is used for mapping the commands to their functions.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class UIProxy {
	
	/**
	 * Singleton instance of UIProxy object
	 */
    private static UIProxy instance;

    /**
     * Getter method for UIProxy instance. Ensures same object is always returned.
     * @return	instance of UIProxy
     */
    public static UIProxy getInstance() {
        if (instance == null)
            instance = new UIProxy();

        return instance;
    }

    /**+
     * method to map the user actions to relevant functions
     * @param command user action
     * @param dtm instance of internal DefaultTableModel class
     * @param stats instance of internal JPanel class
     * @param table instance of internal JTable class
     * @return error message if there is an error otherwise empty string
     */
    public String commandMapper(String command, DefaultTableModel dtm, JPanel stats, JTable table) {
        String msg = "";
        if ("refresh".equals(command)) {
            TradeInitiator.getInstance().startTrade(dtm, stats);
        } else if ("addTableRow".equals(command)) {
            BrokerList obj = BrokerList.getInstance();
            msg = BrokerList.validateSelection(dtm);
            if (msg == "") {
                updateBrokerList(obj, dtm);
                dtm.addRow(new String[3]);
            } else {
                return msg;
            }

        } else if ("remTableRow".equals(command)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1 && dtm.getValueAt(selectedRow, 0) != null && !(selectedRow == (dtm.getRowCount()-1))) {
                // delete broker from broker list to prevent trading beyond the grave
                BrokerList.getInstance().removeBroker(dtm.getValueAt(selectedRow, 0).toString());
                dtm.removeRow(selectedRow);
            }

        }
        return msg;
    }

    /**+
     * method to add new brokers to the broker list instance
     * @param obj   broker list instance
     * @param dtm   instance of internal DefaultTableModel class containing user inputs
     */
    private void updateBrokerList(BrokerList obj, DefaultTableModel dtm) {
        int count = dtm.getRowCount() - 1;
        Object coinObject = dtm.getValueAt(count, 1);
        String[] coinNames = coinObject.toString().split(",");
        for (int x = 0; x < coinNames.length; x++)
            coinNames[x] = coinNames[x].strip().toUpperCase();

        Object strategyObject = dtm.getValueAt(count, 2);
        String strategyName = strategyObject.toString();

        if (strategyName.equals("Strategy-A")) {
            obj.addBroker(new Broker(dtm.getValueAt(count, 0).toString(), coinNames, 'A'));
        }
        if (strategyName.equals("Strategy-B")) {
            obj.addBroker(new Broker(dtm.getValueAt(count, 0).toString(), coinNames, 'B'));
        }
        if (strategyName.equals("Strategy-C")) {
            obj.addBroker(new Broker(dtm.getValueAt(count, 0).toString(), coinNames, 'C'));
        }
        if (strategyName.equals("Strategy-D")) {
            obj.addBroker(new Broker(dtm.getValueAt(count, 0).toString(), coinNames, 'D'));
        }
        if (strategyName.equals("None")) {
            obj.addBroker(new Broker(dtm.getValueAt(count, 0).toString(), coinNames, 'N'));
        }

    }
}
