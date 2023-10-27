package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class CategoryUtility {
    private ArrayList<String> categories = new ArrayList<String>() {
        {
            add("Home");
            add("Work");
            add("Holiday");
            add("College");
            add("Hobby");
        }
    };

    public ArrayList<String> getCategories() {
        return categories;
    }

    public boolean isValidCategory(String category) {
        if(categories.contains(category)){
            return true;
        }else{
            return false;
        }
    }
}