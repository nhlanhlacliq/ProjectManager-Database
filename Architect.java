//Architect class inheriting from Person
/**
 * Inherits Person class
 */
public class Architect extends Person {
  static String personType = "Architect";
  
  //Constructor - creating from user input - use super constructor
  public Architect() {
    super(personType);
    System.out.println("Architect Created!\n");
  }

  //Constructor - creating from file - use super constructor,
  @Deprecated
  public Architect(String name, String telNumber, String emailAddress, String physicalAddress) {
    super(name, telNumber, emailAddress, physicalAddress);
  }

  //Constructor - creating from database using ArchitectID
  public Architect(int personID) {
    super(personID, personType);
  }

  //To string method
  public String toString(){
    String output = "\nArchitect ID:\t\t"+super.getID()+"\n";
    output += "Name: \t\t\t" + super.getName() + "\n";
    output += "Telephone number: \t" + super.getTelNumber() + "\n";
    output += "E-mail address: \t" + super.getEmail() + "\n";
    output += "Physical address: \t" + super.getPhysicalAddress() + "\n";

    return output;
  }
}
