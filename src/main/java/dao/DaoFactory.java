package dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DaoFactory {
	 private String url;
	 private String username;
	 private String password;

	 DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {

        }
        String connectionURL;
		try {
			InitialContext initialContext = new InitialContext();
			Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
	        connectionURL = (String) environmentContext.lookup("databasePath");
	       
		} catch (NamingException e) {
			connectionURL = "";
		}
		DaoFactory instance = new DaoFactory("jdbc:sqlite:"+connectionURL,null,null);
        return instance;
    }

    public Connection getConnection() throws SQLException  {
    	 Connection connexion = DriverManager.getConnection(url, username, password);
         connexion.setAutoCommit(false);
         return connexion;
    }

    public PlayerDao getUtilisateurDao() {
        return new PlayerDaoImpl(this);
    }
}

