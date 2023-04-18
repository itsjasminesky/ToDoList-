import java.util.*;
import java.io.*;
public class TodoListManager {
    public static final boolean EXTENSION_FLAG = false;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        List<String> todos = new ArrayList <>();
        System.out.println("Welcome to your TODO List Manager!");
        String userChoice = "";
        while (!userChoice.equalsIgnoreCase("Q")) { 
            System.out.println("What would you like to do?");
            if (EXTENSION_FLAG) {
                System.out.print("(A)dd TODO, (M)ark TODO as done,"
                + " (L)oad TODOs, (S)ave TODOs, (R)emove item, (Q)uit? ");
            } else {
                System.out.print("(A)dd TODO, (M)ark TODO as done,"
                + " (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
            }
            userChoice = console.nextLine();
            if (userChoice.equalsIgnoreCase("A")) {
                addItem(console, todos);
            } else if (userChoice.equalsIgnoreCase("M")) {
                markItemAsDone(console, todos);
            } else if (userChoice.equalsIgnoreCase("L")) {
                loadItems(console, todos);
            } else if (userChoice.equalsIgnoreCase("S")) {
                saveItems(console, todos);
                // else just reprompt if not one of these options
            } else if  (userChoice.equalsIgnoreCase("R") && EXTENSION_FLAG) {
                removeItems( console,todos);
            } else if (!userChoice.equalsIgnoreCase("q")) {
                System.out.println("Unknown input: " + userChoice);
            }
            if (!userChoice.equalsIgnoreCase("q")) {
                printTodos(todos);
            }
        }
    }
    
    // Print out each item in the todo list numbered
    // Parameters:
    //   - List <String> todos: a list of todos
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if (todos.isEmpty()) {
            System.out.println("  You have nothing to do yet today! Relax!");
        } else {
            for(int i = 0; i < todos.size(); i++) {
                System.out.println ("  " + (i + 1) + ": " + todos.get(i));
            }
        }
    }
    
    //Adding a TODO to the todo printTodos
    //ask unless user return nothing being an empty string ""
    //if enter without entering number it will be entered at the end
    // Parameters:
    //   - Scanner console
    //   - List<String> todos
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String item = console.nextLine();
        if(! todos.isEmpty()) {
            int index = 0;
            System.out.print("Where in the list should it be (1-"
            + (todos.size()+ 1) + ")? (Enter for end): ");
                String placement = console.nextLine();
            if(placement.length() == 0) {
                index = todos.size();
            } else {
                index = Integer.parseInt(placement) -1;
            }
            todos.add(index, item);
        } else {
            todos.add(0, item);
        }
    }
    
    //The markItemAsDone method allow a user to mark a TODO as completed,
    //removing it from the TODO list. When prompted for an item to remove,
    //the user  indicates which item they wish to remove using the item's number.
    //If the TODO list is empty, it should not prompt the user for a tasks number.
    // Parameters:
    //   - Scanner console
    //   - List<String> todos - list of todos
    public static void markItemAsDone(Scanner console, List<String> todos) {
        if (todos.isEmpty()) {
            System.out.println("All done! Nothing left to mark as done!");
        } else {
            int indexSize = todos.size();
            System.out.print("Which item did you complete (1  -" + indexSize + ")? ");
            int completeIndex = Integer.parseInt(console.nextLine()) - 1;
            String remove = todos.remove(completeIndex);
        }
    }
    
    //Prompts user for file name with their list of todos.
    //Overrides contained todo list items.
    //Parameters:
    //   -  Scanner console
    //   - List<String> todos:  List of todo items
    public static void loadItems(Scanner console, List<String> todos)
    throws FileNotFoundException {
        System.out.print ("File name? ");
        String fileName = console.nextLine();
        Scanner inputScan = new Scanner(new File(fileName));
        todos.clear();
        while (inputScan.hasNextLine()) {
            String nextFavorite = inputScan.nextLine();
            todos.add(nextFavorite);
        }
    }
    
    //This method should prompt the user for a file name
    //and print each TODO list item to the file, in order, one item per line.
    //Parameters:
    //   -  Scanner console
    //   - List<String> todos:  List of todo items to be saved to file
    public static void saveItems(Scanner console, List<String> todos)
    throws FileNotFoundException {
        System.out.print ("File name? ");
        String fileName = console.nextLine();
        PrintStream output = new PrintStream(new File(fileName));
        for (int i = 0; i < todos.size(); i++) {
            output.println(todos.get(i));
        }
    }
    
    //This method allow the user to remove items from the
    //list using the name of the item.
    //Parameters:
    //   -  Scanner console
    //   - List<String> todos:  List of todo items to be saved to file
    public static void removeItems(Scanner console, List<String> todos){
		System.out.print("Which item from your list would you like to remove");
		String item = console.nextLine();
		todos.remove(item);
	}
}
