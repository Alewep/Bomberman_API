package servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Player;
import dao.DaoFactory;
import dao.PlayerDao;



@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static PlayerDao utilisateurDao;

	public void init() throws ServletException {
		 DaoFactory daoFactory = DaoFactory.getInstance();
	     this.utilisateurDao = daoFactory.getUtilisateurDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("player") != null) {
				// if connexion works ( can be after register or login)
				Player player = (Player) session.getAttribute("player");
				request.setAttribute("nbPlayed",utilisateurDao.nbGame(player));
				request.setAttribute("nbWined",utilisateurDao.nbWinGame(player));
				this.getServletContext().getRequestDispatcher("/WEB-INF/home_player.jsp").forward(request, response);
				return;
				
			}
		}
		catch (Exception e) {
			session.invalidate();
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String send = request.getParameter("send");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			
			Player player = new Player(login,password);
			if (send.equals("to register")) {
				player.validate();
				utilisateurDao.toInscribe(player);
				session.setAttribute("player", player);
				
			}
			else if (send.equals("to login")) {
				
				boolean succes = utilisateurDao.toLogin(player);
				if (succes) {
					session.setAttribute("player", player);
				}
				else throw new Exception("Registration - bad login or password");
			}
			
		}
		catch (Exception e){
			
			session.invalidate();
			request.setAttribute("error", e.getMessage());

		}
		
		doGet(request,response);
		
		
		
	
		
		

	}
	
}
