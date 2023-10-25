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
            
            System.out.println("Conectado a la BBDD");
            
        } catch (SQLException | ClassNotFoundException ex) {
        	
            System.out.println("ERROR al conectar con la BBDD");
            System.out.println(ex);
        }
    }

    public void disconnect() {
    	
        try {
        	
            connection.close();
            System.out.println("Te desconectaste de la BBDD");
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR al desconectar");
        }
    }

    public void createTable(String tableName, String tableSentence) {
    	
        try {
        	
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableSentence + ")";       
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Tabla " + tableName + " creada");
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR al crear la tabla " + tableName);
            System.out.println(ex.getMessage());
        }
    }
		
    public void insertData(String tableName, String columns, String values) {
    	
        try {
        	
            String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Datos añadidos a la tabla " + tableName);
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR al insertar datos a la tabla " + tableName);
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
        	
            System.out.println("ERROR al imprimir los datos de la tabla " + tableName);
            System.out.println(ex.getMessage());
        }
    }

    public void deleteData(String tableName, String condition) {
    	
        try {
        	
            String query = "DELETE FROM " + tableName + " WHERE " + condition;
            Statement st = connection.createStatement();
            int filas = st.executeUpdate(query);

            if (filas > 0) {
            	
                System.out.println("Datos de la tabla " + tableName + "eliminados");
                
            } else {
            	
                System.out.println("No hay datos en la tabla " + tableName + " para borrar");          
            }
            
        } catch (SQLException ex) {
        	
            System.out.println("ERROR al borrar datos de la tabla " + tableName);
            System.out.println(ex.getMessage());
        }
    }
    
    public void dropTable(String tableName) {
    	
    	try {
    		
            String query = "DROP TABLE IF EXISTS " + tableName;
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Tabla " + tableName + " borrada");

    	} catch (SQLException ex) {
    		
            System.out.println("ERROR al borrar la tabla " + tableName);
            System.out.println(ex.getMessage());
    	}

    }
}