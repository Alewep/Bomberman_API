package beans;

import java.util.regex.Pattern;

public class Player implements Validation{
	
	String login;
	String password;
	

	
	public Player(String login, String password) {
		
		this.login = login;
		this.password = password;	
	}
	
	public void validate() throws BeanException {
		
		if (login == null || password==null)
			throw new BeanException("Informations cannot be empty");
		else if(!Pattern.compile("[A-Za-z0-9]{5,}").matcher(login).find())
			throw new BeanException("Login is invalid," 
					+ "it must be composed of at least 5 alphanumeric characters");
		else if (password.length() < 8 )
			throw new BeanException("The password must be at least 8 characters long");
		
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
	

}
