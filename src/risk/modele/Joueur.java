package risk.modele;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private String prenom;
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

}
