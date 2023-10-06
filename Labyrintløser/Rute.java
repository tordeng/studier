import java.util.List;
import java.util.ArrayList;

abstract class Rute {
	int kolonne;
	int rad;
	Labyrint rutensLab;
	List<Rute> naboListe = new ArrayList<Rute>();
	List<Rute> utgangListe = new ArrayList<Rute>();
	String koordinatStreng;
	Liste<String> utveiListe = new Lenkeliste<String>();
	
	//Naboer vil være på indeks foran og bak, samt samme indeks på forrige og neste array.
	//Det vil si [indeks][indeks+-1], [indeks+-][indeks]
	public Rute(int rad, int kolonne) {
		this.kolonne = kolonne;
		this.rad = rad;
	}
	
	public void leggTilNabo(Rute nabo) {
		naboListe.add(nabo);
	}
	
	// testmetode for et problem jeg hadde
	public void skrivUtNaboer() {
		System.out.println(naboListe.size());
		for (Rute nabo : naboListe) { 
			System.out.println(nabo.rad + "," + nabo.kolonne);
		}
	}
	
	// Skal returnere rutens tegnrepresentasjon. Hvite ruter = . Sorte ruter = #
	public abstract char tilTegn();
	
	// Rekursiv abstrakt metode, implementeres i subklassene.
	public abstract void gaa(Rute forrigeRute, String koordinatStreng, Liste<String> utveier);
	
	// Rekursiv metode, byttet med en abstrakt en som implementeres i subklassene.
	// public void gaa(Rute forrigeRute, String koordinatStreng, Liste<String> utveier) {
		// if (this.getClass() == Aapning.class) {
			// koordinatStreng += " --> " + "(" + this.rad + "," + this.kolonne + ")";
			// utveier.leggTil(koordinatStreng);
		// }
		// for (int i = 0; i < naboListe.size(); i++) {
			// if (naboListe.get(i) != forrigeRute && (naboListe.get(i).getClass().equals(HvitRute.class) || naboListe.get(i).getClass().equals(Aapning.class))) {	
				
				// if (koordinatStreng == "") {
					// naboListe.get(i).gaa(this, koordinatStreng + "(" + this.rad + "," + this.kolonne + ")", utveier);
				// }
				// else {
					// naboListe.get(i).gaa(this, koordinatStreng + " --> " + "(" + this.rad + "," + this.kolonne + ")", utveier);
				// }
			// }
		// }
	// }
		
	public Liste<String> hentUtveier() {
		return utveiListe;
	}
	
	public void finnUtvei() {
		String tomStreng = "";
		this.gaa(null, tomStreng, utveiListe);
	}		
}