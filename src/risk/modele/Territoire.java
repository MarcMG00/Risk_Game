package risk.modele;

public class Territoire {
	private int numTerritoire;
	private String nomTerritoire;
	private int regiments;
	private Joueur joueurPossedantTerritoire;
	private Territoire_Adjacent territoiresAdjacents;
	
	public Territoire(int numTerritoire, String nomTerritoire) {
		this.numTerritoire = numTerritoire;
		this.nomTerritoire = nomTerritoire;
		this.regiments = 0;
		this.territoiresAdjacents = new Territoire_Adjacent();
		this.joueurPossedantTerritoire = new Joueur();
	}
}
