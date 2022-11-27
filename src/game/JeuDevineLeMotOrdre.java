package game;

import java.io.IOException;
import org.xml.sax.SAXException;

public class JeuDevineLeMotOrdre extends Jeu {

	private int nbLettresRestantes;
	private Chronometre chrono;

	public JeuDevineLeMotOrdre() throws SAXException, IOException {
		super();
	}

	@Override
	protected void demarrePartie(Partie partie) {
		// initialiser chrono
		int limChrono = 20;
		chrono = new Chronometre(limChrono);
		chrono.start();
		chrono.stop();

		// initialiser nbLettresRestantes avec nbLettres du mot de la partie
		nbLettresRestantes = partie.getMot().length();

	}

	@Override
	protected void appliqueRegles(Partie partie) {
		// indice de la premiere premiere lettre restante
		int li;

		// s'il reste du temps: 
		// et si lettre trouvee dans l'ordre: nbLettresRestantes diminue de 1
		if (chrono.remainsTime()) {
			li = partie.getMot().length() - nbLettresRestantes;
			if (li < partie.getMot().length() && tuxTrouveLettre(li)) {
				System.out.println("Letter found");
				env.removeObject(letters.get(li));
				nbLettresRestantes = nbLettresRestantes - 1;
			}
			if (nbLettresRestantes == 0) {
				partie.setFini(true);
			}
		} else {
			partie.setFini(true);
		}
	}

	@Override
	protected void terminePartie(Partie partie) {
		if (!chrono.remainsTime()) {
			partie.setTrouve(nbLettresRestantes);
			System.out.println("Loss : " + partie.getMot() + " " + partie.getTrouve() + "%");
		} else {
			partie.setTemps(chrono.timeSpent());
			partie.setTrouve(0);
			System.out.println("Win : " + partie.getMot() + " " + partie.getTemps() + "s et " + partie.getTrouve() + "%");
		}
	}

	private Boolean tuxTrouveLettre(int i) {
		Boolean trouvee = collision(letters.get(i));
		return trouvee;
	}
}
