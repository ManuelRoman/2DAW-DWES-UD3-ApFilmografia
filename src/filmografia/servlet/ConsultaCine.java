package filmografia.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import filmografia.bbdd.*;
import filmografia.beans.*;

@SuppressWarnings("serial")
public class ConsultaCine extends HttpServlet{
	
	/**
	 * Informa si la aplicación se encuentra operativa
	 */
	private boolean appOperativa = true;
	
	/**
	 * Objeto encargado e la comunicación con la base de datos
	 */
	
	private BeanDao beanDao;
	
	/**
	 * Constructor por defecto
	 */
	public ConsultaCine() {
        super();
    }	
	
	/**
	 * Inicializa el servlet y la fuente de datos 
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	ServletContext application = config.getServletContext();
    	String urlDataSource = (String) application.getInitParameter("URLDataSource");
    	InitialContext initCtx = null;
    	DataSource dataSource=null;
    	try {
			initCtx = new InitialContext();
			dataSource = (DataSource) initCtx.lookup(urlDataSource);
		} catch (NamingException e) {
			appOperativa = false;
    	}
		beanDao = new BeanDao(dataSource);
	}

	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//Comprueba si la aplicación puede funcionar.
				if (!appOperativa){
					System.out.println("Aplicación no operativa");
				} else{
					String accion = request.getParameter("accion");
					String director = request.getParameter("director");
					//Comprueba si se ha seleccionado finalizar
					if (accion!=null){
						if (accion.equals("finalizar")){
							RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/consultasRealizadas.jsp");
						    rd.forward(request,response);
						}
					} else{
						ListaPeliculas listaPeliculas = null;
						boolean existeDirector = false;
						try {
						beanDao.getConexion();
						existeDirector = beanDao.ifExistDirector(director);
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Comprueba si existe el director, si existe consulta las películas
					if (existeDirector){
						try {
							beanDao.getConexion();
							listaPeliculas = beanDao.getPeliculas(director);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally{
							try {
								beanDao.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						request.setAttribute("listaPeliculas", listaPeliculas);
						request.setAttribute("director", director);
						RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/resultado.jsp");
					    rd.forward(request,response);
					}else{
						try {
							beanDao.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("No existe el director");
					}
				}
			}
	}
}
