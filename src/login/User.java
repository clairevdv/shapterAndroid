package login;

public class User {
	private int id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private int is_staff;
	private int is_active;
	private int is_superuser;

	public User(){};

	public User(String _username, String _firstname, String _lastname, String _email, String _password) {
		this.username = _username;
		this.first_name = _firstname;
		this.last_name = _lastname;
		this.email = _email;
		this.password = _password;
	}

	public User(String _username, String _firstname, String _lastname, String _email, String _password, int _isstaff, int _isactive, int _issuperuser) {
		this.username = _username;
		this.first_name = _firstname;
		this.last_name = _lastname;
		this.email = _email;
		this.password = _password;
		this.is_staff = _isstaff;
		this.is_active = _isactive;
		this.is_superuser = _issuperuser;
	}

	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public String getUserName () { return username;}
	public void setUser (String _username) { this.username = _username;}
	public String getFirstName () { return first_name;}
	public void setFirstName (String _firstname) { this.first_name = _firstname;}	
	public String getLastName () { return last_name;}
	public void setLastName (String _lastname) { this.last_name = _lastname;}
	public String getEmail () {return email;}
	public void setEmail (String _email) { this.email = _email;}
	public String getPassword () { return password;}
	public void setPassword (String _password) { this.password = _password;}
}
