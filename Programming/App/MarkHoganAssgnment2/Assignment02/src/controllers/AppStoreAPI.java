package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.ISerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer {

    List<App> apps = new ArrayList<>();
    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)


    public boolean addApp(App app) {
        return apps.add(app);
    }

    public App getAppByIndex(int index) {
        if (apps.get(index) != null) {
            return apps.get(index);
        }
        return null;
    }

    public App getAppByName(String name) {
        for (int i = 0; i > apps.size(); i++) {
            if (apps.get(i).getAppName().matches(name)) {
                return apps.get(i);
            }
        }
        return null;
    }


    public String listAllApps() {
        String list = "";
        for (App app : apps) {
            list += apps.indexOf(app) + ": " + app + "\n";
        }
        if (apps.isEmpty()) {
            return "No Apps";
        } else {
            return list;
        }
    }

    public String listSummaryOfaLLApps() {
        String list = "";
        for (App app : apps) {
            list += apps.indexOf(app) + ": " + app.appSummary() + "\n";
        }
        if (apps.isEmpty()) {
            return "No Apps";
        } else {
            return list;
        }
    }

    public String listAllGameApps() {
        String list = "";
        for (App app : apps) {
            if (app instanceof GameApp) {
                list += apps.indexOf(app) + ": " + app + "\n";
            }
            if (app instanceof GameApp) {
                return list;
            }
        }
        return "No Game Apps";
    }

    public String listAllEducationApps() {
        String list = "";
        for (App app : apps) {
            if (app instanceof EducationApp) {
                list += apps.indexOf(app) + ": " + app + "\n";
            }
            if (app instanceof EducationApp) {
                return list;
            }
        }
        return "No Education Apps";
    }

    public String listAllProductivityApps() {
        String list = "";
        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                list += apps.indexOf(app) + ": " + app + "\n";
            }
            if (app instanceof ProductivityApp) {
                return list;
            }
        }
        return "No Productivity Apps";
    }

    public String listAllAppsByName(String name) {
        String list = "";
        for (App app : apps) {
            if (app.getAppName().toLowerCase().contains(name.toLowerCase())) {
                list += apps.indexOf(app) + ": " + app.appSummary() + "\n";
            }
        }
        if (apps.isEmpty()) {
            return list;
        }
        return "No Apps for the name " + name;
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating) {
        String list = "";
        for (App app : apps) {
            if (app.calculateRating() >= rating)
                list += apps.indexOf(app) + ": " + app + "\n";
        }
        if (apps.isEmpty()) {
            return "No Apps for the rating " + rating;
        } else {
            return list;
        }
    }

    public String listAllRecommendedApps() {
        if (apps.isEmpty()) {
            return "No apps";
        }
        String list = "";
        for (App app : apps) {
            if (app.isRecommendedApp()) {
                list += apps.indexOf(app) + ": " + app + "\n";
            }
        }
         if(list.equals("")){
            return "No recommended apps";
        }
        return list;
    }

    public String listAllAppsByChosenDeveloper(Developer developer) {
        String list = "";
        for (App app : apps) {
            if (app.getDeveloper().equals(developer))
                list += apps.indexOf(app) + ": " + app.appSummary() + "\n";
        }
        if (apps.isEmpty()) {
            return "No Apps";
        } else {
            return list;
        }
    }

    public boolean updateGameApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        App foundApp = findApp(indexToUpdate);
        if ((foundApp != null) && (foundApp instanceof GameApp)) {
            foundApp.setDeveloper(developer);
            foundApp.setAppName(appName);
            foundApp.setAppSize(appSize);
            foundApp.setAppVersion(appVersion);
            foundApp.setAppCost(appCost);
            ((GameApp) foundApp).setMultiplayer(isMultiplayer);
            return true;
        }
        return false;
    }

    public boolean updateProductivityApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost) {
        App foundApp = findApp(indexToUpdate);
        if ((foundApp != null) && (foundApp instanceof ProductivityApp)) {
            foundApp.setDeveloper(developer);
            foundApp.setAppName(appName);
            foundApp.setAppSize(appSize);
            foundApp.setAppVersion(appVersion);
            foundApp.setAppCost(appCost);
            return true;
        }
        return false;
    }

    public boolean updateEducationApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        App foundApp = findApp(indexToUpdate);
        if ((foundApp != null) && (foundApp instanceof EducationApp)) {
            foundApp.setDeveloper(developer);
            foundApp.setAppName(appName);
            foundApp.setAppSize(appSize);
            foundApp.setAppVersion(appVersion);
            foundApp.setAppCost(appCost);
            ((EducationApp) foundApp).setLevel(level);
            return true;
        }
        return false;
    }

    public App deleteApp(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return apps.remove(indexToDelete);
        }
        return null;
    }

    public App findApp(int index) {
        if (isValidIndex(index)) {
            return apps.get(index);
        }
        return null;
    }

    public int numberOfAppsByChosenDeveloper(Developer developer) {
        int number = 0;
        for (App app : apps) {
            if (app.getDeveloper().equals(developer))
                number = number + 1;
        }
        return number;
    }

    public int numberOfApps() {
        return apps.size();
    }

    public int numberOfGameApps() {
        int number = 0;
        for (App app : apps) {
            if (app instanceof GameApp) {
                number++;
            }
        }
        return number;
    }

    public int numberOfProductivityApps() {
        int number = 0;
        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                number++;
            }
        }
        return number;
    }

    public int numberOfEducationApps() {
        int number = 0;
        for (App app : apps) {
            if (app instanceof EducationApp) {
                number++;
            }
        }
        return number;
    }

    public App randomApp() {
        Random random = new Random();
        int app = random.nextInt(apps.size());
        if (!apps.isEmpty()) {
            return apps.get(app);
        }
        return null;
    }

    public void sortAppsByNameAscending() {
        for (int i = apps.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps(apps, i, highestIndex);
        }
    }

    private void swapApps(List<App> employees, int i, int j) {
        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i, bigger);
        apps.set(j, smaller);
    }

    public String searchByAppsName(String appName) {
        String matchingApps = "";
        for (App app : apps) {
            if ((app.getAppName().toUpperCase().contains(appName.toUpperCase())) && (isValidAppName(appName))) {
                matchingApps += apps.indexOf(app) + ": " + app.appSummary() + "\n";
            }
        }

        if (matchingApps.equals("")) {
            return "No apps match your search";
        } else {
            return matchingApps;
        }
    }

    public String searchAppsByDeveloperName(String developerName) {
        String matchingApps = "";
        for (App app : apps) {
            if (app.getDeveloper().getDeveloperName().toUpperCase().contains(developerName.toUpperCase())) {
                matchingApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }

        if (matchingApps.equals("")) {
            return "No apps match your search";
        } else {
            return matchingApps;
        }
    }


    //---------------------
    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED method as you start working through this class
    //---------------------
    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }

    //---------------------
    // Validation methods
    //---------------------
    // TODO UNCOMMENT THIS COMPlETED method as you start working through this class
    //---------------------

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    public boolean isValidAppName(String name) {
        for (App app : apps)
            if (app.getAppName().equals(name)) {
                return true;
            }
        return false;
    }


    //---------------------
    // Persistence methods
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED CODE block as you start working through this class
    //---------------------
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName() {
        return "apps.xml";
    }

}