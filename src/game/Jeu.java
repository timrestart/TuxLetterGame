package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;

public class Jeu {

	private Env env;
	private Room room;
	private Profil profil;
	private ArrayList<Letter> letters;
	private Dico dico;

	public Jeu() throws SAXException, IOException {
		// Crée un nouvel environnement
		env = new Env();

		// Instancie une Room
		room = new Room();

		// Règle la camera
		env.setCameraXYZ(50, 60, 175);
		env.setCameraPitch(-20);

		// Désactive les contrôles par défaut
		env.setDefaultControl(false);

		// Instancie un profil par défaut
		profil = new Profil();

		//Instancier une liste de lettres
		letters = new ArrayList<Letter>();

		// Instancier un dictionnaire
		dico = new Dico("src/data/xml/dico.xml");
	}

	public void execute() {
		// pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
		// et nous créons une partie vide, juste pour que cela fonctionne
		joue(new Partie());

		// Détruit l'environnement et provoque la sortie du programme 
		env.exit();
	}

	public void joue(Partie partie) {
		// TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
		env.setRoom(room);

		// Instancie un Tux
		Tux tux = new Tux(env, room); //??
		env.addObject(tux);

		// Charger un mot au hasard depuis le dictionnaire
		String mot = dico.getMotDepuisListeNiveau(1);

		// Initialise une liste de lettre du mot precedent
		Letter letter;
		for (int i = 0; i < mot.length(); i++) {
			letter = new Letter(env, room, mot.charAt(i), (i + 1) * 10, 75);
			letters.add(letter);
		}

		// Affiche ces letters dans l'environnement
		for (int i = 0; i < letters.size(); i++) {
			env.addObject(letters.get(i));
		}

		// Ici, on peut initialiser des valeurs pour une nouvelle partie
		demarrePartie(partie);

		// Boucle de jeu
		Boolean finished;
		finished = false; //??
		while (!finished) {

			// Contrôles globaux du jeu (sortie, ...)
			//1 is for escape key
			if (env.getKey() == 1) {
				finished = true; //??
			}

			// Contrôles des déplacements de Tux (gauche, droite, ...)
			tux.deplace();

			// Ici, on applique les regles
			appliqueRegles(partie);

			// Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
			env.advanceOneFrame();
		}

		// Ici on peut calculer des valeurs lorsque la partie est terminée
		terminePartie(partie);
	}

	protected void demarrePartie(Partie partie) {

	}

	protected void appliqueRegles(Partie partie) {

	}

	protected void terminePartie(Partie partie) {

	}
}
