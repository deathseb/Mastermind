package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Plateau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private Challenger challenger;
    private Defenseur defenseur;
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
        accueil.getChall().addActionListener(new ChallengerListener());
        accueil.getDef().addActionListener(new DefenseurListener());
        accueil.getDuel().addActionListener(new DuelListener());
        accueil.getPropriete().addActionListener(new ProprietesListener());
        accueil.getApropos().addActionListener(new AProposListener());
    }

    /**
     * Lancement du jeu du Mastermind.
     * @param args Non utilisé.
     */
    public static void main(String[] args) {
        Fenetre f = new Fenetre();
        f.setVisible(true);
    }

    /**
     * Inner class pour gérer le click sur le menu Challenger pour le jeu du Mastermind.
     */
    class ChallengerListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            challenger = new Challenger(fenetre);
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(challenger);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    /**
     * Inner class pour gérer le click sur le menu Défenseur pour le jeu du Mastermind.
     */
    class DefenseurListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            defenseur = new Defenseur(fenetre);
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(defenseur);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    /**
     * Inner class pour gérer le click sur le menu Duel pour le jeu du Mastermind.
     */
    class DuelListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(duel);
            fenetre.repaint();
            fenetre.revalidate();
        }
    }

    /**
     * Inner class pour gérer le click sur le menu Propriétés pour le jeu du Mastermind.
     */
    class ProprietesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            propriete = new Propriete(plateau);
            propriete.show(true);
        }
    }

    /**
     * Inner class pour gérer le click sur le menu A propos pour le jeu du Mastermind.
     */
    class AProposListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Developpé par Deathseb le xx/xx/2019", "A propos...", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
