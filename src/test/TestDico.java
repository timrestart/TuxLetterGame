package test;

import game.Dico;
import java.io.IOException;
import org.xml.sax.SAXException;

public class TestDico {

	public static void main(String[] args) throws SAXException, IOException {
		Dico dico = new Dico("src/data/xml/dico.xml");
		String s = dico.getMotDepuisListeNiveau(1);
		System.out.println(s);

	}
}
