package dao;

import java.sql.*;

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

        DaoFactory instance = new DaoFactory("jdbc:sqlite:/home/etud/Bomberman/Bomberman_web/bdd/database.db",null,null);
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	 Connection connexion = DriverManager.getConnection(url, username, password);
         connexion.setAutoCommit(false);
         return connexion;
    }

    public PlayerDao getUtilisateurDao() {
        return new PlayerDaoImpl(this);
    }
}
