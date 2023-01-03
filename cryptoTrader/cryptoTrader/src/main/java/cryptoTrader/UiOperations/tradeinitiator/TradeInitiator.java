package cryptoTrader.UiOperations.tradeinitiator;

import cryptoTrader.Trading.controller.ITrade;
import cryptoTrader.Trading.controller.Trade;
import cryptoTrader.UiOperations.broker.BrokerList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The main function of TradeInitiator class is to initiate trades.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class TradeInitiator {

	/**
	 * Singleton instance of TradeInitiator.
	 */
    private static TradeInitiator instance;

    
    /**
     * Getter method for instance of TradeInitiator. Ensures same object is always returned.
     * @return Instance of TradeInitiator
     */
    public static TradeInitiator getInstance() {
        if (instance == null)
            instance = new TradeInitiator();

        return instance;
    }

    /**
     * Method to start a trade
     * @param dtm
     * @param stats
     */
    public void startTrade(DefaultTableModel dtm, JPanel stats) {
        stats.removeAll();
        ITrade newITrade = new Trade();
        newITrade.executeTrade(BrokerList.getInstance());

    }

}
