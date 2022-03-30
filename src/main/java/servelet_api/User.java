package servelet_api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.Player;
import dao.DaoFactory;
import dao.PlayerDao;


@WebServlet("/api/user")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayerDao utilisateurDao;
       
    
    public User() {
        super();
        
    }

	public void init() throws ServletException {
		 DaoFactory daoFactory = DaoFactory.getInstance();
	     this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean login_sucess = false;
		JSONObject json = new JSONObject();
		
		try {
			boolean success = utilisateurDao.toLogin(new Player(login,password));
			json.put("login", success);
			
		} catch (Exception e) {
			json.put("login", false);
			json.put("error", e.getMessage());
		}
		response.setContentType("json;charset=UTF-8");
		response.getWriter().write(json.toString());
	
	}


}
