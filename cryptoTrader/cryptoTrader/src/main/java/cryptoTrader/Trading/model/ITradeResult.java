package cryptoTrader.Trading.model;

/**
 * The ITradeResult interface is used for exposing the public methods(used for getting and setting the class properties)
 * of TradeResult class
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface ITradeResult {
	
    /**
     * Setter method for broker name
     * @param brokerName New name for broker
     */
    void setName(String brokerName);

    
    /**
     * String representation of TradeResult
     * @return rtnStr	String containing TradeResult information
     */
    String toString();
    
    /**
     * Getter method for object array containing trade data
     */
    Object[] getTrade();

    /**
     * Getter method for the boolean flag of a trade
     */
    Boolean getFlag();

    /**
     * Setter method for the boolean flag of a trade
     */
    void setFlag(Boolean flag);

    /**
     * Getter method for the strategy used in the trade
     */
    String getStrategy();

    /**
     * Getter method for the broker involved in a trade
     */
    String getBrokerName();

    /**
     * Getter method for the price of a trade
     */
    double getPrice();
}
