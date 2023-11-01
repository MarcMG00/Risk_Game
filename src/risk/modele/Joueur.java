package risk.modele;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private String prenom;
	private int nbRegiments;
	private int Score;
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

	public Joueur() {
		// TODO Auto-generated constructor stub
	}

	public int getNbRegiments() {
		return nbRegiments;
	}

	public void setNbRegiments(int nbRegiments) {
		this.nbRegiments = nbRegiments;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
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

	public boolean isaConqueritEnUntour() {
		return aConqueritEnUntour;
	}

	public void setaConqueritEnUntour(boolean aConqueritEnUntour) {
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
	
}
