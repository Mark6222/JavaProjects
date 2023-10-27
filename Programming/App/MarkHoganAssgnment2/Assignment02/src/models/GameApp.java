package models;

import utils.Utilities;

public class GameApp extends App {
    private boolean isMultiplayer = false;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
        this.isMultiplayer = isMultiplayer;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    // Algorithm - app is recommended if the following applies:
    //      isMultiplayer is true
    //      rating is 4.0 or more
    @Override
    public boolean isRecommendedApp() {
        if((isMultiplayer) && (calculateRating() >= 4.0)){
            return true;
        }else{
            return false;
        }
    }
    public String appSummary(){
        return super.appSummary() + "isMultiplayer: " + Utilities.booleanToYN(isMultiplayer);
    }

    public String toString(){
        return  "GameApp{"
                    + "Developer " + getDeveloper()
                    + "Name " + getAppName()
                    + "Size: " + getAppSize()
                    + "MB Cost: " + getAppCost()
                    + "(Version " + getAppVersion()
                    + ") Ratings (" + calculateRating()
                    + ") isMultiplayer: " + isMultiplayer +
                    super.listRatings() +
                    '}';
        }
}
