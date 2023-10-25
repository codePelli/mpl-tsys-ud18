
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
		
		connection.drop("Articulos");
		connection.drop("Fabricantes");
		
		connection.createTable("Fabricantes", "Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre NVARCHAR(100)");
        connection.createTable("Articulos", "Codigo INT PRIMARY KEY AUTO_INCREMENT, CodigoFabricante INT, Nombre NVARCHAR(100), "
        		+ "Precio INT, Fabricante INT, FOREIGN KEY (CodigoFabricante) REFERENCES Fabricantes (Codigo) ON DELETE CASCADE ON UPDATE CASCADE");
        
        connection.insert("Fabricantes", "Nombre", "'Nike'");
        connection.insert("Fabricantes", "Nombre", "'Adidas'");
        
        connection.insert("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","1, 'Pantalon', 79, 1");
        connection.insert("Articulos", "CodigoFabricante, Nombre, Precio, Fabricante","2, 'Zapatillas', 59, 2");
        
        connection.select("Fabricantes");
        connection.select("Articulos");
	    
	}
	
	private static void ejercicio2(ConnectionSQL connection) {
		
		connection.drop("Empleados");
		connection.drop("Departamentos");
		
        connection.createTable("Departamentos", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), presupuesto INT");
        connection.createTable("Empleados", "DNI VARCHAR(8) PRIMARY KEY, nombre NVARCHAR(100), apellidos NVARCHAR(255), departamento INT");

        connection.insert("Departamentos", "codigo, nombre, presupuesto", "1, 'IT', 100000");
        connection.insert("Departamentos", "codigo, nombre, presupuesto", "2, 'RRHH', 80000");

        connection.insert("Empleados", "DNI, nombre, apellidos, departamento", "'1231231X', 'Marc', 'Pellicer', 1");
        connection.insert("Empleados", "DNI, nombre, apellidos, departamento", "'3213212R', 'Xavi', 'Pellicer', 2");

        connection.select("Departamentos");
        connection.select("Empleados");
		
	}
	
	private static void ejercicio3(ConnectionSQL connection) {
		
		connection.drop("Cajas");
		connection.drop("Almacenes");
		
		connection.createTable("Almacenes", "codigo INT PRIMARY KEY, lugar NVARCHAR(100), capacidad INT");
		connection.createTable("Cajas", "num_referencia CHAR(5) PRIMARY KEY, contenido NVARCHAR(100), valor INT, "
				+ "almacen INT, FOREIGN KEY (almacen) REFERENCES Almacenes (codigo) ON DELETE CASCADE ON UPDATE CASCADE");

		connection.insert("Almacenes", "codigo, lugar, capacidad", "1, 'Almacen Tarragona', 500");
		connection.insert("Almacenes", "codigo, lugar, capacidad", "2, 'Almacen Reus', 1500");

		connection.insert("Cajas", "num_referencia, contenido, valor, almacen", "'C034X', 'Discos', 199, 1");
		connection.insert("Cajas", "num_referencia, contenido, valor, almacen", "'C045X', 'Papeles', 95, 2");

		connection.select("Almacenes");
		connection.select("Cajas");

	}
	
	private static void ejercicio4(ConnectionSQL connection) {
		
        connection.drop("Salas");
        connection.drop("Peliculas");

        connection.createTable("Peliculas", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), calificacion_edad INT");
        connection.createTable("Salas", "codigo INT PRIMARY KEY, nombre NVARCHAR(100), "
        		+ "pelicula INT, FOREIGN KEY (pelicula) REFERENCES Peliculas (codigo) ON DELETE CASCADE ON UPDATE CASCADE");

        connection.insert("Peliculas", "codigo, nombre, calificacion_edad", "1, 'El caballero oscuro', 16");
        connection.insert("Peliculas", "codigo, nombre, calificacion_edad", "2, 'Tenet', 18");

        connection.insert("Salas", "codigo, nombre, pelicula", "1, 'Sala 1', 1");
        connection.insert("Salas", "codigo, nombre, pelicula", "2, 'Sala 2', 2");

        connection.select("Peliculas");
        connection.select("Salas");
		
	}
	
	private static void ejercicio5(ConnectionSQL connection) {
		
		connection.drop("Directores");
        connection.drop("Despachos");

        connection.createTable("Despachos", "Numero INT PRIMARY KEY, Capacidad INT");
        connection.createTable("Directores", "DNI VARCHAR(8) PRIMARY KEY NOT NULL, NomApels VARCHAR(255), DNI_Jefe VARCHAR(8), Num_Despacho INT, "
        		+ "FOREIGN KEY (DNI_Jefe) REFERENCES Directores (DNI) ON DELETE CASCADE ON UPDATE CASCADE, "
        		+ "FOREIGN KEY (Num_Despacho) REFERENCES Despachos (Numero) ON DELETE CASCADE ON UPDATE CASCADE");

        connection.insert("Despachos", "Numero, Capacidad", "1, 5");
        connection.insert("Despachos", "Numero, Capacidad", "2, 3");

        connection.insert("Directores", "DNI, NomApels, DNI_Jefe, Num_Despacho", "'1231231R', 'Marc', NULL, 1");
        connection.insert("Directores", "DNI, NomApels, DNI_Jefe, Num_Despacho", "'3213212E', 'Xavi', '1231231R', 2");

        connection.select("Directores");
        connection.select("Despachos");
        
	}

}