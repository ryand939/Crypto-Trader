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
 * The ConcreteStrategyB class contains trading strategy logic that is used if the user selects
 * trading strategy - "Strategy-B"
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class ConcreteStrategyB extends Strategy {

    /**
     * Getter method for a list of coins involved in strategy B
     * @return	List of coin symbols relevant to a strategy
     */
    @Override
    public List<String> getCoinList() {
        return List.of("eth", "ltc");
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
            if (!((findCoin("ETH", ICoinList)) || (findCoin("LTC", ICoinList)))) {
                String msg = "Strategy-B only relates to coins ETH and LTC. Please ensure row " + index + " selects these coins.";
                return null;
            }
        }

        for (ICoin currICoin : ICoinList) {
            String symbol = currICoin.getSymbol().toLowerCase();
            double price = currICoin.getPrice();

            // dont trade if price of eth > 5k, and price ltc < 140
            if ((symbol.equals("eth") && price > 5000) ||
                    (symbol.equals("ltc") && price < 100) || price == 0) {
                return new ArrayList<ITradeResult>();
            }
        }

        // get eth price and quantity
        double priceETH = -1;
        double quantity = -1;

        // search for coin price

        priceETH = coinPrice.get(AvailableCryptoList.getInstance().getCryptoID("ETH"));

        quantity = (int) (Math.random() * 100) + 20;
        priceETH = Math.round(priceETH * 100.0) / 100.0;

        trades.add(new TradeResult("name", "Strategy-B", "ETH", "Buy", quantity, priceETH, date, false));
        return trades;


    }
}
