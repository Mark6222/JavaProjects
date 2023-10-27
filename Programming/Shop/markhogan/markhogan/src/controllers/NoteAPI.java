package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Item;
import models.Note;
import utils.CategoryUtility;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class NoteAPI {

    private ArrayList<Note> notes = new ArrayList<>();

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < notes.size());
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public boolean addNote(Note note) {
        return notes.add(note);
    }


    public boolean updateNote(int indexToUpdate, String noteTitle, int notePriority, String noteCategory) {
        Note foundNote = findNote(indexToUpdate);//creates an object of note and finds the note in the arraylist

        if (foundNote != null) {
            foundNote.setNoteTitle(noteTitle);
            foundNote.setNotePriority(notePriority);
            foundNote.setNoteCategory(noteCategory);
            return true;
        }
        return false;
    }

    public Note deleteNote(int index) {
        if (isValidIndex(index)) {
            return notes.remove(index);
        }
        return null;
    }

    public boolean archiveNote(int indexToArchive) {
        if (isValidIndex(indexToArchive)) {
            Note findNote = findNote(indexToArchive);
            if ((!findNote.isNoteArchived()) && (findNote.checkNoteCompletionStatus())) {
                findNote.setNoteArchived(true);
            }
        }
        return false;
    }

    public String archiveNotesWithAllItemsComplete() {
        if (!notes.isEmpty()) {
            String itemsComplete = "";
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).checkNoteCompletionStatus()) {
                    notes.get(i).setNoteArchived(true);
                    itemsComplete = i + ": " + notes.get(i) + "\n";
                }
            }
            return itemsComplete;
        } else {
            return "No active notes stored";
        }
    }

    public int numberOfNotes() {
        return notes.size();
    }

    public int numberOfArchivedNotes() {
        int archived = 0;
        for (Note note : notes) {
            if (note.isNoteArchived()) {
                archived = archived + 1;
            }
        }
        return archived;
    }

    public int numberOfActiveNotes() {
        int active = 0;
        for (Note note : notes) {
            if ((!note.isNoteArchived()) && (!note.checkNoteCompletionStatus())) {
                active = active + 1;
            }
        }
        return active;
    }

    public int numberOfNotesByCategory(String category) {
        int type = 0;
        for (Note note : notes) {
            if (note.getNoteCategory().matches(category)) {
                type = type + 1;
            }
        }
        return type;
    }

    public int numberOfNotesByPriority(int priority) {
        int numberOf = 0;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getNotePriority() == priority) {
                numberOf = numberOf + 1;
            }
        }
        return numberOf;
    }

    public int numberOfItems() {
        int number = 0;
        for (int i = 0; i < notes.size(); i++) {
            number = number + notes.get(i).numberOfItems();
        }
        return number;
    }

    public int numberOfCompletedItems() {
        int number = 0;
        for (int n = 0; n < notes.size(); n++) {
            for (int i = 0; i < notes.get(n).numberOfItems(); i++) {
                if (notes.get(n).getItems().get(i).isItemCompleted()) {
                    number = number + 1;
                }
            }
        }
        return number;
    }

    public int numberOfToDoItems() {
        int number = 0;
        for (int n = 0; n < notes.size(); n++) {
            for (int i = 0; i < notes.get(n).numberOfItems(); i++) {
                if (!notes.get(n).getItems().get(i).isItemCompleted()) {
                    number = number + 1;
                }
            }
        }
        return number;
    }

    public String listAllNotes() {
        String listAllNotes;
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            listAllNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                listAllNotes += i + ": " + notes.get(i) + "\n"
                        + notes.get(i).listItems() + "\n";
            }
        }
        return listAllNotes;
    }

    public String listActiveNotes() {
        if (!notes.isEmpty()) {
            String listActiveNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                if ((!notes.get(i).isNoteArchived()) && (!notes.get(i).checkNoteCompletionStatus())) {
                    listActiveNotes += i + ": " + notes.get(i) + "\n"
                            + notes.get(i).listItems() + "\n";
                }
            }
            return listActiveNotes;
        } else {
            return "No active notes stored";
        }
    }

    public String listArchivedNotes() {
        if (!notes.isEmpty()) {
            String listArchivedNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).isNoteArchived()) {
                    listArchivedNotes += i + ": " + notes.get(i) + "\n"
                            + notes.get(i).listItems() + "\n";
                }
            }
            return listArchivedNotes;
        } else {
            return "No Archived notes stored";
        }
    }

    public String listNotesBySelectedCategory(String category) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String listNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                if ((notes.get(i).getNoteCategory().equals(category))) {
                    return "No notes with category";
                }
            }
        }
        int numberOfNotes = 0;
        String selectedCategory = "";
        for (int i = 0; i < notes.size(); i++) {
            if ((!notes.isEmpty()) && (notes.get(i).getNoteCategory().equals(category))) {
                numberOfNotes = numberOfNotes + 1;
                selectedCategory = i + ": " + notes.get(i) + "\n"
                        + notes.get(i).listItems() + "\n";
            }
        }
        return category
                + selectedCategory + "\n";
    }

    public String listNotesBySelectedPriority(int priority) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            for (int i = 0; i < notes.size(); i++) {
                if ((notes.get(i).getNotePriority() != priority)) {
                    return "No notes with that priority";
                }
            }
        }
        int numberOfNotes = 0;
        String selectedPriority = "";
        for (int i = 0; i < notes.size(); i++) {
            if ((!notes.isEmpty()) && (notes.get(i).getNotePriority() == priority)) {
                numberOfNotes = numberOfNotes + 1;
                selectedPriority += i + ": " + notes.get(i) + "\n"
                        + notes.get(i).listItems() + "\n";
            }
        }
        return " " + selectedPriority + "\n";
    }

    public String listToDoItems() {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String ToDo = "";
            for (int n = 0; n < notes.size(); n++) {
                for (int i = 0; i < notes.get(n).numberOfItems(); i++) {
                    if (!notes.get(n).getItems().get(i).isItemCompleted()) {
                        ToDo = notes.get(n).getNoteTitle() + ": " + notes.get(n).getItems().get(i).getItemDescription() + ". [TODO]";
                    }
                }
            }
            return ToDo;
        }
    }

    public String listItemStatusByCategory(String category) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            int TODO = 0;
            int COMPLETE = 0;
            String todo = "";
            String complete = "";
            for (Note note : notes) {
                int i = 0;
                while (i < note.numberOfItems()) {
                    if ((note.getItems().get(i).isItemCompleted()) && (note.getNoteCategory().equals(category))) {
                        COMPLETE = COMPLETE + 1;
                        complete += note.getItems().get(i).getItemDescription() + "(Note: " + note.getNoteTitle() + ")";
                    } else {
                        TODO = TODO + 1;
                        todo += note.getItems().get(i).getItemDescription() + "(Note: " + note.getNoteTitle() + ")";
                    }
                    i++;
                }
            }
            return "Number Completed: " + COMPLETE + "\n"
                    + complete + "\n"
                    + "Number TODO: " + TODO + "\n"
                    + todo ;
        }
    }

    public Note findNote(int note) {
        if (isValidIndex(note)) {
            return notes.get(note);
        }
        return null;
    }

    public String searchNotesByTitle(String searchString) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String noteTitle = "";
            for (int n = 0; n < notes.size(); n++) {
                if (notes.get(n).getNoteTitle().contains(searchString)) {
                    noteTitle += n + ": " + notes.get(n);
                } else {
                    return "No notes found for: " + n;
                }
            }
            return noteTitle;
        }
    }

    public String searchItemsByDescription(String searchString) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String Item = "";
            for (int n = 0; n < notes.size(); n++) {
                for (int i = 0; i < notes.get(n).numberOfItems(); i++) {
                    if (notes.get(n).getItems().get(i).getItemDescription().matches(searchString)) {
                        Item += n + ": Note: " + notes.get(n).getNoteTitle() + " Item: " + notes.get(n).getItems().get(i).getItemDescription();
                    } else {
                        return "No items found for: " + n;
                    }
                }
            }
            return Item;
        }
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{NoteAPI.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("notes.xml"));
        notes = (ArrayList<Note>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("notes.xml"));
        out.writeObject(notes);
        out.close();
    }

}