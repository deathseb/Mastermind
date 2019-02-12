package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CombiDef extends JDialog {

    private JDialog jd = this;
    private List<Pastille> prop = new ArrayList<Pastille>();

    //Partie pastille
    private List<AffichagePastille> listPastille = new ArrayList<>();
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
    private GridLayout couleurs;
    private int nbCouleursMax;

    private JLabel texte = new JLabel("Veuillez entrer la combinaison à trouver");
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 2);
    private GridLayout decoupeProposition = new GridLayout(1,10);
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");

    private Defenseur defenseur;

    public CombiDef(Defenseur d){
        defenseur = d;
        nbCouleursMax = d.getPlateau().getP().getNbCouleursMax();
        couleurs = new GridLayout(1, nbCouleursMax);
        this.setSize(new Dimension(400,200));
        this.setTitle("Entrez la combinaison à trouver");
        this.setLocationRelativeTo(null);
        initCommande();
        texte.setBackground(Color.white);
        this.add(texte, BorderLayout.CENTER);
        this.setBackground(Color.white);
    }

    public void initCommande(){
        valider.addActionListener(new validerCombi());
        effacer.addActionListener(new effacerCombi());
        panCommande.setLayout(new BorderLayout());
        panBoutton.setLayout(commandes);
        proposition.setPreferredSize(new Dimension(300,50));
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
        listPastille.add(bleu);
        listPastille.add(bleu_clair);
        listPastille.add(gris);
        listPastille.add(jaune);
        listPastille.add(marron);
        listPastille.add(orange);
        listPastille.add(rose);
        listPastille.add(rouge);
        listPastille.add(vert);
        listPastille.add(violet);
        for(int i=0; i<nbCouleursMax; i++){
            panPastille.add(listPastille.get(i));
        }

        panPastille.setBackground(Color.white);
        panCommande.add(panPastille, BorderLayout.SOUTH);
    }

    public java.util.List<Pastille> getProp() {
        return prop;
    }

    public void setProp(List<Pastille> prop) {
        this.prop = prop;
    }

    public void ajoutProp(){
        proposition.setPreferredSize(new Dimension(300, 55));
        proposition.add(new JLabel(new ImageIcon(prop.get(prop.size()-1).getFichier())));
        proposition.repaint();
        proposition.revalidate();
    }


    class validerCombi implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(prop.size()==defenseur.getPlateau().getNbChiffre()){
                jd.setVisible(false);
                defenseur.getPlateau().getGmDef().creerCombi(prop);
                defenseur.initDefenseur();
            }
        }
    }

    class effacerCombi implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }
}
