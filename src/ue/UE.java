package ue;

/*
 * Création d'un cours de la BDD des cours (qui devra être importée)
 */

public class UE {
	private int id;
	private int school_id;
	private String code;
	private String title;
	private String description;
	
	public UE(){};
	
	public UE(int _school_id, String _code, String _title) {
		this.school_id = _school_id;
		this.code = _code;
		this.title = _title;
		this.description = "Cette UE n'a pas de description enregistrée";
	}
	
	public UE(int _school_id, String _code, String _title, String _description) {
		this.school_id = _school_id;
		this.code = _code;
		this.title = _title;
		this.description = _description;
	}
	
	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public int getSchool () { return school_id;}
	public void setSchool (int _school) { this.school_id = _school;}
	public String getCode () { return code;}
	public void setCode (String _code) { this.code = _code;};
	public String getTitle () { return title;}
	public void setTitle (String _title) { this.title = _title;};
	public String getDescriptionUE () { return description;}
	public void setDescriptionUE (String _description) { this.description = _description;};
}
