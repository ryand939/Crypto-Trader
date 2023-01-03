package cryptoTrader.Trading.controller;

import cryptoTrader.Trading.AvailableCryptoList;
import cryptoTrader.Trading.DataFetcher;
import cryptoTrader.Trading.IDataFetcher;
import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.Trading.model.TradeResult;
import cryptoTrader.Trading.model.TradeResultList;
import cryptoTrader.UiOperations.broker.Broker;
import cryptoTrader.UiOperations.broker.BrokerList;
import cryptoTrader.UiOperations.coin.ICoin;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Trade class is a concrete implementation of ITrade interface.
 * It contains implementation of trade execution login
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class Trade implements ITrade {


    /**
     * Current date in the format dd-mm-yyyy
     */
    private final String date;


    /**
     * Constructor for Trade object
     */
    public Trade() {
        // get date
        String format = "dd-MM-yyyy";
        date = new SimpleDateFormat(format).format(new Date());
    }

    /**
     * Method to execute trade.
     * @param brokers List of brokers who wish to trade cryptocoins
     */
    @Override
    public void executeTrade(BrokerList brokers) {
        Map<String, Double> coinPrice = null;
        coinPrice = fetchCoinPrices(brokers);
        Broker broker = null;

        for (int x = 0; x < brokers.length(); x++) {
            broker = brokers.getBroker(x);
            List<ICoin> brokerICoinList = broker.getCoinList();
            setBrokerCoinPrice(brokerICoinList, coinPrice);

            // Invoke the trade strategy belonging to the broker
            List<ITradeResult> trades = broker.execute(brokerICoinList, x + 1, coinPrice);

            // didn't complete trade either because values
            // didnt hold or user was rate-limited. Next
            String strategyName = "";
            strategyName = getStrategyName(strategyName, broker);

            String coinList = "";
            coinList = getCoinList(brokerICoinList);

            if (trades == null) {

                TradeResultList.getInstance().add(List.of(new TradeResult(broker.getName(), strategyName, coinList, "FAILED", 0, 0, date, true)));

            }
            else if(trades.isEmpty()){
                TradeResultList.getInstance().add(List.of(new TradeResult(broker.getName(), strategyName, coinList, "FAILED", 0, 0, date, false)));

            }
            else
                TradeResultList.getInstance().add(trades);
            System.out.println("broker count is" + brokers.length());
        }

        // notify observers
        TradeResultList.getInstance().display();


    }

    /**
     * Getter method for a broker's strategy
     * @param strategyName	String name of strategy
     * @param broker		Broker to get strategy from
     * @return	String name of strategy
     */
    private String getStrategyName(String strategyName, Broker broker) {
        String strategyClassName = broker.getStrategy().getClass().toString();

        if (strategyClassName.endsWith("A"))
            strategyName = "Strategy-A";
        else if (strategyClassName.endsWith("B"))
            strategyName = "Strategy-B";
        else if (strategyClassName.endsWith("C"))
            strategyName = "Strategy-C";
        else if (strategyClassName.endsWith("D"))
            strategyName = "Strategy-D";
        else
            strategyName = "None";

        return strategyName;
    }

    /**+
     * method to format  coin list that is to be displayed in case of a failed trade
     * @param brokerICoinList List of coins relevant to broker
     * @return List of coins relevant to broker in string format
     */
    private String getCoinList(List<ICoin> brokerICoinList) {
        String coinList = "";
        for (ICoin ICoin : brokerICoinList) {
            if (coinList == "")
                coinList = ICoin.getSymbol();
            else
                coinList = coinList + "," + ICoin.getSymbol();
        }
        return coinList;
    }

    /**
     * Setter method for prices of coins
     * @param brokerICoinList	List of coins
     * @param coinPrice	Datastructure containing coin prices
     */
    private void setBrokerCoinPrice(List<ICoin> brokerICoinList, Map<String, Double> coinPrice) {
        // coin list of current broker being processed
        // update price of each coin in the broker's list
        for (int y = 0; y < brokerICoinList.size(); y++) {
            if (coinPrice.get(brokerICoinList.get(y).getName()) != null)
                brokerICoinList.get(y).setPrice(coinPrice.get(brokerICoinList.get(y).getName()));
            else
                brokerICoinList.get(y).setPrice(0.0);
        }
    }

    /**+
     * method to fetch coin prices
     * @param brokers List of brokers who wish to trade crypto coins
     * @return map containing coin id and price
     */
    private Map<String, Double> fetchCoinPrices(BrokerList brokers) {
        IDataFetcher fetch = new DataFetcher();

        //List<String> coinList = new ArrayList<String>();
        Set<String> set = new LinkedHashSet<>(new ArrayList<String>());
        for (int x = 0; x < brokers.length(); x++) {
            if (brokers.getBroker(x).getRelatedCoinList() != null)
                set.addAll(brokers.getBroker(x).getRelatedCoinList());
        }
        List<String> coinList = new ArrayList<String>();
        for (String coin : set) {
            coinList.add(AvailableCryptoList.getInstance().getCryptoID(coin.toUpperCase()));
        }
        String ids = String.join(",", coinList);
        return fetch.getPricesForCoinList(coinList);
    }


}
