package cryptoTrader.UiOperations.broker;

import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.UiOperations.coin.Coin;
import cryptoTrader.UiOperations.coin.ICoin;
import cryptoTrader.UiOperations.strategy.Strategy;
import cryptoTrader.UiOperations.strategy.StrategyCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Broker class is a concrete implementation of IBroker interface.
 * It acts as a model that is mapped to each broker. It also executes trades of the broker.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class Broker implements IBroker {

    /**
     * Name of broker/trader
     */
    private final String brokerName;

    /**
     * Strategy to be executed
     */
    private Strategy strategy;

    /**
     * List of coins broker selects
     */
    private List<ICoin> ICoinList;


    /**
     * Constructor for broker object
     * @param brokerName Name of broker
     * @param coinList   List of coins the broker is interested in trading
     * @param strategyID Which trading strategy the broker is to use (A, B, C, or D)
     */
    public Broker(String brokerName, String[] coinList, char strategyID) {
        // factory design pattern
        Strategy newStrat = StrategyCreator.getInstance().create(strategyID);


        this.strategy = newStrat;
        this.brokerName = brokerName;
        this.ICoinList = generateCoinList(coinList);
    }


    /**
     * Generates an ArrayList of coin objects from an array of strings
     * @param symbolList String array of cryptocoin symbols
     * @return ArrayList of coin objects
     */
    private List<ICoin> generateCoinList(String[] symbolList) {
        List<ICoin> myICoins = new ArrayList<ICoin>();

        ICoin newICoin;

        for (int x = 0; x < symbolList.length; x++) {
            newICoin = new Coin(symbolList[x].toUpperCase(), -1);
            // if the coin entered is not real, it's generated name will be null
            if (newICoin.getName() != null)
                myICoins.add(newICoin);

        }

        return myICoins;
    }

    /**
     * Getter method for a broker's strategy
     * @return Strategy of broker
     */
    @Override
    public Strategy getStrategy() {
        return this.strategy;
    }

    /**
     * Setter method for the broker's strategy
     * @param strategy	New strategy for broker
     */
    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Getter method for the broker's list of coins
     * @return List of coin objects
     */
    @Override
    public List<ICoin> getCoinList() {
        return ICoinList;
    }

    /**
     * Setter method for broker's coin list
     * @param ICoinList
     */
    @Override
    public void setCoinList(List<ICoin> ICoinList) {
        this.ICoinList = ICoinList;
    }

    /**
     * Getter method for the coin list relevant to a broker's strategy
     * @return String list of coins
     */
    @Override
    public List<String> getRelatedCoinList() {
        return strategy.getCoinList();
    }

    /**
     * Method to execute a broker's trade strategy
     * 
     * @return 	List<ITradeResult>    Results of broker trading. Null if the trade was not completed
     */
    @Override
    public List<ITradeResult> execute(List<ICoin> brokerICoinList, int index, Map<String, Double> coinPrice) {
        List<ITradeResult> rtnTrades = strategy.logic(brokerICoinList, index, coinPrice);
        // if trade conditions were not met, the TradeResult will be null
        if (rtnTrades != null)
            for (ITradeResult trade : rtnTrades) {
                trade.setName(brokerName);
            }

        return rtnTrades;
    }


    /**
     * Getter method for a broker's name
     * @return String representing broker's name
     */
    @Override
    public String getName() {
        return brokerName;
    }


}














