package cryptoTrader.Trading;

import java.util.List;
import java.util.Map;

/**
 * The IDataFetcher interface is used for exposing the public methods of DataFetcher class that is
 * used for getting price of coins
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface IDataFetcher {

    /**+
     * method to fetch coin prices
     * @param coinList List of coins for which we want to fetch the price
     * @return map containing coin id and price
     */
    Map<String, Double> getPricesForCoinList(List<String> coinList);
}
