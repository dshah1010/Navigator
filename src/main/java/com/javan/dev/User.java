package com.javan.dev;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class User {
    /**
     * Declaring the username and password of the user.
     */
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Constructor for the User class to initialize user/pass when account created
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    /**
     * Function to turn a user into an admin
     * @param None
     * @return None
     */
    public void makeAdmin() {
        this.isAdmin = true;
    }

    /**
     * Getter for the username
     * @param None
     * @return None
     */
    public String getUsername() {
        return this.username;
    }
}