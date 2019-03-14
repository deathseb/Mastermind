package fr.rasen.mastermind.JeuPm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Propriete {
	private static final Logger logger = LogManager.getLogger();

	private Integer nbTours;
	private Integer nbChiffre;
	private boolean modeDev;

	public Propriete() {
		Properties prop = new Properties();	
		FileInputStream in ;
		try {
			in = new FileInputStream("ressources/infoPM.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			logger.error("La lecture du fichier properties a échoué");
		}
		nbTours = Integer.parseInt(prop.getProperty("nbTours"));
		nbChiffre = Integer.parseInt(prop.getProperty("nbChiffre"));
		String dev = prop.getProperty("modeDev");
		if (dev.equals("true"))
			modeDev = true;
		else
			modeDev = false;
		logger.trace("Properties chargées.");
	}

	/**
	 * Sauvegarde dans le fichier properties.
	 */
	public void sauvegarde() {
		Properties properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("ressources/infoPM.properties");
			properties.load(fis);
			properties.setProperty("nbTours", String.valueOf(nbTours));
			properties.setProperty("nbChiffre", String.valueOf(nbChiffre));
			properties.setProperty("modeDev", String.valueOf(modeDev));
			FileOutputStream fos = new FileOutputStream("ressources/infoPM.properties");
			properties.store(fos,null);
			fis.close();
			fos.close();
		} catch (Exception e) {
			logger.error("La sauvegarde dans le fichier properties a échoué.");
		}
		logger.trace("Sauvegarde du fichier properties réussi.");
	}

	public int getNbTours() {
		return nbTours;
	}

	public void setNbTours(int nbTours) {
		this.nbTours = nbTours;
	}

	public int getNbChiffre() {
		return nbChiffre;
	}

	public void setNbChiffre(int nbChiffre) {
		this.nbChiffre = nbChiffre;
	}

	public boolean isModeDev() {
		return modeDev;
	}

	public void setModeDev(boolean modeDev) {
		this.modeDev = modeDev;
	}
	
	public String toString() {
		String str = "Nombre de tours = " + nbTours + "\nNombre de chiffre de la combinaison = " + nbChiffre + "\nMode développeur activé = " + modeDev;
		return str;
	}

}
