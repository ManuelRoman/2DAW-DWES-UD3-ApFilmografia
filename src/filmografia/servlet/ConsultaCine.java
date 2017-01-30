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
public class ConsultaCine extends HttpServlet {

	/**
	 * Información de la Base de datos
	 */
	private DataSource dataSource;

	/**
	 * Objeto que encapsula la información a nivel de la aplicación
	 */
	private ServletContext sc;

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
		sc = config.getServletContext();
		String urlDataSource = (String) sc.getInitParameter("URLDataSource");
		InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dataSource = (DataSource) initCtx.lookup(urlDataSource);
			// Se almacena el DataSource en la aplicación.
			sc.setAttribute("dataSource", dataSource);
		} catch (NamingException ne) {
			System.out.println("Error: Método init(). " + ne.getMessage());
			sc.setInitParameter("appOperativa", "false");
		}
		sc.setInitParameter("appOperativa", "true");
	}

	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BeanError error = null;
		// Comprueba si la aplicación ha accedido al datasource
		if (sc.getInitParameter("appOperativa").equals("true")) {
			BeanDao beanDao = new BeanDao(dataSource);
			String accion = request.getParameter("accion");
			String director = request.getParameter("director");
			// Comprueba si se ha seleccionado finalizar
			if (accion != null) {
				if (accion.equals("finalizar")) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/consultasRealizadas.jsp");
					rd.forward(request, response);
				}
			} else {
				ListaPeliculas listaPeliculas = new ListaPeliculas();
				boolean existeDirector = false;
				try {
					beanDao.getConexion();
					existeDirector = beanDao.ifExistDirector(director);
				} catch (SQLException e) {
					error = new BeanError(e.getErrorCode(), "Error en la base de datos");
					e.printStackTrace();
				}
				// Comprueba si existe el director, si existe consulta las películas
				if (existeDirector) {
					try {
						beanDao.getConexion();
						listaPeliculas = beanDao.getPeliculas(director);
					} catch (SQLException e) {
						error = new BeanError(e.getErrorCode(), "Error en la base de datos");
						e.printStackTrace();
					} finally {
						try {
							beanDao.close();
						} catch (SQLException e) {
							error = new BeanError(e.getErrorCode(), "Error al cerrar la conexión con la base de datos");
							e.printStackTrace();
						}
					}
					request.setAttribute("listaPeliculas", listaPeliculas);
					request.setAttribute("director", director);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/resultado.jsp");
					rd.forward(request, response);
				} else if (existeDirector == false & error == null) {
					try {
						beanDao.close();
					} catch (SQLException e) {
						error = new BeanError(e.getErrorCode(), "Error al cerrar la conexión con la base de datos");
						e.printStackTrace();
					}
					System.out.println("No existe el director");
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/listaTodosDirectores.jsp");
					rd.forward(request, response);
				}
			}

		} else {
			if (error == null) {
				System.out.println("Aplicación no operativa");
				error = new BeanError(1, "Aplicación no operativa");
			}

		}
		if (error != null) {
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/errores.jsp");
			rd.forward(request, response);
		}
	}
}
