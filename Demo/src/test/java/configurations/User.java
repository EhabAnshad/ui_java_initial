package configurations;

public class User 
{
	private String username;
	private String password;
	private boolean canLogin; 
	private String firstName;
	private String midddleName;
	private String lastName;
	private boolean status;
	private String ID="";
	
	public User()
	{

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getCanLogin() {
		return canLogin;
	}

	public void setCanLogin(Boolean canLogin) {
		this.canLogin = canLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidddleName() {
		return midddleName;
	}

	public void setMidddleName(String midddleName) {
		this.midddleName = midddleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}



}
