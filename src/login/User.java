package login;

import android.graphics.Bitmap;

public class User {
	private int id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private Bitmap photo;
	private String password;
	private int id_student;
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
		id_student = -1;
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
	public Bitmap getPhoto () { return photo;}
	public void setPhoto (Bitmap _photo) { this.photo = _photo;};
	public String getPassword () { return password;}
	public void setPassword (String _password) { this.password = _password;}
	public int getStudent () { return id_student;}
	public void setStudent(int _id_student){ this.id_student = _id_student;}
	public int isStaff () { return is_staff;}
	public void setStaff(int _is_staff){ this.is_staff = _is_staff;}
	public int isActive () { return is_active;}
	public void setActive(int _is_active){ this.is_staff = _is_active;}
	public int isSuperUser () { return is_superuser;}
	public void setSuperUser(int _is_superuser){ this.is_superuser = _is_superuser;}	
}
