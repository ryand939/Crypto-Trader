package cryptoTrader.UiOperations.strategy;

/**
 * The StrategyCreator class is the factory class used for creating the trade execution strategy for the user based
 * on their input.
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class StrategyCreator {

	/**
	 * Singleton instance of StrategyCreator
	 */
    private static StrategyCreator instance;

    /**
     * Singleton getter method for instance of StrategyCreator. Ensures same object is always returned.
     * @return	Instance of StrategyCreator
     */
    public static StrategyCreator getInstance() {
        if (instance == null)
            instance = new StrategyCreator();

        return instance;
    }

    /**
     * Factory method to create different strategies
     * @param id ID of desired strategy (A, B, C, D or N)
     * @return The strategy object defined by the parameter. Null if input is invalid
     */
    public Strategy create(char id) {
        switch (id) {
            case 'A':
                return new ConcreteStrategyA();
            case 'B':
                return new ConcreteStrategyB();
            case 'C':
                return new ConcreteStrategyC();
            case 'D':
                return new ConcreteStrategyD();
            case 'N':
                return new ConcreteStrategyN();

        }
        return null;
    }
}
