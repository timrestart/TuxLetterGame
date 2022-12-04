package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;
import org.lwjgl.input.Keyboard;
import java.util.concurrent.TimeUnit;

public abstract class Jeu {

	enum MENU_VAL {
		MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
	}

	protected final Env env; // or protected
	private Tux tux;
	private final Room mainRoom;
	private final Room menuRoom;
	protected EnvTextMap menuText;                         //text (affichage des texte du jeu)
	private Profil profil;
	private final Dico dico;
	protected ArrayList<Letter> letters; // or protected

	public Jeu() throws SAXException, IOException {
		// Crée un nouvel environnement
		env = new Env();

		// Instancie une Room
		mainRoom = new Room();

		// Instancie une autre Room pour les menus
		menuRoom = new Room();
		menuRoom.setTextureEast("textures/black.png");
		menuRoom.setTextureWest("textures/black.png");
		menuRoom.setTextureNorth("textures/black.png");
		menuRoom.setTextureBottom("textures/black.png");

		// Règle la camera
		env.setCameraXYZ(50, 60, 175);
		env.setCameraPitch(-20);

		// Désactive les contrôles par défaut
		env.setDefaultControl(false);

		// Instancie un profil par défaut
		profil = new Profil();

		// Dictionnaire
		dico = new Dico("src/data/xml/dico.xml");

		// instancie le menuText
		menuText = new EnvTextMap(env);

		// Textes affichés à l'écran
		// Menu principal
		menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
		menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
		menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);

		// Menu jeu
		menuText.addText("Voulez vous ?", "Question", 200, 300);
		menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
		menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
		menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
		menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);

		// Saisie des information du joueur
		menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
		menuText.addText("Donnez votre date de naissance : ", "DateNaissanceJoueur", 200, 300);

		// Saisie des information de la partie
		menuText.addText("Choisissez un niveau pour la partie : ", "NiveauPartie", 200, 300);
		menuText.addText("Donnez le mot de la partie recherchée : ", "MotPartie", 200, 300);
		menuText.addText("Donnez le mot de la partie recherchée : ", "DatePartie", 200, 300);
	}

	/**
	 * Gère le menu principal
	 *
	 */
	public void execute() throws InterruptedException {

		MENU_VAL mainLoop;
		mainLoop = MENU_VAL.MENU_SORTIE;
		do {
			mainLoop = menuPrincipal();
		} while (mainLoop != MENU_VAL.MENU_SORTIE);
		this.env.setDisplayStr("Au revoir !", 300, 30);
		env.exit();
	}

	// renvoie le nom saisi par l'utilisateur
	private String getNomJoueur() {
		String nomJoueur = "";
		menuText.getText("NomJoueur").display();
		nomJoueur = menuText.getText("NomJoueur").lire(true);
		menuText.getText("NomJoueur").clean();
		return nomJoueur;
	}

	// renvoie la date de naissance saisie par l'utilisateur
	private String getDateNaissanceJoueur() {
		String date = "";
		menuText.getText("DateNaissanceJoueur").display();
		date = menuText.getText("DateNaissanceJoueur").lire(true);
		menuText.getText("DateNaissanceJoueur").clean();
		return date;
	}

	// renvoie le niveau saisi par l'utilisateur
	private String getNiveauPartie() {
		String niv = "";
		menuText.getText("NiveauPartie").display();
		niv = menuText.getText("NiveauPartie").lire(true);
		menuText.getText("NiveauPartie").clean();
		return niv;
	}

	// renvoie le mot saisi par l'utilisateur
	private String getMotPartie() {
		String mot = "";
		menuText.getText("MotPartie").display();
		mot = menuText.getText("MotPartie").lire(true);
		menuText.getText("MotPartie").clean();
		return mot;
	}

	// renvoie la date de la partie recherchée saisie par l'utilisateur
	private String getDatePartie() {
		String date = "";
		menuText.getText("DatePartie").display();
		date = menuText.getText("DatePartie").lire(true);
		menuText.getText("DatePartie").clean();
		return date;
	}

	// fourni, à compléter
	private MENU_VAL menuJeu() throws InterruptedException {

		MENU_VAL playTheGame;
		playTheGame = MENU_VAL.MENU_JOUE;
		Partie partie;

		do {
			// restaure la room du menu
			env.setRoom(menuRoom);

			// affiche menu
			menuText.getText("Question").display();
			menuText.getText("Jeu1").display();
			menuText.getText("Jeu2").display();
			menuText.getText("Jeu3").display();
			menuText.getText("Jeu4").display();

			// vérifie qu'une touche 1, 2, 3 ou 4 est pressée
			int touche = 0;
			while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
				touche = env.getKey();
				env.advanceOneFrame();
			}

			// nettoie l'environnement du texte
			menuText.getText("Question").clean();
			menuText.getText("Jeu1").clean();
			menuText.getText("Jeu2").clean();
			menuText.getText("Jeu3").clean();
			menuText.getText("Jeu4").clean();

			// et décide quoi faire en fonction de la touche pressée
			switch (touche) {
				// -----------------------------------------
				// Touche 1 : Commencer une nouvelle partie
				// -----------------------------------------                
				case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
					// TODO: choisir un niveau
					int niv = Integer.parseInt(getNiveauPartie());

					// TODO: charger un mot depuis le dico pour le niveau donne
					String mot = dico.getMotDepuisListeNiveau(niv);

					// TODO: crée un nouvelle partie
					partie = new Partie("2018-09-7", niv, mot);

					// afficher le mot pendant 5 secondes sur un menu avant de jouer
					// BUG: delai de 5 secondes mais le mot n'est pas affiché et le texte precedent reste affiché
					menuText.addText(mot.toUpperCase(), "MotStart", 200, 300);
					menuText.getText("MotStart").display();
					TimeUnit.SECONDS.sleep(5);
					menuText.getText("MotStart").clean();

					// joue
					joue(partie);

					// TODO: enregistre la partie dans le profil --> enregistre le profil
					// ajouter la partie au profil du joueur
					profil.addPartie(partie);

					// Update playTheGame
					playTheGame = MENU_VAL.MENU_JOUE;
					break;

				// -----------------------------------------
				// Touche 2 : Charger une partie existante
				// -----------------------------------------                
				case Keyboard.KEY_2: // charge une partie existante
					//partie = new Partie("2018-09-7", 1, "test");

					// TODO: Recupère le mot de la partie existante
					String motPartie = getMotPartie();
					String datePartie = getMotPartie();

					if (profil.partieFound(motPartie, datePartie)) {
						partie = profil.getPartie(motPartie, datePartie);
					} else {
						playTheGame = MENU_VAL.MENU_CONTINUE;
						break;
					}

					// joue
					joue(partie);

					// TODO: enregistre la partie dans le profil --> enregistre le profil
					// ajouter la partie au profil du joueur
					profil.addPartie(partie);

					// Update playTheGame
					playTheGame = MENU_VAL.MENU_JOUE;
					break;

				// -----------------------------------------
				// Touche 3 : Sortie de ce jeu
				// -----------------------------------------                
				case Keyboard.KEY_3:
					playTheGame = MENU_VAL.MENU_CONTINUE;
					break;

				// -----------------------------------------
				// Touche 4 : Quitter le jeu
				// -----------------------------------------                
				case Keyboard.KEY_4:
					playTheGame = MENU_VAL.MENU_SORTIE;
			}
		} while (playTheGame == MENU_VAL.MENU_JOUE);
		return playTheGame;
	}

	private MENU_VAL menuPrincipal() throws InterruptedException {

		MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
		String nomJoueur;
		String dateNaissanceJoueur;

		// restaure la room du menu
		env.setRoom(menuRoom);

		menuText.getText("Question").display();
		menuText.getText("Principal1").display();
		menuText.getText("Principal2").display();
		menuText.getText("Principal3").display();

		// vérifie qu'une touche 1, 2 ou 3 est pressée
		int touche = 0;
		while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
			touche = env.getKey();
			env.advanceOneFrame();
		}

		menuText.getText("Question").clean();
		menuText.getText("Principal1").clean();
		menuText.getText("Principal2").clean();
		menuText.getText("Principal3").clean();

		// et décide quoi faire en fonction de la touche pressée
		switch (touche) {
			// -------------------------------------
			// Touche 1 : Charger un profil existant
			// -------------------------------------
			case Keyboard.KEY_1:
				// demande le nom du joueur existant
				nomJoueur = getNomJoueur();

				// charge le profil de ce joueur si possible
				if (profil.charge(nomJoueur)) {
					choix = menuJeu();
				} else {
					choix = MENU_VAL.MENU_SORTIE;//CONTINUE;
				}
				break;

			// -------------------------------------
			// Touche 2 : Créer un nouveau joueur
			// -------------------------------------
			case Keyboard.KEY_2:
				// demande le nom du nouveau joueur
				nomJoueur = getNomJoueur();
				dateNaissanceJoueur = getDateNaissanceJoueur();
				// crée un profil avec le nom d'un nouveau joueur
				profil = new Profil(nomJoueur, dateNaissanceJoueur);
				choix = menuJeu();
				break;

			// -------------------------------------
			// Touche 3 : Sortir du jeu
			// -------------------------------------
			case Keyboard.KEY_3:
				choix = MENU_VAL.MENU_SORTIE;
		}
		return choix;
	}

	public void joue(Partie partie) throws InterruptedException {

		String mot = partie.getMot();

		// restaure la room du jeu
		env.setRoom(mainRoom);

		// Instancie un Tux
		tux = new Tux(env, mainRoom);
		env.addObject(tux);

		// Instancie la liste letters
		letters = new ArrayList<Letter>();

		// Initialise une liste de lettre du mot
		// BUG: il faut trouver la bonne taille pour placer les lettres
		Letter letter;
		double xStart = 10.0;
		double zStart = 10.0;
		double xEnd = mainRoom.getWidth() * 0.9;
		double zEnd = mainRoom.getDepth() * 0.9;
		double randX;
		double randZ;
		for (int i = 0; i < mot.length(); i++) {
			randX = Math.random() * (xEnd - xStart + 1) - xStart;
			randZ = Math.random() * (zEnd - zStart + 1) - zStart;
			letter = new Letter(env, mainRoom, mot.charAt(i), (i + 1) * 10, 75);
			//letter = new Letter(env, mainRoom, mot.charAt(i), randX, randZ);
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
