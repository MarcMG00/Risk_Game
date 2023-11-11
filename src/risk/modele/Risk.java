package risk.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Risk {
    private int numTour;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte_Territoire> cartes;
	private ArrayList<Continent> continents;
	private ArrayList<Territoire> territoires;
	private ArrayList<Territoire> territoiresChoisits;
	
	public Risk() {
		this.joueurs = new ArrayList<Joueur>();
		this.cartes = new ArrayList<Carte_Territoire>();
		this.continents = new ArrayList<Continent>();
		this.territoires = new ArrayList<Territoire>();
		this.territoiresChoisits = new ArrayList<Territoire>();
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public ArrayList<Carte_Territoire> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte_Territoire> cartes) {
		this.cartes = cartes;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public ArrayList<Territoire> getTerritoires() {
		return territoires;
	}
	
	public void setTerritoires(ArrayList<Territoire> territoires) {
		this.territoires = territoires;
	}

	public ArrayList<Territoire> getTerritoiresChoisits() {
		return territoiresChoisits;
	}

	public void setTerritoiresChoisits(ArrayList<Territoire> territoiresChoisits) {
		this.territoiresChoisits = territoiresChoisits;
	}

	// Lecture du fichier Territoires.txt pour r�cup�rer tous les territoires
	public ArrayList<Territoire> lectureTerritoires(String fileName) {
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] territoires = ligne.split(";");
				// Probl�mes de duplications, j'ai mis ainsi un break (il y a un max de 42 territoires)
				if(this.territoires.size() == 42) {
					break;
				}
				else {
					this.territoires.add(new Territoire(Integer.parseInt(territoires[0]), territoires[1]));
				}
			}
		}
	    catch (IOException e) {
			System.out.println("Exception � la lecture du fichier : " + e.getMessage());
		}
	    finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Exception survenue � la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.territoires;
	}
	
	// Lecture du fichier CartesTerritoires.txt pour r�cup�rer toutes les cartes
	public ArrayList<Carte_Territoire> lectureCartesTerritoire(String fileName) {
//		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
		
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] cartesTerritoire = ligne.split(";");
				for(int iTerrit = 0; iTerrit < this.territoires.size(); iTerrit++) {
					// On transforme les string en objets Territoire, si le nom est trouv�, alors on ajoute le territoire � sa carte correspondante
					if(this.territoires.get(iTerrit).getNomTerritoire().equals(cartesTerritoire[1])) {
						this.cartes.add(new Carte_Territoire(Integer.parseInt(cartesTerritoire[0]), this.territoires.get(iTerrit), cartesTerritoire[2]));
						// Probl�mes de duplications, j'ai mis ainsi un break
						break;
					}
				}
			}
		}
	    catch (IOException e) {
			System.out.println("Exception � la lecture du fichier : " + e.getMessage());
		}
	    finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Exception survenue � la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.cartes;
	}
	
	// Lecture du fichier Continents.txt pour r�cup�rer tous les continents
	public ArrayList<Continent> lectureContinents(String fileName) {
//		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
		ArrayList<String> territoiresContinentAReinitialiserStr = new ArrayList<String>();
		ArrayList<Territoire> territoiresContinentAReinitialiserT = new ArrayList<Territoire>();
		
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] continentL = ligne.split(";");
				// On prend tous les territoires de la ligne � compter de la position 3 du fichier
				for(int iLStr = 3; iLStr < continentL.length; iLStr++) {
					territoiresContinentAReinitialiserStr.add(continentL[iLStr]);
				}
				// On transforme les string en objets Territoire, si le nom est trouv�, alors on ajoute le territoire dans une liste
				for(int iTerrit = 0; iTerrit < this.territoires.size(); iTerrit++) {
					if(territoiresContinentAReinitialiserStr.contains(this.territoires.get(iTerrit).getNomTerritoire())) {
						territoiresContinentAReinitialiserT.add(this.territoires.get(iTerrit));
					}
				}
				// On ajoute le continent dans la liste, avec ses donn�es correspondantes
				this.continents.add(new Continent(Integer.parseInt(continentL[0]), continentL[1], Integer.parseInt(continentL[2]), territoiresContinentAReinitialiserT));
				// On r�initialise les variables pour le prochain continent
				territoiresContinentAReinitialiserStr = new ArrayList<String>();
				territoiresContinentAReinitialiserT = new ArrayList<Territoire>();
			}
		}
	    catch (IOException e) {
			System.out.println("Exception � la lecture du fichier : " + e.getMessage());
		}
	    finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Exception survenue � la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.continents;
	}
	
	// Lecture du fichier TerritoiresAdjacents.txt pour r�cup�rer tous les territoires adjacents de chaque territoire
	public void lectureTerritoiresAdjacents(String fileName) {
//		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
		ArrayList<Territoire> territoiresAReinitialiserT = new ArrayList<Territoire>();
		ArrayList<String> territoiresAReinitialiserStr = new ArrayList<String>();
		
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] territoiresAdjacents = ligne.split(";");
				// On ajoute les territoires (string) d'une ligne � compter de la position 1
				for(int i = 1; i < territoiresAdjacents.length; i++) {
					territoiresAReinitialiserStr.add(territoiresAdjacents[i]);
				}
				// On trasnforme les string en objets Territoire
				for(int iTerrit = 0; iTerrit < this.territoires.size(); iTerrit++) {
					if(territoiresAReinitialiserStr.contains(this.territoires.get(iTerrit).getNomTerritoire())) {
						territoiresAReinitialiserT.add(this.territoires.get(iTerrit));
					}
				}
				// Un objet Territoire_Adjacent correspond � une liste de Territoire
				Territoire_Adjacent territAdjAReinitialiser = new Territoire_Adjacent(territoiresAReinitialiserT);
				// A chaque territoire on lui ajoute le Territoire_Adjacent. Le territoire en question,se trouve � la position 0 du fichier
				for(int iTerrit2 = 0; iTerrit2 < this.territoires.size(); iTerrit2++) {
					if(this.territoires.get(iTerrit2).getNomTerritoire().equals(territoiresAdjacents[0])) {
						this.territoires.get(iTerrit2).setTerritoiresAdjacents(territAdjAReinitialiser);
					}
				}
				// On r�initialise les variables pour le prochain territoire
				territoiresAReinitialiserT = new ArrayList<Territoire>();
				territoiresAReinitialiserStr = new ArrayList<String>();
			}
		}
	    catch (IOException e) {
			System.out.println("Exception � la lecture du fichier : " + e.getMessage());
		}
	    finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Exception survenue � la fermeture du fichier : " + e.getMessage());
			}
		}
	}
	
	// M�thodes du jeu
	// Affiche les territoires adjacents qui ne sont pas poss�d�s par le joueur, des territoires poss�d�s par ce joueur
	public ArrayList<Territoire> afficherTerritoiresEnnemis(Joueur joueur) {
		
		ArrayList<Territoire> territoireAdjacents = new ArrayList<Territoire>();
		
		// Obtention de tous les territoires
		for(Territoire territ : joueur.getTerritoires()){
			for (Territoire tAdj : territ.getTerritoiresAdjacents().getTerritoiresAdjacents()) {
				if(!territoireAdjacents.contains(tAdj)) {
					territoireAdjacents.add(tAdj);
				}
			}
		}
		
		// On enl�ve les territoires adjacents poss�d�s par le joueur
		for (int i = 0; i < territoireAdjacents.size(); i++) {
			if (joueur.getTerritoires().contains(territoireAdjacents.get(i))) {
				territoireAdjacents.remove(i);
			}
		}
//		System.out.println("Liste des territoires que " + joueur.getNom() +  " peut attaquer : " + TerritoireAdjacents);
		for(Territoire tAttaquer : territoireAdjacents) {
			System.out.println(tAttaquer.getNumTerritoire() + " - " + tAttaquer.getNomTerritoire() + ", poss�de " + tAttaquer.getRegiments() + " r�giments");
		}
		
		return territoireAdjacents;
	}
	
	// Affiche les territoires poss�d�s par le joueur pour attaquer le territoire souhait�
	public ArrayList<Territoire> verifierAdjacenceTerritoireEnnemi(ArrayList<Territoire> territs, Joueur joueur, int numTerritoireAattaquer) {
		ArrayList<Territoire> appartenanceTerritoiresAdj = new ArrayList<Territoire>();
//		this.territoires = lectureTerritoires("data/TerritoiresPetitExemple.txt");
//		this.lectureTerritoiresAdjacents("data/TerritoiresAdjacentsPetitExemple.txt");
		
		for(Territoire t : territs) {
			// On ajoute que les territoires adjacents poss�d�s par le joueur et avec un nombre de r�giments sup�rieur � 1
			if(t.getNumTerritoire() == numTerritoireAattaquer) {
				for(Territoire tAdj : t.getTerritoiresAdjacents().getTerritoiresAdjacents()) {
					if(joueur.getTerritoires().contains(tAdj) && tAdj.getRegiments() > 1) {
						if(!appartenanceTerritoiresAdj.contains(tAdj)) {
							appartenanceTerritoiresAdj.add(tAdj);
						}
					}
				}
			}
//			break;
		}
		System.out.println("Territoires adjacents au territoire (" +  numTerritoireAattaquer + ") pour l'attaquer :");
		for(Territoire t : appartenanceTerritoiresAdj) {
			System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " avec un nombre de " + t.getRegiments() + " r�giments");
		}
		
		return appartenanceTerritoiresAdj;
	}
	
	// Re�oit des r�giments suppl�mentaires en fonction des territoires et continents poss�d�s
	public void recevoirRegimentsSupplementairesDebutTour(Joueur joueur) {
		if(joueur.getTerritoires().size() < 9) {
			joueur.setNbRegimentsRecusParTour(3);
		}
		else {
			// Nombre de r�giments en fonction du nombre de territoires
			joueur.setNbRegimentsRecusParTour(joueur.getTerritoires().size() / 3);
			
			// Nombre de r�giments suppl�mentaaires en fonction de la possession de contients
			for(Continent c : joueur.getContinents()) {
				if(c.verifierPossessionTousTerritoires(joueur)) {
					joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() + c.getValeurBonus());
				}
			}
		}
	}
	
	public boolean verificationRegimentsParTerritoire(int numTerritoire, int nbRegiments) {
		boolean regimentsCorrects = true;
		
		if(nbRegiments == 0) {
			System.out.println("Saisissez une valeur positive, diff�rente de 0");
			regimentsCorrects = false;
		}
		else {
			for(Territoire t : this.territoires) {
				if(t.getNumTerritoire() == numTerritoire) {
					if(t.getRegiments() < nbRegiments) {
						System.out.println("Vous ne poss�dez pas autant de r�giments");
						regimentsCorrects = false;
					}
					else if(t.getRegiments() == 3 && nbRegiments == 3 || t.getRegiments() == 2 && nbRegiments == 2) {
						System.out.println("Vous devez laisser au moins un r�giment sur votre territoire");
						regimentsCorrects = false;
					}
					else {
						regimentsCorrects = true;
					}
				}
			}
		}
		
		return regimentsCorrects;
	}
	
	
	public void lancerPartie() {
		// V�rification des donn�es de la lecture des fichiers
//		this.territoires = lectureTerritoires("data/TerritoiresPetitExemple.txt");
//		this.cartes = lectureCartesTerritoire("data/CartesTerritoires.txt");
//		this.continents = lectureContinents("data/Continents.txt");
//		lectureTerritoiresAdjacents("data/TerritoiresAdjacents.txt");
//		
//		for(Territoire t : this.territoires) {
//			System.out.println(t.getNumTerritoire() + " " + t.getNomTerritoire());
//		}
//		System.out.println();
//		
//		for(Carte_Territoire ct : this.cartes) {
//			System.out.println(ct.getNumCarte() + " " + ct.getTerritoire().getNomTerritoire() + " " + ct.getTypeRegiment());
//		}
//		System.out.println();
//		
//		for(Continent co : this.continents) {
//			System.out.println(co.getNumContinent() + " " + co.getNomContinent()  + " " + co.getValeurBonus() + " " + co.getTerritoires());
//		}
//		System.out.println();
//		
//		for(Territoire t2 : this.territoires) {
//			for(Territoire ta : t2.getTerritoiresAdjacents().getTerritoiresAdjacents()) {
//				System.out.println(t2.getNomTerritoire() + " " + ta.getNomTerritoire());
//			}
//		}
//		System.out.println();
//		
//		for(int i = 0; i < this.joueurs.size(); i++) {
//			System.out.println(this.joueurs.get(i).getNom());
//		}

		// START GAME
		// Initialisation des listes du jeu
		this.territoires = lectureTerritoires("data/TerritoiresPetitExemple.txt");
		this.cartes = lectureCartesTerritoire("data/CartesTerritoiresPetitExemple.txt");
		this.continents = lectureContinents("data/ContinentsPetitExemple.txt");
		this.lectureTerritoiresAdjacents("data/TerritoiresAdjacentsPetitExemple.txt");
		
		// Position al�atoire des joueurs, pour �tablir l'ordre du jeu / m�lange des cartes
		Collections.shuffle(this.joueurs);
		Collections.shuffle(this.cartes);
		
		// Choix des diff�rents territoires, commen�ant par le premier joueur
		// Tous les territoires doivent �tre pris
		while (this.territoires.size() != 0) {
			// On passe � tour de r�le pour chaque joueur
			for(Joueur joueur : this.joueurs) {
				// On affiche les territoires qui sont disponibles
				for(Territoire territoire : this.territoires) {
					System.out.println(territoire.getNumTerritoire() + " - " + territoire.getNomTerritoire());
				}
				
				System.out.println(joueur.getNom() + " choisit un territoire parmis ceux propos�s ci-dessus. Ecrit seulement le num�ro. S'il n'y a plus de territoires, �crit al�atoirement un num�ro (rien ne se produira)");
				Scanner sc = new Scanner(System.in);
				int numTerritoire = sc.nextInt();
				sc.useDelimiter(";|\r?\n|\r");
				
				for(Territoire tChoisit : this.territoires) {
					if(tChoisit.getNumTerritoire() == numTerritoire) {
						// On attribue le joueur au territoire choisit
						tChoisit.setJoueurPossedantTerritoire(joueur);
						joueur.ajouterTerritoire(tChoisit);
						
						// On enl�ve le territoire choisi de la liste et on le met dans une nouvelle liste
						this.territoires.remove(tChoisit);
						this.territoiresChoisits.add(tChoisit);
						// On arr�te la boucle pour arr�ter de parcourir la liste, car on la modifie entre temps (on enl�ve des territoires)
						break;
					}
				}
			}
		}
		
		// R�capitulatif de la possession des territoires
		System.out.println("VOICI LA LISTE DES TERRITOIRES DES JOUEURS : ");
		for(Territoire t : this.territoiresChoisits) {
			System.out.println(t.getNomTerritoire() + " est poss�d� par le joueur " + t.getJoueurPossedantTerritoire().getNom());
		}
		
		System.out.println();
		System.out.println("Le choix des territoires a �t� fait. Proc�dons � l'emplacement des r�giments");
		
		// Affectation des r�giments au premier tour (avant de commencer r�ellement le jeu)
		for(Joueur j : this.joueurs) {
			// Stock des num�ros de territoires poss�d�s par le joueur pour un contr�le
			ArrayList<Integer> numTerritoires = new ArrayList<Integer>();
			// Pour chaque joueur, on lui demande de placer les r�giments du d�but du jeu (voir AppRisk.java)
			while(j.getNbRegimentsRecusParTour() > 0) {
				for(Territoire t : j.getTerritoires()) {
					// Tous les territoires doivent avoir au moins 1 r�giment. Donc, on place 1 automatiquement
					if(t.getRegiments() == 0) {
						t.setRegiments(1);
						j.setNbRegimentsRecusParTour(j.getNbRegimentsRecusParTour()-1);
					}
					if(!numTerritoires.contains(t.getNumTerritoire())) {
						numTerritoires.add(t.getNumTerritoire());
					}
					System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " - nombre de r�giments : " + t.getRegiments());
				}
				
				System.out.println(j.getNom() + " il vous manque " + j.getNbRegimentsRecusParTour() + " r�giments � placer");
				System.out.println(j.getNom() + " choisit un territoire dans lequel placer des r�giments (�crit le num�ro du territoire) :");
				Scanner sc = new Scanner(System.in);
				int numTerritoire = sc.nextInt();
				
				// Si le num�ro du territoire n'est pas poss�d� par le joueur, on lui demande � nouveau de choisir un num�ro de territoire
				if(!numTerritoires.contains(numTerritoire)) {
					while(!numTerritoires.contains(numTerritoire)) {
						System.out.println("Choisissez le num�ro d'un territoire que vous poss�dez :");
						numTerritoire = sc.nextInt();
						sc.useDelimiter(";|\r?\n|\r");
					}
				}
				
				System.out.println(j.getNom() + " choisit le nombre de r�giments � placer sur ce territoire :");
				int nbRegiments = sc.nextInt();
				
				// Si le nombre de r�giments n'est pas le correct, on lui demande � nouveau de saisir une valeur valide
				if(nbRegiments > j.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
					while(nbRegiments > j.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
						System.out.println("Vous ne poss�dez pas autant de r�giments OU la valeur ne peut pas �tre inf�rieure ou �gale � 0. Saisissez une valeur correcte :");
						nbRegiments = sc.nextInt();
						sc.useDelimiter(";|\r?\n|\r");
					}
				}
				
				for(Territoire t : j.getTerritoires()) {
					// On attribue le nombre de r�giments dans le territoires choisi, puis on enl�ve les r�giments plac�s au joueur en question
					if(t.getNumTerritoire() == numTerritoire) {
						t.setRegiments(t.getRegiments() + nbRegiments);
						j.setNbRegimentsRecusParTour(j.getNbRegimentsRecusParTour() - nbRegiments);
						System.out.println(j.getNom() + " a plac� " + nbRegiments + " r�giments sur le territoire : " + t.getNomTerritoire());
						System.out.println();
					}
				}
			}
			
			// R�capitulatif des choix du joueur
			System.out.println("Voici le nombre de r�giments par territoire que vous poss�dez : ");
			for(Territoire t : j.getTerritoires()) {
				System.out.println(t.getNomTerritoire() + " avec " + t.getRegiments() + " r�giments");
			}
			System.out.println();
		}
		
		System.out.println("Vous avez fini de placer les r�giments de d�part. Proc�dons au jeu JEJEJE");
		
		// D�but du jeu : un jeu ne fini que lorsqu'il y a un joueur dans la liste de joueurs
		while(this.joueurs.size() > 1) {
			Scanner sc = new Scanner(System.in);
			
		    for (int j = 0; j < this.joueurs.size(); j++) {
		    	
		        //R�giments de d�but du tour
		    	recevoirRegimentsSupplementairesDebutTour(this.joueurs.get(j));
		    	
				// Stock des num�ros de territoires poss�d�s par le joueur
				ArrayList<Integer> numTerritoires = new ArrayList<Integer>();
		    	while(this.joueurs.get(j).getNbRegimentsRecusParTour() > 0) {
					for(Territoire t : this.joueurs.get(j).getTerritoires()) {
						// Tous les territoires doivent avoir au moins 1 r�giment. Donc, on place 1 automatiquement
						if(t.getRegiments() == 0) {
							t.setRegiments(1);
							this.joueurs.get(j).setNbRegimentsRecusParTour(this.joueurs.get(j).getNbRegimentsRecusParTour()-1);
						}
						if(!numTerritoires.contains(t.getNumTerritoire())) {
							numTerritoires.add(t.getNumTerritoire());
						}
						System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " - nombre de r�giments : " + t.getRegiments());
					}
					
					System.out.println(this.joueurs.get(j).getNom() + " il vous manque " + this.joueurs.get(j).getNbRegimentsRecusParTour() + " r�giments � placer");
					System.out.println(this.joueurs.get(j).getNom() + " choisit un territoire dans lequel placer des r�giments (�crit le num�ro du territoire) :");
					int numTerritoire = sc.nextInt();
					
					// Si le num�ro du territoire n'est pas poss�d� par le joueur, on lui demande � nouveau de choisir un num�ro de territoire
					if(!numTerritoires.contains(numTerritoire)) {
						while(!numTerritoires.contains(numTerritoire)) {
							System.out.println("Choisissez le num�ro d'un territoire que vous poss�dez :");
							numTerritoire = sc.nextInt();
							sc.useDelimiter(";|\r?\n|\r");
						}
					}
					
					System.out.println(this.joueurs.get(j).getNom() + " choisit le nombre de r�giments � placer sur ce territoire :");
					int nbRegiments = sc.nextInt();
					
					// Si le nombre de r�giments n'est pas le correct, on lui demande � nouveau de saisir une valeur valide
					if(nbRegiments > this.joueurs.get(j).getNbRegimentsRecusParTour() || nbRegiments <= 0) {
						while(nbRegiments > this.joueurs.get(j).getNbRegimentsRecusParTour() || nbRegiments <= 0) {
							System.out.println("Vous ne poss�dez pas autant de r�giments OU la valeur ne peut pas �tre inf�rieure ou �gale � 0. Saisissez une valeur correcte :");
							nbRegiments = sc.nextInt();
							sc.useDelimiter(";|\r?\n|\r");
						}
					}
					
					for(Territoire t : this.joueurs.get(j).getTerritoires()) {
						// On attribue le nombre de r�giments dans le territoires choisi, puis on enl�ve les r�giments plac�s au joueur en question
						if(t.getNumTerritoire() == numTerritoire) {
							t.setRegiments(t.getRegiments() + nbRegiments);
							this.joueurs.get(j).setNbRegimentsRecusParTour(this.joueurs.get(j).getNbRegimentsRecusParTour() - nbRegiments);
							System.out.println(this.joueurs.get(j).getNom() + " a plac� " + nbRegiments + " r�giments sur le territoire : " + t.getNomTerritoire());
							System.out.println();
						}
					}
				}
		    	// R�capitulatif des choix du joueur
				System.out.println("Voici le nombre de r�giments par territoire que vous poss�dez : ");
				for(Territoire t : this.joueurs.get(j).getTerritoires()) {
					System.out.println(t.getNomTerritoire() + " avec " + t.getRegiments() + " r�giments");
				}
				System.out.println();
				
				// D�but attaque
		    	boolean attaquer = true;
		    	
		    	while(attaquer == true) {
					System.out.println(this.joueurs.get(j).getNom() + " - Voulez-vous attaquer un territoire : (1 (oui), 2 (non))");
					int attaquerYN = sc.nextInt();
					sc.useDelimiter(";|\r?\n|\r");
					
					if(attaquerYN == 1) {
						System.out.println(this.joueurs.get(j).getNom() + " voici la liste des territoires que vous pouvez attaquer : ");
						// On affiche la liste des territoires qu'un joueur peut attaquer
						ArrayList<Territoire> territoiresAdj = this.afficherTerritoiresEnnemis(this.joueurs.get(j));
						ArrayList<Integer> numTerritoiresAtq = new ArrayList<Integer>();
						
						// On ajoute les num�ros des territoires ennemis dans une liste
						for(Territoire t : territoiresAdj) {
							numTerritoiresAtq.add(t.getNumTerritoire());
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez un num�ro de territoire auquel attaquer : ");
						int numTerritAttaquer = sc.nextInt();
						
						// Si le num�ro saisi ne correspond pas � un des territoires propos�s, il redemande de saisir une valeur valide
						if(!numTerritoiresAtq.contains(numTerritAttaquer)) {
							while(!numTerritoiresAtq.contains(numTerritAttaquer)) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez le num�ro d'un territoire que vous pouvez attaquer :");
								numTerritAttaquer = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						// On affiche les territoires adjacents que le joueur poss�de pour attaquer le territoire saisi auparavant
						ArrayList<Territoire> appartenanceTerritoiresAdj =  this.verifierAdjacenceTerritoireEnnemi(this.territoiresChoisits, this.joueurs.get(j), numTerritAttaquer);
						ArrayList<Integer> numTerritoiresAttaquant = new ArrayList<Integer>();
						
						// On ajoute les num�ros des territoires adjacents du joueur dans une liste
						for(Territoire t : appartenanceTerritoiresAdj) {
							numTerritoiresAttaquant.add(t.getNumTerritoire());
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez un num�ro de territoire qui vous appartient (parmis ceux propos�s ci-dessus) :");
						int numTerritAttaquant = sc.nextInt();

						// Si le num�ro saisi ne correspond pas � un des territoires propos�s, il redemande de saisir une valeur valide
						if(!numTerritoiresAttaquant.contains(numTerritAttaquant)) {
							while(!numTerritoiresAttaquant.contains(numTerritAttaquant)) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez le num�ro d'un territoire que vous poss�dez :");
								numTerritAttaquant = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez le nombre de r�giments pour attaquer (Min 1, Max 3), en fonction du nombre de r�giments :");
						int nbRegimentsAtq = sc.nextInt();
						
						// V�rifie que le nombre de r�giments soit valide pour l'attaque
						boolean regimentsCorrects = this.verificationRegimentsParTerritoire(numTerritAttaquant, nbRegimentsAtq);
						if(regimentsCorrects == false) {
							while(regimentsCorrects == false) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez un nombre de r�giments disponible par rapport � votre territoire :");
								nbRegimentsAtq = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						this.joueurs.get(j).attaquer(numTerritAttaquant, numTerritAttaquer, nbRegimentsAtq);
					
					}
					else {
						attaquer = false;
					}
		    	}
		    }
		}
	}
	
}
