package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Duel extends JPanel {

    private Fenetre fenetre;
    private Challenger challenger;
    private Defenseur defenseur;
    private Duel duel;

    public Duel (Fenetre f){
        fenetre = f;
        duel = this;
        challenger = new Challenger(fenetre);
        ActionListener [] al = challenger.getValider().getActionListeners();
        challenger.getValider().removeActionListener(al[0]);
        challenger.getValider().addActionListener(new ChallengerListener());
        this.add(challenger);
    }

    class ChallengerListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String rep = challenger.getPlateau().challenger(challenger.convertPastilleString());
            challenger.getListProp().add(challenger.getProp());
            challenger.affichageProp();
            challenger.getProposition().removeAll();
            challenger.getProposition().repaint();
            challenger.getProposition().revalidate();
            challenger.setProp(new ArrayList<Pastille>());
            challenger.getListIndic().add(challenger.convertStringPastille(rep));
            challenger.affichageIndic(challenger.getListIndic().get(challenger.getListIndic().size()-1));
            if(challenger.getListIndic().get(challenger.getListIndic().size()-1).equals(challenger.getListBouleNoires())){ //gestion victoire
                FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
            } else if(challenger.getPlateau().getTourActuel()==challenger.getPlateau().getNbToursMax()){ //gestion défaite
                FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
            }
            challenger.getPlateau().setTourActuel(challenger.getPlateau().getTourActuel()+1);
            transitionCD();
        }
    }

    class DefenseurListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            defenseur.getListIndic().add(defenseur.getProp());
            defenseur.affichageIndic(defenseur.getProp());
            defenseur.setProp(new ArrayList<>());
            defenseur.getProposition().removeAll();
            defenseur.getProposition().repaint();
            defenseur.getProposition().revalidate();
            if(defenseur.getListIndic().get(defenseur.getListIndic().size()-1).equals(defenseur.getListBouleNoires())){ //gestion victoire
                FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
            } else if(defenseur.getPlateau().getTourActuel()== defenseur.getPlateau().getNbToursMax()){ //gestion défaite
                FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
            } else { //Si la partie n'est ni gagné ni perdu, on continue
                defenseur.getPlateau().setTourActuel(defenseur.getPlateau().getTourActuel() + 1);
                defenseur.jouerTour();
            }
            transitionDC();
        }
    }

    public void transitionCD(){
        Thread t = new Thread(new ThreadFocusCD());
        t.start();
    }

    public void transitionDC(){
        Thread t = new Thread(new ThreadFocusDC());
        t.start();
    }

    class ThreadFocusCD implements Runnable {
        public void run() {
            challenger.getValider().setEnabled(false);
            duel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            Thread thread = new Thread();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                //logger.error("Erreur lors du thread de pause.");
            }
            if(challenger.getPlateau().getTourActuel()==1){
                defenseur = new Defenseur(fenetre);
                defenseur.getValider().setEnabled(false);
                ActionListener [] al = defenseur.getValider().getActionListeners();
                defenseur.getValider().removeActionListener(al[0]);
                defenseur.getValider().addActionListener(new DefenseurListener());
            }
            duel.removeAll();
            duel.add(defenseur);
            duel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
            defenseur.getValider().setEnabled(true);
            duel.revalidate();
        }
    }

    class ThreadFocusDC implements Runnable {
        public void run() {
            defenseur.getValider().setEnabled(false);
            duel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            Thread thread = new Thread();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
               // logger.error("Erreur lors du thread de pause.");
            }
            duel.removeAll();
            duel.add(challenger);
            duel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
            challenger.getValider().setEnabled(true);
            duel.revalidate();
        }
    }
}
