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
	
	public void lancerPartie() {
		// Vérification des données de la lecture des fichiers
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
		lectureTerritoiresAdjacents("data/TerritoiresAdjacentsPetitExemple.txt");
		
		// Position aléatoire des joueurs, pour établir l'ordre du jeu / mélange des cartes
		Collections.shuffle(this.joueurs);
		Collections.shuffle(this.cartes);
		
		// Choix des différents territoires, commençant par le premier joueur
		// Tous les territoires doivent être pris
		while (this.territoires.size() != 0) {
			// On passe à tour de rôle pour chaque joueur
			for(Joueur joueur : this.joueurs) {
				// On affiche les territoires qui sont disponibles
				for(Territoire territoire : this.territoires) {
					System.out.println(territoire.getNumTerritoire() + " - " + territoire.getNomTerritoire());
				}
				
				System.out.println(joueur.getNom() + " choisit un territoire parmis ceux proposés ci-dessus. Ecrit seulement le numéro. S'il n'y a plus de territoires, écrit aléatoirement un numéro (rien ne se produira)");
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
			// Stock des numéros de territoires possédés par le joueur pour un contrôle
			ArrayList<Integer> numTerritoires = new ArrayList<Integer>();
			// Pour chaque joueur, on lui demande de placer les régiments du début du jeu (voir AppRisk.java)
			while(j.getNbRegimentsRecusParTour() > 0) {
				for(Territoire t : j.getTerritoires()) {
					// Tous les territoires doivent avoir au moins 1 régiment. Donc, on place 1 automatiquement
					if(t.getRegiments() == 0) {
						t.setRegiments(1);
						j.setNbRegimentsRecusParTour(j.getNbRegimentsRecusParTour()-1);
					}
					if(!numTerritoires.contains(t.getNumTerritoire())) {
						numTerritoires.add(t.getNumTerritoire());
					}
					System.out.println(t.getNumTerritoire() + " - " + t.getNomTerritoire() + " - nombre de régiments : " + t.getRegiments());
				}
				
				System.out.println(j.getNom() + " il vous manque " + j.getNbRegimentsRecusParTour() + " régiments à placer");
				System.out.println(j.getNom() + " choisit un territoire dans lequel placer des régiments (écrit le numéro du territoire) :");
				Scanner sc = new Scanner(System.in);
				int numTerritoire = sc.nextInt();
				
				// Si le numéro du territoire n'est pas possédé par le joueur, on lui demande à nouveau de choisir un numéro de territoire
				if(!numTerritoires.contains(numTerritoire)) {
					while(!numTerritoires.contains(numTerritoire)) {
						System.out.println("Choisissez le numéro d'un territoire que vous possédez :");
						numTerritoire = sc.nextInt();
						sc.useDelimiter(";|\r?\n|\r");
					}
				}
				
				System.out.println(j.getNom() + " choisit le nombre de régiments à placer sur ce territoire :");
				int nbRegiments = sc.nextInt();
				
				// Si le nombre de régiments n'est pas le correct, on lui demande à nouveau de saisir une valeur valide
				if(nbRegiments > j.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
					while(nbRegiments > j.getNbRegimentsRecusParTour() || nbRegiments <= 0) {
						System.out.println("Vous ne possédez pas autant de régiments OU la valeur ne peut pas être inférieure ou égale à 0. Saisissez une valeur correcte :");
						nbRegiments = sc.nextInt();
						sc.useDelimiter(";|\r?\n|\r");
					}
				}
				
				for(Territoire t : j.getTerritoires()) {
					// On attribue le nombre de régiments dans le territoires choisi, puis on enlève les régiments placés au joueur en question
					if(t.getNumTerritoire() == numTerritoire) {
						t.setRegiments(t.getRegiments() + nbRegiments);
						j.setNbRegimentsRecusParTour(j.getNbRegimentsRecusParTour() - nbRegiments);
						System.out.println(j.getNom() + " a placé " + nbRegiments + " régiments sur le territoire : " + t.getNomTerritoire());
						System.out.println();
					}
				}
			}
			
			// Récapitulatif des choix du joueur
			System.out.println("Voici le nombre de régiments par territoire que vous possédez : ");
			for(Territoire t : j.getTerritoires()) {
				System.out.println(t.getNomTerritoire() + " avec " + t.getRegiments() + " régiments");
			}
			System.out.println();
		}
		
		System.out.println("Vous avez fini de placer les régiments de départ. Procédons au jeu JEJEJE");
		// Début du jeu : un jeu ne fini que lorsqu'il y a un joueur dans la liste de joueurs
		
	}
	
}
