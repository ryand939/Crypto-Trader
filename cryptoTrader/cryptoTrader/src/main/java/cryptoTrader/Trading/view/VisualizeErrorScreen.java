package cryptoTrader.Trading.view;

import cryptoTrader.Trading.model.TradeResultList;

/**
 * The VisualizeErrorScreen class is an observer which is responsible for calling displayError to display the error
 * whenever it's notified of a change by Subject
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class VisualizeErrorScreen implements Observer {

	/**
	 * Subject of observer
	 */
    private final TradeResultList subject;

    /**
     * Constructor for VisualizeErrorScreen object
     * @param subject	Subject of observer
     */
    public VisualizeErrorScreen(TradeResultList subject) {
        this.subject = subject;
        subject.attach(this);
    }


    /**
     * Method to update VisualizeErrorScreen object
     * @param trade Subject to update
     */
    @Override
    public void update(Subject trade) {
        if (trade.equals(subject))
            displayError();

    }

    /**
     * Method to display an error
     */
    private void displayError() {
        IDataVisualizationCreator creator = new DataVisualizationCreator();
        creator.displayError(subject);
    }
}
