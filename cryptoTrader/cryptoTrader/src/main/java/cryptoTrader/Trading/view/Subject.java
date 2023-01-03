package cryptoTrader.Trading.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Subject abstract class maintains a list of all observers and notifies them whenever their state changes.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public abstract class Subject {

	/**
	 * List of all observers
	 */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Method to add an observer to the list
     * @param observer	Observer to add
     */
    public void attach(Observer observer) {
        // make sure observers arent attached multiple times
        if (!observers.contains(observer))
            observers.add(observer);
        else
            System.out.println("Warning: Same observer has been added before. Ignored.");

    }

    /**
     * Method to detach an observer in the observer list
     * @param observer Observer to remove
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Method to notify all attached observers
     */
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }


}
