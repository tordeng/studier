class Aapning extends HvitRute {
	public Aapning(int rad, int kolonne) {
		super(rad, kolonne);
	}
	
	public void gaa(Rute forrigeRute, String koordinatStreng, Liste<String> utveier) {
		koordinatStreng += " --> " + "(" + this.rad + "," + this.kolonne + ")";
		utveier.leggTil(koordinatStreng);
	}
}