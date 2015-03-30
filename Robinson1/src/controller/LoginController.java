package controller;

import java.util.ArrayList;

import model.User;

public class LoginController {
	
	private static int userCount = 3;
	private static User[] user = new User[userCount];
	public static String currentLogin;
	
	
	
	
	static void setLogins(){
		 user[0] = new User("Tom Jones", "jones1","Production Manager", "tjones@hotmail.com");
		 user[1] = new User("Sue Smith", "smith1","Inventory Manager","smith.sue@gmail.com");
		 user[2] = new User("Ragnar Nelson", "nelson1","Admin", "rnelson71@gmail.com");
	
	}
	
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
