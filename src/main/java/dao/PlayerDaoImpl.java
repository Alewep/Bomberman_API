package dao; 

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import beans.BeanException;
import beans.Game;
import beans.Player;

public class PlayerDaoImpl implements PlayerDao {
	
	private DaoFactory daoFactory;

	public PlayerDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	
	public void toInscribe(Player player) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		try  {
			
		    connexion = daoFactory.getConnection();
		    preparedStatement = connexion.prepareStatement("INSERT INTO player VALUES (?, ?);");
		    preparedStatement.setString(1, player.getLogin());
		    preparedStatement.setString(2, player.getPassword());
		
		    preparedStatement.executeUpdate();
		    connexion.commit();
		    
		    return;
		    
		} catch (SQLException e) {
		    try {
		        if (connexion != null) {
		            connexion.rollback();
		        }
		    } catch (SQLException e2) {
		    	
		    }
		    if (e.getErrorCode() == 19 ) // primary_key error
		    	throw new DaoException("A player with the same login already exists");
		    else throw new DaoException("Database connection problem:");
		    
		}
		finally {
		    try {
		        if (connexion != null) {
		            connexion.close();  
		        }
		    } catch (SQLException e) {
		        throw new DaoException("Database connection problem");
		            }
		        }
		
		}
	
	
	public boolean toLogin(Player player) throws DaoException {
		PreparedStatement preparedStatement = null;
		try ( Connection connexion = daoFactory.getConnection()){
		   
		    preparedStatement = connexion.prepareStatement("SELECT * FROM player where (login_player=? and password_player=?)");
		    preparedStatement.setString(1, player.getLogin());
		    preparedStatement.setString(2, player.getPassword());
		    ResultSet resultSet = preparedStatement.executeQuery();
		    if (resultSet.next()) {
				return true;
		    }
		     
		    return false;
		} catch (SQLException e) {
			throw new DaoException("Database connection problem :");
		}
		
		
		
	}


	@Override
	public ArrayList<Game> allGames(String login) throws DaoException {
	
		PreparedStatement preparedStatement = null;
		ArrayList<Game> result = new ArrayList<Game>() ;
	
		try(Connection connexion = daoFactory.getConnection())  {
			
		    
		    preparedStatement = connexion.prepareStatement(""
		    		+ "SELECT * "
		    		+ "FROM history H CROSS JOIN game G ON (H.game_id = G.game_id) "
		    		+ "WHERE login_player = ?");
		    
		    preparedStatement.setString(1, login);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    while (resultSet.next()) {
		    	String winer = resultSet.getString("winer");
		    	String map_name = resultSet.getString("map_name");
		    	Timestamp start = resultSet.getTimestamp("time_start");
		    	Timestamp end = resultSet.getTimestamp("time_end");
		    	
		    	Game entry = new Game(winer,map_name,start,end);
		    	result.add(entry);	
		    }
		    return result;
		} 
		catch (SQLException e) {
				throw new DaoException("Database connection problem :");
		}
		
		
	}


	@Override
	public void addGame(Game game,String[] logins) throws DaoException {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
	
		try  {
			connexion = daoFactory.getConnection();
		    
			preparedStatement = connexion.prepareStatement("INSERT INTO GAME VALUES (?,?,?,?,?);");
			
			preparedStatement.setNull(1,Types.INTEGER);
		    preparedStatement.setString(2,game.getWiner());
		    preparedStatement.setString(3,game.getNameMap());
		    preparedStatement.setTimestamp(4, game.getStartTime());
		    preparedStatement.setTimestamp(5, game.getEndTime());
		
		  
		    preparedStatement.executeUpdate();
		  
		    // recupération de l'id de la game
		    preparedStatement = connexion.prepareStatement("SELECT last_insert_rowid() as 'id';");
		    ResultSet resultSet = preparedStatement.executeQuery();
		    int idGame = 0;
		    if(resultSet.next()) {
		    	 idGame = resultSet.getInt("id");
		    } 
		    else throw new DaoException("Cannot add entry in history");
		    
		    preparedStatement = connexion.prepareStatement("INSERT INTO history VALUES (?,?);");
		    preparedStatement.setInt(2, idGame);
		    for (int i =0;i<logins.length;++i) {
		    	preparedStatement.setString(1, logins[i]);
		    	preparedStatement.executeUpdate();

		    }
			connexion.commit();
		    
		    return;
		} catch (SQLException e) {
		    try {
		        if (connexion != null) {
		            connexion.rollback();
		        }
		    } catch (SQLException e2) {
		    	
		    }
		    throw new DaoException("Database connection problem ");
		}
		finally {
		    try {
		        if (connexion != null) {
		            connexion.close();  
		        }
		    } 
		    catch (SQLException e) {
		        throw new DaoException("Database connection problem ");
		    }
		}
		
		
		
	}


	@Override
	public int nbWinGame(Player player) throws DaoException {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection connexion = daoFactory.getConnection()) {
				    
			preparedStatement = connexion.prepareStatement("Select count(*) as count from GAME where winer = ?");
			preparedStatement.setString(1,player.getLogin());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
		    	 int count = resultSet.getInt("count");
		    	 return count;
		    } else throw new DaoException("Database connection problem");
			
			
		} catch (SQLException e) {
		  
		    throw new DaoException("Database connection problem");
		}
		
	}
	
	public int nbGame (Player player)  throws DaoException {
		
		PreparedStatement preparedStatement = null;
		
		try(Connection connexion = daoFactory.getConnection() ) {
			
			preparedStatement = connexion.prepareStatement("Select count(*) as count from HISTORY where login_player=?");
			preparedStatement.setString(1,player.getLogin());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
		    	 int count = resultSet.getInt("count");
		    	 return count;
		    } else throw new DaoException("Database connection problem");
			
			
		} catch (SQLException e) {
		    throw new DaoException("Database connection problem");
		}
		
		
	}


	



	
 
    

}
