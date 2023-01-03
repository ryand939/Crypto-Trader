package cryptoTrader.UiOperations.strategy;

import cryptoTrader.Trading.AvailableCryptoList;
import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.Trading.model.TradeResult;
import cryptoTrader.UiOperations.coin.ICoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The ConcreteStrategyA class contains trading strategy logic that is used if the user selects
 * trading strategy - "Strategy-A"
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class ConcreteStrategyA extends Strategy {

    /**
     * Getter method for a list of coins involved in strategy A
     * @return	List of coin symbols relevant to a strategy
     */
    @Override
    public List<String> getCoinList() {
        return List.of("btc", "ada");
    }

    /**
     * Method which defines trading logic
     * @param ICoinList List of coins to be traded
     * @return List<ITradeResult>	Trades which have been completed
     */
    @Override
    public List<ITradeResult> logic(List<ICoin> ICoinList, int index, Map<String, Double> coinPrice) {
        List<ITradeResult> trades = new ArrayList<ITradeResult>();

        if(ICoinList.isEmpty())
            return null;

        for (ICoin ICoin : ICoinList) {
            if (!((findCoin("ADA", ICoinList)) || (findCoin("BTC", ICoinList)))) {
                String msg = "Strategy-A only relates to coins BTC and ADA. Please ensure row " + index + " selects these coins.";
                return null;
            }
        }

        for (ICoin currICoin : ICoinList) {
            String symbol = currICoin.getSymbol().toLowerCase();
            double price = currICoin.getPrice();

            // dont trade if price of bitcoin < 50k, and price cardano > 3
            if ((symbol.equals("btc") && price < 30000) ||
                    (symbol.equals("ada") && price > 3) || price == 0) {
                return new ArrayList<ITradeResult>();
            }
        }


        // get ada price and quantity
        double priceADA = -1;
        double quantity = -1;

        // search for coin price
        priceADA = coinPrice.get(AvailableCryptoList.getInstance().getCryptoID("ADA"));

        // move decimal place right 2, round, then move left 2 / round to 2 decimal place
        quantity = (int) (Math.random() * 100) + 10;

        trades.add(new TradeResult("name", "Strategy-A", "ADA", "Buy", quantity, priceADA, date, false));

        return trades;

    }

}
