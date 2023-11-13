package risk.model.action;

import risk.modele.Carte_Territoire;
import risk.modele.Joueur;

public class EchangerCarte {
	private Joueur joueur;
	private Carte_Territoire carte1;
	private Carte_Territoire carte2;
	private Carte_Territoire carte3;
	
	public EchangerCarte(Joueur joueur, Carte_Territoire carte1, Carte_Territoire carte2, Carte_Territoire carte3) {
		this.joueur = joueur;
		this.carte1 = carte1;
		this.carte2 = carte2;
		this.carte3 = carte3;
	}
	
}
