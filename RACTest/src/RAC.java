import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;


public class RAC {

	/**
	 * @param args
	 */
	static Connection connection = null;	
		
	static Statement statement = null;
	
	static int updateQuery = 0;
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		String dburl = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.72.241)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.72.252)(PORT = 1521)))(FAILOVER=on)(LOAD_BALANCE=yes)(CONNECT_DATA =(SERVER=DEDICATED)(SERVICE_NAME = BW)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))";
//		
//		//String dburl = "jdbc:oracle:thin:@192.168.69.157:1521:orcl";
//
//		String username = "be_shawn";
//		String password = "tibco";
//
//		String query = "select * from AMX_RAC_CHARLEY.MODELNAME";
//		
//		
//		
//				
//		while ( !getConnection(dburl, username, password)) {
//			
//			System.out.println("Get DB Connection failure, please try again");
//			
//		}
//		
//		System.out.println("Get DB connection successfully");
//		int i = 1;
//		while (i <= 100){
//			executeQuery(query, i);
//			Thread.sleep(5000);		
//			i++;
//		}
//		
//		System.out.println("Finish testing");
//		statement.close();
//		connection.close();
//		
//		
//	}

    public static boolean getConnection (String url, String username, String password) {
    	
    	try {
    		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
	    	DatabaseMetaData meta = connection.getMetaData();
	    	System.out.println("JDBC driver version is " + meta.getDriverVersion());
	    	System.out.println(connection.toString());
	    	return true;
	    	
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			//e.printStackTrace();		
			return false;
			
		}
    	
    }

	public static void executeQuery(String query, int i) {
		
		try {
			
			statement = connection.createStatement();
			
			updateQuery = statement.executeUpdate(query);
			System.out.println("Update number : " + i);
									
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				
			System.out.println("Update Query failure");
		} 
	}
    
    
}
