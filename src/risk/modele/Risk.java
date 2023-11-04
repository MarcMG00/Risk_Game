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
		lectureTerritoiresAdjacents("data/TerritoiresAdjacentsPetitExemple.txt");
		
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
		
	}
	
}
