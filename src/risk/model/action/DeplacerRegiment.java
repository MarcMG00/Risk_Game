package risk.model.action;

import risk.modele.Territoire;

public class DeplacerRegiment {
	private Territoire territDepart;
	private int nbRegiments;
	
	public DeplacerRegiment(Territoire territDepart, int nbRegiments) {
		this.territDepart = territDepart;
		this.nbRegiments = nbRegiments;
	}
	
	public boolean deplacerRegiment() {
		boolean demanderDeplacement = true;
		
		if(territDepart.getRegiments() < nbRegiments) {
			System.out.println("Vous ne poss�dez pas autant de r�giments dans ce territoire. Vous n'avez que " + territDepart.getRegiments() + " regiments");
			demanderDeplacement = false;
		}
		else if(territDepart.getRegiments() == nbRegiments) {
			System.out.println("Vous devez laisser au moins un r�giment sur votre territoire de d�part");
			demanderDeplacement = false;
		}
		else if(nbRegiments == 0) {
			System.out.println("Ecrivez une quantit� valide != 0");
			demanderDeplacement = false;
		}
		else {
			for(int j = 0; j < territDepart.getJoueurPossedantTerritoire().getTerritoires().size(); j++) {
				for(int i = 0; i < territDepart.getTerritoiresAdjacents().getTerritoiresAdjacents().size(); i++) {
					if(territDepart.getJoueurPossedantTerritoire().getTerritoires().get(j).equals(territDepart.getTerritoiresAdjacents().getTerritoiresAdjacents().get(i))) {
						System.out.println("Territoire : " + territDepart.getTerritoiresAdjacents().getTerritoiresAdjacents().get(i).getNumTerritoire() + " - " 
								+ territDepart.getTerritoiresAdjacents().getTerritoiresAdjacents().get(i).getNomTerritoire() + ", nombre de r�giments : "
								+ territDepart.getJoueurPossedantTerritoire().getTerritoires().get(j).getRegiments());
					}
				}
			}
		}
		return demanderDeplacement;
	}
}
