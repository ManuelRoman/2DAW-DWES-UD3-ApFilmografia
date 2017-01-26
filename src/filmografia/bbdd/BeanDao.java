package filmografia.bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import filmografia.beans.*;

public class BeanDao {
	
	/**
	 * Información de la base de datos
	 */
	private DataSource dataSource;

	/**
	 * Representa la conexión con la base de datos
	 */
	private Connection conexion = null;

	/**
	 * Constructor que recibe el DataSource
	 * @param dsBdValidaLogin
	 */
	public BeanDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConexion() throws SQLException {
		if (conexion == null){
		this.conexion = dataSource.getConnection();
		}
		return conexion;
	}
	
	public void close() throws SQLException {
		
	}
	
	public Boolean ifExistDirector(String director) throws SQLException {
		Boolean exist = false;
		getConexion();
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select director from peliculas where director = '" + director + "'");
		// Se comprueba si existe el director
		if (rs.next()) {
			exist = true;
		} 
		return exist;
	}
	
	public ListaPeliculas getPeliculas(String director) throws SQLException {
        ListaPeliculas listaPeliculas = new ListaPeliculas();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM peliculas where director = '" + director + "'");
        while (rs.next()) {
            Pelicula pelicula = new Pelicula(rs.getString("director"), rs.getString("titulo"), rs.getDate("fecha"));
            listaPeliculas.add(pelicula);
        }
		return listaPeliculas;
	}

}
