package cryptoTrader.UiOperations.broker;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The BrokerList class acts as a model that is used for working with list of brokers. It's used for performing
 * functions like validating brokers, adding new brokers, etc.
 *
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class BrokerList {

    /**
     * Instance of BrokerList class
     */
    private static BrokerList instance;
    /**
     * List of all brokers
     */
    private final List<Broker> brokerList;

    /**
     * Constructor for BrokerList object
     */
    private BrokerList() {
        brokerList = new ArrayList<Broker>();
    }

    /**
     * Singleton method to get BrokerList object. Ensures same object is always returned.
     *
     * @return BrokerList object
     */
    public static BrokerList getInstance() {
        if (instance == null)
            instance = new BrokerList();

        return instance;
    }

    /**+
     * method to validate user input.
     * @param dtm DefaultTableModel containing the user input at the last row
     * @return error message if input is invalid otherwise empty string
     */
    public static String validateSelection(DefaultTableModel dtm) {
        String msg = "";
        int count = dtm.getRowCount() - 1;

        Object traderObject = dtm.getValueAt(count, 0);
        // invalid input catch *****
        if (traderObject == null) {
            msg = "please fill in Trader name on line " + (count + 1);
            return msg;
        }
        String traderName = traderObject.toString();

        // check if trader name contains special characters
        Pattern specialCharacters = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = specialCharacters.matcher(traderName);
        if(matcher.find()){
            msg = "Trading client name cannot have special characters!";
            return msg;
        }

        // check for duplicate trader names
        if (BrokerList.getInstance().isDuplicateName(traderName)) {
            msg = "Duplicate trader names are prohibited. Please rename the trader on line " + (count + 1);
            return msg;
        }

        Object coinObject = dtm.getValueAt(count, 1);
        if (coinObject == null) {
            msg = "please fill in cryptocoin list on line " + (count + 1);
            return msg;
        }
        Object strategyObject = dtm.getValueAt(count, 2);
        if (strategyObject == null) {
            msg = "please fill in strategy name on line " + (count + 1);
            return msg;
        }

        return msg;
    }

    /**
     * Method to add a broker to the broker list
     * @param newBroker Broker to add
     */
    public void addBroker(Broker newBroker) {
        brokerList.add(newBroker);
    }

    /**
     * Method to remove broker from the broker list
     * @param brokerName Broker to remove
     */
    public void removeBroker(String brokerName) {
        for (int x = 0; x < brokerList.size(); x++)
            if (brokerList.get(x).getName().equals(brokerName))
                brokerList.remove(brokerList.get(x));
    }

    /**
     * Getter method for a particular broker in the list
     * @param index Index of broker
     * @return Broker object or null if the broker does not exist in the list
     */
    public Broker getBroker(int index) {
        return brokerList.get(index);
    }

    /**
     * Number of items in the broker list
     *
     * @return Integer representing length of broker list
     */
    public int length() {
        return brokerList.size();
    }

    /**
     * Method to remove all brokers from the broker list
     */
    public void refreshBrokers() {
        brokerList.clear();
    }

    /**
     * Method to determine if another trader has the same name
     *
     * @param brokerName Name to check
     * @return True if duplicate name, false otherwise
     */
    public boolean isDuplicateName(String brokerName) {
        for (int x = 0; x < brokerList.size(); x++)
            if (brokerList.get(x).getName().equals(brokerName))
                return true;
        return false;
    }


}
