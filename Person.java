// Person class
import java.util.Scanner;
import java.sql.*;
import java.text.MessageFormat;

/**
 * Implements a Person super class for the Customer, Architect, Contractor,
 * (structural) Engineer and (project) Manager. 
 */
public class Person {
  //instance variables
  private int id;
  private String name;
  private String telNumber;
  private String emailAddress;
  private String physicalAddress;
  private String personType;
  private int projectNumber;
  private Scanner sc = new Scanner(System.in);
  Statement statement = ConnectDatabase.getStatement();
  ResultSet results;

  /**
   * Constructors a person with user input.
   * 
   * Calls setter methods with internal logic for acquiring data.
   * @param personType person type (e.g. Customer, Architect, etc.)
   */
  public Person(String personType) {
    this.personType = personType;
    setID();
    setName();
    setTelNumber();
    setEmail();
    setPhysicalAddress();
  }

  /**
   * Constructs a person from the data file.
   * @param name this person's name.
   * @param telNumber this person's number.
   * @param emailAddress this person's e-mail.
   * @param physicalAddress this person's address.
   */
  @Deprecated
  public Person(String name, String telNumber, String emailAddress, String physicalAddress) {
    this.name = name;
    this.telNumber = telNumber;
    this.emailAddress = emailAddress;
    this.physicalAddress = physicalAddress;
  }

  /**
   * Constructs a person from the database
   * 
   * @param personID person's ID
   * @param personType person type (e.g. Customer, Architect, etc.)
   */
  public Person(int personID, String personType) {
    try {
      //choose table using personType, 
      //get persons's details using id, assign details to variables, 
      //then set person object details
      results = statement.executeQuery(
        "SELECT * FROM "+ personType +"s WHERE "+personType+"ID="+personID);
      results.first();
      String name = results.getString("Name");
      String telephone = results.getString("Telephone");
      String email = results.getString("Email");
      String address = results.getString("Address");
      this.id = personID;
      this.name = name;
      this.telNumber = telephone;
      this.emailAddress = email;
      this.physicalAddress = address;
      this.personType = personType;

    } catch (Exception e) {
      System.out.println("Unable to retrieve "+personType+"s details");
      e.printStackTrace();
    }
  }

  /**
   * Gets this person's ID
   * 
   * @return person's ID
   */
  public int getID() {
    return id;
  }

  /**
   * Sets this person's ID
   * 
   * Gets last person's ID in the database, then adds 1.
   * Create person in correct table
   */
  public void setID() {
    try {
      String command = MessageFormat.format(
        "SELECT * FROM {0}s ORDER BY {0}ID DESC LIMIT 1", personType);
      results = statement.executeQuery(command);
      results.first();
      this.id = results.getInt(personType+"ID") + 1;
      String insertCommand = MessageFormat.format(
        "INSERT INTO {0}s VALUES({1,number,#},null,null,null,null)",personType,id);
      statement.executeUpdate(insertCommand);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets this person's name (user input).
   * 
   * Gets user input, validates it and assigns the name.
   * Updates database
   */
  public void setName() {
    //get input from user, making sure name isn't empty
    System.out.println("Enter name");
    String name = sc.nextLine();
    while (name.isEmpty()) {
      System.out.println("Name can't be blank. Please retry");
      name = sc.nextLine();
    }
    name.strip();
    this.name = name;
    name = escapeApostrophe(name);
    updatePerson(personType, "Name","'"+name+"'");
  }

  
  /**
   * Gets this person's name.
   * 
   * @return person's name.
   */
  public String getName() {
    return name;
  } 

  /**
   * Sets this person's telephone (from user input).
   * 
   * Gets user input, validates it and assigns the number.
   * Updates Database
   */
  public void setTelNumber() {
    //get input from user, making sure number has a length of atleast 10 and starts with 0
    System.out.println("Enter telephone number");
    String telNumber = sc.nextLine();
    while (((telNumber.startsWith("0")) & (telNumber.length() >= 10) & (telNumber.length() < 13)) == false) {
      System.out.println("Enter full telephone number(10-12 digits & Starts with 0)");
      telNumber = sc.nextLine();
    }
    this.telNumber = telNumber;
    updatePerson(personType, "Telephone","'"+telNumber+"'");
  }

  /**
   * Gets this person's telephone number.
   * 
   * @return person's number.
   */
  public String getTelNumber() {
    return telNumber;
  }

  /**
   * Sets this person's e-mail(from user input).
   * 
   * Gets user input, validates it and assigns the email.
   * Updates Database
   */
  public void setEmail() {
    //get input form user, making sure email has "@" and "." characters
    System.out.println("Enter e-mail address");
    String email = sc.nextLine();
    while (((email.contains("@") & (email.contains("."))) == false)) {
      System.out.println("Enter valid e-mail");
      email = sc.nextLine();
    }
    this.emailAddress = email;
    email = escapeApostrophe(email);
    updatePerson(personType, "Email","'"+email+"'");
  }
  
  /**
   * Gets this person's e-mail.
   * 
   * @return person's e-mail.
   */
  public String getEmail() {
    return emailAddress;
  }

  /**
   * Sets this person's address(from user input).
   * 
   * Gets user input, validates it and assigns the address.
   * Updates database.
   */
  public void setPhysicalAddress() {
    //get input form user, making sure address isn't empty
    System.out.println("Enter physical address");
    String physicalAddress = sc.nextLine();
    while (physicalAddress.isEmpty()) {
      System.out.println("Address can't be empty");
      physicalAddress = sc.nextLine();
    }
    this.physicalAddress = physicalAddress;
    physicalAddress = escapeApostrophe(physicalAddress);
    updatePerson(personType, "Address","'"+physicalAddress+"'");
  }
  
  /**
   * Gets this person's address.
   * 
   * @return person's address.
   */
  public String getPhysicalAddress() {
    return physicalAddress;
  }

  /**
   * Get's this person's type (Customer, Architect etc.)
   * 
   * @return person's type.
   */
  public String getPersonType() {
    return personType;
  }

  /**
   * Set's this person project number that they are assigned to.
   * 
   * Only used for updating the projects table with this person's id.
   * @param projectNumber this person's project number they are assigned to
   */
  public void setProjectNumber(int projectNumber) {
    this.projectNumber = projectNumber;
  }

  /**
   * Updates the relevant table with the peron's details.
   * 
   * @param personType person's type and table that is updated
   * @param column column that is updated
   * @param value value to update column with
   */
  public void updatePerson(String personType, String column, String value) {
    //use an update command (updates person based on Person's ID), 
    //that has different arguments depending on calling method to update required fields.
    try {
      String updateCommand = MessageFormat.format(
        "UPDATE {0}s SET {1}={2} WHERE {0}ID ="+id, personType,column,value);  
      statement.executeUpdate(updateCommand);
      System.out.println("\t-Value Updated-");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Updates PersonID field in projects table
   */
  public void updateProject() {
    //use an update command (updates person ID column in projects table), 
    try {
      String updateCommand = MessageFormat.format(
        "UPDATE projects SET {0}ID={1,number,#} WHERE ProjectID={2,number,#}",personType,id,projectNumber);  
      statement.executeUpdate(updateCommand);
      System.out.println("Value Updated.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates this person's details with details from database.
   * 
   * Called when this person is viewed.
   */
  public void updateFromDatabase() {
    try {
      //get persons's details from database, then update this person
      results = statement.executeQuery(
        "SELECT * FROM "+ personType +"s WHERE "+personType+"ID="+id);
      results.first();
      String name = results.getString("Name");
      String telephone = results.getString("Telephone");
      String email = results.getString("Email");
      String address = results.getString("Address");
      this.name = name;
      this.telNumber = telephone;
      this.emailAddress = email;
      this.physicalAddress = address;

    } catch (Exception e) {
      System.out.println("Unable to retrieve "+personType+"s updated details");
      e.printStackTrace();
    }
  }

  /**
   * Adds escape character to apostrophes if found in string,
   * For storing data in database without errors.
   * 
   * @param string string to add escape character to
   * @return string with escape character for the apostrophe
   */
  public static String escapeApostrophe(String string) {
    string = string.replaceAll("'", "''");
    return string;
  }

  /**
   * Returns this person's details in format for an invoice.
   * 
   * @return the person's detail string.
   */
  public String invoiceFormat() {
    String output = "Name:\t\t\t" + name + "\n";
    output += "Telephone number:\t" + telNumber + "\n";
    output += "E-mail address:\t\t" + emailAddress + "\n";
    output += "Physical address:\t" + physicalAddress + "\n";

    return output;
  }
}
