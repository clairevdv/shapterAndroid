package testBDD;

/*
 * Création d'un cours de la BDD des cours (qui devra être importée)
 */

public class Cours {
	private int parcours;
	private int id;
	private String titreUE;
	
	public Cours(){};
	
	public Cours(int _parcours, String _titreUE) {
		this.parcours = _parcours;
		this.titreUE = _titreUE;
	}
	
	public int getParcours () { return parcours;}
	public void setParcours (int _parcours) { this.parcours = _parcours;}
	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public String getTitreUE () { return titreUE;}
	public void setTitreUE (String _titreUE) { this.titreUE = _titreUE;};
	
	public String description() {
		return "l'UE "+titreUE+" est une UE du parcours "+parcours;
	}
}
