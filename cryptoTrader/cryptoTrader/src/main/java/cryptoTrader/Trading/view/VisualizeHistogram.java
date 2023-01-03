package cryptoTrader.Trading.view;


import cryptoTrader.Trading.model.TradeResultList;

/**
 * The VisualizeHistogram class is an observer which is responsible for calling updateHistogram to update the histogram
 * whenever it's notified of a change by Subject
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class VisualizeHistogram implements Observer {

	/**
	 * Subject of observer 
	 */
    private final TradeResultList subject;

    
    /**
     * Constructor for VisualizeHistogram object
     * @param subject	Subject of observer
     */
    public VisualizeHistogram(TradeResultList subject) {
        this.subject = subject;
        subject.attach(this);
    }


    /**
     * Method to update VisualizeHistogram object
     * @param trade	Subject to update
     */
    @Override
    public void update(Subject trade) {
        if (trade.equals(subject))
            updateHistogram();

    }

    /**
     * Method to update histogram visually
     */
    private void updateHistogram() {
        IDataVisualizationCreator creator = new DataVisualizationCreator();
        creator.createBar(subject);
    }

}
