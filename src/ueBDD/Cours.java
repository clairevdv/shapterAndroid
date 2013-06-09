package ueBDD;

/*
 * Création d'un cours de la BDD des cours (qui devra être importée)
 */

public class Cours {
	private int id;
	private int parcours;
	private String titreUE;
	private String description;
	
	public Cours(){};
	
	public Cours(int _parcours, String _titreUE) {
		this.parcours = _parcours;
		this.titreUE = _titreUE;
		this.description = "Cette UE n'a pas de description enregistrée";
	}
	
	public Cours(int _parcours, String _titreUE, String _description) {
		this.parcours = _parcours;
		this.titreUE = _titreUE;
		this.description = _description;
	}
	
	public int getParcours () { return parcours;}
	public void setParcours (int _parcours) { this.parcours = _parcours;}
	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public String getTitreUE () { return titreUE;}
	public void setTitreUE (String _titreUE) { this.titreUE = _titreUE;};
	public String getDescriptionUE () { return description;}
	public void setDescriptionUE (String _description) { this.description = _description;};
}
