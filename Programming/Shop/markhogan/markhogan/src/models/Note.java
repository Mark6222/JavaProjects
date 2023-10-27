package models;

import controllers.NoteAPI;
import utils.CategoryUtility;
import utils.Utilities;
import java.util.ArrayList;
import java.util.Objects;

public class Note {
    private String noteTitle = "";
    private int notePriority = 1;
    private String noteCategory = "";
    private boolean isNoteArchived = false;
    private ArrayList<Item> items = new ArrayList<Item>();
    private CategoryUtility category = new CategoryUtility();

    public Note(String noteTitle, int notePriority, String noteCategory) {
        if (!Utilities.validRange(notePriority, 1, 5)) {
            this.notePriority = 1;
        }
        if(category.isValidCategory(noteCategory)){
            this.noteCategory = noteCategory;
        }else{
            this.noteCategory = "";
        }
        this.noteTitle = noteTitle;
    }


    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public int getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(int notePriority) {
        this.notePriority = notePriority;
    }

    public String getNoteCategory() {
        return noteCategory;
    }

    public void setNoteCategory(String noteCategory) {
        this.noteCategory = noteCategory;
    }

    public boolean isNoteArchived() {
        return isNoteArchived;
    }

    public void setNoteArchived(boolean noteArchived) {
        isNoteArchived = noteArchived;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public String listItems() {
        if (items.isEmpty()) {
            return "No items added";
        } else {
            String listOfItems = "";
            for (int i = 0; i < items.size(); i++) {
                listOfItems += "     " + i + ": " + items.get(i) + "\n";
            }
            return listOfItems;
        }
    }

    public Item findItem(int item) {
        if (isValidIndex(item)) {
            return items.get(item);
        }
        return null;
    }

    public boolean updateItem(int index, String itemDescription, boolean itemComplete) {
        Item foundItem = findItem(index);

        if (foundItem != null) {
            foundItem.setItemDescription(itemDescription);
            foundItem.setItemCompleted(itemComplete);
            return true;
        }
        return false;
    }

    public Item deleteItem(int index) {
        if (isValidIndex(index)) {
            return items.remove(index);
        }
        return null;
    }

    public int numberOfItems() {
        return items.size();
    }

    public boolean isValidIndex(int index) {
        if ((index >= 0) && (index < items.size())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNoteCompletionStatus() {
        if (!items.isEmpty()) {
            for (Item item : items) {
                if (item.isItemCompleted()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return getNotePriority() == note.getNotePriority() && isNoteArchived() == note.isNoteArchived() && getNoteTitle().equals(note.getNoteTitle()) && getNoteCategory().equals(note.getNoteCategory()) && getItems().equals(note.getItems());
    }

    public String toString() {
        return "Title" + noteTitle + " ,Priority=" + notePriority + ", Category=" + noteCategory + ", Archived=" + Utilities.booleanToYN(isNoteArchived);
    }


}
