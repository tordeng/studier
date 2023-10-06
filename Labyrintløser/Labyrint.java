import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

class Labyrint {
	Rute[][] labRutenett;
	int antKolonner;
	int antRader;
	
	public Labyrint(int kolonner, int rader) {
		antKolonner = kolonner;
		antRader = rader;
		labRutenett = new Rute[rader][kolonner];
		for (int i = 0; i < antRader; i++) {
			for (int j = 0; j < antKolonner; j++) {
				if (i > 0) {
					labRutenett[i][j].leggTilNabo(labRutenett[i-1][j]);
				}
				else if (i < antRader -1) {
					labRutenett[i][j].leggTilNabo(labRutenett[i+1][j]);
				}
				if (j > 0) {
					labRutenett[i][j].leggTilNabo(labRutenett[i][j-1]);
				}
				else if (j < antKolonner -1) {
					labRutenett[i][j].leggTilNabo(labRutenett[i][j+1]);
				}
			}
		}
	}
	
	//Kan sjekke en enkelt array for antall kolonner, siden alle er like store.
	private Labyrint(Rute[][] ruteArray) {
		antRader = ruteArray.length;
		antKolonner = ruteArray[0].length;
		labRutenett = ruteArray;
		for (int i = 0; i < antRader; i++) {
			for (int j = 0; j < antKolonner; j++) {
				if (i > 0) {
					labRutenett[i][j].leggTilNabo(labRutenett[i-1][j]);
				}
				if (i < antRader -1) {
					labRutenett[i][j].leggTilNabo(labRutenett[i+1][j]);
				}
				if (j > 0) {
					labRutenett[i][j].leggTilNabo(labRutenett[i][j-1]);
				}
				if (j < antKolonner -1) {
					labRutenett[i][j].leggTilNabo(labRutenett[i][j+1]);
				}
			}
		}
	}
	
	public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
		// File fil = new File(fil);
		Scanner labInnlesing = new Scanner(fil);
		int rader = labInnlesing.nextInt();
		int kolonner = labInnlesing.nextInt();
		labInnlesing.nextLine();
		Rute[][] labListe = new Rute[rader][kolonner];
		for (int i = 0; i < rader; i++) { 
			String tegn = labInnlesing.nextLine();
			for (int j = 0; j < tegn.length(); j++) {
				if (tegn.charAt(j) == '#') 
					labListe[i][j] = new SortRute(i,j);
				else if (tegn.charAt(j) == '.') {
					if (i == 0 || i == rader-1 || j == 0 || j == kolonner-1)
						labListe[i][j] = new Aapning(i,j);
					else 
						labListe[i][j] = new HvitRute(i,j);
				}
			}
		}
		Labyrint nyLab = new Labyrint(labListe);
		return nyLab;
	}
	
	public Rute[][] ruteNett() {
		return labRutenett;
	}
	
	public int hentAntRader() {
		return this.antRader;
	}
	
	public int hentAntKolonner() {
		return this.antKolonner;
	}
	
	public Rute hentRute(int rad, int kolonne) {
		return labRutenett[rad][kolonne];
	}
	
	public void skrivLab() {
		String utSkrivLabyrint = "";
		for (int i = 0; i < antRader; i++) {
			for (int j = 0; j < antKolonner; j++) {
				System.out.print(labRutenett[i][j].tilTegn());
			}
			System.out.println("\n");
		}
	}
	
	public Liste<String> finnUtveiFra(int rad, int kol) {
		labRutenett[rad][kol].finnUtvei();
		return labRutenett[rad][kol].hentUtveier();
	}
}