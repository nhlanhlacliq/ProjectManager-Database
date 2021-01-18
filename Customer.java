//Customer class inheriting from Person
/**
 * Inherits Person class
 */
public class Customer extends Person {
  static String personType = "Customer";

  // Constructor - creating from user input - use super constructor
  public Customer() {
    super(personType);
    System.out.println("Customer Created!\n");
  }

  //Constructor - creating from file - use super constructor
  @Deprecated
  public Customer(String name, String telNumber, String emailAddress, String physicalAddress) {
    super(name, telNumber, emailAddress, physicalAddress);
  }

  //Constructor - creating from database using CustomerID
  public Customer(int personID) {
    super(personID, personType);
  }

  //To string method
  public String toString(){
    String output = "\nCustomer ID:\t\t"+super.getID()+"\n";
    output += "Name: \t\t\t" + super.getName() + "\n";
    output += "Telephone number: \t" + super.getTelNumber() + "\n";
    output += "E-mail address: \t" + super.getEmail() + "\n";
    output += "Physical address: \t" + super.getPhysicalAddress() + "\n";

    return output;
  }
}
