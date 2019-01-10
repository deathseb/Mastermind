package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;
import fr.rasen.mastermind.Mastermind.Plateau;

import javax.swing.*;
import java.awt.*;

public class Challenger extends JPanel {

    private Plateau plateau = new Plateau();

    // Affichage pastille de couleurs
    private JLabel bleu = new JLabel(new ImageIcon(Pastille.BLEU.getFichier()));
    private JLabel bleu_clair = new JLabel(new ImageIcon(Pastille.BLEU_CLAIR.getFichier()));
    private JLabel gris = new JLabel(new ImageIcon(Pastille.GRIS.getFichier()));
    private JLabel jaune = new JLabel(new ImageIcon(Pastille.JAUNE.getFichier()));
    private JLabel marron = new JLabel(new ImageIcon(Pastille.MARRON.getFichier()));
    private JLabel orange = new JLabel(new ImageIcon(Pastille.ORANGE.getFichier()));
    private JLabel rose = new JLabel(new ImageIcon(Pastille.ROSE.getFichier()));
    private JLabel rouge = new JLabel(new ImageIcon(Pastille.ROUGE.getFichier()));
    private JLabel vert = new JLabel(new ImageIcon(Pastille.VERT.getFichier()));
    private JLabel violet = new JLabel(new ImageIcon(Pastille.VIOLET.getFichier()));
    private JPanel panPastille = new JPanel();
    private GridLayout couleurs = new GridLayout(1,10);

    //Affichage titre et info générales
    private JPanel panInfo = new JPanel();
    private GridLayout informations = new GridLayout(4, 1);
    private JLabel titre = new JLabel("Mastermind");
    private JLabel combinaison;
    private JLabel nbTours = new JLabel("Tour 0");
    private JLabel mode = new JLabel("Mode : Challenger");

    //Panneau commande
    private JPanel panCommande = new JPanel();
    private GridLayout commandes = new GridLayout(2, 2);
    private JLabel proposition = new JLabel();
    private JButton valider = new JButton("Valider");



    public Challenger(){
        this.setLayout(new BorderLayout());
        initInfo();
        initCommande();
    }

    public void initInfo(){
        panInfo.setLayout(informations);
        panInfo.add(titre);
        panInfo.add(mode);
        panInfo.add(nbTours);
        if(plateau.getP().isModeDev()){
            combinaison = new JLabel(plateau.getGmChall().showCombi());
            panInfo.add(combinaison);
        }
        this.add(panInfo, BorderLayout.NORTH);
    }

    public void initCommande(){
        panCommande.setLayout(commandes);
        panCommande.add(proposition);
        panCommande.add(valider);
        initCouleurs();
        this.add(panCommande, BorderLayout.SOUTH);
    }

    public void initCouleurs(){
        panPastille.setLayout(couleurs);
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
        panCommande.add(panPastille);
    }
}


