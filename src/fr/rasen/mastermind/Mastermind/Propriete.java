package fr.rasen.mastermind.Mastermind;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Propriete {
    private int nbTours;
    private int nbChiffre;
    private boolean modeDev;
    private int nbCouleursMax;

    /**
     * Créer en lisant les informations du fichier properties.
     */
    public Propriete() {
        Properties prop = new Properties();
        FileInputStream in ;
        try {
            in = new FileInputStream("src/infoPM.properties");
            prop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nbTours = Integer.parseInt(prop.getProperty("nbTours"));
        nbChiffre = Integer.parseInt(prop.getProperty("nbChiffre"));
        String dev = prop.getProperty("modeDev");
        if (dev.equals("true"))
            modeDev = true;
        else
            modeDev = false;
        nbCouleursMax = Integer.parseInt(prop.getProperty("nbCouleursMax"));
    }

    /**
     * Sauvegarde le fichier properties.
     */
    public void sauvegarde() {
        Properties properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/infoPM.properties");
            properties.load(fis);
            properties.setProperty("nbTours", String.valueOf(nbTours));
            properties.setProperty("nbChiffre", String.valueOf(nbChiffre));
            properties.setProperty("modeDev", String.valueOf(modeDev));
            properties.setProperty("nbCouleursMax", String.valueOf(nbCouleursMax));
            FileOutputStream fos = new FileOutputStream("src/infoPM.properties");
            properties.store(fos,null);
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public int getNbCouleursMax() {
        return nbCouleursMax;
    }

    public void setNbCouleursMax(int nbCouleursMax) {
        this.nbCouleursMax = nbCouleursMax;
    }

    public String toString() {
        String str = "Nombre de tours = " + nbTours + "\nNombre de chiffre de la combinaison = " + nbChiffre + "\nMode développeur activé = " + modeDev;
        return str;
    }

}
