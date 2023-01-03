package cryptoTrader.Trading.model;

/**
 * The TradeResult class is a concrete implementation of ITradeResult interface.
 * It is used as a model for creating trade result entries.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class TradeResult implements ITradeResult {

    /**
     * Attributes which describe the result of a trade
     */
    private String brokerName;
	private final String strategy;
	private final String coinTraded;
	private final String action;
	private final String date;
    private final double quantity;
	private final double price;
    private Boolean flag;

    /**
     * Constructor for TradeResult class
     *
     * @param brokerName Name of broker
     * @param strategy   Strategy used when trading
     * @param coinTraded Coin that was bought/sold
     * @param action     Type of trade (buy/sell)
     * @param quantity   Quantity of cryptocoin traded
     * @param price      Price at which the cryptocoin was traded
     * @param date       Date on which the trade was completed
     * @param flag		 Whether or not an error must be logged
     */
    public TradeResult(String brokerName, String strategy, String coinTraded, String action, double quantity, double price, String date, Boolean flag) {
        this.brokerName = brokerName;
        this.strategy = strategy;
        this.coinTraded = coinTraded;
        this.action = action;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.flag = flag;
    }

    /**
     * Setter method for broker name
     * @param brokerName New name for broker
     */
    @Override
    public void setName(String brokerName) {
        this.brokerName = brokerName;
    }

    /**
     * String representation of TradeResult
     * @return rtnStr	String containing TradeResult information
     */
    @Override
    public String toString() {
        String rtnStr = brokerName + " " + strategy + " " + coinTraded + " " + action + " " + quantity + " " + price + " " + date;
        return rtnStr;
    }

    /**
     * Getter method for object array containing trade data
     */
    @Override
    public Object[] getTrade() {
        Object[] obj = {brokerName, strategy, coinTraded, action, quantity, price, date};
        return obj;
    }

    /**
     * Getter method for the boolean flag of a trade
     */
    @Override
    public Boolean getFlag() {
        return this.flag;
    }

    /**
     * Setter method for the boolean flag of a trade
     */
    @Override
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * Getter method for the strategy used in the trade
     */
    @Override
    public String getStrategy() {
        return this.strategy;
    }

    /**
     * Getter method for the broker involved in a trade
     */
    @Override
    public String getBrokerName() {
        return this.brokerName;
    }

    /**
     * Getter method for the price of a trade
     */
    @Override
    public double getPrice() {
        return this.price;
    }

}
