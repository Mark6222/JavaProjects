package models;

public class ProductivityApp extends App{

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        super(developer, appName, appSize, appVersion, appCost);
    }

    // Algorithm - app is recommended if the following applies:
    //      appCost is greater tahn or equal to 1.99
    //      calculated rating of more than 3.0
    @Override
    public boolean isRecommendedApp() {
        if((super.getAppCost() > 1.99) && (super.calculateRating() >= 3.0)) {
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return  "Productivity App{"
                + "Developer " + getDeveloper()
                + "Name " + getAppName()
                + "Size " + getAppSize()
                + "MB Cost: " + getAppCost()
                + "(Version " + getAppVersion()
                + ") Ratings (" + calculateRating()
                + super.listRatings() +
                '}';
    }

}
