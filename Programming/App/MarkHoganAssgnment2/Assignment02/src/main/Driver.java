package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    public int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    public void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> developerMenu();
                case 2 -> appStoreMenu();
                case 3 -> ReportsMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5 -> sortAppsByNameAscending();
                case 6 -> recommendedApps();
                case 7 -> randomAppOfTheDay();
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    public void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    public void ReportsMenu() {
        System.out.println("""
                 -------Reports Menu-------
                |   1) App Overview          |
                |   2) Developer Overview    |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        int option = ScannerInput.validNextInt("==>> ");
        while (option != 0) {
            switch (option) {
                case 1 -> appsOverview();
                case 3 -> listDevelopers();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            runMainMenu();
        }
    }

    public void appsOverview() {
        System.out.println("""
               --------------------Apps Overview Menu--------------------
               |   ---------List--------                                |
               |   1)  List All Apps                                    |
               |   2)  List Summary Of All Apps                         |
               |   3)  List All Game Apps                               |
               |   4)  List All Productivity Apps                       |
               |   5)  List ALl Education Apps                          |
               |   6)  List All Apps By Name                            |
               |   7)  List All Apps By Chosen Developer                |
               |   --------Numbers-------                               |
               |   8)  Number Of Apps By Chosen Developer               |
               |   9)  Number Of Apps                                   |
               |   10) Number Of Game Apps                              |
               |   11) Number Of Productivity Apps                      |
               |   12) Number Of Education Apps                         |
               |   0) RETURN to main menu                               |
                
                 ----------------------------""");
        int option = ScannerInput.validNextInt("==>> ");
        while (option != 0) {
            switch (option) {
                case 1 -> listAllApps();
                case 2 -> listSummaryOfAllApps();
                case 3 -> listAllGameApps();
                case 4 -> listAllProductivityApps();
                case 5 -> listAllEducationApps();
                case 6 -> listAllAppsByName();
                case 7 -> listAllAppsByChosenDeveloper();
                case 8 -> numberOfAppsByChosenDeveloper();
                case 9 -> numberOfApps();
                case 10 -> numberOfGameApps();
                case 11 -> numberOfProductivityApps();
                case 12 -> numberOfEducationApps();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            runMainMenu();
        }
    }

    public void listAllApps(){
    System.out.println(appStoreAPI.listAllApps());
    }

    public void listSummaryOfAllApps() {
    System.out.println(appStoreAPI.listSummaryOfaLLApps());
    }

    public void listAllGameApps() {
        System.out.println(appStoreAPI.listAllGameApps());
    }

    public void listAllProductivityApps(){
        System.out.println(appStoreAPI.listAllProductivityApps());
    }

    public void listAllEducationApps(){
        System.out.println(appStoreAPI.listAllEducationApps());
    }

    public void listAllAppsByName(){
        String name = ScannerInput.validNextLine("Enter the name of the apps ==> ");
        System.out.println(appStoreAPI.listAllAppsByName(name));
    }

    public void listAllAppsByChosenDeveloper(){
        int index = ScannerInput.validNextChar("Enter the index of the developer ==> ");
        System.out.println(appStoreAPI.listAllAppsByChosenDeveloper(developerAPI.getDeveloperByIndex(index)));
    }

    public void listDevelopers(){
        System.out.println(developerAPI.listDevelopers());
    }

    public void numberOfAppsByChosenDeveloper(){
        int index = ScannerInput.validNextInt("Enter the index of the Developer");
        System.out.println("Number of apps for this developer: " + appStoreAPI.numberOfAppsByChosenDeveloper(developerAPI.getDeveloperByIndex(index)));
    }

    public void numberOfApps(){
        System.out.println("Number of apps: " + appStoreAPI.numberOfApps());
    }

    public void numberOfGameApps(){
        System.out.println("Number of game apps: " + appStoreAPI.numberOfGameApps());
    }

    public void numberOfProductivityApps(){
        System.out.println("Number of productivity apps: " + appStoreAPI.numberOfProductivityApps());
    }

    public void numberOfEducationApps(){
        System.out.println("Number of education apps" + appStoreAPI.numberOfEducationApps());
    }

    //--------------------------------------------------
    //  Apps Management - Menu Items
    //--------------------------------------------------
    public void appStoreMenu() {
        System.out.println("""
                 -------App Store Menu-------
                |   1) Add a app             |
                |   2) Update app            |
                |   3) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        int option = ScannerInput.validNextInt("==>> ");
        while (option != 0) {
            switch (option) {
                case 1 -> addApp();
                case 2 -> updateApp();
                case 3 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            runMainMenu();
        }
    }

    public void addApp() {

        boolean isAdded = false;

        int option = ScannerInput.validNextInt("""
                -------------------------------
                |   1) Add a Game App         |
                |   2) Add a productivity app |
                |   3) Add an Eduction App    |
                -------------------------------
                ==>> """);

        switch (option) {
            case 1 -> {
                Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer ==> "));
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                char isMultiplayer = ScannerInput.validNextChar("Enter if it is isMultiplayer (y/n)");
                isAdded = appStoreAPI.addApp(new GameApp(developer, appName, appSize, appCost, appVersion, Utilities.YNtoBoolean(isMultiplayer)));
            }
            case 2 -> {
                Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer"));
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                isAdded = appStoreAPI.addApp(new ProductivityApp(developer, appName, appSize, appCost, appVersion));
            }
            case 3 -> {
                Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer: "));
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                int level = ScannerInput.validNextInt("Enter the level: ");
                isAdded = appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appCost, appVersion, level));
            }
            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded) {
            System.out.println("App Added Successfully");
        } else {
            System.out.println("No app Added");
        }
    }

    public void updateApp() {

        if (appStoreAPI.numberOfApps() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.validNextInt("""
                    ----------------------------------
                    |   1) Update a Game App         |
                    |   2) Update a productivity app |
                    |   3) Update an Eduction App    |
                    ----------------------------------
                    ==>> """);

            switch (option) {
                case 1 -> {
                    showGameApps();
                    if (appStoreAPI.numberOfGameApps() > 0) {
                        int gameIndex = ScannerInput.validNextInt("Enter the index of the game to update ==> ");
                        if (appStoreAPI.isValidIndex(gameIndex)) {
                            Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer"));
                            String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                            double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                            double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                            double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                            char isMultiplayer = ScannerInput.validNextChar("Enter if it is isMultiplayer (y/n)");
                            isUpdated = appStoreAPI.updateGameApp(gameIndex, developer, appName, appSize, appCost, appVersion, Utilities.YNtoBoolean(isMultiplayer));
                        }
                    }
                }
                case 2 -> {
                    showProductivityApps();
                    if (appStoreAPI.numberOfProductivityApps() > 0) {
                        int productivityIndex = ScannerInput.validNextInt("Enter the index of the productivity post to update ==> ");
                        if (appStoreAPI.isValidIndex(productivityIndex)) {
                            Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer"));
                            String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                            double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                            double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                            double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                            isUpdated = appStoreAPI.updateProductivityApp(productivityIndex, developer, appName, appSize, appCost, appVersion);
                        }
                    }
                }
                case 3 -> {
                    showEducationApp();
                    if (appStoreAPI.numberOfEducationApps() > 0) {
                        int educationIndex = ScannerInput.validNextInt("Enter the index of the Education post to update ==> ");
                        if (appStoreAPI.isValidIndex(educationIndex)) {
                            Developer developer = developerAPI.getDevelopers().get(ScannerInput.validNextInt("Enter the index of the developer"));
                            String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                            double appSize = ScannerInput.validNextDouble("Enter the App Size:  ");
                            double appCost = ScannerInput.validNextDouble("Enter the App Cost:  ");
                            double appVersion = ScannerInput.validNextDouble("Enter the App Version: ");
                            int level = ScannerInput.validNextInt("Enter the level: ");
                            isUpdated = appStoreAPI.updateEducationApp(educationIndex, developer, appName, appSize, appCost, appVersion, level);
                        }
                    }
                }
                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isUpdated) {
                System.out.println("App Updated Successfully");
            } else {
                System.out.println("No App Updated");
            }
        } else {
            System.out.println("No apps added yet");
        }
    }
    public void deleteApp(){
        showApps();
        if (appStoreAPI.numberOfApps() > 0){
            int indexToDelete = ScannerInput.validNextInt("Enter the index of the post to delete ==> ");
            App AppToDelete = appStoreAPI.deleteApp(indexToDelete);
            if (AppToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + AppToDelete.appSummary());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }


    public void showApps() {
        System.out.println("List of All Posts are:");
        System.out.println(appStoreAPI.listAllApps());
    }

    //print the message posts in newsfeed i.e. array list.
    public void showGameApps() {
        System.out.println("List of Message Posts are:");
        System.out.println(appStoreAPI.listAllGameApps());
    }

    //print the photo posts in newsfeed i.e. array list.
    public void showProductivityApps() {
        System.out.println("List of Photo Posts are:");
        System.out.println(appStoreAPI.listAllProductivityApps());
    }

    //print the photo posts in newsfeed i.e. array list.
    public void showEducationApp() {
        System.out.println("List of Event Posts are:");
        System.out.println(appStoreAPI.listAllEducationApps());
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    public void developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        int option = ScannerInput.validNextInt("==>> ");
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            runMainMenu();
        }
    }

    public void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    public void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    public void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    public Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }

    public void sortAppsByNameAscending(){
        appStoreAPI.sortAppsByNameAscending();
    }

    public void recommendedApps(){
        System.out.println(appStoreAPI.listAllRecommendedApps());
    }

    public void randomAppOfTheDay(){
        System.out.println(appStoreAPI.randomApp());
    }


    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Get app by Index
                  3) Developer Name
                  4) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
             case 1 -> searchAppsByName();
             case 2 -> getAppByIndex();
             case 3 -> searchAppsByDeveloper();
             case 4 -> searchAppsEqualOrAboveAStarRating();
             default -> System.out.println("Invalid option");
        }
        ScannerInput.validNextLine("\n Press the enter key to continue");
        runMainMenu();
    }

    public void searchAppsByName(){
        String name = ScannerInput.validNextLine("Please enter a app name to search by:");
        System.out.println(appStoreAPI.searchByAppsName(name));
    }

    public void searchAppsByDeveloper(){
        String name = ScannerInput.validNextLine("Please enter a app name to search by:");
        System.out.println(appStoreAPI.searchAppsByDeveloperName(name));
    }

    public void searchAppsEqualOrAboveAStarRating(){
        int index = ScannerInput.validNextInt("Enter the rating ==>");
        System.out.println(appStoreAPI.listAllAppsAboveOrEqualAGivenStarRating(index));
    }

    public void getAppByIndex(){
        int index = ScannerInput.validNextInt("Enter the index of the app ==> ");
        System.out.println(appStoreAPI.getAppByIndex(index));
    }

    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    private void simulateRatings() {
        // simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfaLLApps());
        } else {
            System.out.println("No apps");
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        try {
            System.out.println("Saving to file: " + appStoreAPI.fileName());
            appStoreAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData() {
        try {
            System.out.println("Loading from file: " + appStoreAPI.fileName());
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}