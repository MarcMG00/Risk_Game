package risk.modele;

import java.util.ArrayList;


public class Continent {
	private int numContinent;
	private String nomContinent;
	private ArrayList<Territoire> territoires;
	private int valeurBonus;
	
	public Continent(int numContinent, String nomContinent, int valeurBonus, ArrayList<Territoire> territoires) {
		this.numContinent = numContinent;
		this.nomContinent = nomContinent;
		this.valeurBonus = valeurBonus;
		this.territoires = territoires;
	}

	public int getNumContinent() {
		return numContinent;
	}

	public String getNomContinent() {
		return nomContinent;
	}

	public ArrayList<Territoire> getTerritoires() {
		return territoires;
	}

	public int getValeurBonus() {
		return valeurBonus;
	}
	
	// Méthode pour attribuer la valeur bonus au joueur possédant tous les territoires d'un contient
	public boolean verifierPossessionTousTerritoires(Joueur joueur) {
    	if (joueur.getTerritoires().containsAll(this.territoires)) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}
}
