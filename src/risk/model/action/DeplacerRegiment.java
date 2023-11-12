package risk.model.action;

import risk.modele.Territoire;

public class DeplacerRegiment {
	private Territoire territDepart;
	private int nbRegiments;
	
	public DeplacerRegiment(Territoire territDepart, int nbRegiments) {
		this.territDepart = territDepart;
		this.nbRegiments = nbRegiments;
	}
}
