package models;

import utils.Utilities;

import java.util.Objects;

public class Item {

    private String itemDescription = "";
    private boolean isItemCompleted = false;

    public Item(String itemDescription) {
        this.itemDescription = Utilities.truncateString(itemDescription, 50);
    }

    public Item(String itemDescription, boolean isItemCompleted) {
        this.itemDescription = itemDescription;
        this.isItemCompleted = isItemCompleted;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isItemCompleted() {
        return isItemCompleted;
    }

    public void setItemCompleted(boolean itemCompleted) {
        isItemCompleted = itemCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isItemCompleted == item.isItemCompleted && Objects.equals(itemDescription, item.itemDescription);
    }

    public String toString() {
        String complete = "";
        if (isItemCompleted) {
            complete = "Complete";
        }else{
            complete = "ToDo";
        }
        return itemDescription + ". [" + complete + "]";
    }
}
