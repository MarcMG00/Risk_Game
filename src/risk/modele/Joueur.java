package risk.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private String prenom;
	private int nbRegiments;
	private int nbRegimentsRecusParTour;
	private int score;
	private int regimentsAdditionnels;
	private int numFoisEchangesCartes;
	private boolean aConqueritEnUntour;
	private int nbDefenses;
	private int nbAttaques;
	private int nbFoisLanceesUn;
	private int nbTerritoiresConquis;
	private ArrayList<Territoire> territoires;
	private ArrayList<Continent> continents;
	private ArrayList<Carte_Territoire> cartesTerritoires;

	public Joueur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.nbRegiments = 0;
		this.nbRegimentsRecusParTour = 0;
		this.score = 0;
		this.regimentsAdditionnels = 0;
		this.numFoisEchangesCartes = 0;
		this.aConqueritEnUntour = false;
		this.nbDefenses = 0;
		this.nbAttaques = 0;
		this.nbFoisLanceesUn = 0;
		this.nbTerritoiresConquis = 0;
		this.territoires = new ArrayList<Territoire>();
		this.continents = new ArrayList<Continent>();
		this.cartesTerritoires = new ArrayList<Carte_Territoire>();
	}

	public int getNbRegiments() {
		return nbRegiments;
	}

	public void setNbRegiments(int nbRegiments) {
		this.nbRegiments = nbRegiments;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRegimentsAdditionnels() {
		return regimentsAdditionnels;
	}

	public void setRegimentsAdditionnels(int regimentsAdditionnels) {
		this.regimentsAdditionnels = regimentsAdditionnels;
	}

	public int getNumFoisEchangesCartes() {
		return numFoisEchangesCartes;
	}

	public void setNumFoisEchangesCartes(int numFoisEchangesCartes) {
		this.numFoisEchangesCartes = numFoisEchangesCartes;
	}

	public boolean getAConqueritEnUntour() {
		return aConqueritEnUntour;
	}

	public void setAConqueritEnUntour(boolean aConqueritEnUntour) {
		this.aConqueritEnUntour = aConqueritEnUntour;
	}

	public int getNbDefenses() {
		return nbDefenses;
	}

	public void setNbDefenses(int nbDefenses) {
		this.nbDefenses = nbDefenses;
	}

	public int getNbAttaques() {
		return nbAttaques;
	}

	public void setNbAttaques(int nbAttaques) {
		this.nbAttaques = nbAttaques;
	}

	public int getNbFoisLanceesUn() {
		return nbFoisLanceesUn;
	}

	public void setNbFoisLanceesUn(int nbFoisLanceesUn) {
		this.nbFoisLanceesUn = nbFoisLanceesUn;
	}

	public int getNbTerritoiresConquis() {
		return nbTerritoiresConquis;
	}

	public void setNbTerritoiresConquis(int nbTerritoiresConquis) {
		this.nbTerritoiresConquis = nbTerritoiresConquis;
	}

	public ArrayList<Territoire> getTerritoires() {
		return territoires;
	}

	public void setTerritoires(ArrayList<Territoire> territoires) {
		this.territoires = territoires;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	public ArrayList<Carte_Territoire> getCartesTerritoires() {
		return cartesTerritoires;
	}

	public void setCartesTerritoires(ArrayList<Carte_Territoire> cartesTerritoires) {
		this.cartesTerritoires = cartesTerritoires;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNbRegimentsRecusParTour() {
		return nbRegimentsRecusParTour;
	}

	public void setNbRegimentsRecusParTour(int nbRegimentsRecusParTour) {
		this.nbRegimentsRecusParTour = nbRegimentsRecusParTour;
	}
	
	public void ajouterTerritoire(Territoire territoire) {
		this.territoires.add(territoire);
	}
	
	public void enleverTerritoire(Territoire territoire) {
		if(this.territoires.contains(territoire)) {
			this.territoires.remove(territoire);
		}
	}
	
	public void ajouterCarteTerritoire(Carte_Territoire carte_territoire) {
		this.cartesTerritoires.add(carte_territoire);
	}
	
	public void enleverCarteTerritoire(Carte_Territoire carte_territoire) {
		if(this.cartesTerritoires.contains(carte_territoire)) {
			this.cartesTerritoires.remove(carte_territoire);
		}
	}
	
	public void calculNbRegimentsTotaux() {
		this.nbRegiments = 0;
		for(Territoire t : this.getTerritoires()) {
			this.nbRegiments += t.getRegiments();
		}
	}
	
	public void attaquer(int numTerritoireAtq, int numTerritoireDef, int nbRegiments) {
		// Variables pour stocker les lanc�es de d� des joueurs
		ArrayList<Integer> numerosDeAttaquant = new ArrayList<Integer>();
		ArrayList<Integer> numerosDeDefenseur = new ArrayList<Integer>();
		Territoire territAttaq = new Territoire(0, "");
		Territoire territDef = new Territoire(0, "");
		
		for(Territoire t : this.territoires) {
			if(t.getNumTerritoire() == numTerritoireAtq) {
				territAttaq = t;
			}
			else if(t.getNumTerritoire() == numTerritoireDef) {
				territDef = t;
			}
		}
		
		// Si le nombre de r�giments du d�fenseur de ce territoire est sup�rieur � 1, on lui donne le choix de saisir le nombre de r�giments pour la d�fense
		if(territDef.getRegiments() > 1) {
			System.out.println("Le d�fenseur est : " + territDef.getJoueurPossedantTerritoire().getNom() + " et poss�de " + territDef.getRegiments()+ " r�giments dans le territoire " + territDef.getNomTerritoire());
			
			// On demande au d�fenseur de saisir un nombre de r�giments
		    Scanner myObj = new Scanner(System.in);
		    System.out.println("Rentrez le nombre de r�giments pour d�fendre (au moins 1, au max 2)");
		    int choixRegimentDefense = myObj.nextInt();
		    
		    // Si le nombre est inf�rieur � 1 ou sup�rieur � 2, alors on redemande de saisir un entier valide
		    while (choixRegimentDefense > 2 || choixRegimentDefense < 1) {
		    	System.out.println("Vous ne pouvez choisir qu'un ou 2 r�giments :");
			    choixRegimentDefense = myObj.nextInt();
			    myObj.useDelimiter(";|\r?\n|\r");
		    }
		    System.out.println("Le d�fenseur a choisi : " + choixRegimentDefense + " r�giments");  // Output user input

		    // On ajoute autant de fois de lanc�es de d� dans la liste de d�fense, que de r�giments choisits par le d�fenseur
//			for(int jd = 0; jd < choixRegimentDefense; jd++) {
//				int numD = (int) ((Math.random() * 6) + 1);
//				numerosDeDefenseur.add(numD);
//				if(numD == 1) {
//					territDef.getJoueurPossedantTerritoire().setNbFoisLanceesUn(territDef.getJoueurPossedantTerritoire().getNbFoisLanceesUn() + 1);
//				}
//			}
			numerosDeDefenseur = this.lanceesDes(territDef, choixRegimentDefense);
			System.out.println("Num�ros de d� lanc�s par le d�fenseur : " + numerosDeDefenseur);
			
			// On ajoute autant de fois de lanc�es de d� dans la liste d'attaque, que de r�giments choisits par l'attaquant
//			for(int ja = 0; ja < nbRegiments; ja++) {
//				int numD = (int) ((Math.random() * 6) + 1);
//				numerosDeAttaquant.add(numD);
//				if(numD == 1) {
//					this.setNbFoisLanceesUn(this.getNbFoisLanceesUn() + 1);
//				}
//			}
			numerosDeAttaquant = this.lanceesDes(territAttaq, nbRegiments);
			System.out.println("Num�ros de d� lanc�s par l'attaquant : " + numerosDeAttaquant);
											
			// On enl�ve le num�ro de d� le plus petit de l'attaquant, si la taille de la liste est sup�rieure � 1
			if(choixRegimentDefense == 2) {
				if(numerosDeAttaquant.size() > 2) {
//					int min = 7;
//					for(int i = 0; i < numerosDeAttaquant.size(); i++) {
//						if(numerosDeAttaquant.get(i) < min) {
//							min = numerosDeAttaquant.get(i);
//						}
//					}
//					numerosDeAttaquant.remove(numerosDeAttaquant.indexOf(min));
					numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
				}
			}
			
			else if(choixRegimentDefense < 2) {
				while(numerosDeAttaquant.size() > 2) {
//					int min = 7;
//					for(int i = 0; i < numerosDeAttaquant.size(); i++) {
//						if(numerosDeAttaquant.get(i) < min) {
//							min = numerosDeAttaquant.get(i);
//						}
//					}
//					numerosDeAttaquant.remove(numerosDeAttaquant.indexOf(min));
					numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
				}
			}
			
			// On place par ordre d�croissant les listes des nums de d�s de chaque joueur (pour comparer les index)
			System.out.println("Affichage d�finitif de l'ordre des num�ros du d� (par ordre d�croissant) : ");
			Collections.sort(numerosDeAttaquant, Collections.reverseOrder());
			Collections.sort(numerosDeDefenseur, Collections.reverseOrder());
			System.out.println("Attaquant : " + numerosDeAttaquant);
			System.out.println("D�fenseur : " + numerosDeDefenseur);
			
			// Comparaison des lanc�es de d�s de chaque joueur, pour supprimer le r�giment du perdant
			// On ajoute en plus le num�ro d'attaques et de d�fenses pour chaque joueur
//			int countAt = 0;
//			int countDf = 0;
//			for(int i = 0; i < numerosDeAttaquant.size(); i++) {
//				if(numerosDeAttaquant.get(i) > numerosDeDefenseur.get(i)) {
//					territDef.getJoueurPossedantTerritoire().setNbRegiments(territDef.getJoueurPossedantTerritoire().getNbRegiments() - 1);
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					territDef.setRegiments(territDef.getRegiments() - 1);
//					countDf += 1;
//				}
//				
//				else if(numerosDeAttaquant.get(i) == numerosDeDefenseur.get(i)) {
//					this.setNbRegiments(this.getNbRegiments() - 1);
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					countAt += 1;
//				}
//				
//				else if(numerosDeAttaquant.get(i) < numerosDeDefenseur.get(i)) {
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					this.setNbRegiments(this.getNbRegiments() - 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					countAt += 1;
//				}
//			}
//			System.out.println("L'attaquant a perdu " + countAt + " r�giments. " + "Nombre de r�giments restants � l'attaquant : " + this.getNbRegiments());
//			System.out.println("Le d�fenseur a perdu " + countDf + " r�giments. " + "Nombre de r�giments restants au d�fenseur : " + territDef.getJoueurPossedantTerritoire().getNbRegiments());
//			System.out.println("Nombre de r�giments restants dans le territoire du d�fenseur : " + territDef.getRegiments());
			this.comparaisonLanceesDes(territAttaq, numerosDeAttaquant, territDef, numerosDeDefenseur);
		}
		
		// Si le nombre de r�giments du d�fenseur dans ce territoire est �gal � 1, pas le choix, il ne d�fend qu'avec 1 r�giment
		else if(territDef.getRegiments() == 1) {
			// On ajoute une lanc�e de d� dans la liste de d�fense
			int numD = (int) ((Math.random() * 6) + 1);
			numerosDeDefenseur.add(numD);
			if(numD == 1) {
				territDef.getJoueurPossedantTerritoire().setNbFoisLanceesUn(territDef.getJoueurPossedantTerritoire().getNbFoisLanceesUn() + 1);
			}
			System.out.println("Num�ros de d� lanc� par le d�fenseur : " + numerosDeDefenseur);
			
			// On ajoute autant de fois de lanc�es de d� dans la liste d'attaque, que de r�giments choisits par l'attaquant
//			for(int ja = 0; ja < nbRegiments; ja++) {
//				int numDd = (int) ((Math.random() * 6) + 1);
//				numerosDeAttaquant.add(numD);
//				if(numDd == 1) {
//					this.setNbFoisLanceesUn(this.getNbFoisLanceesUn() + 1);
//				}
//			}
			numerosDeAttaquant = this.lanceesDes(territAttaq, nbRegiments);
			System.out.println("Num�ros de d� lanc�s par l'attaquant : " + numerosDeAttaquant);
											
			// On enl�ve les num�ros de d� les plus petits de l'attaquant
//			int min = 7;
			do {
//				for(int i = 0; i < numerosDeAttaquant.size(); i++) {
//					if(numerosDeAttaquant.get(i) < min) {
//						min = numerosDeAttaquant.get(i);
//					}
//				}
//				numerosDeAttaquant.remove(numerosDeAttaquant.indexOf(min));
				numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
			} while(numerosDeAttaquant.size() > 1);
			System.out.println("Num�ro de d� le plus grand pour l'attaquant : " + numerosDeAttaquant);
			
			// Comparaison des lanc�es de d�s de chaque joueur, pour supprimer le r�giment du perdant
//			int countAt = 0;
//			int countDf = 0;
//			for(int i = 0; i < numerosDeAttaquant.size(); i++) {
//				if(numerosDeAttaquant.get(i) > numerosDeDefenseur.get(i)) {
//					territDef.getJoueurPossedantTerritoire().setNbRegiments(territDef.getJoueurPossedantTerritoire().getNbRegiments() - 1);
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					territDef.setRegiments(territDef.getRegiments() - 1);
//					countDf += 1;
//				}
//				
//				else if(numerosDeAttaquant.get(i) == numerosDeDefenseur.get(i)) {
//					this.setNbRegiments(this.getNbRegiments() - 1);
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					countAt += 1;
//				}
//				
//				else if(numerosDeAttaquant.get(i) < numerosDeDefenseur.get(i)) {
//					this.setNbAttaques(this.getNbAttaques() + 1);
//					this.setNbRegiments(this.getNbRegiments() - 1);
//					territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
//					countAt += 1;
//				}
//			}
//			System.out.println("L'attaquant a perdu " + countAt + " r�giments. " + "Nombre de r�giments restants � l'attaquant : " + this.getNbRegiments());
//			System.out.println("Le d�fenseur a perdu " + countDf + " r�giments. " + "Nombre de r�giments restants au d�fenseur : " + territDef.getJoueurPossedantTerritoire().getNbRegiments());
//			System.out.println("Nombre de r�giments restants dans le territoire du d�fenseur : " + territDef.getRegiments());
			this.comparaisonLanceesDes(territAttaq, numerosDeAttaquant, territDef, numerosDeDefenseur);
			
			if(territDef.getRegiments() == 0) {
				// Dans ce cas, le d�fenseur n'a plus de r�giments dans le territoire, donc l'attaquant a conqu�rit un territoire,
				// ce qui lui permettra de recevoir une carte territoire
				boolean vrai = true;
				this.setAConqueritEnUntour(vrai);
			
				this.setNbTerritoiresConquis(this.getNbTerritoiresConquis() + 1);
				System.out.println("Le joueur " + territAttaq.getJoueurPossedantTerritoire().getNom() + " a conqu�rit le territoire : " + territDef.getNomTerritoire());
				
				// On supprime le territoire de la liste de territoires du d�fenseur, puis on l'ajoute � la liste de l'attaquant
				territDef.getJoueurPossedantTerritoire().enleverTerritoire(territDef);
				this.ajouterTerritoire(territDef);
			}
			
			// Si le joueur d�fenseur n'a plus de r�giments, on prend toutes les cartes du d�fenseur et on les ajoute � celle de l'attaquant s'il n'a plus de r�giments
			if(territDef.getJoueurPossedantTerritoire().getNbRegiments() == 0) {
//				if(territDef.getJoueurPossedantTerritoire().getCartesTerritoires().size() >= 1) {
//					for(int i = 0; i < territDef.getJoueurPossedantTerritoire().getCartesTerritoires().size(); i++) {
//						this.ajouterCarteTerritoire(territDef.getJoueurPossedantTerritoire().getCartesTerritoires().get(i));
//					}
//					territDef.getJoueurPossedantTerritoire().setCartesTerritoires(null);
//				}
				this.recevoirCartesTerritoires(territDef.getJoueurPossedantTerritoire());
			}
			// Ou si le joueur attaquant n'a plus de r�giments, on fait la m�me chose, mais en donnant les cartes au d�fenseur
			else if(this.getNbRegiments() == 0) {
//				if(this.getCartesTerritoires().size() >= 1) {
//					for(int i = 0; i < this.getCartesTerritoires().size(); i++) {
//						territDef.getJoueurPossedantTerritoire().ajouterCarteTerritoire(this.getCartesTerritoires().get(i));
//					}
//					this.setCartesTerritoires(null);
				territDef.getJoueurPossedantTerritoire().recevoirCartesTerritoires(territAttaq.getJoueurPossedantTerritoire());
//				}
			}			
		}
	}
	
	// M�thode d'ajout de lanc�es de d�s + nombre de fois de lanc�es �gales � 1
	public ArrayList<Integer> lanceesDes(Territoire territoire, int nbLanceesDes) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i = 0; i < nbLanceesDes; i++) {
			int numD = (int) ((Math.random() * 6) + 1);
			numeros.add(numD);
			if(numD == 1) {
				territoire.getJoueurPossedantTerritoire().setNbFoisLanceesUn(territoire.getJoueurPossedantTerritoire().getNbFoisLanceesUn() + 1);
			}
		}
		return numeros;
	}
	
	// M�thode de suppression des lanc�s de d�s ayant le/les num�ros les plus petits
	public ArrayList<Integer> supressionNumerosPetits(ArrayList<Integer> listeNumeros) {
		// Min 7 pour �crire un num�ro quelconque, sup�rieur � 6 (nombre le plus grand d'un d�)
		int min = 7;
		for(int i = 0; i < listeNumeros.size(); i++) {
			if(listeNumeros.get(i) < min) {
				min = listeNumeros.get(i);
			}
		}
		listeNumeros.remove(listeNumeros.indexOf(min));
		return listeNumeros;
	}			
	
	// M�thode de comparaison des r�sultats des lanc�es de d�s
	// On ajoute en plus le num�ro d'attaques et de d�fenses pour chaque joueur
	public void comparaisonLanceesDes(Territoire territAttaq, ArrayList<Integer> numerosAttaquant, Territoire territDef, ArrayList<Integer> numerosDefenseur) {
		int countAt = 0;
		int countDf = 0;
		
		for(int i = 0; i < numerosAttaquant.size(); i++) {
			if(numerosAttaquant.get(i) > numerosDefenseur.get(i)) {
				this.setNbAttaques(this.getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				territDef.setRegiments(territDef.getRegiments() - 1);
				
				// Calcul des r�giments restants au d�fenseur
				territDef.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countDf += 1;
			}
			
			else if(numerosAttaquant.get(i) == numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				this.setNbAttaques(this.getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des r�giments restants � l'attaquant
				this.calculNbRegimentsTotaux();
				countAt += 1;
			}
			
			else if(numerosAttaquant.get(i) < numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				this.setNbAttaques(this.getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des r�giments restants � l'attaquant
				this.calculNbRegimentsTotaux();
				countAt += 1;
			}
		}
		
		System.out.println("L'attaquant a perdu " + countAt + " r�giments. " + " Nombre de r�giments restants � l'attaquant provenant du territoire " + territAttaq.getNomTerritoire() + " : " + territAttaq.getRegiments());
		System.out.println("Le d�fenseur a perdu " + countDf + " r�giments. " + " Nombre de r�giments restants au d�fenseur dans le territoire " + territDef.getNomTerritoire() + " : " +  territDef.getRegiments());
	}
	
	// M�thode pour donner toutes les cartes, une fois perdus tous les territoires
	public void recevoirCartesTerritoires(Joueur joueurPerdant) {
		if(joueurPerdant.getCartesTerritoires().size() >= 1) {
			for(int i = 0; i < joueurPerdant.getCartesTerritoires().size(); i++) {
				this.ajouterCarteTerritoire(joueurPerdant.getCartesTerritoires().get(i));
			}
			joueurPerdant.setCartesTerritoires(null);
		}
	}
	
}
