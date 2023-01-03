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
 * The ConcreteStrategyC class contains trading strategy logic that is used if the user selects
 * trading strategy - "Strategy-C"
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class ConcreteStrategyC extends Strategy {

    /**
     * Getter method for a list of coins involved in strategy C
     * @return	List of coin symbols relevant to a strategy
     */
    @Override
    public List<String> getCoinList() {
        return List.of("ltc", "btc");
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
            if (!((findCoin("BTC", ICoinList)) || (findCoin("LTC", ICoinList)))) {
                String msg = "Strategy-C only relates to coins BTC and LTC. Please ensure row " + index + " selects these coins.";
                return null;
            }
        }


        for (ICoin currICoin : ICoinList) {
            String symbol = currICoin.getSymbol().toLowerCase();
            double price = currICoin.getPrice();

            // dont trade if price of LTC <= 150, and price BTC > 70000
            if ((symbol.equals("ltc") && price <= 150) ||
                    (symbol.equals("btc") && price > 70000) || price == 0) {
                return new ArrayList<ITradeResult>();
            }
        }


        // get ltc price and quantity
        double priceLTC = -1;
        double quantity = -1;

        // search for coin price
        priceLTC = coinPrice.get(AvailableCryptoList.getInstance().getCryptoID("LTC"));

        quantity = (int) (Math.random() * 200) + 50;
        priceLTC = Math.round(priceLTC * 100.0) / 100.0;

        trades.add(new TradeResult("name", "Strategy-C", "LTC", "Sell", quantity, priceLTC, date, false));
        return trades;


    }

}
