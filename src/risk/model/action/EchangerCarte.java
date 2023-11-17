package risk.model.action;

import risk.modele.Carte_Territoire;
import risk.modele.Joueur;

public class EchangerCarte {
	private Joueur joueur;
	private int RegimentsSuppSixEchange;
	private int numFoisEchangeCarte;
	private Carte_Territoire carte1;
	private Carte_Territoire carte2;
	private Carte_Territoire carte3;
	
	public EchangerCarte(Joueur joueur, Carte_Territoire carte1, Carte_Territoire carte2, Carte_Territoire carte3) {
		this.joueur = joueur;
		this.carte1 = carte1;
		this.carte2 = carte2;
		this.carte3 = carte3;
		this.RegimentsSuppSixEchange = 0;
		this.numFoisEchangeCarte = 0;
	}
	
	public int getRegimentsSuppSixEchange() {
		return RegimentsSuppSixEchange;
	}

	public void setRegimentsSuppSixEchange(int regimentsSuppSixEchange) {
		RegimentsSuppSixEchange = regimentsSuppSixEchange;
	}

	public int getNumFoisEchangeCarte() {
		return numFoisEchangeCarte;
	}

	public void setNumFoisEchangeCarte(int numFoisEchangeCarte) {
		this.numFoisEchangeCarte = numFoisEchangeCarte;
	}

	public boolean EchnagerCartes() {
		boolean autoriserEchange = true;
		if(!joueur.getCartesTerritoires().contains(carte1) || !joueur.getCartesTerritoires().contains(carte2) || !joueur.getCartesTerritoires().contains(carte3)) {
			System.out.println(joueur.getNom() + " Au moins une des cartes que vous voulez �changer, vous ne la poss�dez pas");
			autoriserEchange = false;
		}
		else if(!((carte1.getTypeRegiment() == carte2.getTypeRegiment()) && (carte1.getTypeRegiment() == carte3.getTypeRegiment()) && (carte2.getTypeRegiment() == carte3.getTypeRegiment())) ||
				!((carte1.getTypeRegiment() != carte2.getTypeRegiment()) && (carte1.getTypeRegiment() != carte3.getTypeRegiment()) && (carte2.getTypeRegiment() != carte3.getTypeRegiment()))) {
			System.out.println(joueur.getNom() + " Soit vous n'avez pas les 3 cartes ayant un m�me type de r�giment diff�rent, soit vous en avez que deux qui en ont un m�me type");
			System.out.println("Pour rappel, il faut avoir soit les 3 cartes avec un m�me type de r�giment, soit elles ont chacune un type de r�giment diff�rent");
			autoriserEchange = false;
		}
		else {
			joueur.setNumFoisEchangesCartes(joueur.getNumFoisEchangesCartes() + 1);
			
			for(Carte_Territoire ct : joueur.getCartesTerritoires()) {
				if(ct.getTerritoire().equals(carte1.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte1.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce r�giment");
					joueur.calculNbRegimentsTotaux();
				}
				if(ct.getTerritoire().equals(carte2.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte2.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce r�giment");
					joueur.calculNbRegimentsTotaux();
				}
				if(ct.getTerritoire().equals(carte3.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte3.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce r�giment");
					joueur.calculNbRegimentsTotaux();
				}
			}
			
			if(this.numFoisEchangeCarte == 1) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 4);
				System.out.println(joueur.getNom() + " C'est le premier �change du jeu, ainsi vous recevez " + 4 + " r�giments");
			}
			else if(this.numFoisEchangeCarte == 2) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 6);
				System.out.println(joueur.getNom() + " C'est le deuxi�me �change du jeu, ainsi vous recevez " + 6 + " r�giments");
			}
			else if(this.numFoisEchangeCarte == 3) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 8);
				System.out.println(joueur.getNom() + " C'est le troisi�me �change du jeu, ainsi vous recevez " + 8 + " r�giments");
			}
			else if(this.numFoisEchangeCarte == 4) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 10);
				System.out.println(joueur.getNom() + " C'est le quatri�me �change du jeu, ainsi vous recevez " + 10 + " r�giments");
			}
			else if(this.numFoisEchangeCarte == 5) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 12);
				System.out.println(joueur.getNom() + " C'est le cinqui�me �change du jeu, ainsi vous recevez " + 12 + " r�giments");
			}
			else if(this.numFoisEchangeCarte == 6) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 15);
				System.out.println(joueur.getNom() + " C'est le sixi�me �change du jeu, ainsi vous recevez " + 15 + " r�giments");
			}
			else if(this.numFoisEchangeCarte > 6) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 20 + this.getRegimentsSuppSixEchange());
				this.setRegimentsSuppSixEchange(this.getRegimentsSuppSixEchange() + 5);
				System.out.println(joueur.getNom() + " C'est le" + this.numFoisEchangeCarte + " �change du jeu, ainsi vous recevez " + 20+this.getRegimentsSuppSixEchange() + " r�giments");
			}
			
			// On augmente le num�ro d'�change de cartes
			this.numFoisEchangeCarte ++;
		}
		return autoriserEchange;
	}
}

// ajouter les cartes dans le jeu, les supprimer du joueur
// Mettre boucle while dans le jeu � chaque fois que cette fonction retour ne false en proposant si r�ellement le joueur veut �changer des cartes

// faire fonction plus avanc�e qui v�rifie si tu peux �changer des cartes ? En parcourant sa liste de cartes ?
