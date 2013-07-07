package eleves;

public class Eleve {
	private int _id;
	private int student_year;
	private int school;
	private String personal_desc;

	public Eleve(){};

	public Eleve(int _student_year, int _school) {
		this.student_year = _student_year;
		this.school = _school;
	}

	public void addDesc(String description){
		this.personal_desc = description;
	}

	public int getId () { return _id;}
	public int getYear () { return student_year;}
	public void setYear (int _year) { this.student_year = _year;}
	public int getSchool () { return school;}
	public void setSchool (int _school) { this.school = _school;}
	public String getDescription () { return personal_desc;}
	public void setDescription (String _personal_desc) { this.personal_desc = _personal_desc;};
}
