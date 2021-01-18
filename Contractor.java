//Contractor class inheriting from Person class
/**
 * Inherits Person class
 */
public class Contractor extends Person {
  static String personType = "Contractor";
  
  //Constructor - creating from user input - use super constructor
  public Contractor() {
    super(personType);
    System.out.println("Contractor Created!\n");
  }

  //Constructor - creating from file - use super constructor
  @Deprecated
  public Contractor(String name, String telNumber, String emailAddress, String physicalAddress) {
    super(name, telNumber, emailAddress, physicalAddress);
  }

  //Constructor - creating from database using ContractorID
  public Contractor(int personID) {
    super(personID, personType);
  }

  //To string method
  public String toString(){
    String output = "\nContractor ID:\t\t"+super.getID()+"\n";
    output += "Name: \t\t\t" + super.getName() + "\n";
    output += "Telephone number: \t" + super.getTelNumber() + "\n";
    output += "E-mail address: \t" + super.getEmail() + "\n";
    output += "Physical address: \t" + super.getPhysicalAddress() + "\n";

    return output;
  }
}