import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;


public class OCI_RAC {

	/**
	 * @param args
	 */
	static Connection connection = null;		
	static Statement statement = null;	
	static int updateQuery = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String dburl = "jdbc:oracle:oci:@RAC";
		String username = "be_shawn";
		String password = "tibco";
		// SQL query for testing
		String query = "select * from AMX_RAC_CHARLEY.MODELNAME";
		
		
				
		while ( !getConnection(dburl, username, password)) {
			
			System.out.println("Get DB Connection failure, please try again");
			
		}
		
		System.out.println("Get DB connection successfully");
		int i = 1;
		while (i <= 100){
			executeQuery(query, i);
			Thread.sleep(5000);		
			i++;
		}
		
		System.out.println("Finish testing");
		statement.close();
		connection.close();
	
		
	}

    public static boolean getConnection (String url, String username, String password) {
    	
    	try {
    		
    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection=DriverManager.getConnection(url, username, password);			
	    	DatabaseMetaData meta = connection.getMetaData();
	    	System.out.println("JDBC driver version is " + meta.getDriverVersion());
	    	return true;
	    	
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			//e.printStackTrace();		
			return false;
			
		}
    	
    }

	public static void executeQuery(String query, int i) {
		java.util.Date date= new java.util.Date();
		try {
			
			statement = connection.createStatement();			
			updateQuery = statement.executeUpdate(query);
			

			System.out.println(new Timestamp(date.getTime()) + " Update number : " + i);								
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();				
			System.out.println(new Timestamp(date.getTime()) + " Update Query failure");
		} 
	}
    
    
}
