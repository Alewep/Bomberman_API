package servelet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Game;
import beans.Player;
import dao.DaoFactory;
import dao.PlayerDao;

/**
 * Servlet implementation class HistoryUser
 */
@WebServlet("/history")
public class HistoryUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayerDao utilisateurDao;
       
    
    public HistoryUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		 DaoFactory daoFactory = DaoFactory.getInstance();
	     this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("player") != null) {
				Player player = (Player) session.getAttribute("player");
				
				ArrayList<Game> games =utilisateurDao.allGames(player.getLogin());
				request.setAttribute("games", games);
				this.getServletContext().getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
				return;
			}
		}
		catch (Exception e) {
			session.invalidate();
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
		}
		
		response.sendRedirect(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
