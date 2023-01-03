package cryptoTrader.Authentication;

import cryptoTrader.gui.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginUI class is used for creating UI for user to login to application
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class LoginUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    /**
     * Singleton instance of LoginUI
     */
    private static LoginUI instance;
    /**
     * Elements of login ui
     */
    private final JButton loginButton;
    private final JPanel loginPanel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JTextField usernameText;
    private final JTextField passwordText;

    /**
     * Constructor for LoginUI. Initializes login screen
     */
    private LoginUI() {
        // window title
        super("Cryptotrader: Login");

        // new JPanel with null layout, will manually structure layout in fixed window
        loginPanel = new JPanel(null);
        loginPanel.setBackground(new Color(190, 190, 190));

        // username label and textbox
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 11));
        usernameLabel.setForeground(Color.black);
        usernameLabel.setBounds(70, 7, 400, 30);
        usernameText = new JTextField(12);
        usernameText.setBounds(70, 30, 160, 25);
        usernameText.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90)));

        // password label and textbox
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 11));
        passwordLabel.setForeground(Color.black);
        passwordLabel.setBounds(70, 52, 400, 30);
        passwordText = new JPasswordField(12);
        passwordText.setBounds(70, 75, 160, 25);
        passwordText.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90)));

        // login button
        loginButton = new JButton("Login");
        loginButton.setBounds(70, 110, 160, 30);
        loginButton.setForeground(Color.black);
        loginButton.setBackground(Color.white);
        loginButton.setFont(new Font("Verdana", Font.BOLD, 12));
        // draw border around button
        loginButton.setBorderPainted(true);
        // don't draw box around "Login" when button is focused
        loginButton.setFocusPainted(false);
        // listen if pressed, invokes actionPerformed method
        loginButton.addActionListener(this);
        loginButton.setToolTipText("Submit credentials for verification");
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90)));

        // add all elements to the login panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(loginButton);

        // add panel to JFrame
        this.add(loginPanel, BorderLayout.CENTER);
    }

    /**
     * Getter method for instance of LoginServer
     */
    public static LoginUI getInstance() {
        if (instance == null)
            instance = new LoginUI();
        return instance;
    }

    /**+
     * starts the initial rendering of the program
     * @param args arguments in case some special commands need to be passed
     */
    public static void main(String[] args) {
        JFrame frame = getInstance();
        frame.setSize(300, 210);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**+
     * handler to handle "Login" action
     * @param e event of ActionEvent type
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // login button pressed
        if (command.equals("Login"))
            submitCredentials(usernameText.getText(), passwordText.getText());
    }

    /**
     * Method to verify user credentials. User is admitted to MainUI if credentials exist in CredentialDB
     *
     * @param username Username string
     * @param password Password string
     */
    private void submitCredentials(String username, String password) {
        LoginServer server = LoginServer.getInstance();

        // case valid credentials
        if (server.verifyCredentials(username, password)) {
            // invoke initiateTrading method of MainUI
            MainUI.initiateTrading();
            // dispose of login form
            dispose();
        }
        // case invalid credentials
        else {
            JOptionPane.showMessageDialog(this, "Invalid username/password combination.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            passwordText.setText("");
        }
    }

}