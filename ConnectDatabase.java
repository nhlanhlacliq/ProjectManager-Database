//module imports
import java.sql.*;
import java.util.Properties;

/**
 * This class creates a singleton database connection that the other classes
 * can utilize for accessing the database.
 */
public class ConnectDatabase {
  private static Connection connection = null;
  private static Statement statement = null;

  /**
   * Returns a statement object from the database connection
   * 
   * @return statement object
   */
  public static Statement getStatement() {
    if (statement != null) {
      return statement;
    }
    return createStatement();
  }

  /**
   * Creates a connection to the database, 
   * then creates the statement object from the connection
   * 
   * @return statement object
   */
  private static Statement createStatement() {
    try {
      //Set url to the poisepms database using jdbc:mysql: channel on localhost.
      //Create, then add to Properties: user "otheruser", password "octopus".
      //Create connection to database. 
      String url = "jdbc:mysql://localhost:3306/poisepms";
      Properties info = new Properties();
      info.put("user", "otheruser");
      info.put("password", "octopus"); 
      connection = DriverManager.getConnection(url, info);

      // Create a statement object for running queries
      // parameters added to allow for moving backwards in the resultset
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
    } catch (SQLException e) {
      System.out.println("Unable to connect to database");
      e.printStackTrace();
    }
    //return created statement
    return statement;
  }
}
