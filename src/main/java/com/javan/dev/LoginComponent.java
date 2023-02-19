package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class LoginComponent extends JPanel implements ActionListener, FocusListener, MouseListener {
    /**
     * Initialize private variables for the UI
     */
    private boolean passwordFlag;
    private boolean confirmPassFlag;
    private JPasswordField passwordInput;
    /**
     * Variables for login components
     */
    private JPanel loginPanel;
    private JLabel loginTitle;
    private JTextField usernameInput;
    private JButton loginButton;
    private JLabel forgotPassword;
    /**
     * Variables for create account components
     */
    private JLabel createAccount;
    private JPanel createAccountPanel;
    private JTextField createAccountUsername;
    private JPasswordField createAccountPassword;
    private JPasswordField createAccountConfirmPassword;
    private JButton createAccountButton;
    private JLabel createAccountTitle;
    private JLabel loginLabel;
    /**
     * Boolean to check if the login window is open and a boolean to check if logged in is true
     */
    private boolean isLoginWindowOpen;
    private boolean isLoggedIn;


    /**
     * Constructor to create Login Component of the UI. This will be in the main frame when the application is opened,
     * as well as when the user logs out. It will be removed when the user logs in successfully.
     * @param None
     * @return None
     */
    public LoginComponent() {
        /**
         * Initialize logged in as false
         */
        isLoggedIn = false;
        /**
         * JPanel to hold the login components with BoxLayout vertically
         */
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        /**
        * Initialize a list of JPanel objects for cards 1-6
        */
        List<JPanel> cards = new ArrayList<JPanel>();
        for (int i = 0; i < 6; i++) {
            cards.add(new JPanel());
        }

        /**
         * JLabel to hold the title of the login component. Set the font and alignment of the title.
         */
        loginTitle = new JLabel("Campus Navigation System");
        loginTitle.setFont(new java.awt.Font("Georgia", 1, 40));
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        loginTitle.setVerticalAlignment(JLabel.TOP);
        cards.get(0).add(loginTitle);

        /**
         * JLabel to hold the username text and JTextField to hold the username input
         */
        usernameInput = new JTextField(30);
        usernameInput.setText("Username:");
        usernameInput.setPreferredSize(new Dimension(150, 40));
        usernameInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameInput.setFont(new Font("Georgia", 1, 15));
        /**
         * Action listener to username field to show default text of Username when focus is gained
         */
        usernameInput.addFocusListener(this);
        cards.get(1).add(usernameInput);

        /**
         * JLabel to hold the password text and JPasswordField to hold the password input
         */
        passwordInput = new JPasswordField(30);
        passwordInput.setText("Password:");
        passwordInput.setPreferredSize(new Dimension(150, 40));
        passwordInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordInput.setFont(new Font("Georgia", 1, 15));
        /**
         * Add action listener to password field to show default text of Password when focus is gained
         */
        passwordInput.addFocusListener(this);
        passwordInput.setEchoChar((char) 0);
        passwordFlag = true;
        cards.get(2).add(passwordInput);

        /**
         * JButton for the login button that will be used to login to the system, deferring to the action listener
         * where other classes will authenticate the login request
         */
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        /**
         * Customize the login button
         */
        loginButton.setPreferredSize(new Dimension(120, 45));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        loginButton.setFont(new Font("Georgia", 1, 15));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addMouseListener(this);
        cards.get(3).add(loginButton);

        /**
         * "Forgot your password" Label that allows a user to reset their password
         */
        forgotPassword = new JLabel("Forgot your password?");
        forgotPassword.setFont(new Font("Georgia", 1, 15));
        forgotPassword.setForeground(Color.RED);
        forgotPassword.addMouseListener(this);
        cards.get(4).add(forgotPassword);

        /**
         * "New User? Create Account" Label that will update the UI to switch to a create account panel
         */
        createAccount = new JLabel("New User? Create Account");
        createAccount.setFont(new Font("Georgia", 1, 15));
        createAccount.addMouseListener(this);
        cards.get(5).add(createAccount);

        /**
         * Adding the cards to the login panel with a loop, adding vertical spacing between each card
         */
        int[] verticalSpacing = {80, 50, 25, 15, 15, 0};
        for (int i = 0; i < cards.size(); i++) {
            loginPanel.add(cards.get(i));
            loginPanel.add(Box.createVerticalStrut(verticalSpacing[i]));
        }
        /**
         * Adding the login panel to the UserInterface frame
         */
        add(loginPanel);
    }

    /**
     * Getter for the JPanel of the login component
     * @return JPanel of the login component
     */
    public JPanel getLoginPanel() {
        return loginPanel;
    }

    /**
     * Getter for the JPanel of the create account component
     * @return JPanel of the create account component
     */
    public JPanel getCreateAccountPanel() {
        return createAccountPanel;
    }

    /**
     * Getter for the panel that is currently open and being displayed
     * @return JPanel of the current panel
     */
    public JPanel getCurrentPanel() {
        if (isLoginWindowOpen == true) {
            return getLoginPanel();
        }
        else {
            return getCreateAccountPanel();
        }
    }

    /**
     * Getter for login status
     */
    public boolean getLoginStatus() {
        return isLoggedIn;
    }

    /**
     * Method to transform the loginPanel into a createAccountPanel when the "New User? Create Account" label is clicked
     * @param None
     * @return None
     */
    public void createAccountPanel() {
        /**
         * Remove the login panel from the frame
         */
        remove(loginPanel);

        /**
        * Initialize a list of JPanel objects for cards 1-6
        */
        List<JPanel> createAccCards = new ArrayList<JPanel>();
        for (int i = 0; i < 6; i++) {
            createAccCards.add(new JPanel());
        }

        /**
         * Create a new JPanel to hold the create account components
         */
        createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        /**
         * JLabel to hold the title of the create account component. Set the font and alignment of the title.
         */
        createAccountTitle = new JLabel("Create Account");
        createAccountTitle.setFont(new java.awt.Font("Georgia", 1, 40));
        createAccountTitle.setHorizontalAlignment(JLabel.CENTER);
        createAccountTitle.setVerticalAlignment(JLabel.TOP);
        createAccCards.get(0).add(createAccountTitle);

        /**
         * JLabel to hold the username text and JTextField to hold the username input
         */
        createAccountUsername = new JTextField(30);
        createAccountUsername.setText("Username:");
        createAccountUsername.setPreferredSize(new Dimension(150, 40));
        createAccountUsername.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createAccountUsername.setFont(new Font("Georgia", 1, 15));
        /**
         * Action listener to username field to show default text of Username when focus is gained
         */
        createAccountUsername.addFocusListener(this);
        createAccCards.get(1).add(createAccountUsername);

        /**
         * JLabel to hold the password text and JPasswordField to hold the password input
         */
        createAccountPassword = new JPasswordField(30);
        createAccountPassword.setText("Password:");
        createAccountPassword.setPreferredSize(new Dimension(150, 40));
        createAccountPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createAccountPassword.setFont(new Font("Georgia", 1, 15));
        /**
         * Add action listener to password field to show default text of Password when focus is gained
         */
        createAccountPassword.addFocusListener(this);
        createAccountPassword.setEchoChar((char) 0);
        passwordFlag = true;
        createAccCards.get(2).add(createAccountPassword);

        /**
         * JLabel to hold the confirm password text and JPasswordField to hold the confirm password input
         */
        createAccountConfirmPassword = new JPasswordField(30);
        createAccountConfirmPassword.setText("Confirm Password:");
        createAccountConfirmPassword.setPreferredSize(new Dimension(150, 40));
        createAccountConfirmPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createAccountConfirmPassword.setFont(new Font("Georgia", 1, 15));

        /**
         * Add action listener to confirm password field to show default text of Password when focus is gained
         */
        createAccountConfirmPassword.addFocusListener(this);
        createAccountConfirmPassword.setEchoChar((char) 0);
        confirmPassFlag = true;
        createAccCards.get(3).add(createAccountConfirmPassword);

        /**
         * JButton for the create account button that will be used to create an account, deferring to the action listener
         * where other classes will authenticate the create account request
         */
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(this);
        createAccountButton.setPreferredSize(new Dimension(120, 45));
        createAccountButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createAccountButton.setFont(new Font("Georgia", 1, 15));
        createAccountButton.setBackground(Color.BLACK);
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        createAccountButton.addMouseListener(this);
        createAccCards.get(4).add(createAccountButton);

        /**
         * JLabel to hold the "Already have an account? Login" text that will update the UI to switch to a login panel
         */
        loginLabel = new JLabel("Already have an account? Login");
        loginLabel.setFont(new Font("Georgia", 1, 15));
        loginLabel.addMouseListener(this);
        createAccCards.get(5).add(loginLabel);

        /**
         * Loop to add the cards to the create account panel
         */
        int[] cardSpacing = {80, 50, 50, 15, 15, 0};
        for (int i = 0; i < createAccCards.size(); i++) {
            createAccountPanel.add(createAccCards.get(i));
            createAccountPanel.add(Box.createVerticalStrut(cardSpacing[i]));
        }

        /**
         * Adding the create account panel to the UserInterface frame
         */
        add(createAccountPanel);
        revalidate();
        repaint();


    }


    /**
     * Method to get username and password when login button is pressed, creating a User class to verify login\
     * Also gets username and password when create account button is pressed, creating a User class to verify create account
     * @param e
     * @return None
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * Logging In
         */
        if (e.getSource() == loginButton) {
            /**
             * If username or password field are empty (the defaults), do nothing
             */
            if (usernameInput.getText().contains("Username:") || new String(passwordInput.getPassword()).contains("Password:")) {
                JOptionPane.showMessageDialog(null, "Please enter a username and password");
                return;
            }

            /**
             * Verify the login credentials
             */
            String username = usernameInput.getText();
            String password = new String(passwordInput.getPassword());
            DataProcessor process = new DataProcessor();
            // TODO: boolean isValid = process.authenticateLogin(username, password); // To implement in the future

            /**
             * Empty text fields
             */
            usernameInput.setText("");
            passwordInput.setText("");

            /**
             * If the login is valid (isValid != 0), then remove the login panel from the frame and add the main panel
             */
            boolean isValid = true; // TEMPORARY
            if (isValid == true) {
                /**
                 * Remove the login panel from the frame and set loggedIn to true for the rest of the program
                 */
                remove(loginPanel);
                revalidate();
                repaint();
                isLoggedIn = true;
            }
            else {
                /**
                 * Display a message informing the user that they have entered an invalid username or password
                 */
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
        /**
         * Creating Account
         */
        else if (e.getSource() == createAccountButton) {
            /**
             * If username, password, or confirm password field are empty (the defaults), do nothing
             */
            if (createAccountUsername.getText().contains("Username:") || new String(createAccountPassword.getPassword()).contains("Password:") || new String(createAccountConfirmPassword.getPassword()).contains("Password:")) {
                JOptionPane.showMessageDialog(null, "Please enter a username and password");
                return;
            }

            /**
             * Verify the create account credentials
             */
            String username = createAccountUsername.getText();
            String password = new String(createAccountPassword.getPassword());
            String confirmPassword = new String(createAccountConfirmPassword.getPassword());

            /**
             * If the password and confirm password fields do not match, display a message informing the user
             */
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match.");
                return;
            }
            else {
                /**
                 * Create a user account and JSON storage of the user account using the DataProcessor class
                 */
                DataProcessor process = new DataProcessor();
                // TODO: process.createAccount(username password); // To implement in the future
                /**
                 * Bring user to login screen
                 */
                createAccountPanel.setVisible(false);
                /**
                 * Make the loginPanel visible on the screen again
                 */
                loginPanel.setVisible(true);
                add(loginPanel);
                revalidate();
                repaint();
                /**
                 * Reset password flag
                 */
                passwordFlag = true;
            }
        }
    }

    /**
     * Method for when focus is gained
     * Applies for password input, create account password, and confirming password
     * Also applies for the username input for both login and create account page
     * @param e
     * @return None
     */
    public void focusGained(FocusEvent e) {
        /**
         * If the password input is the default text of Password, then set the text to blank and set the echo char to *
         */
        if (e.getSource() == passwordInput || e.getSource() == createAccountPassword) {
            if (passwordFlag) {
                ((JPasswordField) e.getSource()).setText("");
                ((JPasswordField) e.getSource()).setEchoChar('*');
                passwordFlag = false;
            }
        }
        else if (e.getSource() == createAccountConfirmPassword) {
            if (confirmPassFlag) {
                ((JPasswordField) e.getSource()).setText("");
                ((JPasswordField) e.getSource()).setEchoChar('*');
                confirmPassFlag = false;
            }
        }
        /**
         * If the username input is the default text of Username, then set the text to blank
         */
        else if (e.getSource() == usernameInput || e.getSource() == createAccountUsername) {
            ((JTextField) e.getSource()).setText("");
        }
    }


    /**
     * Method for when focus is lost on all 3 passport input fields
     * Also for when username input is blank and focus is lost, setting default text again if no input given
     * @param e
     * @return None
     */
    public void focusLost(FocusEvent e) {
        /**
         * If no password input, then set the text to Password again (length of 0)
         */
        if (e.getSource() == passwordInput || e.getSource() == createAccountPassword) {
            if (((JPasswordField) e.getSource()).getPassword().length == 0) {
                ((JPasswordField) e.getSource()).setText("Password:");
                ((JPasswordField) e.getSource()).setEchoChar((char) 0);
                passwordFlag = true;
            }
        }
        else if (e.getSource() == createAccountConfirmPassword) {
            if (((JPasswordField) e.getSource()).getPassword().length == 0) {
                ((JPasswordField) e.getSource()).setText("Confirm Password:");
                ((JPasswordField) e.getSource()).setEchoChar((char) 0);
                confirmPassFlag = true;
            }
        }
        /**
         * If the username input is blank, then set the text to Username again
         */
        else if (e.getSource() == usernameInput || e.getSource() == createAccountUsername) {
            if (((JTextField) e.getSource()).getText().length() == 0) {
                ((JTextField) e.getSource()).setText("Username:");
            }
        }   
    }

    /**
     * Method for mouse listener for button on hovering over the button (login/create account) to change appearance
     * Also mouse entered listened for the labels for forgot password, create account, and login if account exists
     * @param e
     * @return None
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton || e.getSource() == createAccountButton) {
            /**
             * Set background colour and mouse appearance to hover
             */
            ((Component) e.getSource()).setBackground(Color.DARK_GRAY);
            ((Component) e.getSource()).setForeground(Color.WHITE);
            ((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if (e.getSource() == forgotPassword || e.getSource() == createAccount || e.getSource() == loginLabel) {
            /**
             * Set mouse appearance to hover
             */
            ((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Method to change back appearance of button (login / create account) when no longer hovering over the buttom
     * Also applies for Forgot your Password and Create Account labels, as well as Login Label from Create Account page
     * @param e
     * @return None
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton || e.getSource() == createAccountButton) {
            /**
             * Reset background colour and mouse appearance to default
             */
            ((Component) e.getSource()).setBackground(Color.BLACK);
            ((Component) e.getSource()).setForeground(Color.WHITE);
            ((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else if (e.getSource() == forgotPassword || e.getSource() == createAccount || e.getSource() == loginLabel) {
            /**
             * Set mouse appearance to default
             */
            ((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    /**
     * Method to handle mouse click events for Forgot your Password and for Create Account labels, as well as Login Label from Create Account page
     * @param e
     * @return None
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == forgotPassword) {
            /**
             * Get the username from the username input field, and if it is empty inform user to enter a username
             */
            String username = usernameInput.getText();
            if (username.contains("Username:")) {
                JOptionPane.showMessageDialog(null, "Please enter your username, and we will give you your password");
                return;
            }
            /**
             * Get the password from the database and display it to the user
             */
            DataProcessor process = new DataProcessor();
            // TODO: String password = process.getPasswordFromUserName(username); // To implement in the future
            JOptionPane.showMessageDialog(null, "Your password is: " + "password"); // TODO: Update to password variable after implemented
        }
        else if (e.getSource() == createAccount) {
            /**
             * Convert the loginPanel into a createAccountPanel
             */
            System.out.println("create account");
            loginPanel.setVisible(false);
            this.createAccountPanel();
        }
        else if (e.getSource() == loginLabel) {
            /**
             * Convert the createAccountPanel into a loginPanel
             */
            System.out.println("login");
            createAccountPanel.setVisible(false);
            /**
             * Make the loginPanel visible on the screen again
             */
            loginPanel.setVisible(true);
            add(loginPanel);
            isLoggedIn = false;
            revalidate();
            repaint();
            /**
             * Reset password flag
             */
            passwordFlag = true;
        }
    }

    /**
     * Unneeded method from mouse listener
     * @param e
     * @return None
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unneeded method from mouse listener
     * @param e
     * @return None
     */
    public void mouseReleased(MouseEvent e) {
    }
}