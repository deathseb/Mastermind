package fr.rasen.mastermind.JeuPm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class Propriete {
	private int nbTours;
	private int nbChiffre;
	private boolean modeDev;

	public Propriete() {
		Properties prop = new Properties();	
		FileInputStream in ;
		try {
			in = new FileInputStream("src/infoPM.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nbTours = Integer.parseInt(prop.getProperty("nbTours"));
		nbChiffre = Integer.parseInt(prop.getProperty("nbChiffre"));
		String dev = prop.getProperty("modeDev");
		if (dev.equals("true"))
			modeDev = true;
		else
			modeDev = false;
	}
	
	public void sauvegarde() {
		Properties properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("src/infoPM.properties");
			properties.load(fis);
			properties.setProperty("nbTours", String.valueOf(nbTours));
			properties.setProperty("nbChiffre", String.valueOf(nbChiffre));
			properties.setProperty("modeDev", String.valueOf(modeDev));
			FileOutputStream fos = new FileOutputStream("src/infoPM.properties");
			properties.store(fos,null);
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Done!");
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
