package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;
import fr.rasen.mastermind.Mastermind.Plateau;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Defenseur extends JPanel {

    private static final Logger logger = LogManager.getLogger();

    private Plateau plateau = new Plateau();
    private Fenetre fenetre;
    private List<Pastille> prop = new ArrayList<Pastille>();
    private List<Pastille> listBouleNoires = new ArrayList<Pastille>();
    private List<JPanel> listPanProp = new LinkedList<>();
    private List<JPanel> listPanIndic = new LinkedList<>();

    // Affichage pastille de couleurs
    private JPanel panDroite = new JPanel();
    private JPanel panGauche = new JPanel();
    private AffichagePastille blanc = new AffichagePastille(Pastille.BLANC, this);
    private AffichagePastille noir = new AffichagePastille(Pastille.NOIR, this);
    private JPanel panPastille = new JPanel();
    private GridLayout couleurs = new GridLayout(1, 2);

    //Affichage titre et info générales
    private JPanel panInfo = new JPanel();
    private GridLayout informations = new GridLayout(4, 1);
    private JLabel titre = new JLabel("Mastermind");
    private int compteurTours = 0;
    private JLabel combinaison;
    private JLabel nbTours = new JLabel("Tour 0");
    private JLabel mode = new JLabel("Mode : Défenseur");

    //Panneau commande
    private JLabel propo = new JLabel("Proposition");
    private JLabel indication = new JLabel("Indication");
    private JPanel panProp = new JPanel();
    private JPanel panIndic = new JPanel();
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 1);
    private GridLayout decoupeProposition = new GridLayout(1, plateau.getNbChiffre());
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");

    //Panneau Central
    private GridLayout tableau = new GridLayout(plateau.getNbToursMax(), 2, 5, 5);
    private GridLayout gridProp = new GridLayout(1, plateau.getNbChiffre(), 5, 5);
    private JPanel panCentre = new JPanel();
    private java.util.List<java.util.List<Pastille>> listProp = new LinkedList<java.util.List<Pastille>>();
    private java.util.List<java.util.List<Pastille>> listIndic = new LinkedList<List<Pastille>>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JScrollPane jScrollPane = new JScrollPane();

    public Defenseur(Fenetre f) {
        fenetre = f;
        valider.addActionListener(new validerIndic());
        CombiDef cd = new CombiDef(this);
        cd.setVisible(true);
    }

    /**
     * Créer la fenêtre du mode défenseur une fois que la combinaison est validée.
     */
    public void initDefenseur() {
        for (int i = 0; i < plateau.getNbChiffre(); i++) {
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

    /**
     * Créé la partie contenant les infos générales du mode.
     */
    public void initInfo() {
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
        JLabel textCombi = new JLabel("Combinaison : ");
        textCombi.setFont(font);
        textCombi.setHorizontalAlignment(JLabel.CENTER);
        textCombi.setOpaque(true);
        textCombi.setBackground(Color.white);

        JPanel allCombi = new JPanel();
        allCombi.setLayout(new GridLayout(1, 2));
        allCombi.add(textCombi, BorderLayout.WEST);
        JPanel combinaison = new JPanel();
        combinaison.setBackground(Color.white);
        combinaison.setLayout(new GridLayout(1, plateau.getNbChiffre()));
        JLabel lab;
        for (int i = 0; i < plateau.getGmChall().showCombi().length(); i++) {
            switch (plateau.getGmChall().showCombi().charAt(i)) {
                case '0':
                    lab = new JLabel(new ImageIcon(Pastille.BLEU.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '1':
                    lab = new JLabel(new ImageIcon(Pastille.BLEU_CLAIR.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '2':
                    lab = new JLabel(new ImageIcon(Pastille.GRIS.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '3':
                    lab = new JLabel(new ImageIcon(Pastille.JAUNE.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '4':
                    lab = new JLabel(new ImageIcon(Pastille.MARRON.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '5':
                    lab = new JLabel(new ImageIcon(Pastille.ORANGE.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '6':
                    lab = new JLabel(new ImageIcon(Pastille.ROSE.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '7':
                    lab = new JLabel(new ImageIcon(Pastille.ROUGE.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '8':
                    lab = new JLabel(new ImageIcon(Pastille.VERT.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
                case '9':
                    lab = new JLabel(new ImageIcon(Pastille.VIOLET.getFichier()));
                    lab.setBackground(Color.white);
                    combinaison.add(lab);
                    break;
            }
        }
        allCombi.add(combinaison, BorderLayout.EAST);
        allCombi.setBackground(Color.white);
        panInfo.add(allCombi);
        panInfo.setBackground(Color.white);
        this.add(panInfo, BorderLayout.NORTH);
        logger.trace("Affichage mode Défenseur réussi.");
    }

    /**
     * Créé la partie utilisé par le joueur pour intéragir.
     */
    public void initCommande() {
        effacer.addActionListener(new effacerIndic());
        panCommande.setLayout(new BorderLayout());
        panBoutton.setLayout(commandes);
        proposition.setPreferredSize(new Dimension(500, 50));
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

    /**
     * Créé l'affichage de pastille de couleur.
     */
    public void initCouleurs() {
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

    /**
     * Créé le tableau central
     */
    public void initPanCentral() {
        Font ft = new Font("showcard gothic", Font.BOLD, 20);
        propo.setFont(ft);
        propo.setHorizontalAlignment(JLabel.CENTER);
        indication.setFont(ft);
        indication.setHorizontalAlignment(JLabel.CENTER);
        panProp.setBorder(BorderFactory.createLineBorder(Color.black));
        panProp.setBackground(Color.white);
        panProp.setPreferredSize(new Dimension(180, 30));
        panProp.setMinimumSize(new Dimension(180, 30));
        panProp.add(propo);
        panIndic.setBorder(BorderFactory.createLineBorder(Color.black));
        panIndic.setBackground(Color.white);
        panIndic.setPreferredSize(new Dimension(180, 30));
        panIndic.setMinimumSize(new Dimension(180, 30));
        panIndic.add(indication);

        panCentre.setLayout(new GridBagLayout());
        panCentre.setBackground(Color.white);
        panCentre.setBorder(BorderFactory.createLineBorder(Color.black));
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panCentre.add(panProp, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        panCentre.add(panIndic, gbc);
        for (int i = 0; i < plateau.getNbToursMax() + 1; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            JLabel propLab = new JLabel();
            propLab.setBorder(BorderFactory.createLineBorder(Color.black));
            propLab.setHorizontalAlignment(JLabel.CENTER);
            propLab.setPreferredSize(new Dimension(180, 30));
            propLab.setMinimumSize(new Dimension(180, 30));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.LINE_START;
            panCentre.add(propLab, gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridx = 1;
            gbc.gridy = i + 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            JLabel indicLab = new JLabel();
            indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
            indicLab.setHorizontalAlignment(JLabel.CENTER);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.BASELINE_LEADING;
            indicLab.setPreferredSize(new Dimension(180, 30));
            indicLab.setMinimumSize(new Dimension(180, 30));
            panCentre.add(indicLab, gbc);

        }
        jScrollPane.add(panCentre);
        jScrollPane.setViewportView(panCentre);
        jScrollPane.setBounds(100, 200, 400, 400);
        this.add(jScrollPane, BorderLayout.CENTER);
    }

    public List<Pastille> getProp() {
        return prop;
    }

    public void setProp(List<Pastille> prop) {
        this.prop = prop;
    }

    public void jouerTour() {
        if (plateau.getTourActuel() == 0) {
            plateau.defenseur(null);
            prop = plateau.getjDef().getDerProp();
            listProp.add(prop);
            affichageProp();
            prop = new ArrayList<>();
        } else {
            plateau.defenseur(convertPastilleString());
            prop = plateau.getjDef().getDerProp();
            affichageProp();
            listProp.add(prop);
            prop = new ArrayList<>();
        }

    }

    public void ajoutProp() {
        proposition.add(new JLabel(new ImageIcon(prop.get(prop.size() - 1).getFichier())));
        proposition.repaint();
        proposition.revalidate();
    }


    public String convertPastilleString() {
        String str = "";
        for (int i = 0; i < listIndic.get(listIndic.size() - 1).size(); i++) {
            str = str + listIndic.get(listIndic.size() - 1).get(i).getValeur();
        }
        while (str.length() != plateau.getNbChiffre()) {
            str = str + "_";
        }
        return str;
    }

    public List<Pastille> convertStringPastille(String rep) {
        List<Pastille> list = new ArrayList<Pastille>();
        for (int i = 0; i < rep.length(); i++) {
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

    /**
     * Met à jour l'affichage du panneau central après une nouvelle proposition.
     */
    public void affichageProp() {
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(400, 30));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(gridProp);
        for (int i = 0; i < prop.size(); i++) {
            JLabel jl = new JLabel((new ImageIcon(prop.get(i).getFichier())));
            jl.setPreferredSize(new Dimension(50, 50));
            pan.add(jl);
        }
        listPanProp.add(pan);

        panCentre.removeAll();
        panCentre.setBackground(Color.white);
        panCentre.setBorder(BorderFactory.createLineBorder(Color.black));
        panCentre.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panCentre.add(panProp, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        panCentre.add(panIndic, gbc);
        int ligne = 1;
        for (int i = 0; i < listProp.size(); i++) {
            if (i == listProp.size() - 1) {
                gbc.gridy = ligne;
                gbc.gridx = 0;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                panCentre.add(listPanProp.get(i), gbc);
                gbc.gridy = ligne;
                gbc.gridx = 1;
                JLabel indicLab = new JLabel();
                indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
                indicLab.setHorizontalAlignment(JLabel.CENTER);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                indicLab.setPreferredSize(new Dimension(180, 30));
                indicLab.setMinimumSize(new Dimension(180, 30));
                panCentre.add(indicLab, gbc);
            } else {
                gbc.gridx = 0;
                gbc.gridy = ligne;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                panCentre.add(listPanProp.get(i), gbc);
                gbc.gridx = 1;
                gbc.gridy = ligne;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                panCentre.add(listPanIndic.get(i), gbc);
            }
            ligne++;
        }
        compteurTours++;
        for (int j = plateau.getNbToursMax(); j > compteurTours; j--) {
            gbc.gridx = 0;
            gbc.gridy = ligne;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            JLabel propLab = new JLabel();
            propLab.setBorder(BorderFactory.createLineBorder(Color.black));
            propLab.setHorizontalAlignment(JLabel.CENTER);
            propLab.setPreferredSize(new Dimension(180, 30));
            propLab.setMinimumSize(new Dimension(180, 30));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.LINE_START;
            panCentre.add(propLab, gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridx = 1;
            gbc.gridy = ligne;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            JLabel indicLab = new JLabel();
            indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
            indicLab.setHorizontalAlignment(JLabel.CENTER);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.BASELINE_LEADING;
            indicLab.setPreferredSize(new Dimension(180, 30));
            indicLab.setMinimumSize(new Dimension(180, 30));
            panCentre.add(indicLab, gbc);
            ligne++;
        }
        panCentre.revalidate();
        this.add(jScrollPane, BorderLayout.CENTER);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void affichageIndic(List<Pastille> list) {
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(200, 30));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < list.size(); i++) {
            JLabel jl = new JLabel((new ImageIcon(prop.get(i).getFichier())));
            jl.setPreferredSize(new Dimension(50, 50));
            pan.add(jl);
        }
        listPanIndic.add(pan);
    }

    /**
     * Inner class permettant au joueur de valider son indication, avant que le jeu se met à jour.
     */
    class validerIndic implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listIndic.add(prop);
            affichageIndic(prop);
            prop = new ArrayList<>();
            proposition.removeAll();
            proposition.repaint();
            proposition.revalidate();
            if (listIndic.get(listIndic.size() - 1).equals(listBouleNoires)) { //gestion victoire
                FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
                logger.trace("Fin de partie mode Défenseur");
            } else if (plateau.getTourActuel() == plateau.getNbToursMax()) { //gestion défaite
                FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
                logger.trace("Fin de partie mode Défenseur.");
            } else { //Si la partie n'est ni gagné ni perdu, on continue
                plateau.setTourActuel(plateau.getTourActuel() + 1);
                jouerTour();
            }
        }
    }

    class effacerIndic implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            proposition.remove(prop.size() - 1);
            prop.remove(prop.size() - 1);
            proposition.repaint();
            proposition.revalidate();
        }
    }

    public JButton getValider() {
        return valider;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public List<Pastille> getListBouleNoires() {
        return listBouleNoires;
    }


    public JPanel getProposition() {
        return proposition;
    }

    public void setProposition(JPanel proposition) {
        this.proposition = proposition;
    }

    public List<List<Pastille>> getListIndic() {
        return listIndic;
    }

}
