package cryptoTrader.UiOperations.strategy;

import cryptoTrader.Trading.AvailableCryptoList;
import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.Trading.model.TradeResult;
import cryptoTrader.UiOperations.coin.ICoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The ConcreteStrategyD class contains trading strategy logic that is used if the user selects
 * trading strategy - "Strategy-D"
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class ConcreteStrategyD extends Strategy {

    /**
     * Getter method for a list of coins involved in strategy D
     * @return	List of coin symbols relevant to a strategy
     */
    @Override
    public List<String> getCoinList() {
        return List.of("xmr", "sol", "eth");
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
            if (!((findCoin("XMR", ICoinList)) || (findCoin("SOL", ICoinList)))) {
                String msg = "Strategy-D only relates to coins XMR and SOL. Please ensure row " + index + " selects these coins.";
                return null;
            }
        }


        double priceXMR = -1;
        double priceSOL = -1;
        double priceETH = -1;
        double quantity = -1;

        for (ICoin currICoin : ICoinList) {
            String symbol = currICoin.getSymbol().toLowerCase();
            double price = currICoin.getPrice();

            // dont trade if price of xmr > 300, or price sol < 140
            if (price == 0) {
                return null;
            }

            if (symbol.equals("xmr") && price > 200) {
                priceXMR = Math.round(price * 100.0) / 100.0;
                quantity = (int) (Math.random() * 100) + 20;
                trades.add(new TradeResult("name", "Strategy-D", "XMR", "Sell", quantity, priceXMR, date, false));
            }

            if (symbol.equals("sol") && price < 150) {
                priceSOL = Math.round(price * 100.0) / 100.0;
                quantity = (int) (Math.random() * 100) + 20;
                trades.add(new TradeResult("name", "Strategy-D", "SOL", "Sell", quantity, priceSOL, date, false));
            }

            if ((symbol.equals("xmr") && price > 300) ||
                    (symbol.equals("sol") && price < 140)) {
                priceETH = coinPrice.get(AvailableCryptoList.getInstance().getCryptoID("ETH"));
                priceETH = Math.round(priceETH * 100.0) / 100.0;
                quantity = (int) (Math.random() * 100) + 20;
                trades.add(new TradeResult("name", "Strategy-D", "ETH", "Buy", quantity, priceETH, date, false));
            }
        }


        return trades;


    }

}
