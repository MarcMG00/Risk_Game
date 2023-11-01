package risk.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Risk {
    private int numTour;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte_Territoire> cartes;
	private HashMap<Territoire, Territoire_Adjacent> territoiresAdjacents;
	private ArrayList<Continent> continents;
	private ArrayList<Territoire> territoires;
	
	public Risk() {
		this.joueurs = new ArrayList<Joueur>();
		this.cartes = new ArrayList<Carte_Territoire>();
		this.territoiresAdjacents = new  HashMap<Territoire, Territoire_Adjacent>();
		this.continents = new ArrayList<Continent>();
		this.territoires = new ArrayList<Territoire>();
	}
	
	public ArrayList<Territoire> lectureTerritoires(String fileName) {
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] territoires = ligne.split(";");
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
					if(territoiresL.get(iTerrit).getNomTerritoire().equals(cartesTerritoire[1])) {
						this.cartes.add(new Carte_Territoire(Integer.parseInt(cartesTerritoire[0]), territoiresL.get(iTerrit), cartesTerritoire[2]));
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
	
	public ArrayList<Continent> lectureContinents(String fileName) {
		ArrayList<Territoire> territoiresLL = lectureTerritoires("data/Territoires.txt");
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
				for(int iLStr = 3; iLStr < continentL.length; iLStr++) {
					territoiresContinentAReinitialiserStr.add(continentL[iLStr]);
				}
				for(int iTerrit = 0; iTerrit < territoiresLL.size(); iTerrit++) {
					if(territoiresContinentAReinitialiserStr.contains(territoiresLL.get(iTerrit).getNomTerritoire())) {
						territoiresContinentAReinitialiserT.add(territoiresLL.get(iTerrit));
					}
				}
				this.continents.add(new Continent(Integer.parseInt(continentL[0]), continentL[1], Integer.parseInt(continentL[2]), territoiresContinentAReinitialiserT));
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
	
	public HashMap<Territoire, Territoire_Adjacent> lectureTerritoiresAdjacents(String fileName) {
		ArrayList<Territoire> territoires = lectureTerritoires("data/Territoires.txt");
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] territoiresAdjacents = ligne.split(";");
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
		
		return this.territoiresAdjacents;
	}
	
	public void lancerPartie() {
		ArrayList<Territoire> territoiresF = lectureTerritoires("data/Territoires.txt");
		for(Territoire t : territoiresF) {
			System.out.println(t.getNumTerritoire() + " "+ t.getNomTerritoire());
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
		
	}
	
}
