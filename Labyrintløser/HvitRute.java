class HvitRute extends Rute {
	
	public HvitRute(int rad, int kolonne) {
		super(rad, kolonne);
	}
	
	public char tilTegn() {
		return '.';
	}
	
	public void gaa(Rute forrigeRute, String koordinatStreng, Liste<String> utveier) {
		for (int i = 0; i < naboListe.size(); i++) {	
			if (naboListe.get(i) != forrigeRute) {
				if (koordinatStreng == "") {
					naboListe.get(i).gaa(this, koordinatStreng + "(" + this.rad + "," + this.kolonne + ")", utveier);
				}
				else {
					naboListe.get(i).gaa(this, koordinatStreng + " --> " + "(" + this.rad + "," + this.kolonne + ")", utveier);
				}
			}
		}
	}
}