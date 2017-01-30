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
	
	/**
	 * Proceso que crea la conexión con la base de datos.
	 * @return la conexión
	 * @throws SQLException
	 */
	public Connection getConexion() throws SQLException {
		if (conexion == null){
		this.conexion = dataSource.getConnection();
		}
		return conexion;
	}
	
	/**
     * Proceso que cierra la conexión con la base de datos.
     * @throws SQLException
     */
	public void close() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
		conexion = null;
	}
	
	/**
	 * Proceso que comprueba si existe un director, en la base de datos
	 * @param director
	 * @return devuelve true si existe 
	 * @throws SQLException
	 */
	public Boolean ifExistDirector(String director) throws SQLException {
		Boolean exist = false;
		boolean conexionNula = false;
		if (conexion == null){
			getConexion();
			conexionNula = true;
		}
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select director from peliculas where director = '" + director + "'");
		// Se comprueba si existe el director
		if (rs.next()) {
			exist = true;
		}
		if (st != null) {
			st = null;
			if (conexionNula){
				close();
			}
		}
		return exist;
	}
	
	/**
	 * Proceso que obtiene todas las películas de un director
	 * @param director
	 * @return ListaPeliculas
	 * @throws SQLException
	 */
	public ListaPeliculas getPeliculas(String director) throws SQLException {
        ListaPeliculas listaPeliculas = new ListaPeliculas();
        boolean conexionNula = false;
		if (conexion == null){
			getConexion();
			conexionNula = true;
		}
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM peliculas where director = '" + director + "'");
        while (rs.next()) {
            Pelicula pelicula = new Pelicula(rs.getString("director"), rs.getString("titulo"), rs.getDate("fecha"));
            listaPeliculas.add(pelicula);
        }
        if (st != null) {
			st = null;
			if (conexionNula){
				close();
			}
		}
		return listaPeliculas;
	}
	
	/**
	 * Proceso que obtiene todos los directores que existen en la base de datos
	 * @return ListaDirectores
	 * @throws SQLException
	 */
	public ListaDirectores getDirectores() throws SQLException {
		ListaDirectores listaTodosDirectores = new ListaDirectores();
		boolean conexionNula = false;
		if (conexion == null){
			getConexion();
			conexionNula = true;
		}
		Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT DISTINCT director FROM peliculas");
        while (rs.next()) {
            listaTodosDirectores.add(rs.getString("director"));
        }
        if (st != null) {
			st = null;
			if (conexionNula){
				close();
			}
		}
		return listaTodosDirectores;
	}

}
