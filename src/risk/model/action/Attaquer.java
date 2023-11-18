package risk.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import risk.modele.Joueur;
import risk.modele.Territoire;

public class Attaquer {
	private Territoire territAttaquant;
	private Territoire territDefenseur;
	private int nbRegimentsAtq;
	
	public Attaquer(Territoire territAttaquant, Territoire territDefenseur,  int nbRegimetnsAtq) {
		this.territAttaquant = territAttaquant;
		this.territDefenseur = territDefenseur;
		this.nbRegimentsAtq = nbRegimetnsAtq;
	}

	public Territoire getTerritAttaquant() {
		return territAttaquant;
	}

	public Territoire getTerritDefenseur() {
		return territDefenseur;
	}

	public int getNbRegimentsAtq() {
		return nbRegimentsAtq;
	}
	
	public void InicierAttaque() {
		// Variables pour stocker les lanc�es de d� des joueurs
		ArrayList<Integer> numerosDeAttaquant = new ArrayList<Integer>();
		ArrayList<Integer> numerosDeDefenseur = new ArrayList<Integer>();

		// Si le nombre de r�giments du d�fenseur de ce territoire est sup�rieur � 1, on lui donne le choix de saisir le nombre de r�giments pour la d�fense
		if(territDefenseur.getRegiments() > 1) {
			System.out.println("Le d�fenseur est : " + territDefenseur.getJoueurPossedantTerritoire().getNom() + " et poss�de " + territDefenseur.getRegiments()+ " r�giments dans le territoire " + territDefenseur.getNomTerritoire());
			
			// On demande au d�fenseur de saisir un nombre de r�giments
		    Scanner myObj = new Scanner(System.in);
		    System.out.println("Rentrez le nombre de r�giments pour d�fendre (au moins 1, au max 2)");
		    int choixRegimentDefense = myObj.nextInt();
		    
		    // Si le nombre est inf�rieur � 1 ou sup�rieur � 2, alors on redemande de saisir un entier valide
		    while (choixRegimentDefense > 2 || choixRegimentDefense < 1) {
		    	System.out.println("Vous ne pouvez choisir qu'1 ou 2 r�giments :");
			    choixRegimentDefense = myObj.nextInt();
			    myObj.useDelimiter(";|\r?\n|\r");
		    }
		    System.out.println("Le d�fenseur a choisi : " + choixRegimentDefense + " r�giments");  // Output user input

		    // On ajoute autant de fois de lanc�es de d� dans la liste de d�fense, que de r�giments choisits par le d�fenseur
			numerosDeDefenseur = this.lanceesDes(territDefenseur.getJoueurPossedantTerritoire(), choixRegimentDefense);
			System.out.println("Num�ros de d� lanc�s par le d�fenseur : " + numerosDeDefenseur);
			
			// On ajoute autant de fois de lanc�es de d� dans la liste d'attaque, que de r�giments choisits par l'attaquant
			numerosDeAttaquant = this.lanceesDes(territAttaquant.getJoueurPossedantTerritoire(), nbRegimentsAtq);
			System.out.println("Num�ros de d� lanc�s par l'attaquant : " + numerosDeAttaquant);
											
			// On enl�ve le num�ro de d� le plus petit de l'attaquant, si la taille de la liste est sup�rieure � 1
			if(choixRegimentDefense == 2) {
				if(numerosDeAttaquant.size() > 2) {
					numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
				}
			}
			
			else if(choixRegimentDefense < 2) {
				while(numerosDeAttaquant.size() > 2 || numerosDeAttaquant.size() > numerosDeDefenseur.size()) {
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
			this.comparaisonLanceesDes(territAttaquant, numerosDeAttaquant, territDefenseur, numerosDeDefenseur);
		}
		
		// Si le nombre de r�giments du d�fenseur dans ce territoire est �gal � 1, pas le choix, il ne d�fend qu'avec 1 r�giment
		else if(territDefenseur.getRegiments() == 1) {
			// On ajoute une lanc�e de d� dans la liste de d�fense
			numerosDeDefenseur = this.lanceesDes(territDefenseur.getJoueurPossedantTerritoire(), 1);
			System.out.println("Num�ros de d� lanc� par le d�fenseur : " + numerosDeDefenseur);
			
			numerosDeAttaquant = this.lanceesDes(territAttaquant.getJoueurPossedantTerritoire(), nbRegimentsAtq);
			System.out.println("Num�ros de d� lanc�s par l'attaquant : " + numerosDeAttaquant);
											
			// On enl�ve les num�ros de d� les plus petits de l'attaquant
			do {
				numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
			} while(numerosDeAttaquant.size() > 1);
			System.out.println("Num�ro de d� le plus grand pour l'attaquant : " + numerosDeAttaquant);
			
			// Comparaison des lanc�es de d�s de chaque joueur, pour supprimer le r�giment du perdant
			this.comparaisonLanceesDes(territAttaquant, numerosDeAttaquant, territDefenseur, numerosDeDefenseur);
			
			if(territDefenseur.getRegiments() == 0) {
				// Dans ce cas, le d�fenseur n'a plus de r�giments dans le territoire, donc l'attaquant a conqu�rit un territoire,
				// ce qui lui permettra de recevoir une carte territoire
				territAttaquant.getJoueurPossedantTerritoire().setAConqueritEnUntour(true);
			
				territAttaquant.getJoueurPossedantTerritoire().setNbTerritoiresConquis(territAttaquant.getJoueurPossedantTerritoire().getNbTerritoiresConquis() + 1);
				System.out.println("Le joueur " + territAttaquant.getJoueurPossedantTerritoire().getNom() + " a conqu�rit le territoire : " + territDefenseur.getNomTerritoire());
				
				// On supprime le territoire de la liste de territoires du d�fenseur, puis on l'ajoute � la liste de l'attaquant
				territDefenseur.getJoueurPossedantTerritoire().enleverTerritoire(territDefenseur);
				territAttaquant.getJoueurPossedantTerritoire().ajouterTerritoire(territDefenseur);
			}
			
			// Si le joueur d�fenseur n'a plus de r�giments, on prend toutes les cartes du d�fenseur et on les ajoute � celle de l'attaquant s'il n'a plus de r�giments
			if(territDefenseur.getJoueurPossedantTerritoire().getNbRegiments() == 0) {
				this.recevoirCartesTerritoires(territDefenseur.getJoueurPossedantTerritoire());
			}
			// Ou si le joueur attaquant n'a plus de r�giments, on fait la m�me chose, mais en donnant les cartes au d�fenseur
			else if(territAttaquant.getJoueurPossedantTerritoire().getNbRegiments() == 0) {
				this.recevoirCartesTerritoires(territAttaquant.getJoueurPossedantTerritoire());
			}
		}
	}
	
	// M�thode d'ajout de lanc�es de d�s + nombre de fois de lanc�es �gales � 1
	public ArrayList<Integer> lanceesDes(Joueur joueur, int nbLanceesDes) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i = 0; i < nbLanceesDes; i++) {
			int numD = (int) ((Math.random() * 6) + 1);
			numeros.add(numD);
			if(numD == 1) {
				joueur.setNbFoisLanceesUn(joueur.getNbFoisLanceesUn() + 1);
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
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				territDef.setRegiments(territDef.getRegiments() - 1);
				
				// Calcul des r�giments restants au d�fenseur
				territDef.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countDf += 1;
			}
			
			else if(numerosAttaquant.get(i) == numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des r�giments restants � l'attaquant
				territAttaquant.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countAt += 1;
			}
			
			else if(numerosAttaquant.get(i) < numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des r�giments restants � l'attaquant
				territAttaquant.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
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
				territAttaquant.getJoueurPossedantTerritoire().ajouterCarteTerritoire(joueurPerdant.getCartesTerritoires().get(i));
			}
			joueurPerdant.setCartesTerritoires(null);
		}
	}
}
