package cryptoTrader.Trading.view;

/**
 * The Observer interface exposes the update method which is called whenever the value of observed object is changed.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
interface Observer {

	/**
	 * Method to update the observer
	 * @param subject	Subject of observer
	 */
    void update(Subject subject);

}
