package risk.model.action;

import java.util.ArrayList;

import risk.modele.Carte_Territoire;
import risk.modele.Joueur;
import risk.modele.Territoire;

public class EchangerCarte {
	private Joueur joueur;
	private int RegimentsSuppSixEchange;
	private int numFoisEchangeCarte;
	
	public EchangerCarte(Joueur joueur) {
		this.joueur = joueur;
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

	public boolean EchangerCartes(Carte_Territoire carte1, Carte_Territoire carte2, Carte_Territoire carte3) {
		boolean echangement = true;
		
		if(!joueur.getCartesTerritoires().contains(carte1) || !joueur.getCartesTerritoires().contains(carte2) || !joueur.getCartesTerritoires().contains(carte3)) {
			System.out.println(joueur.getNom() + " Au moins une des cartes que vous voulez �changer, vous ne la poss�dez pas");
			echangement = false;
		}
		else {
			joueur.setNumFoisEchangesCartes(joueur.getNumFoisEchangesCartes() + 1);
			
			for(Carte_Territoire ct : joueur.getCartesTerritoires()) {
				if(ct.getTerritoire().equals(carte1.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte1.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce territoire");
					joueur.calculNbRegimentsTotaux();
				}
				if(ct.getTerritoire().equals(carte2.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte2.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce territoire");
					joueur.calculNbRegimentsTotaux();
				}
				if(ct.getTerritoire().equals(carte3.getTerritoire())) {
					ct.getTerritoire().setRegiments(ct.getTerritoire().getRegiments() + 2);
					System.out.println(joueur.getNom() + " Vous poss�dez le territoire de la carte " + carte3.getTerritoire().getNomTerritoire() + ", 2 r�giments ont �t� plac�s sur ce territoire");
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
		return echangement;
	}
	
	// Verifie si le joueur poss�de de cartes potentielles pour �changer
		public boolean VerificationEchangeCartes() {
			boolean echangePossible = false;
			int nbSoldat = 0;
			ArrayList<Carte_Territoire> cartesSoldat = new ArrayList<Carte_Territoire>();
			int nbArtillerie = 0;
			ArrayList<Carte_Territoire> cartesArtillerie = new ArrayList<Carte_Territoire>();
			int nbCavalier = 0;
			ArrayList<Carte_Territoire> cartesCavalier = new ArrayList<Carte_Territoire>();
			
			for(Carte_Territoire ct : joueur.getCartesTerritoires()) {
				if(ct.getTypeRegiment().equals("Soldat")) {
					nbSoldat++;
					cartesSoldat.add(ct);
				}
				else if(ct.getTypeRegiment().equals("Cavalier")) {
					nbCavalier++;
					cartesCavalier.add(ct);
				}
				else {
					nbArtillerie++;
					cartesArtillerie.add(ct);
				}
			}
			
			// 3 cartes du m�me r�giment
			if(nbSoldat >= 3) {
				System.out.println(joueur.getNom() + " Vous poss�dez au moins 3 cartes avec le r�giment 'Soldat' :");
				for(Carte_Territoire cts : cartesSoldat) {
					System.out.println("Carte : " + cts.getNumCarte() + " - " + cts.getTerritoire().getNomTerritoire() + " - " + cts.getTypeRegiment());
				}
				System.out.println();
				echangePossible = true;
			}
			
			if(nbCavalier >= 3) {
				System.out.println(joueur.getNom() + " Vous poss�dez au moins 3 cartes avec le r�giment 'Cavalier' :");
				for(Carte_Territoire cts : cartesCavalier) {
					System.out.println("Carte : " + cts.getNumCarte() + " - " + cts.getTerritoire().getNomTerritoire() + " - " + cts.getTypeRegiment());
				}
				System.out.println();
				echangePossible = true;
			}
			
			if(nbArtillerie >= 3) {
				System.out.println(joueur.getNom() + " Vous poss�dez au moins 3 cartes avec le r�giment 'Artillerie' :");
				for(Carte_Territoire cts : cartesArtillerie) {
					System.out.println("Carte : " + cts.getNumCarte() + " - " + cts.getTerritoire().getNomTerritoire() + " - " + cts.getTypeRegiment());
				}
				System.out.println();
				echangePossible = true;
			}
			
			// 3 cartes de r�giment diff�rents
			if(nbSoldat >= 1 && nbCavalier >= 1 && nbArtillerie >= 1) {
				System.out.println(joueur.getNom() + " Vous poss�dez au moins 1 carte avec un type de r�giment diff�rent chacune : ");
				System.out.println("Carte : " + this.firstOrDefault(cartesSoldat).getNumCarte() + " - " + this.firstOrDefault(cartesSoldat).getTerritoire().getNomTerritoire() + " - " + this.firstOrDefault(cartesSoldat).getTypeRegiment());
				System.out.println("Carte : " + this.firstOrDefault(cartesCavalier).getNumCarte() + " - " + this.firstOrDefault(cartesCavalier).getTerritoire().getNomTerritoire() + " - " + this.firstOrDefault(cartesCavalier).getTypeRegiment());
				System.out.println("Carte : " + this.firstOrDefault(cartesArtillerie).getNumCarte() + " - " + this.firstOrDefault(cartesArtillerie).getTerritoire().getNomTerritoire() + " - " + this.firstOrDefault(cartesArtillerie).getTypeRegiment());
				echangePossible = true;
			}
			
			// On fait une derni�re v�rification pour savoir si le joueur a les combinaisons n�cessaires pour �changer
			if(!(nbSoldat >= 1 && nbCavalier >= 1 && nbArtillerie >= 1) && nbSoldat < 3 && nbCavalier < 3 && nbArtillerie < 3) {
				System.out.println(joueur.getNom() + " Soit vous ne poss�dez pas au moins une carte de chaque type de r�giment, soit vous ne poss�dez pas au moins 3 cartes du m�me type de r�giment");
				echangePossible = false;
			}
			
			return echangePossible;
		}
	
   // Retourne la premi�re valeur d'une liste de cartes
   public Carte_Territoire firstOrDefault(ArrayList<Carte_Territoire> items) {
        if (items.size() < 1) {
            return null;
        }
        return items.get(0);
    }
   
   // Affiche les meilleures combinaisons de cartes (l� o� le joueur obtiendra le plus de r�giments)
   public void AfficherComboCartes() {
		
		// Liste contenant tous les combos de cartes, reng�s par ordre d'avantage
		ArrayList<ArrayList<Carte_Territoire>> combosCartes = new ArrayList<ArrayList<Carte_Territoire>>();
		ArrayList<Carte_Territoire> cartesPrioritaires= new ArrayList<Carte_Territoire>();
		
		ArrayList<Carte_Territoire> cartesSoldat = new ArrayList<Carte_Territoire>();
		ArrayList<Carte_Territoire> cartesCavalier = new ArrayList<Carte_Territoire>();
		ArrayList<Carte_Territoire> cartesArtillerie = new ArrayList<Carte_Territoire>();
		
		// Nous ajoutons les cartes dans des listes par type de r�giment
		for(Carte_Territoire cjr : this.joueur.getCartesTerritoires()) {
			if(cjr.getTypeRegiment().equals("Soldat")) {
				cartesSoldat.add(cjr);
			}
			else if(cjr.getTypeRegiment().equals("Cavalier")) {
				cartesCavalier.add(cjr);
			}
			else {
				cartesArtillerie.add(cjr);
			}
		}
		
		// V�rification : on a au moins 3 cartes avec le m�me type de r�giment, pour chacune des listes de type de r�giments
		// Artillerie
		combosCartes = ajouterCartesMemeTypeRegiment(cartesArtillerie, combosCartes);
		
		// Soldat
		combosCartes = ajouterCartesMemeTypeRegiment(cartesSoldat, combosCartes);
		
		// Cavalier
		combosCartes = ajouterCartesMemeTypeRegiment(cartesCavalier, combosCartes);
		
		// V�rification : on a  3 cartes avec le type de r�giment qui est diff�rent
		// Pour ce faire, v�rifier que les listes de soldats, artillerie, cavalier aient au moins une carte
		while(cartesArtillerie.size() > 1 && cartesSoldat.size() > 1 && cartesCavalier.size() > 1) {
			// Ajouter une carte d'artillerie
			for(Territoire t : this.joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesArtillerie.size(); iCarte++) {
					if(cartesPrioritaires.size() < 1 && cartesArtillerie.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesArtillerie.get(iCarte));
						cartesArtillerie.remove(cartesArtillerie.get(iCarte));
						break;
					}
				}
				// On arr�te de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 1) {
					break;
				}
			}
			// Si aucune carte avec un territoire poss�d� par le joueur n'a �t� trouv�e, on prend une carte au hasard (par exemple, la premi�re de la liste)
			if(cartesPrioritaires.size() < 1) {
				cartesPrioritaires.add(cartesArtillerie.get(0));
				cartesArtillerie.remove(cartesArtillerie.get(0));
			}
			
			// Ajouter une carte de soldat
			for(Territoire t : this.joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesSoldat.size(); iCarte++) {
					if(cartesPrioritaires.size() < 2 && cartesSoldat.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesSoldat.get(iCarte));
						cartesSoldat.remove(cartesSoldat.get(iCarte));
						break;
					}
				}
				// On arr�te de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 2) {
					break;
				}
			}
			// Si aucune carte avec un territoire poss�d� par le joueur n'a �t� trouv�e, on prend une carte au hasard (par exemple, la premi�re de la liste)
			if(cartesPrioritaires.size() < 2) {
				cartesPrioritaires.add(cartesSoldat.get(0));
				cartesSoldat.remove(cartesSoldat.get(0));
			}
			
			// Ajouter une carte de cavalier
			for(Territoire t : this.joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesCavalier.size(); iCarte++) {
					if(cartesPrioritaires.size() < 3 && cartesCavalier.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesCavalier.get(iCarte));
						cartesCavalier.remove(cartesCavalier.get(iCarte));
						break;
					}
				}
				// On arr�te de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 3) {
					break;
				}
			}
			// Si aucune carte avec un territoire poss�d� par le joueur n'a �t� trouv�e, on prend une carte au hasard (par exemple, la premi�re de la liste)
			if(cartesPrioritaires.size() < 3) {
				cartesPrioritaires.add(cartesCavalier.get(0));
				cartesCavalier.remove(cartesCavalier.get(0));
			}
			
			combosCartes.add(cartesPrioritaires);
			cartesPrioritaires = new ArrayList<Carte_Territoire>();
		}
		
		// Affichage de tous les combos de cartes
		System.out.println();
		for(ArrayList<Carte_Territoire> listCombos: combosCartes) {
			for(Carte_Territoire cts : listCombos) {
				System.out.println(cts.getNumCarte() + " " + cts.getTerritoire().getNomTerritoire() + " - " + cts.getTypeRegiment());
			}
			System.out.println();
		}
	}
	
	// M�tode pour ajouter des cartes avec le m�me type de r�giment dans un combo de cartes
	public ArrayList<ArrayList<Carte_Territoire>> ajouterCartesMemeTypeRegiment(ArrayList<Carte_Territoire> cartesMemeRegiment, ArrayList<ArrayList<Carte_Territoire>> combosCartes) {
		ArrayList<Carte_Territoire> cartesPrioritaires= new ArrayList<Carte_Territoire>();
		while(cartesMemeRegiment.size() >= 3) {
			for(Territoire t : this.joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesMemeRegiment.size(); iCarte++) {
					if(cartesMemeRegiment.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire()) && cartesPrioritaires.size() < 3) {
						cartesPrioritaires.add(cartesMemeRegiment.get(iCarte));
						cartesMemeRegiment.remove(cartesMemeRegiment.get(iCarte));
					}
				}
			}
			// Si on a 3 cartes dans le combo, on ajoute ces trois derni�res dans la liste globale des combos de cartes
			// Et on remet � 0 la liste de combos pour donner place � d'autres combos
			if(cartesPrioritaires.size() == 3) {
				combosCartes.add(cartesPrioritaires);
				cartesPrioritaires = new ArrayList<Carte_Territoire>();
			}
			// Si on a encore de la place dans les combos de cartes, et s'il y a des cartes du m�me type de r�giment, on ajoute des cartes dans ce combo
			else if(cartesPrioritaires.size() < 3 && cartesMemeRegiment.size() >= 1) {
				while(!cartesMemeRegiment.isEmpty() && cartesPrioritaires.size() < 3) {
					cartesPrioritaires.add(cartesMemeRegiment.get(0));
					cartesMemeRegiment.remove(0);
				}
				if(cartesPrioritaires.size() == 3) {
					combosCartes.add(cartesPrioritaires);
					cartesPrioritaires = new ArrayList<Carte_Territoire>();
				}
			}
		}
		return combosCartes;
	}
	
}
