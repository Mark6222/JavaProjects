package main;

import controllers.NoteAPI;
import models.Item;
import models.Note;
import utils.ScannerInput;
import utils.Utilities;



public class Driver {

    private NoteAPI noteAPI = new NoteAPI();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    private int mainMenu() {
        return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                            Shop Menu                           |
                ------------------------------------------------------------------
                |   1) Add a note                                                |
                |   2) List all notes (all, active, archived)                    |
                |   3) Update a note                                             |
                |   4) Delete a note                                             |
                |   5) Archive a note                                            |
                ------------------------------------------------------------------
                |   ITEM MENU                                                    |
                |   6) add a item to a note                                      |
                |   7) Update item description on a note                         |
                |   8) Delete an item from a note                                |
                |   9) Mark item as complete/todo                                |
                ------------------------------------------------------------------
                |   REPORT MENU FOR ITEMS                                        |
                |   10) All notes and there items (active & archived)            |
                |   11) Archive notes whose items are all complete               |
                |   12) All notes within a selected Category                     |
                |   13) All notes within a selected Priority                     |
                |   14) Search for all the notes (by note title )                |
                ------------------------------------------------------------------
                |   REPORT MENU FOR ITEMS                                        |
                |   15) All items that are todo (with note title)                |
                |   16) Overall number of items todo/complete                    |
                |   17) Todo/complete items by specific Category                 |
                |   18) Search for all items (by item description)               |
                ------------------------------------------------------------------
                SETTINGS MENU                                                    |
                |   20) Save                                                     |
                |   21) Load                                                     |
                |   0) Exit                                                      |
                ------------------------------------------------------------------
                ==>>""");
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addNote();
                case 2 -> viewNotes();
                case 3 -> updateNote();
                case 4 -> deleteNote();
                case 5 -> archiveNote();
                case 6 -> addItemToNote();
                case 7 -> updateItemDescriptionOnNote();
                case 8 -> deleteItemFromNote();
                case 9 -> markCompletionOfItem();
                case 10 -> printActiveAndArchivedReport();
                case 11 -> archiveNotesWithAllItemsComplete();
                case 12 -> printNotesBySelectedCategory();
                case 13 -> printNotesByPriority();
                case 14 -> searchNotesByTitle();
                case 15 -> printAllTodoItems();
                case 16 -> printOverallItemsTodoComplete();
                case 17 -> printItemCompletionStatusByCategory();
                case 18 -> searchItemsByDescription();
                case 20 -> save();
                case 21 -> load();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }
        System.out.println("Exiting...bye");
        System.exit(0);

    }

    private void addNote() {
        String noteTitle = ScannerInput.readNextLine("Enter the notes title");
        int notePriority = ScannerInput.readNextInt("Enter the priority");
        String noteCategory = ScannerInput.readNextLine("Choose a Category for the note");
        boolean isAdded = noteAPI.addNote(new Note(noteTitle, notePriority, noteCategory));
        if (isAdded) {
            System.out.println("Note Added Successfully");
        } else {
            System.out.println("No Note Added");
        }
    }

    private void viewNotes() {
        int number = ScannerInput.readNextInt("""
                ----------------------------
                |   1) View All notes      |
                |   2) view Active notes   |
                |   3) view Archived notes | 
                ----------------------------
                ==>> """);

        while (number != 0) {

            switch (number) {
                case 1 -> printAllNotes();
                case 2 -> printActiveNotes();
                case 3 -> printArchivedNotes();
                default -> System.out.println("Invalid option entered: " + number);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");
            break;
        }
        mainMenu();
    }

    private void updateNote() {
        printAllNotes();
        if (noteAPI.numberOfNotes() < 0) {
            System.out.println("No notes to update");
        } else {
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the product to update ==> ");
            if (noteAPI.isValidIndex(indexToUpdate)) {
                String noteTitle = ScannerInput.readNextLine("Enter the notes title: ");
                int notePriority = ScannerInput.readNextInt("Enter the priority: ");
                String noteCategory = ScannerInput.readNextLine("Choose a Category for the note: ");

                if (noteAPI.updateNote(indexToUpdate, noteTitle, notePriority, noteCategory)) {
                    System.out.println("Update Successful");
                } else {
                    System.out.println("Update NOT Successful");
                }
            }
        }
    }

    private void deleteNote() {
        printAllNotes();
        if (noteAPI.numberOfNotes() < 0) {
            System.out.println("No notes to update");
        } else {
            int indexToDelete = ScannerInput.readNextInt("Enter the note of the product to delete ==> ");
            Note noteToDelete = noteAPI.deleteNote(indexToDelete);
            if (noteToDelete != null) {
                System.out.println("Delete Successful! Deleted " + noteToDelete.getNoteTitle());
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void archiveNote() {
        printAllNotes();
        int indexToArchive = ScannerInput.readNextInt("Enter the index of the note to archive ==> ");
        noteAPI.archiveNote(indexToArchive);
    }

    private void addItemToNote() {
        printAllNotes();
        int indexOfNote = ScannerInput.readNextInt("Enter the index of the note ==> ");
        if (noteAPI.isValidIndex(indexOfNote)) {
            String itemDescription = ScannerInput.readNextLine("Enter the item description");
            boolean isAdded = noteAPI.getNotes().get(indexOfNote).addItem(new Item(itemDescription));
            if (isAdded) {
                System.out.println("Item Added Successfully");
            } else {
                System.out.println("No Item Added");
            }
        } else {
            System.out.println("Note is not valid");
        }
    }

    private void updateItemDescriptionOnNote() {
        if (noteAPI.numberOfNotes() < 0) {
            System.out.println("No notes to update");
        } else {
            int indexToUpdateNote = ScannerInput.readNextInt("Enter the index of the note to update ==> ");
            int indexToUpdateItem = ScannerInput.readNextInt("Enter the index of the item to update ==> ");
            if ((noteAPI.isValidIndex(indexToUpdateNote)) && (noteAPI.isValidIndex(indexToUpdateItem))) {
                String itemDescription = ScannerInput.readNextLine("Enter the item description ");
                char isItemComplete = ScannerInput.readNextChar("Enter if it is complete (y/n): ");
                if (noteAPI.getNotes().get(indexToUpdateNote).updateItem(indexToUpdateItem, itemDescription, Utilities.YNtoBoolean(isItemComplete))) {
                    System.out.println("Update Successful");
                } else {
                    System.out.println("Update NOT Successful");
                }
            } else {
                System.out.println("Index NOT valid");
            }
        }
    }

    private void deleteItemFromNote() {
        if (noteAPI.numberOfNotes() < 0) {
            System.out.println("No notes to update");
        } else {
            int indexNote = ScannerInput.readNextInt("Enter the index of the note ==> ");
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the item to delete ==> ");
            noteAPI.getNotes().get(indexNote).deleteItem(indexToDelete);
            if (noteAPI.getNotes().get(indexNote).deleteItem(indexToDelete) == null) {
                System.out.println("Delete Successful!");
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void markCompletionOfItem() {
        printActiveNotes();
        int noteIndex = ScannerInput.readNextInt("Enter The index of the note you want to access");
        if ((noteAPI.isValidIndex(noteIndex)) && (noteAPI.getNotes().get(noteIndex).numberOfItems() > 0)) {
            int itemIndex = ScannerInput.readNextInt("Enter The index of the item you want to access");
            char markCompletion = ScannerInput.readNextChar("Enter if the item is complete (y/n): ");
            noteAPI.getNotes().get(noteIndex).getItems().get(itemIndex).setItemCompleted(Utilities.YNtoBoolean(markCompletion));
        } else {
            System.out.println("Note is not valid or there are no items in teh note");
        }
    }

    private void printAllNotes() {
        System.out.println(noteAPI.listAllNotes());
    }

    private void printArchivedNotes() {
        System.out.println(noteAPI.listArchivedNotes());
    }

    private void printActiveNotes() {
        System.out.println(noteAPI.listActiveNotes());
    }

    private void printActiveAndArchivedReport() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("number of active notes: " + noteAPI.numberOfActiveNotes());
        System.out.println(noteAPI.listActiveNotes());
        System.out.println("------------------------------------------------------------------");
        System.out.println("Number of Archived notes: " + noteAPI.numberOfArchivedNotes());
        System.out.println(noteAPI.listArchivedNotes());
        System.out.println("------------------------------------------------------------------");
    }

    private void archiveNotesWithAllItemsComplete() {
        noteAPI.archiveNotesWithAllItemsComplete();
    }

    private void printNotesBySelectedCategory() {
        if (noteAPI.numberOfNotes() > 0) {
            String category = ScannerInput.readNextLine("Enter a category for the note, Home, Holiday, Hobby, College, Work");
            System.out.println(noteAPI.numberOfNotesByCategory(category) + " notes with that category: " + "\n" + noteAPI.listNotesBySelectedCategory(category));
        } else {
            System.out.println("No notes exist");
        }
    }

    private void printNotesByPriority() {
        if (noteAPI.numberOfNotes() > 0) {
            int Priority = ScannerInput.readNextInt("Enter a priority for note, 1 to 5");
            System.out.println(noteAPI.numberOfNotesByPriority(Priority) + " notes with that priority: " + "\n" + noteAPI.listNotesBySelectedPriority(Priority));
        } else {
            System.out.println("No notes exist");
        }
    }

    private void searchNotesByTitle() {
        if (noteAPI.numberOfNotes() > 0) {
            String Title = ScannerInput.readNextLine("Enter the Title of the note: ");
            System.out.println(noteAPI.searchNotesByTitle(Title));
        } else {
            System.out.println("No notes exist");
        }
    }

    private void printAllTodoItems() {
        System.out.println(noteAPI.listToDoItems());
    }

    private void printOverallItemsTodoComplete() {
        if (noteAPI.numberOfItems() > 0) {
            System.out.println("Number of complete items" + noteAPI.numberOfCompletedItems());
            System.out.println("Number of ToDo items" + noteAPI.numberOfToDoItems());
        } else {
            System.out.println("No items stored");
        }
    }

    private void printItemCompletionStatusByCategory() {
        if (noteAPI.numberOfItems() < 0) {
            System.out.println("No items stored");
        } else {
            String category = ScannerInput.readNextLine("Enter a category, Home, Holiday, Hobby, College, Work: ");
            System.out.println(noteAPI.listItemStatusByCategory(category));
        }
    }

    private void searchItemsByDescription() {
        if (noteAPI.numberOfItems() > 0) {
            String Description = ScannerInput.readNextLine("Enter the description for the item :");
            System.out.println(noteAPI.searchItemsByDescription(Description));
        } else {
            System.out.println("No notes exist");
        }
    }

    public void save(){
        try {
            noteAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    public void load(){
        try {
            noteAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }
}
