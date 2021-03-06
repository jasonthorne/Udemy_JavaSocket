
import java.sql.*;


/**
 * 
 * NOTE that the mysql-connector-java.jar MUST be included in here and added to project build path. (properties > build path)
 *
 */

public class JdbcTest {
	
	public static void main(String[] args) throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//get a connection to the DB:
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/remote_test_db", "student", "student"); //(db url, user, password)
			connection = DriverManager.getConnection("jdbc:mysql://johnny.heliohost.org:3306/remote_test_db", "student", "student"); //(db url, user, password)
			
			System.out.println("DB connection sucessfull!\n");
			
			//Create statement:
			statement = connection.createStatement();
	
			//Execute query:
			resultSet = statement.executeQuery("select * from users");
			
			resultSet = statement.executeQuery("select host from information_schema.processlist WHERE ID=connection_id()");
			
			//Process the result set:
			while(resultSet.next()) {
				/*System.out.println(resultSet.getString("name")
						+ " |  " + resultSet.getInt("ip")
						+ " |  " + resultSet.getInt("port"));*/
				System.out.println(resultSet.getString(1));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
