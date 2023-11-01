package risk.modele;

import java.util.ArrayList;

public class Territoire {
	private int numTerritoire;
	private String nomTerritoire;
	private int regiments;
	private Joueur joueurPossedantTerritoire;
	private Territoire_Adjacent territoiresAdjacents;
	
	public Territoire(int numTerritoire, String nomTerritoire) {
		ArrayList<Territoire> territAdj = new ArrayList<Territoire>();
		this.numTerritoire = numTerritoire;
		this.nomTerritoire = nomTerritoire;
		this.regiments = 0;
		this.territoiresAdjacents = new Territoire_Adjacent(territAdj);
		this.joueurPossedantTerritoire = new Joueur();
	}

	public int getRegiments() {
		return regiments;
	}

	public void setRegiments(int regiments) {
		this.regiments = regiments;
	}

	public Joueur getJoueurPossedantTerritoire() {
		return joueurPossedantTerritoire;
	}

	public void setJoueurPossedantTerritoire(Joueur joueurPossedantTerritoire) {
		this.joueurPossedantTerritoire = joueurPossedantTerritoire;
	}

	public int getNumTerritoire() {
		return numTerritoire;
	}

	public String getNomTerritoire() {
		return nomTerritoire;
	}

	public Territoire_Adjacent getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}

	public void setTerritoiresAdjacents(Territoire_Adjacent territoiresAdjacents) {
		this.territoiresAdjacents = territoiresAdjacents;
	}
	
}
