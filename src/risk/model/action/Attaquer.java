package risk.model.action;

import risk.modele.Territoire;

public class Attaquer {
	private Territoire territAttaquant;
	private Territoire territDefenseur;
	private int nbRegimentsAtq;
	
	public Attaquer(Territoire territAttaquant, Territoire territDefenseur,  int nbRegimetnsAtq) {
		this.territAttaquant = territAttaquant;
		this.territDefenseur = territDefenseur;
		this.nbRegimentsAtq = nbRegimetnsAtq;
	}

	public Territoire getTerritAttaquant() {
		return territAttaquant;
	}

	public Territoire getTerritDefenseur() {
		return territDefenseur;
	}

	public int getNbRegimentsAtq() {
		return nbRegimentsAtq;
	}
	
}
