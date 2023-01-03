package cryptoTrader.Trading.controller;

import cryptoTrader.UiOperations.broker.BrokerList;

/**
 * The ITrade interface is used for exposing the public methods of Trade class that contains trade execution login
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public interface ITrade {
    /**
     * Method to execute trade.
     * @param brokers List of brokers who wish to trade cryptocoins
     */
    void executeTrade(BrokerList brokers);
}
