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
		// Variables pour stocker les lancées de dé des joueurs
		ArrayList<Integer> numerosDeAttaquant = new ArrayList<Integer>();
		ArrayList<Integer> numerosDeDefenseur = new ArrayList<Integer>();

		// Si le nombre de régiments du défenseur de ce territoire est supérieur à 1, on lui donne le choix de saisir le nombre de régiments pour la défense
		if(territDefenseur.getRegiments() > 1) {
			System.out.println("Le défenseur est : " + territDefenseur.getJoueurPossedantTerritoire().getNom() + " et possède " + territDefenseur.getRegiments()+ " régiments dans le territoire " + territDefenseur.getNomTerritoire());
			
			// On demande au défenseur de saisir un nombre de régiments
		    Scanner myObj = new Scanner(System.in);
		    System.out.println("Rentrez le nombre de régiments pour défendre (au moins 1, au max 2)");
		    int choixRegimentDefense = myObj.nextInt();
		    
		    // Si le nombre est inférieur à 1 ou supérieur à 2, alors on redemande de saisir un entier valide
		    while (choixRegimentDefense > 2 || choixRegimentDefense < 1) {
		    	System.out.println("Vous ne pouvez choisir qu'1 ou 2 régiments :");
			    choixRegimentDefense = myObj.nextInt();
			    myObj.useDelimiter(";|\r?\n|\r");
		    }
		    System.out.println("Le défenseur a choisi : " + choixRegimentDefense + " régiments");  // Output user input

		    // On ajoute autant de fois de lancées de dé dans la liste de défense, que de régiments choisits par le défenseur
			numerosDeDefenseur = this.lanceesDes(territDefenseur.getJoueurPossedantTerritoire(), choixRegimentDefense);
			System.out.println("Numéros de dé lancés par le défenseur : " + numerosDeDefenseur);
			
			// On ajoute autant de fois de lancées de dé dans la liste d'attaque, que de régiments choisits par l'attaquant
			numerosDeAttaquant = this.lanceesDes(territAttaquant.getJoueurPossedantTerritoire(), nbRegimentsAtq);
			System.out.println("Numéros de dé lancés par l'attaquant : " + numerosDeAttaquant);
											
			// On enlève le numéro de dé le plus petit de l'attaquant, si la taille de la liste est supérieure à 1
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
			
			// On place par ordre décroissant les listes des nums de dés de chaque joueur (pour comparer les index)
			System.out.println("Affichage définitif de l'ordre des numéros du dé (par ordre décroissant) : ");
			Collections.sort(numerosDeAttaquant, Collections.reverseOrder());
			Collections.sort(numerosDeDefenseur, Collections.reverseOrder());
			System.out.println("Attaquant : " + numerosDeAttaquant);
			System.out.println("Défenseur : " + numerosDeDefenseur);
			
			// Comparaison des lancées de dés de chaque joueur, pour supprimer le régiment du perdant
			// On ajoute en plus le numéro d'attaques et de défenses pour chaque joueur
			this.comparaisonLanceesDes(territAttaquant, numerosDeAttaquant, territDefenseur, numerosDeDefenseur);
		}
		
		// Si le nombre de régiments du défenseur dans ce territoire est égal à 1, pas le choix, il ne défend qu'avec 1 régiment
		else if(territDefenseur.getRegiments() == 1) {
			// On ajoute une lancée de dé dans la liste de défense
			numerosDeDefenseur = this.lanceesDes(territDefenseur.getJoueurPossedantTerritoire(), 1);
			System.out.println("Numéros de dé lancé par le défenseur : " + numerosDeDefenseur);
			
			numerosDeAttaquant = this.lanceesDes(territAttaquant.getJoueurPossedantTerritoire(), nbRegimentsAtq);
			System.out.println("Numéros de dé lancés par l'attaquant : " + numerosDeAttaquant);
											
			// On enlève les numéros de dé les plus petits de l'attaquant
			do {
				numerosDeAttaquant = this.supressionNumerosPetits(numerosDeAttaquant);
			} while(numerosDeAttaquant.size() > 1);
			System.out.println("Numéro de dé le plus grand pour l'attaquant : " + numerosDeAttaquant);
			
			// Comparaison des lancées de dés de chaque joueur, pour supprimer le régiment du perdant
			this.comparaisonLanceesDes(territAttaquant, numerosDeAttaquant, territDefenseur, numerosDeDefenseur);
			
			if(territDefenseur.getRegiments() == 0) {
				// Dans ce cas, le défenseur n'a plus de régiments dans le territoire, donc l'attaquant a conquérit un territoire,
				// ce qui lui permettra de recevoir une carte territoire
				territAttaquant.getJoueurPossedantTerritoire().setAConqueritEnUntour(true);
			
				territAttaquant.getJoueurPossedantTerritoire().setNbTerritoiresConquis(territAttaquant.getJoueurPossedantTerritoire().getNbTerritoiresConquis() + 1);
				System.out.println("Le joueur " + territAttaquant.getJoueurPossedantTerritoire().getNom() + " a conquérit le territoire : " + territDefenseur.getNomTerritoire());
				
				// On supprime le territoire de la liste de territoires du défenseur, puis on l'ajoute à la liste de l'attaquant
				territDefenseur.getJoueurPossedantTerritoire().enleverTerritoire(territDefenseur);
				territAttaquant.getJoueurPossedantTerritoire().ajouterTerritoire(territDefenseur);
			}
			
			// Si le joueur défenseur n'a plus de régiments, on prend toutes les cartes du défenseur et on les ajoute à celle de l'attaquant s'il n'a plus de régiments
			if(territDefenseur.getJoueurPossedantTerritoire().getNbRegiments() == 0) {
				this.recevoirCartesTerritoires(territDefenseur.getJoueurPossedantTerritoire());
			}
			// Ou si le joueur attaquant n'a plus de régiments, on fait la même chose, mais en donnant les cartes au défenseur
			else if(territAttaquant.getJoueurPossedantTerritoire().getNbRegiments() == 0) {
				this.recevoirCartesTerritoires(territAttaquant.getJoueurPossedantTerritoire());
			}
		}
	}
	
	// Méthode d'ajout de lancées de dés + nombre de fois de lancées égales à 1
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
	
	// Méthode de suppression des lancés de dés ayant le/les numéros les plus petits
	public ArrayList<Integer> supressionNumerosPetits(ArrayList<Integer> listeNumeros) {
		// Min 7 pour écrire un numéro quelconque, supérieur à 6 (nombre le plus grand d'un dé)
		int min = 7;
		for(int i = 0; i < listeNumeros.size(); i++) {
			if(listeNumeros.get(i) < min) {
				min = listeNumeros.get(i);
			}
		}
		listeNumeros.remove(listeNumeros.indexOf(min));
		return listeNumeros;
	}			
	
	// Méthode de comparaison des résultats des lancées de dés
	// On ajoute en plus le numéro d'attaques et de défenses pour chaque joueur
	public void comparaisonLanceesDes(Territoire territAttaq, ArrayList<Integer> numerosAttaquant, Territoire territDef, ArrayList<Integer> numerosDefenseur) {
		int countAt = 0;
		int countDf = 0;
		
		for(int i = 0; i < numerosAttaquant.size(); i++) {
			if(numerosAttaquant.get(i) > numerosDefenseur.get(i)) {
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				territDef.setRegiments(territDef.getRegiments() - 1);
				
				// Calcul des régiments restants au défenseur
				territDef.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countDf += 1;
			}
			
			else if(numerosAttaquant.get(i) == numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des régiments restants à l'attaquant
				territAttaquant.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countAt += 1;
			}
			
			else if(numerosAttaquant.get(i) < numerosDefenseur.get(i)) {
				territAttaq.setRegiments(territAttaq.getRegiments() - 1);
				territAttaquant.getJoueurPossedantTerritoire().setNbAttaques(territAttaquant.getJoueurPossedantTerritoire().getNbAttaques() + 1);
				territDef.getJoueurPossedantTerritoire().setNbDefenses(territDef.getJoueurPossedantTerritoire().getNbDefenses() + 1);
				
				// Calcul des régiments restants à l'attaquant
				territAttaquant.getJoueurPossedantTerritoire().calculNbRegimentsTotaux();
				countAt += 1;
			}
		}
		
		System.out.println("L'attaquant a perdu " + countAt + " régiments. " + " Nombre de régiments restants à l'attaquant provenant du territoire " + territAttaq.getNomTerritoire() + " : " + territAttaq.getRegiments());
		System.out.println("Le défenseur a perdu " + countDf + " régiments. " + " Nombre de régiments restants au défenseur dans le territoire " + territDef.getNomTerritoire() + " : " +  territDef.getRegiments());
	}
	
	// Méthode pour donner toutes les cartes, une fois perdus tous les territoires
	public void recevoirCartesTerritoires(Joueur joueurPerdant) {
		if(joueurPerdant.getCartesTerritoires().size() >= 1) {
			for(int i = 0; i < joueurPerdant.getCartesTerritoires().size(); i++) {
				territAttaquant.getJoueurPossedantTerritoire().ajouterCarteTerritoire(joueurPerdant.getCartesTerritoires().get(i));
			}
			joueurPerdant.setCartesTerritoires(null);
		}
	}
}
