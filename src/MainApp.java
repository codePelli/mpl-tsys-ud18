
public class MainApp {

	public static void main(String[] args) {

	    ConnectionSQL connection = new ConnectionSQL("jdbc:mysql://localhost:33060/mysql", "root", "password");
	    
	    ejercicio1(connection);
	    
	}

	private static void ejercicio1(ConnectionSQL connection) {
		
		connection.dropTable("Articulos");
		connection.dropTable("Fabricantes");
		
		connection.createTable("Fabricantes", "Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre NVARCHAR(100)");
        connection.createTable("Articulos", "Codigo INT PRIMARY KEY AUTO_INCREMENT, CodigoFabricante INT, Nombre NVARCHAR(100), Precio INT, Fabricante INT, FOREIGN KEY (CodigoFabricante) REFERENCES Fabricantes (Codigo) ON DELETE CASCADE ON UPDATE CASCADE");
        
        connection.insertData("Fabricantes", "Nombre", "'Nike'");
        connection.insertData("Fabricantes", "Nombre", "'Adidas'");
        
        connection.insertData("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","1, 'Pantalon', 79, 1");
        connection.insertData("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","2, 'Zapatillas', 59, 2");
        
        connection.selectData("Fabricantes");
        connection.selectData("Articulos");
	    
	    connection.disconnect();
	}

}
