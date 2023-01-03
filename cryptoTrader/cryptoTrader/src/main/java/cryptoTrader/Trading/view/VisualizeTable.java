package cryptoTrader.Trading.view;


import cryptoTrader.Trading.model.TradeResultList;

/**
 * The VisualizeTable class is an observer which is responsible for calling updateTable to update the table
 * whenever it's notified of a change by Subject
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class VisualizeTable implements Observer {

	/**
	 * Subject of observer 
	 */
    private final TradeResultList subject;

    /**
     * Constructor for VisualizeTable object
     * @param subject	Subject of observer
     */
    public VisualizeTable(TradeResultList subject) {
        this.subject = subject;
        subject.attach(this);
    }
    
    
    /**
     * Method to update VisualizeTable object
     * @param trade	Subject to update
     */
    @Override
    public void update(Subject trade) {

        if (trade.equals(subject))
            updateTable();

    }

    
    /**
     * Method to update table visually
     */
    private void updateTable() {
        IDataVisualizationCreator creator = new DataVisualizationCreator();
        creator.createTableOutput(subject);
    }

}
