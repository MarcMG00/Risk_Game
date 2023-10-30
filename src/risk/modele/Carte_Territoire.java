package risk.modele;


public class Carte_Territoire {
	private int numCarte;
	private Territoire territoire;
	private String typeRegiment;
	
	public Carte_Territoire(int numCarte, Territoire territoire, String typeRegiment) {
		this.numCarte = numCarte;
		this.territoire = territoire;
		this.typeRegiment = typeRegiment;
	}

	public int getNumCarte() {
		return numCarte;
	}

	public Territoire getTerritoire() {
		return territoire;
	}

	public String getTypeRegiment() {
		return typeRegiment;
	}
}
