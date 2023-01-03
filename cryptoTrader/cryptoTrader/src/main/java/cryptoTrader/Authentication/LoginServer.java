package cryptoTrader.Authentication;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The LoginServer class is used for verifying the user credentials
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class LoginServer {

    /**
     * Singleton instance of LoginServer
     */
    private static LoginServer instance;

    /**
     * Constructor for LoginServer
     */
    private LoginServer() {

    }

    /**
     * Getter method for instance of LoginServer
     * @return instance
     */
    public static LoginServer getInstance() {
        if (instance == null)
            instance = new LoginServer();

        return instance;
    }

    /**
     * Method to verify a users credentials
     * @param username	String representing username
     * @param password	String representing password
     * @return	True if user can be found in the CredentialDB, false otherwise
     */
    public boolean verifyCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("CredentialDB.txt"))) {
            String line;
            String[] currentCred;
            while ((line = br.readLine()) != null) {
                currentCred = line.split(":");
                if (currentCred[0].equals(username) && currentCred[1].equals(password))
                    return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }


}