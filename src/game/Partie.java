package game;

public class Partie {

	private String date;
	private int niveau;
	private String mot;
	private int trouve;
	private int temps;
	private Boolean fini;

	public Partie(String date, int niveau, String mot) {
		this.date = date;
		this.niveau = niveau;
		this.mot = mot;
		this.fini = false;
	}

	public void setTrouve(int nbLettresRestantes) {
		double nbLettresTrouvees = mot.length() - nbLettresRestantes;
		double ratio = nbLettresTrouvees / mot.length();
		trouve = (int) (ratio * 100);
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}

	public int getTrouve() {
		return trouve;
	}

	public int getTemps() {
		return temps;
	}

	public Boolean getFini() {
		return fini;
	}

	public int getNiveau() {
		return niveau;
	}

	public String getMot() {
		return mot;
	}

	@Override
	public String toString() {
		String s = "";
		return s;
	}

}
