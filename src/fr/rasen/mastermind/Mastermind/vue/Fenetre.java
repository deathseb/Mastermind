package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.JeuPm.Plateau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private Challenger challenger = new Challenger(this);
    private Defenseur defenseur = new Defenseur();
    private Duel duel = new Duel();
    private Accueil accueil = new Accueil();
    private Fenetre fenetre = this;
    private Propriete propriete;
    private Plateau plateau = new Plateau();

    public Fenetre(){
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
    }

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
        f.setVisible(true);
    }

    class challengerListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(challenger);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    class defenseurListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
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
}
