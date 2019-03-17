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

public class Challenger extends JPanel {

    private static final Logger logger = LogManager.getLogger();

    private Plateau plateau = new Plateau();
    private Fenetre fenetre;
    private List<Pastille> prop = new ArrayList<Pastille>();
    private List<Pastille> listBouleNoires = new ArrayList<Pastille>();
    private List<JPanel> listPanProp = new LinkedList<>();
    private List<JPanel> listPanIndic = new LinkedList<>();

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
    private GridLayout couleurs;
    private int nbCouleursMax;
    private List<AffichagePastille> listPastille = new ArrayList<>();


    //Affichage titre et info générales
    private JPanel panInfo = new JPanel();
    private GridLayout informations = new GridLayout(4, 1);
    private JLabel titre = new JLabel("Mastermind");
    private JLabel nbTours = new JLabel("Tour " + plateau.getTourActuel());
    private int compteurTours = 0;
    private JLabel mode = new JLabel("Mode : Challenger");

    //Panneau commande
    private JPanel panCommande = new JPanel();
    private JPanel panBoutton = new JPanel();
    private GridLayout commandes = new GridLayout(2, 1);
    private GridLayout decoupeProposition = new GridLayout(1, plateau.getNbChiffre());
    private JPanel proposition = new JPanel();
    private JButton valider = new JButton("Valider");
    private JButton effacer = new JButton("Effacer");

    //Panneau Central
    private JLabel propo = new JLabel("Proposition");
    private JLabel indication = new JLabel("Indication");
    private JPanel panProp = new JPanel();
    private JPanel panIndic = new JPanel();
    private GridLayout tableau = new GridLayout(plateau.getNbToursMax(), 2);
    private GridLayout gridProp = new GridLayout(1, plateau.getNbChiffre());
    private JPanel panCentre = new JPanel();
    private List<List<Pastille>> listProp = new LinkedList<List<Pastille>>();
    private List<List<Pastille>> listIndic = new LinkedList<List<Pastille>>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JScrollPane jScrollPane = new JScrollPane();


    public Challenger(Fenetre f) {
        fenetre = f;
        nbCouleursMax = plateau.getP().getNbCouleursMax();
        couleurs = new GridLayout(1, nbCouleursMax);
        this.setLayout(new BorderLayout());
        initInfo();
        initCommande();
        initPanCentral();
        for (int i = 0; i < plateau.getNbChiffre(); i++) {
            listBouleNoires.add(Pastille.NOIR);
        }
        this.setBackground(Color.white);
        logger.trace("Affichage mode Challenger réussi.");
    }

    /**
     * Créé les informations générales
     */
    public void initInfo() {
        panInfo.setLayout(informations);
        Font font = new Font("showcard gothic", Font.BOLD, 50);
        titre.setFont(font);
        font = new Font("showcard gothic", Font.BOLD, 20);
        mode.setFont(font);
        nbTours.setText("Tours :" + plateau.getTourActuel());
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
        if (plateau.getP().isModeDev()) {
            JLabel textCombi = new JLabel("Combinaison : ");
            textCombi.setFont(font);
            textCombi.setHorizontalAlignment(JLabel.CENTER);
            textCombi.setOpaque(true);
            textCombi.setBackground(Color.white);

            JPanel allCombi = new JPanel();
            allCombi.setLayout(new GridLayout(1,2));
            allCombi.add(textCombi,BorderLayout.WEST);
            JPanel combinaison = new JPanel();
            combinaison.setBackground(Color.white);
            combinaison.setLayout(new GridLayout(1, plateau.getNbChiffre()));
            JLabel lab;
            for (int i=0; i<plateau.getGmChall().showCombi().length(); i++){
                switch (plateau.getGmChall().showCombi().charAt(i)){
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
                allCombi.add(combinaison, BorderLayout.EAST);
                allCombi.setBackground(Color.white);
                panInfo.add(allCombi);
            }
        }
        panInfo.setBackground(Color.white);
        this.add(panInfo, BorderLayout.NORTH);
    }

    public void initCommande() {
        valider.addActionListener(new validerProp());
        effacer.addActionListener(new effacerProp());
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
     * Créé la partie avec toutes les pastilles de couleurs.
     */
    public void initCouleurs() {
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
        for (int i = 0; i < nbCouleursMax; i++) {
            panPastille.add(listPastille.get(i));
        }
        panPastille.setBackground(Color.white);
        panCommande.add(panPastille, BorderLayout.SOUTH);
    }

    /**
     * Création du tableau central.
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
            gbc.gridy = i+1;
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
            gbc.gridy = i+1;
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

    public void ajoutProp() {
        proposition.add(new JLabel(new ImageIcon(prop.get(prop.size() - 1).getFichier())));
        proposition.repaint();
        proposition.revalidate();
    }

    public List<Pastille> getProp() {
        return prop;
    }

    public void setProp(List<Pastille> prop) {
        this.prop = prop;
    }

    public String convertPastilleString() {
        String str = "";
        for (int i = 0; i < prop.size(); i++) {
            str = str + prop.get(i).getValeur();
        }
        return str;
    }

    public List<Pastille> convertStringPastille(String rep) {
        List<Pastille> list = new ArrayList<Pastille>();
        for (int i = 0; i < rep.length(); i++) {
            if (rep.charAt(i) == 'B') {
                list.add(Pastille.BLANC);
            }
            if (rep.charAt(i) == 'N') {
                list.add(Pastille.NOIR);
            }
        }
        return list;
    }

    public void sauvegardeProp() {
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(400, 30));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(gridProp);
        for (int i = 0; i < prop.size(); i++) {
            pan.add(new JLabel(new ImageIcon(prop.get(i).getFichier())));
        }
        listPanProp.add(pan);
    }

    /**
     * Mise à jour de l'affichage
     * @param list list contenant la réponse à la proposition émise par le joueur.
     */
    public void affichageIndic(List<Pastille> list) {
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(200, 30));
        pan.setBackground(Color.white);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        pan.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < list.size(); i++) {
            pan.add(new JLabel(new ImageIcon(list.get(i).getFichier())));
        }
        listPanIndic.add(pan);
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
            ligne++;
        }
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
        panInfo.removeAll();
        initInfo();
        panInfo.repaint();
        panInfo.revalidate();
        this.add(jScrollPane, BorderLayout.CENTER);
        compteurTours++;
    }

    /**
     * Inner class validant la proposition du joueur et mettant à jour l'affichage.
     */
    class validerProp implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(prop.size() == plateau.getP().getNbChiffre()) {
                String rep = plateau.challenger(convertPastilleString());
                listProp.add(prop);
                sauvegardeProp();
                proposition.removeAll();
                proposition.repaint();
                proposition.revalidate();
                prop = new ArrayList<Pastille>();
                listIndic.add(convertStringPastille(rep));
                affichageIndic(listIndic.get(listIndic.size() - 1));
                if (listIndic.get(listIndic.size() - 1).equals(listBouleNoires)) { //gestion victoire
                    FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
                    logger.trace("Fin de partie mode Challenger.");
                } else if (plateau.getTourActuel() == plateau.getNbToursMax()) { //gestion défaite
                    FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
                    logger.trace("Fin de partie mode Challenger.");
                }
                plateau.setTourActuel(plateau.getTourActuel() + 1);
            } else{
                JOptionPane.showMessageDialog(null, "Veuillez entrer une combinaison de " + plateau.getP().getNbChiffre()+ " couleurs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class effacerProp implements ActionListener {
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

    public List<Pastille> getListBouleNoires() {
        return listBouleNoires;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public JPanel getProposition() {
        return proposition;
    }

    public void setProposition(JPanel proposition) {
        this.proposition = proposition;
    }

    public List<List<Pastille>> getListProp() {
        return listProp;
    }

    public List<List<Pastille>> getListIndic() {
        return listIndic;
    }

}




