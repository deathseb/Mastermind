package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class combiDef extends JDialog {

    //Partie pastille
    private AffichagePastille bleu = new AffichagePastille(Pastille.BLEU, this);
    private AffichagePastille bleu_clair = new AffichagePastille(Pastille.BLEU_CLAIR, this);
    private AffichagePastille gris = new AffichagePastille(Pastille.GRIS, this);
    private AffichagePastille jaune = new AffichagePastille(Pastille.JAUNE, this);
    private AffichagePastille marron = new AffichagePastille(Pastille.MARRON, this);
    private AffichagePastille orange = new AffichagePastille(Pastille.ORANGE, this);
    private AffichagePastille rose = new AffichagePastille(Pastille.ROSE, this);
    private AffichagePastille rouge = new AffichagePastille(Pastille.ROUGE, this);
    private AffichagePastille vert = new AffichagePastille(Pastille.VERT, this);
    private AffichagePastille violet = new AffichagePastille(Pastille.VIOLET, this);
    private JPanel panPastille = new JPanel();
    private GridLayout couleurs = new GridLayout(1,10);

    //Panneau commande
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 1);
    private GridLayout decoupeProposition = new GridLayout(1,10);
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");

    private Defenseur defenseur;

    public combiDef (Defenseur d){
        defenseur = d;
        this.setSize(new Dimension(400,200));
        this.setTitle("Entrez la combinaison à trouver");
        this.setLocationRelativeTo(null);
        initCommande();
    }

    public void initCommande(){
        valider.addActionListener(new validerCombi());
        effacer.addActionListener(new effacerCombi());
        panCommande.setLayout(new BorderLayout());
        panBoutton.setLayout(commandes);
        proposition.setPreferredSize(new Dimension(500,50));
        proposition.setBorder(BorderFactory.createTitledBorder("Proposition"));
        proposition.setOpaque(true);
        proposition.setBackground(Color.white);
        proposition.setLayout(decoupeProposition);
        panCommande.add(proposition);
        panBoutton.add(valider);
        panBoutton.add(effacer);
        panBoutton.setBackground(Color.white);
        panCommande.add(proposition, BorderLayout.WEST);
        panCommande.add(panBoutton, BorderLayout.EAST);
        initCouleurs();
        panCommande.setBackground(Color.white);
        this.add(panCommande, BorderLayout.SOUTH);
    }

    public void initCouleurs(){
        panPastille.setPreferredSize(new Dimension(600, 50));
        panPastille.setLayout(couleurs);
        bleu.setOpaque(true);
        bleu_clair.setOpaque(true);
        gris.setOpaque(true);
        jaune.setOpaque(true);
        marron.setOpaque(true);
        orange.setOpaque(true);
        rose.setOpaque(true);
        rouge.setOpaque(true);
        vert.setOpaque(true);
        violet.setOpaque(true);
        bleu.setBackground(Color.white);
        bleu_clair.setBackground(Color.white);
        gris.setBackground(Color.white);
        jaune.setBackground(Color.white);
        marron.setBackground(Color.white);
        orange.setBackground(Color.white);
        rouge.setBackground(Color.white);
        rose.setBackground(Color.white);
        vert.setBackground(Color.white);
        violet.setBackground(Color.white);
        panPastille.add(bleu);
        panPastille.add(bleu_clair);
        panPastille.add(gris);
        panPastille.add(jaune);
        panPastille.add(marron);
        panPastille.add(orange);
        panPastille.add(rose);
        panPastille.add(rouge);
        panPastille.add(vert);
        panPastille.add(violet);
        panPastille.setBackground(Color.white);
        panCommande.add(panPastille, BorderLayout.SOUTH);
    }

    class validerCombi implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }

    class effacerCombi implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }
}
