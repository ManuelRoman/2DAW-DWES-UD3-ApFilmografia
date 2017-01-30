package filmografia.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsula el concepto de pelñícula 
 */
public class Pelicula {
	
	/**
	 * Información del director de la película.
	 */
	private String director;
	
	/**
	 * Información del título de la película.
	 */
	private String titulo;
	
	/**
	 * Información de la fecha de la película.
	 */
	private Date fecha;
	
	/**
	 * Constructor sin prámetros
	 */
	public Pelicula(){
		this.director = "";
		this.titulo = "";
		this.fecha = new Date();
	}
	
	/**
	 * Constructor con prámetros
	 * @param director
	 * @param titulo
	 * @param fecha
	 */
	public Pelicula(String director,String titulo, Date fecha){
		this.director=director;
		this.titulo=titulo;
		this.fecha=fecha;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    return dateFormat.format(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
