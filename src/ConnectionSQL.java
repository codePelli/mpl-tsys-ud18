import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.*;

public class ConnectionSQL {
	
    private Connection connection;

    public ConnectionSQL(String ip, String user, String pass) {
    	
        connect(ip, user, pass);
        
    }

    private void connect(String ip, String user, String pass) {
    	
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(ip, user, pass);
            
            System.out.println("Connected to the server DB");
            
        } catch (SQLException | ClassNotFoundException ex) {
        	
            System.out.println("ERROR connecting to DB");
            System.out.println(ex);
        }
    }

    public void disconnect() {
    	
        try {
        	
            connection.close();
            System.out.println("Connection with the server has been CLOSED succesfully");
            
        } catch (SQLException ex) {
        	
            System.out.println("FAILED to disconnect");
        }
    }

    public void createTable(String tableName, String tableSentence) {
    	
        try {
        	
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableSentence + ")";       
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Table " + tableName + " created successfully.");
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR creating table " + tableName);
            System.out.println(ex.getMessage());
        }
    }
		
    public void insertData(String tableName, String columns, String values) {
    	
        try {
        	
            String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Data inserted successfully into " + tableName);
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR inserting data into " + tableName);
            System.out.println(ex.getMessage());
        }
    }

    public void selectData(String tableName) {
    	
        try {
        	
            String query = "SELECT * FROM " + tableName;
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()) {
            	
            	int codigo = resultSet.getInt("Codigo");
                String nombre = resultSet.getString("Nombre");
                
                System.out.println("Codigo: " + codigo + ", Nombre: " + nombre);
            }
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR collecting data from " + tableName);
            System.out.println(ex.getMessage());
        }
    }

    public void deleteData(String tableName, String condition) {
    	
        try {
        	
            String query = "DELETE FROM " + tableName + " WHERE " + condition;
            Statement st = connection.createStatement();
            int rowsDltd = st.executeUpdate(query);

            if (rowsDltd > 0) {
            	
                System.out.println("Data from " + tableName + "deleted");
                
            } else {
            	
                System.out.println("No data deleted from " + tableName + " (Condition: " + condition + ")");          
            }
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR deleting data from " + tableName);
            System.out.println(ex.getMessage());
        }
    }
    
    public void dropTable(String tableName) {
    	
    	try {
    		
            String query = "DROP TABLE IF EXISTS " + tableName;
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Table " + tableName + " dropped successfully.");

    	} catch (SQLException ex) {
    		
            System.out.println("ERROR dropping table " + tableName);
            System.out.println(ex.getMessage());
    	}

    }
}