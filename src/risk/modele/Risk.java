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
		
	}
	
	public ArrayList<Carte_Territoire> lectureCartesTerritoire(String fileName) {
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String ligne;
			while ((ligne = bufferedReader.readLine()) != null) {
				String[] carteTerritoire = ligne.split(";");
				
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
	
	public HashMap<Territoire, Territoire_Adjacent> lectureTerritoiresAdjacents(String fileName) {
		return this.territoiresAdjacents;
	}
	
	public ArrayList<Continent> lectureContinents(String fileName) {
		return this.continents;
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
				this.territoires.add(new Territoire(Integer.parseInt(territoires[0]), territoires[1]));
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
	
}
