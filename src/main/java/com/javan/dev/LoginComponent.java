package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.*;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class LoginComponent extends JPanel implements ActionListener, FocusListener, MouseListener {
    /**
     * Initialize private variables for the UI
     */
    private boolean flag;
    private JPasswordField passwordInput;
    private JPanel card1;
    private JPanel card2;
    private JPanel card3;
    private JPanel card4;
    private JPanel card5;
    private JPanel card6;
    private JPanel loginPanel;
    private JLabel loginTitle;
    private JTextField usernameInput;
    private JButton loginButton;
    private JLabel forgotPassword;
    private JLabel createAccount;


    /**
     * Constructor to create Login Component of the UI. This will be in the main frame when the application is opened,
     * as well as when the user logs out. It will be removed when the user logs in successfully.
     */
    public LoginComponent() {
        /**
         * JPanel to hold the login components with BoxLayout vertically
         */
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        /**
         * JLabel to hold the title of the login component. Set the font and alignment of the title.
         */
        card1 = new JPanel();
        loginTitle = new JLabel("Campus Navigation System");
        loginTitle.setFont(new java.awt.Font("Georgia", 1, 40));
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        loginTitle.setVerticalAlignment(JLabel.TOP);
        card1.add(loginTitle);

        /**
         * JLabel to hold the username text and JTextField to hold the username input
         */
        card2 = new JPanel();
        usernameInput = new JTextField(30);
        usernameInput.setText("Username:");
        usernameInput.setPreferredSize(new Dimension(150, 40));
        usernameInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameInput.setFont(new Font("Georgia", 1, 15));
        /**
         * Action listener to username field to show default text of Username when focus is gained
         */
        usernameInput.addFocusListener(this);
        card2.add(usernameInput);

        /**
         * JLabel to hold the password text and JPasswordField to hold the password input
         */
        card3 = new JPanel();
        passwordInput = new JPasswordField(30);
        passwordInput.setText("Password:");
        passwordInput.setPreferredSize(new Dimension(200, 40));
        passwordInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordInput.setFont(new Font("Georgia", 1, 15));
        /**
         * Add action listener to password field to show default text of Password when focus is gained
         */
        passwordInput.addFocusListener(this);
        passwordInput.setEchoChar((char) 0);
        flag = true;
        card3.add(passwordInput);

        /**
         * JButton for the login button that will be used to login to the system, deferring to the action listener
         * where other classes will authenticate the login request
         */
        card4 = new JPanel();
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
        card4.add(loginButton);

        /**
         * "Forgot your password" Label that allows a user to reset their password
         */
        card5 = new JPanel();
        forgotPassword = new JLabel("Forgot your password?");
        forgotPassword.setFont(new Font("Georgia", 1, 15));
        forgotPassword.setForeground(Color.RED);
        forgotPassword.addMouseListener(this);
        card5.add(forgotPassword);

        /**
         * "New User? Create Account" Label that will update the UI to switch to a create account panel
         */
        card6 = new JPanel();
        createAccount = new JLabel("New User? Create Account");
        createAccount.setFont(new Font("Georgia", 1, 15));
        createAccount.addMouseListener(this);
        card6.add(createAccount);

        /**
         * Adding the cards to the login panel -> ensure adequate vertical spacing
         */
        loginPanel.add(card1);
        loginPanel.add(Box.createVerticalStrut(80));
        loginPanel.add(card2);
        loginPanel.add(Box.createVerticalStrut(50));
        loginPanel.add(card3);
        loginPanel.add(Box.createVerticalStrut(25));
        loginPanel.add(card4);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(card5);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(card6);


        /**
         * Adding the login panel to the UserInterface frame
         */
        add(loginPanel);
    }


    /**
     * Method to get username and password when login button is pressed, creating a User class to verify login\
     * @param e
     * @return None
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            /**
             * If username or password field are empty (the defaults), do nothing
             */
            if (usernameInput.getText().contains("Username:") || new String(passwordInput.getPassword()).contains("Password:")) {
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
            boolean isValid = false;
            if (isValid == true) {
                loginPanel.setVisible(false);
                // TODO: add(new MapComponent()); // To implement in the future
            }
            else {
                /**
                 * Display a message informing the user that they have entered an invalid username or password
                 */
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }

    /**
     * Method for when focus is gained on password input field and for when it is gained on username input field
     * @param e
     * @return None
     */
    public void focusGained(FocusEvent e) {
        /**
         * If the password input is the default text of Password, then set the text to blank and set the echo char to *
         */
        if (e.getSource() == passwordInput) {
            if (flag) {
                passwordInput.setText("");
                passwordInput.setEchoChar('*');
                flag = false;
            }
        }
        /**
         * If the username input is the default text of Username, then set the text to blank
         */
        else if (e.getSource() == usernameInput) {
            usernameInput.setText("");
        }
    }


    /**
     * Method for when focus is lost on password input field and for when focus is lost on username input field
     * @param e
     * @return None
     */
    public void focusLost(FocusEvent e) {
        /**
         * If no password input, then set the text to Password again (length of 0)
         */
        if (e.getSource() == passwordInput) {
            if (passwordInput.getPassword().length == 0) {
                passwordInput.setText("Password:");
                passwordInput.setEchoChar((char) 0);
                flag = true;
            }
        }
        /**
         * If the username input is blank, then set the text to Username again
         */
        else if (e.getSource() == usernameInput) {
            if (usernameInput.getText().length() == 0) {
                usernameInput.setText("Username:");
            }
        }   
    }

    /**
     * Method for mouse listener for button on hovering over the button to change appearance, as well as for Forgot your Password and Create Account labels
     * @param e
     * @return None
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton) {
            /**
             * Set background colour and mouse appearance to hover
             */
            loginButton.setBackground(Color.DARK_GRAY);
            loginButton.setForeground(Color.WHITE);
            loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if (e.getSource() == forgotPassword) {
            /**
             * Set mouse appearance to hover
             */
            forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if (e.getSource() == createAccount) {
            /**
             * Set mouse appearance to hover
             */
            createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Method to change back appearance of button when no longer hovering over the buttom, as well for Forgot your Password and Create Account labels
     * @param e
     * @return None
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton) {
            /**
             * Reset background colour and mouse appearance to default
             */
            loginButton.setBackground(Color.BLACK);
            loginButton.setForeground(Color.WHITE);
            loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else if (e.getSource() == forgotPassword) {
            /**
             * Set mouse appearance to default
             */
            forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else if (e.getSource() == createAccount) {
            /**
             * Set mouse appearance to default
             */
            createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    /**
     * Method to handle mouse click events for Forgot your Password and for Create Account labels
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
             * Create a new CreateAccountComponent class in place of LoginComponent to create a new account
             */
            this.remove(loginPanel);
            this.setVisible(false);
            //CreateAccountComponent create = new CreateAccountComponent();
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