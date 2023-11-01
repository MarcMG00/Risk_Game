package risk.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Risk {
    private int numTour;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte_Territoire> cartes;
	private ArrayList<Continent> continents;
	private ArrayList<Territoire> territoires;
	
	public Risk() {
		this.joueurs = new ArrayList<Joueur>();
		this.cartes = new ArrayList<Carte_Territoire>();
		this.continents = new ArrayList<Continent>();
		this.territoires = new ArrayList<Territoire>();
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
		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
		
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] cartesTerritoire = ligne.split(";");
				for(int iTerrit = 0; iTerrit < territoiresL.size(); iTerrit++) {
					// On transforme les string en objets Territoire, si le nom est trouvé, alors on ajoute le territoire à sa carte correspondante
					if(territoiresL.get(iTerrit).getNomTerritoire().equals(cartesTerritoire[1])) {
						this.cartes.add(new Carte_Territoire(Integer.parseInt(cartesTerritoire[0]), territoiresL.get(iTerrit), cartesTerritoire[2]));
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
		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
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
				for(int iTerrit = 0; iTerrit < territoiresL.size(); iTerrit++) {
					if(territoiresContinentAReinitialiserStr.contains(territoiresL.get(iTerrit).getNomTerritoire())) {
						territoiresContinentAReinitialiserT.add(territoiresL.get(iTerrit));
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
		ArrayList<Territoire> territoiresL = lectureTerritoires("data/Territoires.txt");
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
				for(int iTerrit = 0; iTerrit < territoiresL.size(); iTerrit++) {
					if(territoiresAReinitialiserStr.contains(territoiresL.get(iTerrit).getNomTerritoire())) {
						territoiresAReinitialiserT.add(territoiresL.get(iTerrit));
					}
				}
				// Un objet Territoire_Adjacent correspond à une liste de Territoire
				Territoire_Adjacent territAdjAReinitialiser = new Territoire_Adjacent(territoiresAReinitialiserT);
				// A chaque territoire on lui ajoute le Territoire_Adjacent. Le territoire en question,se trouve à la position 0 du fichier
				for(int iTerrit2 = 0; iTerrit2 < territoiresL.size(); iTerrit2++) {
					if(territoiresL.get(iTerrit2).getNomTerritoire().equals(territoiresAdjacents[0])) {
						territoiresL.get(iTerrit2).setTerritoiresAdjacents(territAdjAReinitialiser);
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
		// Vérification des données
		ArrayList<Territoire> territoiresF = lectureTerritoires("data/Territoires.txt");
		for(Territoire t : territoiresF) {
			System.out.println(t.getNumTerritoire() + " " + t.getNomTerritoire());
		}
		System.out.println();
		
		ArrayList<Carte_Territoire> cartesF = lectureCartesTerritoire("data/CartesTerritoires.txt");
		for(Carte_Territoire ct : cartesF) {
			System.out.println(ct.getNumCarte() + " " + ct.getTerritoire().getNomTerritoire() + " " + ct.getTypeRegiment());
		}
		System.out.println();
		
		ArrayList<Continent> continentsF = lectureContinents("data/Continents.txt");
		for(Continent co : continentsF) {
			System.out.println(co.getNumContinent() + " " + co.getNomContinent()  + " " + co.getValeurBonus() + " " + co.getTerritoires());
		}
		System.out.println();
		
		lectureTerritoiresAdjacents("data/TerritoiresAdjacents.txt");
		for(Territoire t2 : territoiresF) {
			for(Territoire ta : t2.getTerritoiresAdjacents().getTerritoiresAdjacents()) {
				System.out.println(t2.getNomTerritoire() + " " + ta.getNomTerritoire());
			}
		}
		System.out.println();
	}
	
}
