package cryptoTrader.UiOperations.coin;

import cryptoTrader.Trading.AvailableCryptoList;

/**
 * The Coin class is a concrete implementation of ICoin interface.
 * It is a model that represent a coin and has some getter and setter methods.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class Coin implements ICoin {

    /**
     * Name of cryptocoin
     */
    private final String name;

    /**
     * Symbol of cryptocoin
     */
    private final String symbol;

    /**
     * Price of cryptocoin
     */
    private double price;

    /**
     * Constructor for Coin object
     *
     * @param symbol
     * @param price
     */
    public Coin(String symbol, float price) {
        this.symbol = symbol.toUpperCase();
        this.name = symbolToName(this.symbol);
        this.price = price;
    }


    /**
     * Constructor for Coin object. Overloaded with name parameter
     *
     * @param name
     * @param symbol
     * @param price
     */
    public Coin(String name, String symbol, float price) {
        this.name = name.toUpperCase();
        this.symbol = symbol;
        this.price = price;
    }


    /**
     * Helper method to convert a symbol to a cryptocoin name
     *
     * @param symbol
     * @return Lowercase name of cryptocoin
     */
    private String symbolToName(String symbol) {
        return AvailableCryptoList.getInstance().getCryptoID(symbol);
    }

    /**
     * Getter method for coin price
     * @return double representing price of cryptocoin
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Setter method for coin price
     * @param newPrice
     */
    @Override
    public void setPrice(double newPrice) {
        price = newPrice;
    }

    /**
     * Getter method for coin name
     * @return string representing cryptocoin name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter method for coin symbol
     * @return string representing symbol of cryptocoin
     */
    @Override
    public String getSymbol() {
        return symbol;
    }

}
