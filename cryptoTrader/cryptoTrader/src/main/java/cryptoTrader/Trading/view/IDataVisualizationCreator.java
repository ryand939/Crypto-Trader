package cryptoTrader.Trading.view;

import cryptoTrader.Trading.model.TradeResultList;

/**
 * The IDataVisualizationCreator interface is used for exposing the public methods of DataVisualizationCreator class
 * that is used for visualizing the raw data into various formats like table,chart,etc,
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface IDataVisualizationCreator {

    /**+
     * method to populate table with all the trades executed
     * @param trades list of all the trades made
     */
    void createTableOutput(TradeResultList trades);

    /**+
     * method to create bar graph for all the trades executed
     * @param trades list of all the trades made
     */
    void createBar(TradeResultList trades);

    /**+
     * method to display error popup for failing trades due to unsupported coin type for strategies
     * @param trades list of all the trades made
     */
    void displayError(TradeResultList trades);
}
