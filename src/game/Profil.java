package game;

import java.util.ArrayList;

public class Profil {

	private String nom;
	private String avatar;
	private String anniversaire;
	private ArrayList<Partie> parties;

	public Profil() {
		this.nom = "Alban";
		this.anniversaire = "2006-05-08";
		parties = new ArrayList<Partie>();
		parties.add(new Partie("2022-11-11", 2, "test"));
	}

	public Profil(String nom, String dateNaissance) {
		this.nom = nom;
		this.anniversaire = dateNaissance;
		parties = new ArrayList<Partie>();
		parties.add(new Partie("2022-11-11", 2, "test"));
	}

	public Boolean charge(String nomJoueur) {
		Boolean joueurFound = false;
		joueurFound = this.nom.equals(nomJoueur);
		return joueurFound;
	}

	public void addPartie(Partie partie) {
		parties.add(partie);
	}

	public Boolean partieFound(String mot, String date) {
		Boolean found = false;
		int i = 0;
		while (i < parties.size() && !mot.equals(parties.get(i).getMot()) && !date.equals(parties.get(i).getDate())) {
			i++;
		}
		if (i < parties.size()) {
			found = true;
		}
		return found;
	}

	public Partie getPartie(String mot, String date) {
		Partie partie = null;

		int i = 0;
		while (i < parties.size() && mot.equals(parties.get(i).getMot()) && date.equals(parties.get(i).getDate())) {
			i++;
		}
		if (i < parties.size()) {
			partie = parties.get(i);
		}

		return partie;
	}

}
