import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Subclass of Building which has a collection of book titles to be checked out
 * @author Greta Wang
 * @version 11/5/2025
 */
public class Library extends Building implements LibraryRequirements {
  private Hashtable<String, Boolean> collection;

  /**
   * Constructor for Library
   * @param name Library's name
   * @param address Library's address
   * @param nFloors Number of floors
   */
  public Library(String name, String address, int nFloors) {
    super(name, address, nFloors);
    this.collection = new Hashtable<String, Boolean>();
    System.out.println("You have built a library: 📖");
  }

  /**
   * Adds the given title to the collection
   * @param title Title to add
   */
  public void addTitle(String title){
    if (this.containsTitle(title)){
      throw new RuntimeException("Title is already in collection - cannot add.");
    }
    this.collection.put(title, true);
  }

  /**
   * Removes the given title from the collection, and returns the title removed
   * @param title Title to remove
   * @return Title that was removed
   */
  public String removeTitle(String title){
    if (this.containsTitle(title)){
      this.collection.remove(title);
    } else{
      throw new RuntimeException("Title is not in collection - cannot remove.");
    }
    return title;
  }

  /**
   * Updates Hashtable to list the given title as checked out
   * @param title Title to check out
   */
  public void checkOut(String title){
    if (this.isAvailable(title)){
      this.collection.replace(title, false);
    } else if (this.containsTitle(title)){
      throw new RuntimeException("Title already checked out.");
    } else{
      throw new RuntimeException("Title not in collection - cannot check out.");
    }
  }

  /**
   * Updates Hashtable to list the given title as available
   * @param title Title to return
   */
  public void returnBook(String title){
    if (this.containsTitle(title)){
      if (!this.isAvailable(title)){
        this.collection.replace(title, true);
      } else{
        throw new RuntimeException("Title already available - cannot return.");
      }
    } else{
      throw new RuntimeException("Title not in collection - cannot return.");
    }
  }

  /**
   * Returns true if the given title is in the collection, false otherwise
   * @param title Title to check status of
   * @return True if the title is in the collection, false otherwise
   */
  public boolean containsTitle(String title){
    return this.collection.containsKey(title);
  }

  /**
   * Returns true if the given title is in the collection and available, false otherwise
   * @param title Title to check status of
   * @return True if the title is available, false otherwise
   */
  public boolean isAvailable(String title){
    if (this.containsTitle(title)){
      return this.collection.get(title);
    } else{
      return false;
    }
  }

  /**
   * Prints the Library's collection of titles, including availablility of each
   */
  public void printCollection(){
    System.out.println(this.name + " collection:");
    if (this.collection.isEmpty()){
      System.out.println("No titles.");
    } else{
      for (Enumeration<String> titles = this.collection.keys(); titles.hasMoreElements();){
        String title = titles.nextElement();
        if (this.isAvailable(title)){
          System.out.println(title + ": Available");
        } else{
          System.out.println(title + ": Checked out");
        }
      }
    }
  }

  /**
   * Tests Library class
   * @param args The command line arguments (ignored)
   */
  public static void main(String[] args) {
    Library neilson = new Library("Neilson", "7 Neilson Dr", 5);
    System.out.println(neilson);
    neilson.printCollection();

    neilson.addTitle("Book 1");
    neilson.addTitle("Book 2");
    neilson.printCollection();

    neilson.checkOut("Book 1");
    neilson.removeTitle("Book 2");
    neilson.printCollection();

    neilson.returnBook("Book 1");
    neilson.printCollection();
  }
  
}