package eleves;

public class Eleve {
	private int user_ptr_id;
	private int student_year;
	private int school;
	private String personal_desc;
	
	public Eleve(){};
	
	public Eleve(int _user_ptr_id, int _student_year, int _school) {
		this.user_ptr_id = _user_ptr_id;
		this.student_year = _student_year;
		this.school = _school;
	}
	
	public int getId () { return user_ptr_id;}
	public void setId (int _user_ptr_id) { this.user_ptr_id = _user_ptr_id;};
	public int getYear () { return student_year;}
	public void setYear (int _year) { this.student_year = _year;}
	public int getSchool () { return school;}
	public void setSchool (int _school) { this.school = _school;}
	public String getDescription () { return personal_desc;}
	public void setDescription (String _personal_desc) { this.personal_desc = _personal_desc;};
}
