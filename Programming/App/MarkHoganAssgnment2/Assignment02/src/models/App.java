package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    private Developer developer;
    private String appName = "No app name";
    private double appSize = 0;
    private double appVersion = 1.0;
    private double appCost = 0;
    private List<Rating> ratings = new ArrayList<>();

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;
        if(Utilities.validRange(appSize, 1, 1000)) {
            this.appSize = appSize;
        }
        if(Utilities.greaterThanOrEqualTo(appVersion, 1.0)) {
            this.appVersion = appVersion;
        }
        if(Utilities.greaterThanOrEqualTo(appCost, 0.0)) {
            this.appCost = appCost;
        }
    }

    public abstract boolean isRecommendedApp();

    public String appSummary(){
        return "App name: " + appName + "(V" +  appVersion + ") " + developer + " " + "€"+ appCost + " " + "Rating: " + calculateRating();
    }

    public boolean addRating(Rating rating){
    return ratings.add(rating);
    }

    public String listRatings(){
        String str = "";
    if(!ratings.isEmpty()){
        for(Rating rating: ratings){
            return str += ratings;
        }
    }else{
        return str = "no Rating Added";
    }
    return str;
    }

    public double calculateRating(){
        double Number = 0;
        double rate = 0;
    if(ratings.isEmpty()){
        return Number;
    }else{
         for(Rating rating: ratings){
             if(rating.getNumberOfStars() > 0){
                 Number = Number+ rating.getNumberOfStars();
                 rate = rate +1;
             }
         }
    }
    return Number/ratings.size();
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getAppSize() {
        return appSize;
    }

    public void setAppSize(double appSize) {
        if(Utilities.validRange(appSize, 1, 1000)) {
            this.appSize = appSize;
        }
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        if(Utilities.greaterThanOrEqualTo(appVersion, 1.0)) {
            this.appVersion = appVersion;
        }
    }

    public double getAppCost() {
        return appCost;
    }

    public void setAppCost(double appCost) {
        if(Utilities.greaterThanOrEqualTo(appCost, 0.0)) {
            this.appCost = appCost;
        }
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
