package ueBDD;

public class School {
	private int id;
	private String name;
	private String country;
	private String description;
	
	public School(){};
	
	public School(String _name, String _country) {
		this.name = _name;
		this.country = _country;
		this.description = "Cette école n'a pas souhaité nous fournir une description";
	}
	
	public School(String _name, String _country, String _description) {
		this.name = _name;
		this.country = _country;
		this.description = _description;
	}
	
	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public String getName () { return name;}
	public void setSchool (String _name) { this.name = _name;}
	public String getCountry () { return country;}
	public void setCountry (String _country) { this.country = _country;};
	public String getDescriptionEcole () { return description;}
	public void setDescriptionEcole (String _description) { this.description = _description;};
}
