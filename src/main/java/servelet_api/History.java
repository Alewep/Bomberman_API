package servelet_api;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.Game;
import dao.DaoFactory;
import dao.PlayerDao;

/**
 * Servlet implementation class History
 */
@WebServlet("/api/history")
public class History extends HttpServlet {
	private PlayerDao utilisateurDao;
    
    public History() {
        super();
    }
    
    public void init() throws ServletException {
		 DaoFactory daoFactory = DaoFactory.getInstance();
	     this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String users = request.getParameter("users");
		String winer = request.getParameter("winer");
		String name_map = request.getParameter("name_map");
		String starttime = request.getParameter("start_time");
		String endtime = request.getParameter("end_time");
		
		JSONObject json = new JSONObject();
		
		try {
			
			Timestamp stattimeTimestamp = new Timestamp(Integer.parseInt(starttime));
			Timestamp endtimeTimestamp = new Timestamp(Integer.parseInt(endtime));
			Game game = new Game(winer,name_map,stattimeTimestamp , endtimeTimestamp);
			game.validate();
			utilisateurDao.addGame(game, users.split(";"));
			json.put("success", true);
			
		} 
		catch (Exception e) {
			json.put("success", false);
			json.put("error", e.toString());
		}
		response.setContentType("json;charset=UTF-8");
		response.getWriter().write(json.toString());
		
		
	}


}
