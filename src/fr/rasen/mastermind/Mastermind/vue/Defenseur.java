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
    private JFrame menu;
    private List<Pastille> prop = new ArrayList<Pastille>();

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
    private GridLayout tableau = new GridLayout(plateau.getNbToursMax(),2);
    private GridLayout gridProp = new GridLayout(1,plateau.getNbChiffre());
    private JPanel panCentre = new JPanel();
    private java.util.List<java.util.List<Pastille>> listProp = new LinkedList<java.util.List<Pastille>>();
    private java.util.List<java.util.List<Pastille>> listIndic = new LinkedList<List<Pastille>>();

    public Defenseur(JFrame frame){
        menu = frame;
        combiDef cd = new combiDef(this);
        cd.setVisible(true);
    }

    public void initDefenseur(){
        this.setLayout(new BorderLayout());
        initInfo();
        initCommande();
        initPanCentral();
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
        valider.addActionListener(new Defenseur.validerIndic());
        effacer.addActionListener(new Defenseur.effacerIndic());
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

    public String convertPastilleString(){
        String str = "";
        for(int i=0; i<prop.size(); i++){
            str = str + prop.get(i).getValeur();
        }
        return str;
    }

    public List<Pastille> convertStringPastille(String rep){
        List<Pastille> list = new ArrayList<Pastille>();
        for(int i =0; i<rep.length(); i++){
            if(rep.charAt(i)=='B'){
                list.add(Pastille.BLANC);
            }
            if(rep.charAt(i)=='N'){
                list.add(Pastille.NOIR);
            }
        }
        return list;
    }

    public void affichageProp(){
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(400,25));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(gridProp);
        for (int i =0; i<prop.size(); i++){
            pan.add(new JLabel(new ImageIcon(prop.get(i).getFichier())));
        }
        panCentre.add(pan);
        panCentre.repaint();
        panCentre.revalidate();
    }

    public void affichageIndic(List<Pastille> list){
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(200, 25));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(new GridLayout(2,2));
        for(int i = 0; i<list.size(); i++){
            pan.add(new JLabel(new ImageIcon(list.get(i).getFichier())));
        }
        panCentre.add(pan);
        panCentre.repaint();
        panCentre.revalidate();
    }

    class validerIndic implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String rep = plateau.defenseur(convertPastilleString());
            listProp.add(prop);
            affichageProp();
            proposition.removeAll();
            proposition.repaint();
            proposition.revalidate();
            prop = new ArrayList<Pastille>();
            listIndic.add(convertStringPastille(rep));
            affichageIndic(listIndic.get(listIndic.size()-1));
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
