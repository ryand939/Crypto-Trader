package cryptoTrader.Trading.model;

import cryptoTrader.Trading.view.Subject;
import cryptoTrader.UiOperations.broker.Broker;
import cryptoTrader.UiOperations.broker.BrokerList;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.List;

/**
 * The TradeResultList class acts as a model that is used for working with list of trades.
 * It also contains logic for getting the stats of the TradeResultList based on the active brokers
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class TradeResultList extends Subject {

    /**
     * Instance of TradeResultList class
     */
    private static TradeResultList instance;
    
    /**
     * List of TradeResult objects
     */
    private final List<ITradeResult> results;

    /**
     * Constructor for TradeResultList object
     */
    private TradeResultList() {
        results = new ArrayList<ITradeResult>();

    }

    /**
     * Singleton method for TradeResultList object. Ensures same TradeResultList object is always returned
     *
     * @return TradeResultList object
     */
    public static TradeResultList getInstance() {
        if (instance == null)
            instance = new TradeResultList();

        return instance;
    }

    /**
     * Method to add a number of TradeResult objects to the list
     * @param trades	List of trades to add
     */
    public void add(List<ITradeResult> trades) {
        results.addAll(trades);
    }

    
    /**
     * Getter method for any particular TradeResult object in the list
     * @param index	Index of specified trade result
     * @return	TradeResult at specified index
     */
    public ITradeResult get(int index) {
        return results.get(index);
    }

    
    /**
     * Getter method for length of TradeResultList
     * @return	Length of TradeResultList
     */
    public int length() {
        return results.size();
    }

    /**
     * Method to notify any observers
     */
    public void display() {
        notifyObservers();
    }

    
    /**
     * Method to get the stats of the TradeResultList based on the active brokers
     * @return	Dataset containing past trade data
     */
    public DefaultCategoryDataset getStats() {

        DefaultCategoryDataset set = new DefaultCategoryDataset();
        ITradeResult trade;
        String stratUsed;
        int[] stratTally;
        // for every broker
        for (int brokerNum = 0; brokerNum < BrokerList.getInstance().length(); brokerNum++) {
            // reset the tally
            stratTally = new int[4];
            // current broker
            Broker currBroker = BrokerList.getInstance().getBroker(brokerNum);

            for (int tradeNum = 0; tradeNum < TradeResultList.getInstance().length(); tradeNum++) {
                if (currBroker.getName().equals(TradeResultList.getInstance().get(tradeNum).getBrokerName())) {
                    // now figure out what strategy they used
                    stratUsed = TradeResultList.getInstance().get(tradeNum).getStrategy();
                    if (stratUsed.equals("Strategy-A")) stratTally[0]++;
                    else if (stratUsed.equals("Strategy-B")) stratTally[1]++;
                    else if (stratUsed.equals("Strategy-C")) stratTally[2]++;
                    else if (stratUsed.equals("Strategy-D")) stratTally[3]++;
                }
            }
            // done current broker, now setValue in dataset
            set.setValue(stratTally[0], currBroker.getName(), "Strategy-A");
            set.setValue(stratTally[1], currBroker.getName(), "Strategy-B");
            set.setValue(stratTally[2], currBroker.getName(), "Strategy-C");
            set.setValue(stratTally[3], currBroker.getName(), "Strategy-D");

        }

        return set;
    }

}





























