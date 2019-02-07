package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;
import fr.rasen.mastermind.Mastermind.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Defenseur extends JPanel {

    private Plateau plateau = new Plateau();
    private Fenetre fenetre;
    private List<Pastille> prop = new ArrayList<Pastille>();
    private List<Pastille> listBouleNoires = new ArrayList<Pastille>();

    // Affichage pastille de couleurs
    private JPanel panDroite = new JPanel();
    private JPanel panGauche = new JPanel();
    private AffichagePastille blanc = new AffichagePastille(Pastille.BLANC, this);
    private AffichagePastille noir = new AffichagePastille(Pastille.NOIR, this);
    private JPanel panPastille = new JPanel();
    private GridLayout couleurs = new GridLayout(1,2);

    //Affichage titre et info générales
    private JPanel panInfo = new JPanel();
    private GridLayout informations = new GridLayout(4, 1);
    private JLabel titre = new JLabel("Mastermind");
    private JLabel combinaison;
    private JLabel nbTours = new JLabel("Tour 0");
    private JLabel mode = new JLabel("Mode : Défenseur");

    //Panneau commande
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 1);
    private GridLayout decoupeProposition = new GridLayout(1,plateau.getNbChiffre());
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");

    //Panneau Central
    private GridLayout tableau = new GridLayout(plateau.getNbToursMax(),2, 5,5);
    private GridLayout gridProp = new GridLayout(1,plateau.getNbChiffre(), 5, 5);
    private JPanel panCentre = new JPanel();
    private java.util.List<java.util.List<Pastille>> listProp = new LinkedList<java.util.List<Pastille>>();
    private java.util.List<java.util.List<Pastille>> listIndic = new LinkedList<List<Pastille>>();

    public Defenseur(Fenetre f){
        fenetre = f;
        combiDef cd = new combiDef(this);
        cd.setVisible(true);
    }

    public void initDefenseur(){
        for(int i=0; i<plateau.getNbChiffre(); i++){
            listBouleNoires.add(Pastille.NOIR);
        }
        this.setLayout(new BorderLayout());
        initInfo();
        initCommande();
        initPanCentral();
        this.setBackground(Color.white);
        this.repaint();
        this.revalidate();
        jouerTour();
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
     /*   if(plateau.getP().isModeDev()){
            combinaison = new JLabel("Combinaison : " + plateau.getGmDef().showCombi());
            combinaison.setFont(font);
            combinaison.setHorizontalAlignment(JLabel.CENTER);
            combinaison.setOpaque(true);
            combinaison.setBackground(Color.white);
            panInfo.add(combinaison);
        } */
        panInfo.setBackground(Color.white);
        this.add(panInfo, BorderLayout.NORTH);
    }

    public void initCommande(){
        valider.addActionListener(new validerIndic());
        effacer.addActionListener(new effacerIndic());
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
        blanc.setOpaque(true);
        noir.setOpaque(true);
        blanc.setBackground(Color.white);
        noir.setBackground(Color.white);
        panDroite.setBackground(Color.white);
        panGauche.setBackground(Color.white);
        panPastille.add(panGauche);
        panPastille.add(blanc);
        panPastille.add(noir);
        panPastille.add(panDroite);
        panPastille.setBackground(Color.white);
        panCommande.add(panPastille, BorderLayout.SOUTH);
    }

    public void initPanCentral(){
        panCentre.setLayout(tableau);
        panCentre.setBorder(BorderFactory.createLineBorder(Color.black));
        panCentre.setBackground(Color.white);
        this.add(panCentre, BorderLayout.CENTER);
    }

    public List<Pastille> getProp() {
        return prop;
    }

    public void setProp(List<Pastille> prop) {
        this.prop = prop;
    }

    public void jouerTour(){
        if(plateau.getTourActuel()==0){
            plateau.defenseur(null);
           prop = plateau.getjDef().getDerProp();
           affichageProp();
           listProp.add(prop);
           prop = new ArrayList<>();
        } else{
            plateau.defenseur(convertPastilleString());
            prop = plateau.getjDef().getDerProp();
            affichageProp();
            listProp.add(prop);
            prop = new ArrayList<>();
        }

    }

    public void ajoutProp(){
        proposition.add(new JLabel(new ImageIcon(prop.get(prop.size()-1).getFichier())));
        proposition.repaint();
        proposition.revalidate();
    }


    public String convertPastilleString(){
        String str = "";
        for(int i=0; i<listIndic.get(listIndic.size()-1).size(); i++){
            str = str + listIndic.get(listIndic.size()-1).get(i).getValeur();
        }
        while(str.length() != plateau.getNbChiffre()){
            str = str + "_";
        }
        return str;
    }

    public List<Pastille> convertStringPastille(String rep){
        List<Pastille> list = new ArrayList<Pastille>();
        for(int i =0; i<rep.length(); i++){
            switch (rep.charAt(i)) {
                case 0:
                    list.add(Pastille.BLEU);
                    break;
                case 1:
                    list.add(Pastille.BLEU_CLAIR);
                    break;
                case 2:
                    list.add(Pastille.GRIS);
                    break;
                case 3:
                    list.add(Pastille.JAUNE);
                    break;
                case 4:
                    list.add(Pastille.MARRON);
                    break;
                case 5:
                    list.add(Pastille.ORANGE);
                    break;
                case 6:
                    list.add(Pastille.ROSE);
                    break;
                case 7:
                    list.add(Pastille.ROUGE);
                    break;
                case 8:
                    list.add(Pastille.VERT);
                    break;
                case 9:
                    list.add(Pastille.VIOLET);
                    break;
            }
        }
        return list;
    }

    public void affichageProp(){
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(400,50));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(gridProp);
        for (int i =0; i<prop.size(); i++){
            JLabel jl = new JLabel((new ImageIcon(prop.get(i).getFichier())));
            jl.setPreferredSize(new Dimension(50,50));
            pan.add(jl);
        }
        panCentre.add(pan);
        panCentre.repaint();
        panCentre.revalidate();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void affichageIndic(List<Pastille> list){
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(200, 25));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(new GridLayout(2,2));
        for(int i = 0; i<list.size(); i++){
            JLabel jl = new JLabel((new ImageIcon(prop.get(i).getFichier())));
            jl.setPreferredSize(new Dimension(50,50));
            pan.add(jl);
        }
        panCentre.add(pan);
        panCentre.repaint();
        panCentre.revalidate();
    }

    class validerIndic implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listIndic.add(prop);
            affichageIndic(prop);
            prop = new ArrayList<>();
            proposition.removeAll();
            proposition.repaint();
            proposition.revalidate();
            if(listIndic.get(listIndic.size()-1).equals(listBouleNoires)){ //gestion victoire
                FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
            } else if(plateau.getTourActuel()==plateau.getNbToursMax()){ //gestion défaite
                FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
            } else { //Si la partie n'est ni gagné ni perdu, on continue
                plateau.setTourActuel(plateau.getTourActuel() + 1);
                jouerTour();
            }
        }
    }

    class effacerIndic implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            proposition.remove(prop.size()-1);
            prop.remove(prop.size()-1);
            proposition.repaint();
            proposition.revalidate();
        }
    }
}
