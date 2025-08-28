package Lesson3.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnection 
{
    private final String serverName = "LEVUHAI"; 
    private final String dbName = "WebPrograming";        
    private final String portNumber = "1433";     
    private final String instance = "";            
    private final String userID = "sa";           
    private final String password = "123";  


    public Connection getConnection() throws Exception {
    	String url = "jdbc:sqlserver://" + serverName + ":" + portNumber +
    	";databaseName=" + dbName;
    	if (instance == null || instance.trim().isEmpty())
    		url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	return DriverManager.getConnection(url, userID, password);
    	}
    

}
