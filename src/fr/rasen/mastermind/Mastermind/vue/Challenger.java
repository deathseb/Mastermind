package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;
import fr.rasen.mastermind.Mastermind.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Challenger extends JPanel {

    private Plateau plateau = new Plateau();
    private JFrame menu;
    private List<AffichagePastille> prop = new ArrayList<AffichagePastille>();

    // Affichage pastille de couleurs
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

    //Affichage titre et info générales
    private JPanel panInfo = new JPanel();
    private GridLayout informations = new GridLayout(4, 1);
    private JLabel titre = new JLabel("Mastermind");
    private JLabel combinaison;
    private JLabel nbTours = new JLabel("Tour 0");
    private JLabel mode = new JLabel("Mode : Challenger");

    //Panneau commande
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 1);
    private GridLayout decoupeProposition = new GridLayout(1,plateau.getNbChiffre());
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");



    public Challenger(JFrame frame){
        menu = frame;
        this.setLayout(new BorderLayout());
        initInfo();
        initCommande();
        this.setBackground(Color.white);
    }

    public void initInfo(){
        panInfo.setLayout(informations);
        Font font = new Font("showcard gothic", Font.BOLD, 50);
        titre.setFont(font);
        font = new Font("showcard gothic", Font.BOLD, 20);
        mode.setFont(font);
        nbTours.setFont(font);
        titre.setHorizontalAlignment(JLabel.CENTER);
        mode.setHorizontalAlignment(JLabel.CENTER);
        nbTours.setHorizontalAlignment(JLabel.CENTER);
        titre.setOpaque(true);
        mode.setOpaque(true);
        nbTours.setOpaque(true);
        titre.setBackground(Color.white);
        mode.setBackground(Color.white);
        nbTours.setBackground(Color.white);
        panInfo.add(titre);
        panInfo.add(mode);
        panInfo.add(nbTours);
        if(plateau.getP().isModeDev()){
            combinaison = new JLabel("Combinaison : " + plateau.getGmChall().showCombi());
            combinaison.setFont(font);
            combinaison.setHorizontalAlignment(JLabel.CENTER);
            combinaison.setOpaque(true);
            combinaison.setBackground(Color.white);
            panInfo.add(combinaison);
        }
        panInfo.setBackground(Color.white);
        this.add(panInfo, BorderLayout.NORTH);
    }

    public void initCommande(){
        valider.addActionListener(new validerProp());
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

    public void ajoutProp(){
        proposition.add(prop.get(prop.size()-1));
    }

    public List<AffichagePastille> getProp() {
        return prop;
    }

    public void setProp(List<AffichagePastille> prop) {
        this.prop = prop;
    }

    public String convertPastilleString(){
        String str = "";
        for(int i=0; i<prop.size(); i++){
            str = str + prop.get(i).getPastille().getValeur();
        }
        return str;
    }

    class validerProp implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           String rep = plateau.challenger(convertPastilleString());
        }
    }


}




