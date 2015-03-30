package model;

public class User {
	private String email;
	private String password;
	private String name;
	private String role;
	private Boolean[] permissions;
	private String loginName;
	/*View Product Templates 0
	 * Add/Edit Product Temp 1
	 * Delete Product Temp 2
	 * Create Products 3
	 * Add edit Inventory 4
	 * Add/Edit Parts 5
	 * Delete parts and inventory 6
	 * 
	 * 
	 * */
	
	public User(String name, String password,String role, String email){
		permissions = new Boolean[7];
		setEmail(email);
		setPassword(password);
		setName(name);
		setRole(role);
		setLoginName(name + ": " + role);
		
				
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
		
		if(role.toLowerCase().equals("production manager")){
			for(int i=0; i < permissions.length;i++)
				permissions[i] = true;
			permissions[4]= false;
			permissions[5] = false;
			permissions[6]= false;
		}
		else if(role.equals("Admin")){
			for(int i=0; i < permissions.length;i++)
				permissions[i] = true;
		}
		else if(role.equals("Inventory Manager")){
			for(int i=0; i < permissions.length;i++)
				permissions[i] = false;
			permissions[4] = true;
			permissions[5] = true;
		}
	}
	public Boolean[] getPermissions(){
		return permissions;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}

	
	
}
