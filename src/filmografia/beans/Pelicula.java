package filmografia.beans;

import java.util.Date;

public class Pelicula {
	
	private String director;
	
	private String titulo;
	
	private Date fecha;
	
	public Pelicula(){
		this.director = "";
		this.titulo = "";
		this.fecha = new Date();
	}
	
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}