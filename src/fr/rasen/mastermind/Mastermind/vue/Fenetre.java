package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Plateau;
import fr.rasen.mastermind.Projet3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private Projet3 projet3;

    private Challenger challenger;
    private Defenseur defenseur;
    private Duel duel = new Duel();
    private Accueil accueil = new Accueil();
    private Fenetre fenetre = this;
    private Propriete propriete;
    private Plateau plateau = new Plateau();

    public Fenetre(Projet3 p){
        projet3 = p;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setTitle("Mastermind");
        initAccueil();
        this.getContentPane().add(accueil);
    }

    public void initAccueil(){
        accueil.getChall().addActionListener(new challengerListener());
        accueil.getDef().addActionListener(new defenseurListener());
        accueil.getDuel().addActionListener(new duelListener());
        accueil.getPropriete().addActionListener(new proprieteListener());
    }

    class challengerListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            challenger = new Challenger(fenetre);
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(challenger);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    class defenseurListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            defenseur = new Defenseur(fenetre);
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(defenseur);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    class duelListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(duel);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    class proprieteListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            propriete = new Propriete(plateau);
            propriete.show(true);
        }
    }

    public Projet3 getProjet3() {
        return projet3;
    }
}
