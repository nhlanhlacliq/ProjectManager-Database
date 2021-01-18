//Manager class inheriting from Person
/**
 * Inherits Person class
 */
public class ProjectManager extends Person {
  static String personType = "Manager";

  // Constructor - creating from user input - use super constructor
  public ProjectManager() {
    super(personType);
    System.out.println("Project Manager Created!\n");
  }

  //Constructor - creating from database using ManagerID
  public ProjectManager(int personID) {
    super(personID, personType);
  }

  //To string method
  public String toString(){
    String output = "\nManager ID:\t\t"+super.getID()+"\n";
    output += "Name: \t\t\t" + super.getName() + "\n";
    output += "Telephone number: \t" + super.getTelNumber() + "\n";
    output += "E-mail address: \t" + super.getEmail() + "\n";
    output += "Physical address: \t" + super.getPhysicalAddress() + "\n";

    return output;
  }
}
