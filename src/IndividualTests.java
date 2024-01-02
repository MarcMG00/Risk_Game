import java.util.ArrayList;

import risk.modele.Carte_Territoire;
import risk.modele.Joueur;
import risk.modele.Territoire;


public class IndividualTests {

	public static Joueur creerJoueur() {
		// Création d'un joueur
		Joueur joueurTest = new Joueur("Moli", "Marc");
		
		// Création de territoires
		Territoire territEO = new Territoire(1, "Europe de l'Ouest");
		Territoire territGB = new Territoire(2, "Grande-Bretagne");
		Territoire territEN = new Territoire(3, "Europe du Nord");
		Territoire territIs = new Territoire(4, "Islande");
		Territoire territSc = new Territoire(5, "Scandinavie");
		Territoire territES = new Territoire(6, "Europe du Sud");
		Territoire territRus = new Territoire(7, "Russie");
		Territoire territAfg = new Territoire(8, "Afghanistan");
		Territoire territOur = new Territoire(9, "Oural");
		Territoire territMO = new Territoire(10, "Moyen-Orient");
		
		// Création de cartes
		Carte_Territoire cartEO = new Carte_Territoire(12, territEO, "Artillerie");
		Carte_Territoire cartGB = new Carte_Territoire(7, territGB, "Artillerie");
		Carte_Territoire cartEN = new Carte_Territoire(11, territEN, "Artillerie");
		Carte_Territoire cartIs = new Carte_Territoire(8, territIs, "Soldat");
		Carte_Territoire cartSc = new Carte_Territoire(9, territSc, "Cavalier");
		Carte_Territoire cartEs = new Carte_Territoire(13, territES, "Artillerie");
		Carte_Territoire cartRus = new Carte_Territoire(10, territRus, "Cavalier");
		Carte_Territoire cartAfg = new Carte_Territoire(28, territAfg, "Cavalier");
		Carte_Territoire cartOur = new Carte_Territoire(20, territOur, "Cavalier");
		Carte_Territoire cartMO = new Carte_Territoire(29, territMO, "Soldat");
		
		
		// Ajout de territoires
		joueurTest.ajouterTerritoire(territEO);
		joueurTest.ajouterTerritoire(territGB);
		joueurTest.ajouterTerritoire(territEN);
		joueurTest.ajouterTerritoire(territIs);
		joueurTest.ajouterTerritoire(territSc);
//		joueurTest.ajouterTerritoire(territES);
		joueurTest.ajouterTerritoire(territRus);
		joueurTest.ajouterTerritoire(territAfg);
		joueurTest.ajouterTerritoire(territOur);
		joueurTest.ajouterTerritoire(territMO);
		
		// Ajout de cartes
		joueurTest.ajouterCarteTerritoire(cartEO);
		joueurTest.ajouterCarteTerritoire(cartGB);
		joueurTest.ajouterCarteTerritoire(cartEN);
		joueurTest.ajouterCarteTerritoire(cartEs);
		joueurTest.ajouterCarteTerritoire(cartIs);
		joueurTest.ajouterCarteTerritoire(cartSc);
		joueurTest.ajouterCarteTerritoire(cartOur);
		joueurTest.ajouterCarteTerritoire(cartMO);
		joueurTest.ajouterCarteTerritoire(cartRus);
		joueurTest.ajouterCarteTerritoire(cartAfg);
		
		return joueurTest;
	}
	
	public static void AfficherComboCartes(Joueur joueur) {
		
		// Liste contenant tous les combos de cartes, rengés par ordre d'avantage
		ArrayList<ArrayList<Carte_Territoire>> combosCartes = new ArrayList<ArrayList<Carte_Territoire>>();
		ArrayList<Carte_Territoire> cartesPrioritaires= new ArrayList<Carte_Territoire>();
		
		ArrayList<Carte_Territoire> cartesSoldat = new ArrayList<Carte_Territoire>();
		ArrayList<Carte_Territoire> cartesCavalier = new ArrayList<Carte_Territoire>();
		ArrayList<Carte_Territoire> cartesArtillerie = new ArrayList<Carte_Territoire>();
		
		// Nous ajoutons les cartes dans des listes par type de régiment
		for(Carte_Territoire cjr : joueur.getCartesTerritoires()) {
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
		
		// Vérification : on a au moins 3 cartes avec le même type de régiment, pour chacune des listes de type de régiments
		// Artillerie
		combosCartes = ajouterCartesMemeTypeRegiment(joueur.getTerritoires(), cartesArtillerie, combosCartes);
		
		// Soldat
		combosCartes = ajouterCartesMemeTypeRegiment(joueur.getTerritoires(), cartesSoldat, combosCartes);
		
		// Cavalier
		combosCartes = ajouterCartesMemeTypeRegiment(joueur.getTerritoires(), cartesCavalier, combosCartes);
		
		// Vérification : on a  3 cartes avec le type de régiment qui est différent
		// Pour ce faire, vérifier que les listes de soldats, artillerie, cavalier aient au moins une carte
		while(cartesArtillerie.size() > 1 && cartesSoldat.size() > 1 && cartesCavalier.size() > 1) {
			// Ajouter une carte d'artillerie
			for(Territoire t : joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesArtillerie.size(); iCarte++) {
					if(cartesPrioritaires.size() < 1 && cartesArtillerie.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesArtillerie.get(iCarte));
						cartesArtillerie.remove(cartesArtillerie.get(iCarte));
						break;
					}
				}
				// On arrête de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 1) {
					break;
				}
			}
			// Si aucune carte avec un territoire possédé par le joueur n'a été trouvé, on prend une carte au hasard (par exemple, la première de la liste)
			if(cartesPrioritaires.size() < 1) {
				cartesPrioritaires.add(cartesArtillerie.get(0));
				cartesArtillerie.remove(cartesArtillerie.get(0));
			}
			
			// Ajouter une carte de soldat
			for(Territoire t : joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesSoldat.size(); iCarte++) {
					if(cartesPrioritaires.size() < 2 && cartesSoldat.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesSoldat.get(iCarte));
						cartesSoldat.remove(cartesSoldat.get(iCarte));
						break;
					}
				}
				// On arrête de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 2) {
					break;
				}
			}
			// Si aucune carte avec un territoire possédé par le joueur n'a été trouvé, on prend une carte au hasard (par exemple, la première de la liste)
			if(cartesPrioritaires.size() < 2) {
				cartesPrioritaires.add(cartesSoldat.get(0));
				cartesSoldat.remove(cartesSoldat.get(0));
			}
			
			// Ajouter une carte de cavalier
			for(Territoire t : joueur.getTerritoires()) {
				for(int iCarte = 0; iCarte < cartesCavalier.size(); iCarte++) {
					if(cartesPrioritaires.size() < 3 && cartesCavalier.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire())) {
						cartesPrioritaires.add(cartesCavalier.get(iCarte));
						cartesCavalier.remove(cartesCavalier.get(iCarte));
						break;
					}
				}
				// On arrête de parcourir la boucle car on n'a besoin que d'une carte
				if(cartesPrioritaires.size() == 3) {
					break;
				}
			}
			// Si aucune carte avec un territoire possédé par le joueur n'a été trouvé, on prend une carte au hasard (par exemple, la première de la liste)
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
//		System.out.println(cartesCavalier.size());
//		System.out.println(cartesSoldat.size());
//		System.out.println(cartesArtillerie.size());
	}
	
	// Métode pour ajouter des cartes avec le même type de régiment dans un combo de cartes
	public static ArrayList<ArrayList<Carte_Territoire>> ajouterCartesMemeTypeRegiment(ArrayList<Territoire> territoiresJoueur, ArrayList<Carte_Territoire> cartesMemeRegiment, ArrayList<ArrayList<Carte_Territoire>> combosCartes) {
		ArrayList<Carte_Territoire> cartesPrioritaires= new ArrayList<Carte_Territoire>();
		while(cartesMemeRegiment.size() >= 3) {
			for(Territoire t : territoiresJoueur) {
				for(int iCarte = 0; iCarte < cartesMemeRegiment.size(); iCarte++) {
					if(cartesMemeRegiment.get(iCarte).getTerritoire().getNomTerritoire().equals(t.getNomTerritoire()) && cartesPrioritaires.size() < 3) {
						cartesPrioritaires.add(cartesMemeRegiment.get(iCarte));
						cartesMemeRegiment.remove(cartesMemeRegiment.get(iCarte));
					}
				}
			}
			// Si on a 3 cartes dans le combo, on ajoute ces trois dernières dans la liste globale des combos de cartes
			// Et on remet à 0 la liste de combos pour donner place à d'autres combos
			if(cartesPrioritaires.size() == 3) {
				combosCartes.add(cartesPrioritaires);
				cartesPrioritaires = new ArrayList<Carte_Territoire>();
			}
			// Si on a encore de la place dans les combos de cartes, et s'il y a des cartes du même type de régiment, on ajoute des cartes dans ce combo
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
	
	public static void main(String[] args) {
		Joueur jr = creerJoueur();
		AfficherComboCartes(jr);
	}

}
