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
					//error = new BeanError(0,"La aplicación no se encuentra operativa en este momento, intentelo más tarde.");
					//request.setAttribute("error", error);
					//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
				    //rd.forward(request,response);
					System.out.println("Datasource incorrecto");
				} else{
					System.out.println("Datasource correcto");
					String director = request.getParameter("director");
					ListaPeliculas listaPeliculas = null;
					boolean existeDirector = false;
					System.out.println(director);
					try {
						beanDao.getConexion();
						existeDirector = beanDao.ifExistDirector(director);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(existeDirector);
					if (existeDirector){
						try {
							beanDao.getConexion();
							listaPeliculas = beanDao.getPeliculas(director);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("listaPeliculas", listaPeliculas);
						RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/resultado.jsp");
					    rd.forward(request,response);
					}else{
						System.out.println("No existe el director");
					}
				}
	}
}
