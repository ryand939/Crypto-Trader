package cryptoTrader.UiOperations.strategy;

import cryptoTrader.Trading.model.ITradeResult;
import cryptoTrader.UiOperations.coin.ICoin;

import java.util.List;
import java.util.Map;

/**
 * The ConcreteStrategyN class contains trading strategy logic that is used if the user selects
 * trading strategy - "None"
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class ConcreteStrategyN extends Strategy {
	
    /**
     * Getter method for a list of coins involved in a strategy
     * @return	List of coin symbols relevant to a strategy
     */
    @Override
    public List<String> getCoinList() {
        return null;
    }

    /**
     * Method which defines trading logic
     * @param ICoinList List of coins to be traded
     * @return List<ITradeResult> Trades which have been completed
     */
    @Override
    public List<ITradeResult> logic(List<ICoin> ICoinList, int index, Map<String, Double> coinPrice) {
        return null;
    }
}
