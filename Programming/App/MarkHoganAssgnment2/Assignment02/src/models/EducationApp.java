package models;

import utils.Utilities;

public class EducationApp extends App {

    private int level = 0;


    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        super(developer, appName, appSize, appVersion, appCost);
        if(Utilities.validRange(level, 1, 10)){
            this.level = level;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(Utilities.validRange(level, 1, 10)){
            this.level = level;
        }
    }



    // Algorithm - app is recommended if the following applies:
    //      appCost is greater than 0.99
    //      calculated rating is greater than or equal to 3.5
    //      level is 3 or more.
    public boolean isRecommendedApp(){
    if((super.getAppCost() > 0.99) && (calculateRating() >= 3.5 ) && (level >= 3)){
        return true;
    }else{
        return false;
    }
    }

    public String appSummary(){
        return super.appSummary() + "level " + level;
    }

    public String toString(){
        return "EducationApp{"
                + "Developer " + getDeveloper()
                + "Name " + getAppName()
                + "Size " + getAppSize()
                + "MB Cost: " + getAppCost()
                + "(Version " + getAppVersion()
                + ") Ratings (" + calculateRating()
                + ") Level: " + level
                + super.listRatings() +
                '}';
    }

}