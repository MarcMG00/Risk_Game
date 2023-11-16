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
	
	public boolean EchnagerCartes() {
		boolean autoirserEchange = true;
		if(!joueur.getCartesTerritoires().contains(carte1) || !joueur.getCartesTerritoires().contains(carte2) || !joueur.getCartesTerritoires().contains(carte3)) {
			System.out.println(joueur.getNom() + " Au moins une des cartes que vous voulez �changer, vous ne la poss�dez pas");
			autoirserEchange = false;
		}
		else if(!((carte1.getTypeRegiment() == carte2.getTypeRegiment()) && (carte1.getTypeRegiment() == carte3.getTypeRegiment()) && (carte2.getTypeRegiment() == carte3.getTypeRegiment())) ||
				!((carte1.getTypeRegiment() != carte2.getTypeRegiment()) && (carte1.getTypeRegiment() != carte3.getTypeRegiment()) && (carte2.getTypeRegiment() != carte3.getTypeRegiment()))) {
			System.out.println(joueur.getNom() + " Soit vous n'avez pas les 3 cartes ayant un m�me type de r�giment diff�rent, soit vous en avez que deux qui en ont un m�me type");
			System.out.println("Pour rappel, il faut avoir soit les 3 cartes avec un m�me type de r�giment, soit elles ont chacune un type de r�giment diff�rent");
			autoirserEchange = false;
		}
		else {
			joueur.setNumFoisEchangesCartes(joueur.getNumFoisEchangesCartes() + 1);
			
			for(Carte_Territoire ct : joueur.getCartesTerritoires()) {
				if(ct.getTerritoire().equals(carte1.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
				}
				if(ct.getTerritoire().equals(carte2.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
				}
				if(ct.getTerritoire().equals(carte3.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
				}
			}
			
			int numFoisEchangesCartes = joueur.getNumFoisEchangesCartes();
			if(numFoisEchangesCartes == 1) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 4);
			}
			else if(numFoisEchangesCartes == 2) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 6);
			}
			else if(numFoisEchangesCartes == 3) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 8);
			}
			else if(numFoisEchangesCartes == 4) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 10);
			}
			else if(numFoisEchangesCartes == 5) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 12);
			}
			else if(numFoisEchangesCartes == 6) {
				joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 15);
			}
			else if(numFoisEchangesCartes > 6) {
					joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + 20 + joueur.getRegimentsChangementCartes6Tour());
					joueur.setRegimentsChangementCartes6Tour(joueur.getRegimentsChangementCartes6Tour() + 5);
			}
		}
		return autoirserEchange;
	}
}

// V�rfier si une des cartes contient un territoire poss�d� par le joueur
// ajouter les cartes dans le jeu, les supprimer du joueur
// Mettre boucle while dans le jeu � chaque fois que cette fonction retour ne false en proposant si r�ellement le joueur veut �changer des cartes

// faire fonction plus avanc�e qui v�rifie si tu peux �changer des cartes ? En parcourant sa liste de cartes ?
