package cryptoTrader.UiOperations.broker;

import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.UiOperations.coin.ICoin;
import cryptoTrader.UiOperations.strategy.Strategy;

import java.util.List;
import java.util.Map;

/**
 * The IBroker interface is used for exposing the public methods of Broker class which is a model
 * for the trading client and which executes brokers strategy
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface IBroker {
	
    /**
     * Getter method for a broker's strategy
     * @return Strategy of broker
     */
    Strategy getStrategy();

    
    /**
     * Setter method for the broker's strategy
     * @param strategy	New strategy for broker
     */
    void setStrategy(Strategy strategy);

    
    /**
     * Getter method for the broker's list of coins
     * @return List of coin objects
     */
    List<ICoin> getCoinList();

    
    /**
     * Setter method for broker's coin list
     * @param ICoinList
     */
    void setCoinList(List<ICoin> ICoinList);

    /**
     * Getter method for the coin list relevant to a broker's strategy
     * @return String list of coins
     */
    List<String> getRelatedCoinList();

    /**
     * Method to execute a broker's trade strategy
     * @return List<ITradeResult>    Results of broker trading. Null if the trade was not completed
     */
    List<ITradeResult> execute(List<ICoin> brokerICoinList, int index, Map<String, Double> coinPrice);

    /**
     * Getter method for a broker's name
     * @return String representing broker's name
     */
    String getName();
}
