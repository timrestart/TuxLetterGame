package game;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Dico {

	private ArrayList<String> listeNiveau1;
	private ArrayList<String> listeNiveau2;
	private ArrayList<String> listeNiveau3;
	private ArrayList<String> listeNiveau4;
	private ArrayList<String> listeNiveau5;
	private String cheminFichierDico;

	public Dico(String cheminFichierDico) throws SAXException, IOException {
		listeNiveau1 = new ArrayList<String>();
		listeNiveau2 = new ArrayList<String>();
		listeNiveau3 = new ArrayList<String>();
		listeNiveau4 = new ArrayList<String>();
		listeNiveau5 = new ArrayList<String>();
		this.cheminFichierDico = cheminFichierDico;

		lireDictionnaireDOM(cheminFichierDico);
	}

	public String getMotDepuisListeNiveau(int niveau) {
		String mot = "";
		switch (verifierNiveau(niveau)) {
			case 1:
				mot = getMotDepuisListe(listeNiveau1);
				break;
			case 2:
				mot = getMotDepuisListe(listeNiveau2);
				break;
			case 3:
				mot = getMotDepuisListe(listeNiveau3);
				break;
			case 4:
				mot = getMotDepuisListe(listeNiveau4);
				break;
			case 5:
				mot = getMotDepuisListe(listeNiveau5);
				break;
			default:
				break;
		}
		return mot;
	}

	public void ajouteMotADico(int niveau, String mot) {
		switch (verifierNiveau(niveau)) {
			case 1:
				listeNiveau1.add(mot);
				break;
			case 2:
				listeNiveau2.add(mot);
				break;
			case 3:
				listeNiveau3.add(mot);
				break;
			case 4:
				listeNiveau4.add(mot);
				break;
			case 5:
				listeNiveau5.add(mot);
				break;
			default:
				break;
		}
	}

	public String getChemnFichierDico() {
		return cheminFichierDico;
	}

	public void lireDictionnaireDOM(String path) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse(path);
		Document doc = parser.getDocument();

		NodeList nodeListMots;
		nodeListMots = doc.getElementsByTagName("mot");

		for (int i = 0; i < nodeListMots.getLength(); i++) {
			Element elem = (Element) nodeListMots.item(i);
			int attrib = Integer.parseInt(elem.getAttribute("niveau"));
			ajouteMotADico(attrib, elem.getFirstChild().getNodeValue());
		}
	}

	private int verifierNiveau(int niveau) {
		int niv;
		if (niveau >= 1 && niveau <= 5) {
			niv = niveau;
		} else {
			niv = 1;
		}
		return niv;
	}

	private String getMotDepuisListe(ArrayList<String> liste) {
		int randIndex = (int) (Math.random() * (liste.size() - 1));
		String mot = "???";
		if (!liste.isEmpty()) {
			mot = liste.get(randIndex);
		}
		return mot;
	}
}
