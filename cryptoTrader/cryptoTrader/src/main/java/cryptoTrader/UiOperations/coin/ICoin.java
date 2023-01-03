package cryptoTrader.UiOperations.coin;

/**
 * The ICoin interface is used for exposing the public methods of Coin class(getters and setters)
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface ICoin {
	
    /**
     * Getter method for coin price
     * @return double representing price of cryptocoin
     */
    double getPrice();

    /**
     * Setter method for coin price
     * @param newPrice
     */
    void setPrice(double newPrice);

    /**
     * Getter method for coin name
     * @return string representing cryptocoin name
     */
    String getName();

    /**
     * Getter method for coin symbol
     * @return string representing symbol of cryptocoin
     */
    String getSymbol();
}
