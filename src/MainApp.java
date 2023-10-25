
public class MainApp {

	public static void main(String[] args) {
		
		//Conexion a bbdd llamada "java"
	    ConnectionSQL connection = new ConnectionSQL("jdbc:mysql://localhost:33060/java", "root", "password"); 
	    
	    ejercicio1(connection);
	    ejercicio2(connection);
	    ejercicio3(connection);
	    ejercicio4(connection);
	    ejercicio5(connection);
	    
        connection.disconnect();
	}

	private static void ejercicio1(ConnectionSQL connection) {
		
		connection.dropTable("Articulos");
		connection.dropTable("Fabricantes");
		
		connection.createTable("Fabricantes", "Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre NVARCHAR(100)");
        connection.createTable("Articulos", "Codigo INT PRIMARY KEY AUTO_INCREMENT, CodigoFabricante INT, Nombre NVARCHAR(100), "
        		+ "Precio INT, Fabricante INT, FOREIGN KEY (CodigoFabricante) REFERENCES Fabricantes (Codigo) ON DELETE CASCADE ON UPDATE CASCADE");
        
        connection.insertData("Fabricantes", "Nombre", "'Nike'");
        connection.insertData("Fabricantes", "Nombre", "'Adidas'");
        
        connection.insertData("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","1, 'Pantalon', 79, 1");
        connection.insertData("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","2, 'Zapatillas', 59, 2");
        
        connection.selectData("Fabricantes");
        connection.selectData("Articulos");
	    
	}
	
	private static void ejercicio2(ConnectionSQL connection) {
		
		connection.dropTable("Empleados");
		connection.dropTable("Departamentos");
		
        connection.createTable("Departamentos", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), presupuesto INT");
        connection.createTable("Empleados", "DNI VARCHAR(8) PRIMARY KEY, nombre NVARCHAR(100), apellidos NVARCHAR(255), departamento INT");

        connection.insertData("Departamentos", "codigo, nombre, presupuesto", "1, 'IT', 100000");
        connection.insertData("Departamentos", "codigo, nombre, presupuesto", "2, 'RRHH', 80000");

        connection.insertData("Empleados", "DNI, nombre, apellidos, departamento", "'1231231X', 'Marc', 'Pellicer', 1");
        connection.insertData("Empleados", "DNI, nombre, apellidos, departamento", "'3213212R', 'Xavi', 'Pellicer', 2");

        connection.selectData("Departamentos");
        connection.selectData("Empleados");
		
	}
	
	private static void ejercicio3(ConnectionSQL connection) {
		
		connection.dropTable("Cajas");
		connection.dropTable("Almacenes");
		
		connection.createTable("Almacenes", "codigo INT PRIMARY KEY, lugar NVARCHAR(100), capacidad INT");
		connection.createTable("Cajas", "num_referencia CHAR(5) PRIMARY KEY, contenido NVARCHAR(100), valor INT, "
				+ "almacen INT, FOREIGN KEY (almacen) REFERENCES Almacenes (codigo) ON DELETE CASCADE ON UPDATE CASCADE");

		connection.insertData("Almacenes", "codigo, lugar, capacidad", "1, 'Almacen Tarragona', 500");
		connection.insertData("Almacenes", "codigo, lugar, capacidad", "2, 'Almacen Reus', 1500");

		connection.insertData("Cajas", "num_referencia, contenido, valor, almacen", "'C034X', 'Discos', 199, 1");
		connection.insertData("Cajas", "num_referencia, contenido, valor, almacen", "'C045X', 'Papeles', 95, 2");

		connection.selectData("Almacenes");
		connection.selectData("Cajas");

	}
	
	private static void ejercicio4(ConnectionSQL connection) {
		
        connection.dropTable("Salas");
        connection.dropTable("Peliculas");

        connection.createTable("Peliculas", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), calificacion_edad INT");
        connection.createTable("Salas", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), "
        		+ "pelicula INT, FOREIGN KEY (pelicula) REFERENCES Peliculas (codigo) ON DELETE CASCADE ON UPDATE CASCADE");

        connection.insertData("Peliculas", "codigo, nombre, calificacion_edad", "1, 'El caballero oscuro', 16");
        connection.insertData("Peliculas", "codigo, nombre, calificacion_edad", "2, 'Tenet', 18");

        connection.insertData("Salas", "codigo, nombre, pelicula", "1, 'Sala 1', 1");
        connection.insertData("Salas", "codigo, nombre, pelicula", "2, 'Sala 2', 2");

        connection.selectData("Peliculas");
        connection.selectData("Salas");
		
	}
	
	private static void ejercicio5(ConnectionSQL connection) {
		
		connection.dropTable("Directores");
        connection.dropTable("Despachos");

        connection.createTable("Despachos", "Numero INT PRIMARY KEY, Capacidad INT");
        connection.createTable("Directores", "DNI VARCHAR(8) PRIMARY KEY NOT NULL, NomApels VARCHAR(255), DNI_Jefe VARCHAR(8), Num_Despacho INT, "
        		+ "FOREIGN KEY (DNI_Jefe) REFERENCES Directores (DNI) ON DELETE CASCADE ON UPDATE CASCADE, "
        		+ "FOREIGN KEY (Num_Despacho) REFERENCES Despachos (Numero) ON DELETE CASCADE ON UPDATE CASCADE");

        connection.insertData("Despachos", "Numero, Capacidad", "1, 5");
        connection.insertData("Despachos", "Numero, Capacidad", "2, 3");

        connection.insertData("Directores", "DNI, NomApels, DNI_Jefe, Num_Despacho", "'1231231R', 'Marc', NULL, 1");
        connection.insertData("Directores", "DNI, NomApels, DNI_Jefe, Num_Despacho", "'3213212E', 'Xavi', '1231231R', 2");

        connection.selectData("Directores");
        connection.selectData("Despachos");
        
	}

}