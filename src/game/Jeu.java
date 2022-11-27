package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;

public abstract class Jeu {

	protected Env env;
	private Room room;
	private Profil profil;
	private Tux tux;
	protected ArrayList<Letter> letters;
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

		// Instancie un Tux
		tux = new Tux(env, room); //??

		//Instancier une liste de lettres
		letters = new ArrayList<Letter>();

		// Instancier un dictionnaire
		dico = new Dico("src/data/xml/dico.xml");
	}

	public void execute() {

		// Charger un mot au hasard depuis le dictionnaire
		int niv = 2;
		String mot = dico.getMotDepuisListeNiveau(niv);

		// instancier une nouvelle partie
		Partie p = new Partie("2022-11-27", niv, mot);

		// jouer la partie
		joue(p);

		// Détruit l'environnement et provoque la sortie du programme 
		env.exit();
	}

	public void joue(Partie partie) {
		// TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
		env.setRoom(room);

		// Afficher tux dans l'environnement
		env.addObject(tux);

		// Initialise une liste de lettre du mot
		// BUG: il faut trouver la bonne taille pour placer les lettres
		String mot = partie.getMot();
		Letter letter;
		double xStart = 10.0;
		double zStart = 10.0;
		double xEnd = room.getWidth() * 0.9;
		double zEnd = room.getDepth() * 0.9;
		double randX;
		double randZ;
		for (int i = 0; i < mot.length(); i++) {
			randX = Math.random() * (xEnd - xStart + 1) - xStart;
			randZ = Math.random() * (zEnd - zStart + 1) - zStart;
			letter = new Letter(env, room, mot.charAt(i), (i + 1) * 10, 75);
			//letter = new Letter(env, room, mot.charAt(i), randX, randZ);
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
		finished = false;
		while (!finished) {

			// Contrôles globaux du jeu (sortie, ...)
			//1 is for escape key
			if (env.getKey() == 1 || partie.getFini() == true) {
				finished = true;
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

	protected abstract void demarrePartie(Partie partie);

	protected abstract void appliqueRegles(Partie partie);

	protected abstract void terminePartie(Partie partie);

	protected double distance(Letter letter) {
		double dist;
		dist = Math.sqrt(
				Math.pow(letter.getX() - tux.getX(), 2)
				+ Math.pow(letter.getY() - tux.getY(), 2)
				+ Math.pow(letter.getZ() - tux.getZ(), 2)
		);
		return dist;
	}

	protected Boolean collision(Letter letter) {
		Boolean col;
		//col = tux.getX() == letter.getX() && tux.getX() == letter.getX();
		col = distance(letter) <= tux.getScale() * 1.4;
		return col;
	}
}
