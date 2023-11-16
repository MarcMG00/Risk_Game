package risk.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private String prenom;
	private int nbRegiments;
	private int nbRegimentsRecusParTour;
	private int score;
	private int regimentsAdditionnels;
	private int numFoisEchangesCartes;
	private boolean aConqueritEnUntour;
	private int nbDefenses;
	private int nbAttaques;
	private int nbFoisLanceesUn;
	private int nbTerritoiresConquis;
	private ArrayList<Territoire> territoires;
	private ArrayList<Continent> continents;
	private ArrayList<Carte_Territoire> cartesTerritoires;

	public Joueur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.nbRegiments = 0;
		this.nbRegimentsRecusParTour = 0;
		this.score = 0;
		this.regimentsAdditionnels = 0;
		this.aConqueritEnUntour = false;
		this.nbDefenses = 0;
		this.nbAttaques = 0;
		this.nbFoisLanceesUn = 0;
		this.nbTerritoiresConquis = 0;
		this.territoires = new ArrayList<Territoire>();
		this.continents = new ArrayList<Continent>();
		this.cartesTerritoires = new ArrayList<Carte_Territoire>();
	}

	public int getNbRegiments() {
		return nbRegiments;
	}

	public void setNbRegiments(int nbRegiments) {
		this.nbRegiments = nbRegiments;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRegimentsAdditionnels() {
		return regimentsAdditionnels;
	}

	public void setRegimentsAdditionnels(int regimentsAdditionnels) {
		this.regimentsAdditionnels = regimentsAdditionnels;
	}

	public int getNumFoisEchangesCartes() {
		return numFoisEchangesCartes;
	}

	public void setNumFoisEchangesCartes(int numFoisEchangesCartes) {
		this.numFoisEchangesCartes = numFoisEchangesCartes;
	}

	public boolean getAConqueritEnUntour() {
		return aConqueritEnUntour;
	}

	public void setAConqueritEnUntour(boolean aConqueritEnUntour) {
		this.aConqueritEnUntour = aConqueritEnUntour;
	}

	public int getNbDefenses() {
		return nbDefenses;
	}

	public void setNbDefenses(int nbDefenses) {
		this.nbDefenses = nbDefenses;
	}

	public int getNbAttaques() {
		return nbAttaques;
	}

	public void setNbAttaques(int nbAttaques) {
		this.nbAttaques = nbAttaques;
	}

	public int getNbFoisLanceesUn() {
		return nbFoisLanceesUn;
	}

	public void setNbFoisLanceesUn(int nbFoisLanceesUn) {
		this.nbFoisLanceesUn = nbFoisLanceesUn;
	}

	public int getNbTerritoiresConquis() {
		return nbTerritoiresConquis;
	}

	public void setNbTerritoiresConquis(int nbTerritoiresConquis) {
		this.nbTerritoiresConquis = nbTerritoiresConquis;
	}

	public ArrayList<Territoire> getTerritoires() {
		return territoires;
	}

	public void setTerritoires(ArrayList<Territoire> territoires) {
		this.territoires = territoires;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	public ArrayList<Carte_Territoire> getCartesTerritoires() {
		return cartesTerritoires;
	}

	public void setCartesTerritoires(ArrayList<Carte_Territoire> cartesTerritoires) {
		this.cartesTerritoires = cartesTerritoires;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNbRegimentsRecusParTour() {
		return nbRegimentsRecusParTour;
	}

	public void setNbRegimentsRecusParTour(int nbRegimentsRecusParTour) {
		this.nbRegimentsRecusParTour = nbRegimentsRecusParTour;
	}
	
	public void ajouterTerritoire(Territoire territoire) {
		this.territoires.add(territoire);
	}
	
	public void enleverTerritoire(Territoire territoire) {
		if(this.territoires.contains(territoire)) {
			this.territoires.remove(territoire);
		}
	}
	
	public void ajouterCarteTerritoire(Carte_Territoire carte_territoire) {
		this.cartesTerritoires.add(carte_territoire);
	}
	
	public void enleverCarteTerritoire(Carte_Territoire carte_territoire) {
		if(this.cartesTerritoires.contains(carte_territoire)) {
			this.cartesTerritoires.remove(carte_territoire);
		}
	}
	
	public void calculNbRegimentsTotaux() {
		this.nbRegiments = 0;
		for(Territoire t : this.getTerritoires()) {
			this.nbRegiments += t.getRegiments();
		}
	}
	
	// Méthode pour donner toutes les cartes, une fois perdus tous les territoires
	public void recevoirCartesTerritoires(Joueur joueurPerdant) {
		if(joueurPerdant.getCartesTerritoires().size() >= 1) {
			for(int i = 0; i < joueurPerdant.getCartesTerritoires().size(); i++) {
				this.ajouterCarteTerritoire(joueurPerdant.getCartesTerritoires().get(i));
			}
			joueurPerdant.setCartesTerritoires(null);
		}
	}
	
}
