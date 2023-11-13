package risk.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import risk.model.action.Attaquer;
import risk.model.action.DeplacerRegiment;


public class Risk {
    private int numTour;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte_Territoire> cartes;
	private ArrayList<Continent> continents;
	private ArrayList<Territoire> territoires;
	// Liste qui remplace les territoires (de dessus), lors des choix des territoires au début du jeu
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

	// Lecture du fichier Territoires.txt pour récupérer tous les territoires
	public ArrayList<Territoire> lectureTerritoires(String fileName) {
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] territoires = ligne.split(";");
				// Problèmes de duplications, j'ai mis ainsi un break (il y a un max de 42 territoires)
				if(this.territoires.size() == 42) {
					break;
				}
				else {
					this.territoires.add(new Territoire(Integer.parseInt(territoires[0]), territoires[1]));
				}
			}
		}
	    catch (IOException e) {
			System.out.println("Exception ï¿½ la lecture du fichier : " + e.getMessage());
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
				System.out.println("Exception survenue ï¿½ la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.territoires;
	}
	
	// Lecture du fichier CartesTerritoires.txt pour récupérer toutes les cartes
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
					// On transforme les string en objets Territoire, si le nom est trouvé, alors on ajoute le territoire à sa carte correspondante
					if(this.territoires.get(iTerrit).getNomTerritoire().equals(cartesTerritoire[1])) {
						this.cartes.add(new Carte_Territoire(Integer.parseInt(cartesTerritoire[0]), this.territoires.get(iTerrit), cartesTerritoire[2]));
						// Problèmes de duplications, j'ai mis ainsi un break
						break;
					}
				}
			}
		}
	    catch (IOException e) {
			System.out.println("Exception ï¿½ la lecture du fichier : " + e.getMessage());
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
				System.out.println("Exception survenue ï¿½ la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.cartes;
	}
	
	// Lecture du fichier Continents.txt pour récupérer tous les continents
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
				// On prend tous les territoires de la ligne à compter de la position 3 du fichier
				for(int iLStr = 3; iLStr < continentL.length; iLStr++) {
					territoiresContinentAReinitialiserStr.add(continentL[iLStr]);
				}
				// On transforme les string en objets Territoire, si le nom est trouvé, alors on ajoute le territoire dans une liste
				for(int iTerrit = 0; iTerrit < this.territoires.size(); iTerrit++) {
					if(territoiresContinentAReinitialiserStr.contains(this.territoires.get(iTerrit).getNomTerritoire())) {
						territoiresContinentAReinitialiserT.add(this.territoires.get(iTerrit));
					}
				}
				// On ajoute le continent dans la liste, avec ses données correspondantes
				this.continents.add(new Continent(Integer.parseInt(continentL[0]), continentL[1], Integer.parseInt(continentL[2]), territoiresContinentAReinitialiserT));
				// On réinitialise les variables pour le prochain continent
				territoiresContinentAReinitialiserStr = new ArrayList<String>();
				territoiresContinentAReinitialiserT = new ArrayList<Territoire>();
			}
		}
	    catch (IOException e) {
			System.out.println("Exception ï¿½ la lecture du fichier : " + e.getMessage());
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
				System.out.println("Exception survenue ï¿½ la fermeture du fichier : " + e.getMessage());
			}
		}
		return this.continents;
	}
	
	// Lecture du fichier TerritoiresAdjacents.txt pour récupérer tous les territoires adjacents de chaque territoire
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
				// On ajoute les territoires (string) d'une ligne à compter de la position 1
				for(int i = 1; i < territoiresAdjacents.length; i++) {
					territoiresAReinitialiserStr.add(territoiresAdjacents[i]);
				}
				// On trasnforme les string en objets Territoire
				for(int iTerrit = 0; iTerrit < this.territoires.size(); iTerrit++) {
					if(territoiresAReinitialiserStr.contains(this.territoires.get(iTerrit).getNomTerritoire())) {
						territoiresAReinitialiserT.add(this.territoires.get(iTerrit));
					}
				}
				// Un objet Territoire_Adjacent correspond à une liste de Territoire
				Territoire_Adjacent territAdjAReinitialiser = new Territoire_Adjacent(territoiresAReinitialiserT);
				// A chaque territoire on lui ajoute le Territoire_Adjacent. Le territoire en question,se trouve à la position 0 du fichier
				for(int iTerrit2 = 0; iTerrit2 < this.territoires.size(); iTerrit2++) {
					if(this.territoires.get(iTerrit2).getNomTerritoire().equals(territoiresAdjacents[0])) {
						this.territoires.get(iTerrit2).setTerritoiresAdjacents(territAdjAReinitialiser);
					}
				}
				// On réinitialise les variables pour le prochain territoire
				territoiresAReinitialiserT = new ArrayList<Territoire>();
				territoiresAReinitialiserStr = new ArrayList<String>();
			}
		}
	    catch (IOException e) {
			System.out.println("Exception ï¿½ la lecture du fichier : " + e.getMessage());
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
				System.out.println("Exception survenue ï¿½ la fermeture du fichier : " + e.getMessage());
			}
		}
	}
	
	// METHODES DU JEU
	
	// Affiche les territoires adjacents qui ne sont pas possédés par le joueur, des territoires possédés par ce joueur
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
		
		// On enlève les territoires adjacents possédés par le joueur
		for (int i = 0; i < territoireAdjacents.size(); i++) {
			if (joueur.getTerritoires().contains(territoireAdjacents.get(i))) {
				// enlève tous les dupliqués car un territoire adjacent peut apparaître dans plusieurs territoires
				while(joueur.getTerritoires().contains(territoireAdjacents.get(i))) {
					territoireAdjacents.remove(i);
				}
			}
		}
		
		for(Territoire tAttaquer : territoireAdjacents) {
			System.out.println(tAttaquer.getNumTerritoire() + " - " + tAttaquer.getNomTerritoire() + ", possède " + tAttaquer.getRegiments() + " régiments");
		}
		
		return territoireAdjacents;
	}
	
	// Affiche les territoires possédés par le joueur pour attaquer le territoire souhaité
	public ArrayList<Territoire> verifierAdjacenceTerritoireEnnemi(Joueur joueur, int numTerritoireAattaquer) {
		ArrayList<Territoire> appartenanceTerritoiresAdj = new ArrayList<Territoire>();
		
		for(Territoire t : this.territoiresChoisits) {
			// On ajoute que les territoires adjacents possédés par le joueur et avec un nombre de régiments supérieur à 1
			if(t.getNumTerritoire() == numTerritoireAattaquer) {
				for(Territoire tAdj : t.getTerritoiresAdjacents().getTerritoiresAdjacents()) {
					if(joueur.getTerritoires().contains(tAdj) && tAdj.getRegiments() > 1) {
						if(!appartenanceTerritoiresAdj.contains(tAdj)) {
							appartenanceTerritoiresAdj.add(tAdj);
						}
					}
				}
			}
		}
		System.out.println("Territoires adjacents au territoire (" +  numTerritoireAattaquer + ") pour l'attaquer :");
		for(Territoire t : appartenanceTerritoiresAdj) {
			System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " avec un nombre de " + t.getRegiments() + " régiments");
		}
		
		return appartenanceTerritoiresAdj;
	}
	
	// Reçoit des régiments supplémentaires en fonction des territoires et continents possédés
	public void recevoirRegimentsSupplementairesDebutTour(Joueur joueur) {
		if(joueur.getTerritoires().size() < 9) {
			joueur.setNbRegimentsRecusParTour(3);
		}
		else {
			// Nombre de régiments en fonction du nombre de territoires
			joueur.setNbRegimentsRecusParTour(joueur.getTerritoires().size() / 3);
			
			// Nombre de régiments supplémentaaires en fonction de la possession de contients
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
			System.out.println("Saisissez une valeur positive, différente de 0");
			regimentsCorrects = false;
		}
		else {
			for(Territoire t : this.territoiresChoisits) {
				if(t.getNumTerritoire() == numTerritoire) {
					if(t.getRegiments() < nbRegiments) {
						System.out.println("Vous ne possédez pas autant de régiments");
						regimentsCorrects = false;
					}
					else if(t.getRegiments() == 3 && nbRegiments == 3 || t.getRegiments() == 2 && nbRegiments == 2) {
						System.out.println("Vous devez laisser au moins un régiment sur votre territoire");
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
	

	// DEBUT DU JEU
	public void lancerPartie() {

		// Initialisation des listes du jeu
		this.territoires = lectureTerritoires("data/TerritoiresPetitExemple.txt");
		this.cartes = lectureCartesTerritoire("data/CartesTerritoiresPetitExemple.txt");
		this.continents = lectureContinents("data/ContinentsPetitExemple.txt");
		this.lectureTerritoiresAdjacents("data/TerritoiresAdjacentsPetitExemple.txt");
		
		// Position aléatoire des joueurs, pour établir l'ordre du jeu / mélange des cartes
		Collections.shuffle(this.joueurs);
		Collections.shuffle(this.cartes);
		
		// Choix des différents territoires, commençant par le premier joueur
		// Tous les territoires doivent être pris
		while (!this.territoires.isEmpty()) {
			// On passe à tour de rôle pour chaque joueur
			for(Joueur joueur : this.joueurs) {
				if(!this.territoires.isEmpty()) {
					// On affiche les territoires qui sont disponibles
					for(Territoire territoire : this.territoires) {
						System.out.println(territoire.getNumTerritoire() + " - " + territoire.getNomTerritoire());
					}
					
					System.out.println(joueur.getNom() + " choisit un territoire parmis ceux proposés ci-dessus. Ecrit seulement le numéro");
					Scanner sc = new Scanner(System.in);
					int numTerritoire = sc.nextInt();
					sc.useDelimiter(";|\r?\n|\r");
					
					for(Territoire tChoisit : this.territoires) {
						if(tChoisit.getNumTerritoire() == numTerritoire) {
							// On attribue le joueur au territoire choisit
							tChoisit.setJoueurPossedantTerritoire(joueur);
							joueur.ajouterTerritoire(tChoisit);
							
							// On enlève le territoire choisi de la liste et on le met dans une nouvelle liste
							this.territoires.remove(tChoisit);
							this.territoiresChoisits.add(tChoisit);
							// On arrête la boucle pour arrêter de parcourir la liste, car on la modifie entre temps (on enlève des territoires)
							break;
						}
					}
					
				}
				else {
					System.out.println("Il n'y a plus de territoires à choisir");
					System.out.println();
				}
			}
		}
		
		// Récapitulatif de la possession des territoires
		System.out.println("VOICI LA LISTE DES TERRITOIRES DES JOUEURS : ");
		for(Territoire t : this.territoiresChoisits) {
			System.out.println(t.getNomTerritoire() + " est possédé par le joueur " + t.getJoueurPossedantTerritoire().getNom());
		}
		
		System.out.println();
		System.out.println("Le choix des territoires a été fait. Procédons à l'emplacement des régiments");
		
		// Affectation des régiments au premier tour (avant de commencer réellement le jeu)
		for(Joueur j : this.joueurs) {
			this.placerRegiments(1, j);
		}
		
		System.out.println("Vous avez fini de placer les régiments de départ. Procédons au jeu JEJEJE");
		
		// Début du jeu : un jeu ne fini que lorsqu'il y a un joueur dans la liste de joueurs
		while(this.joueurs.size() > 1) {
			Scanner sc = new Scanner(System.in);
			
		    for (int j = 0; j < this.joueurs.size(); j++) {
		    
		    	this.placerRegiments(2, this.joueurs.get(j));
				
				// Début attaque
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
						
						// On ajoute les numéros des territoires ennemis dans une liste
						for(Territoire t : territoiresAdj) {
							numTerritoiresAtq.add(t.getNumTerritoire());
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez un numéro de territoire auquel attaquer : ");
						int numTerritAttaquer = sc.nextInt();
						
						// Si le numéro saisi ne correspond pas à un des territoires proposés, il redemande de saisir une valeur valide
						if(!numTerritoiresAtq.contains(numTerritAttaquer)) {
							while(!numTerritoiresAtq.contains(numTerritAttaquer)) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez le numéro d'un territoire que vous pouvez attaquer :");
								numTerritAttaquer = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						// On affiche les territoires adjacents que le joueur possède pour attaquer le territoire saisi auparavant
						ArrayList<Territoire> appartenanceTerritoiresAdj =  this.verifierAdjacenceTerritoireEnnemi(this.joueurs.get(j), numTerritAttaquer);
						ArrayList<Integer> numTerritoiresAttaquant = new ArrayList<Integer>();
						
						// On ajoute les numéros des territoires adjacents du joueur dans une liste
						for(Territoire t : appartenanceTerritoiresAdj) {
							numTerritoiresAttaquant.add(t.getNumTerritoire());
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez un numéro de territoire qui vous appartient (parmis ceux proposés ci-dessus) :");
						int numTerritAttaquant = sc.nextInt();

						// Si le numéro saisi ne correspond pas à un des territoires proposés, il redemande de saisir une valeur valide
						if(!numTerritoiresAttaquant.contains(numTerritAttaquant)) {
							while(!numTerritoiresAttaquant.contains(numTerritAttaquant)) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez le numéro d'un territoire que vous possédez :");
								numTerritAttaquant = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						System.out.println(this.joueurs.get(j).getNom() + " - Saisissez le nombre de régiments pour attaquer (Min 1, Max 3), en fonction du nombre de régiments :");
						int nbRegimentsAtq = sc.nextInt();
						
						// Vérifie que le nombre de régiments soit valide pour l'attaque
						boolean regimentsCorrects = this.verificationRegimentsParTerritoire(numTerritAttaquant, nbRegimentsAtq);
						if(regimentsCorrects == false) {
							while(regimentsCorrects == false) {
								System.out.println(this.joueurs.get(j).getNom() + " - Choisissez un nombre de régiments disponible par rapport à votre territoire :");
								nbRegimentsAtq = sc.nextInt();
								sc.useDelimiter(";|\r?\n|\r");
							}
						}
						
						// On recherche les territoires en question pour les passer en paramètres dans la méthode Attaquer
						Territoire tAttaquant = new Territoire(0, "");
						Territoire tDefenseur = new Territoire(0, "");
						
						tAttaquant = this.retourneTerritoire(numTerritAttaquant);
						tDefenseur = this.retourneTerritoire(numTerritAttaquer);
						
						Attaquer nouvelleAttaque = new Attaquer(tAttaquant, tDefenseur, nbRegimentsAtq);
						nouvelleAttaque.InicierAttaque();
					
					}
					else {
						attaquer = false;
					}
		    	}
		    	
		    	// Déplacement des régiments
				boolean continuerTraverssee = true;
				while(continuerTraverssee == true) {
					Scanner myObj = new Scanner(System.in);
					
				    System.out.println("Voulez-vous déployer vos régiments ? (1 (oui), 2 (non))");
				    int dplReg = myObj.nextInt();
				    myObj.useDelimiter(";|\r?\n|\r");
				    
				    if(dplReg == 1) {
				    			    	
				    	this.montrerTerritoiresJoueur(this.joueurs.get(j));
				    	
				    	// Saisir le territoire de départ parmis ceux proposés
				    	System.out.println("Saisissez un de vos territoires de départ : ");
				    	int terrDept = myObj.nextInt();
				    	myObj.useDelimiter(";|\r?\n|\r");
				    	
				    	Territoire tARetournerDpt = this.retourneTerritoire(terrDept);
				    	
				    	System.out.println("Saisissez le nombre de régiments depuis lequel vous partez : ");
				    	int nbReg = myObj.nextInt();
				    	myObj.useDelimiter(";|\r?\n|\r");
				    	
				    	DeplacerRegiment deployerReg = new DeplacerRegiment(tARetournerDpt, nbReg);
				    	boolean demandeDeploiementRegiments = deployerReg.deplacerRegiment();
				    	
				    	// On affiche les territoires adjacents au territoire de départ
				    	if(demandeDeploiementRegiments == true) {
					    	System.out.println("Saisissez le territoire qui suit : ");
					    	int terrQuiSuit = myObj.nextInt();
					    	myObj.useDelimiter(";|\r?\n|\r");
					    	
					    	boolean encoreUnAutreTerritoire = true;
					    	// Si on veut continuer à déplacer les régiments, on propose les territoires adjacents de chaque territoire nouveau choisi
					    	while(encoreUnAutreTerritoire == true) {
						    	System.out.println("Voulez-vous traverser encore d'autres territoires ? (1 (oui), 2 (non))");
						    	dplReg = myObj.nextInt();
						    	myObj.useDelimiter(";|\r?\n|\r");
						    	
						    	if(dplReg == 1) {
						    		
						    		Territoire territSuivant = this.retourneTerritoire(terrQuiSuit);
						    		DeplacerRegiment deployerRegSuiv = new DeplacerRegiment(territSuivant, nbReg);
						    		deployerRegSuiv.deplacerRegiment();
						    		
						    		System.out.println("Saisissez un de vos suivants territoires : ");
						    		terrQuiSuit = myObj.nextInt();
						    		myObj.useDelimiter(";|\r?\n|\r");
						    	}
						    	
						    	// Sinon, on place les régiments au dernier territoire saisit
						    	else {
						    		Territoire tARetournerFin = this.retourneTerritoire(terrQuiSuit);
						    		tARetournerFin.setRegiments(tARetournerFin.getRegiments() + nbReg);
							    	tARetournerDpt.setRegiments(tARetournerDpt.getRegiments() - nbReg);
							    	encoreUnAutreTerritoire = false;
						    	}
					    	}
					    	
					    	continuerTraverssee = false;
					    }
					    else {
					    	continuerTraverssee = true;
					    }
			    	}
			    	else {
			    		continuerTraverssee = false;
			    	}
				}
		    }
		}
	}
	
	// Retourne le territoire en question à partir du numéro passé en paramètres
	public Territoire retourneTerritoire(int numTerritoire) {
		Territoire territoireARetourner = new Territoire(0, "");
		
		for(Territoire t : this.territoiresChoisits) {
			if(t.getNumTerritoire() == numTerritoire) {
				territoireARetourner = t;
				break;
			}
		}
		return territoireARetourner;
	}
	
	public void montrerTerritoiresJoueur(Joueur joueur) {
		for(Territoire t : joueur.getTerritoires()) {
			System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " avec " + t.getRegiments() + " régiments");
		}
	}
	
	// Permet de placer les régiments à chaque tour
	public void placerRegiments(int initJeu, Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		
		if(initJeu != 1) {
			//Régiments de début du tour (à exception du dénut du jeu oú il faut placer les dizaines de régiments par joueur)
	    	recevoirRegimentsSupplementairesDebutTour(joueur);
		}
    	
		// Stock des numéros de territoires possédés par le joueur
		ArrayList<Integer> numTerritoires = new ArrayList<Integer>();
    	while(joueur.getNbRegimentsRecusParTour() > 0) {
			for(Territoire t : joueur.getTerritoires()) {
				// Tous les territoires doivent avoir au moins 1 régiment. Donc, on place 1 automatiquement
				if(t.getRegiments() == 0) {
					t.setRegiments(1);
					joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour()-1);
				}
				if(!numTerritoires.contains(t.getNumTerritoire())) {
					numTerritoires.add(t.getNumTerritoire());
				}
				System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " - nombre de régiments : " + t.getRegiments());
			}
			
			System.out.println(joueur.getNom() + " il vous manque " + joueur.getNbRegimentsRecusParTour() + " régiments à placer");
			System.out.println(joueur.getNom() + " choisit un territoire dans lequel placer des régiments (écrit le numéro du territoire) :");
			int numTerritoire = sc.nextInt();
			
			// Si le numéro du territoire n'est pas possédé par le joueur, on lui demande à nouveau de choisir un numéro de territoire
			if(!numTerritoires.contains(numTerritoire)) {
				while(!numTerritoires.contains(numTerritoire)) {
					System.out.println("Choisissez le numéro d'un territoire que vous possédez :");
					numTerritoire = sc.nextInt();
					sc.useDelimiter(";|\r?\n|\r");
				}
			}
			
			System.out.println(joueur.getNom() + " choisit le nombre de régiments à placer sur ce territoire :");
			int nbRegiments = sc.nextInt();
			
			// Si le nombre de régiments n'est pas le correct, on lui demande à nouveau de saisir une valeur valide
			if(nbRegiments > joueur.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
				while(nbRegiments > joueur.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
					System.out.println("Vous ne possédez pas autant de régiments OU la valeur ne peut pas être inférieure ou égale à 0. Saisissez une valeur correcte :");
					nbRegiments = sc.nextInt();
					sc.useDelimiter(";|\r?\n|\r");
				}
			}
			
			for(Territoire t : joueur.getTerritoires()) {
				// On attribue le nombre de régiments dans le territoires choisi, puis on enlève les régiments placés au joueur en question
				if(t.getNumTerritoire() == numTerritoire) {
					t.setRegiments(t.getRegiments() + nbRegiments);
					joueur.setNbRegimentsRecusParTour(joueur.getNbRegimentsRecusParTour() - nbRegiments);
					System.out.println(joueur.getNom() + " a placé " + nbRegiments + " régiments sur le territoire : " + t.getNomTerritoire());
					System.out.println();
				}
			}
		}
    	// Récapitulatif des choix du joueur
		System.out.println("Voici le nombre de régiments par territoire que vous possédez : ");
		for(Territoire t : joueur.getTerritoires()) {
			System.out.println(t.getNomTerritoire() + " avec " + t.getRegiments() + " régiments");
		}
		System.out.println();
	}
	
}
