package dao;

import java.util.ArrayList;

import beans.Game;
import beans.Player;

public interface PlayerDao {
	
	public void toInscribe(Player player) throws DaoException;
	public boolean toLogin(Player player) throws DaoException;
	public ArrayList<Game> allGames(String login) throws DaoException;
	public void addGame(Game game,String[] logins ) throws DaoException;
	public int nbWinGame(Player player) throws DaoException;
	public int nbGame(Player player) throws DaoException;
	
}
