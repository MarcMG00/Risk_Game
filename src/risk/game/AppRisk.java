package risk.game;

import java.util.ArrayList;
import java.util.Scanner;

import risk.modele.Joueur;
import risk.modele.Risk;

public class AppRisk {
	
	public static Risk chargementPartie() {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		Risk risk = new Risk();
		
		System.out.println("Combien de joueurs souhaitez-vous jouer au jeu du Risk ? (min 2, max 5)");
		Scanner sc = new Scanner(System.in);
		int nbJoueurs = sc.nextInt();
		sc.useDelimiter(";|\r?\n|\r");
		
		// Si la valeur rentrée est un string ou n'est pas dans l'intervalle, on demande à nouveau de saisir une valeur
		if(nbJoueurs > 5 || nbJoueurs < 2) {
			while(nbJoueurs > 5 || nbJoueurs < 2) {
				System.out.println("Vous ne pouvez jouer qu'au minimum 2 et maximum 5 joueurs. Saisissez à nouveau un nombre dans cet intervale :");
				nbJoueurs = sc.nextInt();
				sc.useDelimiter(";|\r?\n|\r");
			}
		}
		
		System.out.println("Vous êtes : " + nbJoueurs + " joueurs. Saisissez le nom et prénom pour chaque joueur : ");
		// On ajoute autant de joueurs, que de joueurs choisis
		for(int i = 1; i < nbJoueurs+1; i++) {
			System.out.println("Nom du joueur " + i);
			String nomJoueur = sc.next();
			System.out.println("Prénom du joueur " + i);
			String prenomJoueur = sc.next();
			joueurs.add(new Joueur(nomJoueur, prenomJoueur));
		}
		
		// On donne à chaque joueur un nombre de régiments de départ, proportionnel au nombre de joueurs
		if(joueurs.size() == 2) {
			for(Joueur j : joueurs) {
				j.setNbRegimentsRecusParTour(40);
			}
		}
		else if(joueurs.size() == 3) {
			for(Joueur j : joueurs) {
				j.setNbRegimentsRecusParTour(35);
			}
		}
		else if(joueurs.size() == 4) {
			for(Joueur j : joueurs) {
				j.setNbRegimentsRecusParTour(30);
			}
		}
		else {
			for(Joueur j : joueurs) {
				j.setNbRegimentsRecusParTour(25);
			}
		}
		
		// On ajoute les joueurs à la partie
		risk.setJoueurs(joueurs);
		
		return risk;
	}
	
	public static void main(String[] s) {
		Risk risk = chargementPartie();
		System.out.println();
		risk.lancerPartie();
	}
}
