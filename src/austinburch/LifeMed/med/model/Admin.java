package austinburch.LifeMed.med.model;

public class Admin {

	
	private String adminName;
	private String password;
	
	public Admin(String adminName, String password)
	{
		this.adminName = adminName;
		this.password = password;
	}
	
	public String getAdminName()
	{
		return adminName;
	}
	
	public String getAdminPassword()
	{
		
		return password;
	}
	
	
}
