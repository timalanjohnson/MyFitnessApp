package com.example.myfitnessapp;

public class User {

    private static String userID;
    private static String username;
    private static String userPassword;
    private static String userEmail;
    private static String userWeight;
    private static String userHeight;
    private static String userTarget;


    public User(){
        // Default constructor
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        User.userID = userID;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        User.userPassword = userPassword;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        User.userEmail = userEmail;
    }

    public static String getUserWeight() {
        return userWeight;
    }

    public static void setUserWeight(String userWeight) {
        User.userWeight = userWeight;
    }

    public static String getUserHeight() {
        return userHeight;
    }

    public static void setUserHeight(String userHeight) {
        User.userHeight = userHeight;
    }

    public static String getUserTarget() {
        return userTarget;
    }

    public static void setUserTarget(String userTarget) {
        User.userTarget = userTarget;
    }
}
