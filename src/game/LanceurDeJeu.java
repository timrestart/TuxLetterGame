package game;

import java.io.IOException;
import org.xml.sax.SAXException;

public class LanceurDeJeu {

	public static void main(String[] args) throws SAXException, IOException {
		Jeu jeu;
		jeu = new JeuDevineLeMotOrdre();
		jeu.execute();
	}

}
