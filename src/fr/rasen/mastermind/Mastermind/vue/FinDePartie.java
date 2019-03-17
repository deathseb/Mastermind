package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Projet3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinDePartie extends JDialog {
    private FinDePartie fp = this;
    private Projet3 p3;
    private Fenetre fenetre;
    private JPanel panTexte = new JPanel();
    private JTextField texte = new JTextField();
    private JLabel option = new JLabel("Veuillez sélectionner votre option pour continuer.");
    private GridLayout gridTexte = new GridLayout(2,1);
    private JButton accueil = new JButton("Accueil");
    private JButton mode = new JButton("Mode");
    private JButton quitter = new JButton("Quitter");
    private JPanel panButton = new JPanel();
    private GridLayout gridButton = new GridLayout(1,3);

    public FinDePartie(String titre, boolean victoire, Projet3 p, Fenetre f){
        p3 =p;
        fenetre = f;
        this.setTitle(titre);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setSize(new Dimension(650,300));
        if(victoire){
            texte.setText("Félicitation ! Vous avez gagné !!");
        } else{
            texte.setText("Dommage ! Vous avez perdu !!");
        }
        texte.setBorder(BorderFactory.createLineBorder(Color.white));
        texte.setFont(new Font("showcard gothic", Font.CENTER_BASELINE, 30));
        texte.setHorizontalAlignment(JLabel.CENTER);
        option.setFont(new Font("showcard gothic", Font.CENTER_BASELINE, 20));
        option.setHorizontalAlignment(JLabel.CENTER);
        panTexte.setLayout(gridTexte);
        panTexte.setBackground(Color.white);
        panTexte.add(texte);
        panTexte.add(option);
        this.add(panTexte, BorderLayout.CENTER);
        initButton();
        this.setVisible(true);
    }

    public void initButton(){
        accueil.addActionListener(new accueilListener());
        mode.addActionListener(new modeListener());
        quitter.addActionListener(new quitterListener());
        panButton.setLayout(gridButton);
        panButton.add(accueil);
        panButton.add(mode);
        panButton.add(quitter);
        this.add(panButton, BorderLayout.SOUTH);
    }

    /**
     * Inner class permettant de revenir à l'accueil.
     */
    class accueilListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            p3.setVisible(true);
            fenetre.setVisible(false);
            fp.setVisible(false);
        }
    }

    /**
     * Inner class permettant de revenir à la sélection du mode de jeu.
     */
    class modeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenetre.getContentPane().removeAll();
            fenetre.initAccueil();
            fenetre.repaint();
            fenetre.revalidate();
            fp.setVisible(false);
        }
    }

    /**
     * Inner class permettant de quitter l'application.
     */
    class quitterListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            fenetre.dispose();
            fp.dispose();
            System.exit(0);
        }
    }
}
