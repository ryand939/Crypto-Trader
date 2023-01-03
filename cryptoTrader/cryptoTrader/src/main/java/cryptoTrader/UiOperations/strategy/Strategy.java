package cryptoTrader.UiOperations.strategy;

import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.UiOperations.coin.ICoin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Strategy abstract class is extended by different strategy classes to implement different trade execution
 * logic based on the strategy selected by user and has some common methods being used by all classes that extend it.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public abstract class Strategy {

    /**
     * Current date in format dd-mm-yyyy
     */
    String date;

    /**
     * Constructor for Strategy object
     */
    public Strategy() {
        // get date
        String format = "dd-MM-yyyy";
        date = new SimpleDateFormat(format).format(new Date());
    }

    /**
     * Getter method for a list of coins involved in a particular trading strategy
     * @return	List of coins relevant to a strategy
     */
    public abstract List<String> getCoinList();

    /**
     * Method which defines trading logic
     * @param ICoinList List of coins to be traded
     * @return List<ITradeResult>	Trades which have been completed
     */
    public abstract List<ITradeResult> logic(List<ICoin> ICoinList, int index, Map<String, Double> coinPrice);

    /**+
     * method to find if the coin symbol exists in the list of coins provided
     * @param symbol coin symbol to check in the given list
     * @param ICoinList list of coins where we need to check if the symbol is present
     * @return  true if the coin symbol exists in the list of coins provided otherwise false
     */
    boolean findCoin(String symbol, List<ICoin> ICoinList) {
        for (ICoin ICoin : ICoinList) {
            if (ICoin.getSymbol().equalsIgnoreCase(symbol))
                return true;
        }

        return false;
    }

}
