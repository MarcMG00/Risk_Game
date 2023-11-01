package risk.modele;

import java.util.ArrayList;

public class Territoire_Adjacent {
	private ArrayList<Territoire> territoiresAdjacents;
	
	public Territoire_Adjacent(ArrayList<Territoire> territoiresAdjacents) {
		this.territoiresAdjacents = territoiresAdjacents;
	}

	public ArrayList<Territoire> getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}
}
