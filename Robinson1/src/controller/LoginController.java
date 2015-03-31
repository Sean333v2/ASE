package controller;

import java.util.ArrayList;

import model.User;

public class LoginController {
	
	private static int userCount = 5;
	private static User[] user = new User[userCount];
	public static String currentLogin;
	
	
	
	
	static void setLogins(){
		 user[0] = new User("Tom Jones", "jones1","Production Manager", "tjones@hotmail.com");
		 user[1] = new User("Sue Smith", "smith1","Inventory Manager","smith.sue@gmail.com");
		 user[2] = new User("Ragnar Nelson", "nelson1","Admin", "rnelson71@gmail.com");
		 user[3] = new User("Barbara Davila", "davila1", "Viewer","barbara_dav@hotmail.com");
		 user[4] = new User("Bad Credentials", "blah", "bad", "hello");
		 setPermissions();
	
	}
	
	public static void setPermissions(){
		for(int j=0; j < userCount; j++){
			String role = user[j].getRole();
			
		if(role.toLowerCase().equals("production manager")){
			for(int i=0; i < user[j].getPermissions().length;i++)
				user[j].getPermissions()[i] = true;
			user[j].getPermissions()[4]= false;
			user[j].getPermissions()[5] = false;
			user[j].getPermissions()[6]= false;
			}
		else if(role.toLowerCase().equals("admin")){
			for(int i=0; i < user[j].getPermissions().length;i++)
				user[j].getPermissions()[i] = true;
			}
		else if(role.toLowerCase().equals("inventory manager")){
			for(int i=0; i < user[j].getPermissions().length;i++)
				user[j].getPermissions()[i] = false;
			user[j].getPermissions()[4] = true;
			user[j].getPermissions()[5] = true;
		
			}
		else if(role.toLowerCase().equals("viewer")){
			for(int i=0; i < user[j].getPermissions().length;i++)
				user[j].getPermissions()[i] = false;
			}
		
		}
	}
	//Used in Login View
	public static String[] getlogins(){
		
		String [] logins = new String[userCount];
		for(int i=0; i<userCount; i++){
			logins[i] = user[i].getLoginName();
		}
		return logins;
		
	}
	
	public static Boolean[] getPermissions(String login){
		for(int i=0; i<userCount; i++){
			if(login.equals(user[i].getLoginName())){
				//System.out.println("Found");
				return user[i].getPermissions();
			}
			//System.out.println(user[i].getLoginName() + " Login  Passed: "+login);
		}
		//System.out.println("Not Found");
		return null;
		
	}
	
}
