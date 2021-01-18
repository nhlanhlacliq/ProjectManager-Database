//Engineer class inheriting from Person
/**
 * Inherits Person class
 */
public class StructuralEngineer extends Person {
  static String personType = "Engineer";

  // Constructor - creating from user input - use super constructor
  public StructuralEngineer() {
    super(personType);
    System.out.println("Structural Engineer Created!\n");
  }

  //Constructor - creating from database using ManagerID
  public StructuralEngineer(int personID) {
    super(personID, personType);
  }

  //To string method
  public String toString(){
    String output = "\nEngineer ID:\t\t"+super.getID()+"\n";
    output += "Name: \t\t\t" + super.getName() + "\n";
    output += "Telephone number: \t" + super.getTelNumber() + "\n";
    output += "E-mail address: \t" + super.getEmail() + "\n";
    output += "Physical address: \t" + super.getPhysicalAddress() + "\n";

    return output;
  }
}
