package com.javan.dev;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public final class User {
    /**
     * Declaring variables of the user class
     */
    private String username;
    private int userID;
    private String password;
    private boolean isAdmin;

    /**
     * Singleton instance of User
     */
    private static User INSTANCE;

    /**
     * Constructor for the User class to initialize user/pass when account created
     * @param username
     * @param password
     */
    private User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    /**
     * Getter for the instance of user
     */
    public static User getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new User("admin", "admin");
        }
        return INSTANCE;
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

    /**
     * Getter for the userID
     * @param None
     * @return int userID
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Getter for isAdmin
     * @param None
     * @return boolean isAdmin
     */
    public boolean getIsAdmin() {
        return this.isAdmin;
    }
}