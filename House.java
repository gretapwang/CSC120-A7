import java.util.ArrayList;

/**
 * Subclass of Building which houses Students and can have a dining room.
 * @author Greta Wang
 * @version 11/5/2025
 */
public class House extends Building implements HouseRequirements {
  private ArrayList<Student> residents;
  private boolean hasDiningRoom;

  /**
   * Constructor for House class
   * @param name House's name
   * @param address House's address
   * @param nFloors Number of floors
   * @param hasDiningRoom True if house has a dining room, false otherwise
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom) {
    super(name, address, nFloors);
    this.residents = new ArrayList<Student>();
    this.hasDiningRoom = hasDiningRoom;
    System.out.println("You have built a house: 🏠");
  }

  /**
   * Accessor for hasDiningRoom
   * @return True if house has a dining room, false otherwise
   */
  public boolean hasDiningRoom(){
    return this.hasDiningRoom;
  }

  /**
   * Returns number of residents in the house
   * @return Number of residents
   */
  public int nResidents(){
    return this.residents.size();
  }

  /**
   * Adds the given Student to the ArrayList of residents 
   * @param s Student to move in
   */
  public void moveIn(Student s){
    if (this.isResident(s)){
      throw new RuntimeException("Student is already a resident - cannot move in.");
    }
    this.residents.add(s);
  }

  /**
   * Removes the given Student from the ArrayList of residents, and returns the Student
   * @param s Student to move out
   * @return Student who moved out
   */
  public Student moveOut(Student s){
    if (this.isResident(s)){
      this.residents.remove(s);
    } else{
      throw new RuntimeException("Student is not a resident - cannot move out.");
    }
    return s;
  }

  /**
   * Returns true if the given Student is a resident, false otherwise
   * @param s Student for whom to determine residence status
   * @return True if the Student is a resident, false otherwise
   */
  public boolean isResident(Student s){
    return this.residents.contains(s);
  }

  /**
   * Tests House class
   * @param args The command line arguments (ignored)
   */
  public static void main(String[] args) {
    House gillett = new House("Gillett", "47 Elm St", 5, true);
    System.out.println(gillett);
    System.out.println("Has a dining room: " + gillett.hasDiningRoom());
    System.out.println("Residents: " + gillett.nResidents());

    Student greta = new Student("Greta", "12345", 2028);
    gillett.moveIn(greta);
    System.out.println("Residents: " + gillett.nResidents());

    gillett.moveOut(greta);
    System.out.println("Residents: " + gillett.nResidents());
  }

}