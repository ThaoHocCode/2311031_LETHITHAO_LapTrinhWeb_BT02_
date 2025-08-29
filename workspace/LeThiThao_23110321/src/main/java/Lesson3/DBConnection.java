package Lesson3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {
    // Thông tin cấu hình kết nối
    private final String serverName = "THAO-PC\\SQLEXPRESS"; 
    private final String dbName = "LapTrinhWeb";        
    private final String portNumber = "1433";     
    private final String instance = "";            
    private final String userID = "user";           
    private final String password = "1234";  


    public Connection getConnection() throws Exception {
    	String url = "jdbc:sqlserver://" + serverName + ":" + portNumber +
    	";databaseName=" + dbName;
    	if (instance == null || instance.trim().isEmpty())
    		url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	return DriverManager.getConnection(url, userID, password);
    	}
    
    public static void main(String[] args) {
    	
    	try {
    		
    		Connection conn = new DBConnection().getConnection();
    		
    		Statement stmt = conn.createStatement();
    		
    		stmt.executeUpdate("INSERT INTO Lesson3(username,password) "
    		+ "VALUES ('user1','123')");
    		// get data from table ‘GiaoVien'
    		ResultSet rs = stmt.executeQuery("SELECT * FROM Lesson3");
    		// show data
    		while (rs.next()) {
    		    System.out.println(rs.getString("username") + " " + rs.getString("password"));
    		}

    		conn.close(); // close connection
    		} catch (Exception ex) {
    		ex.printStackTrace();
    		}
    }

}


