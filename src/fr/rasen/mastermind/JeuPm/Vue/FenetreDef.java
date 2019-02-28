package fr.rasen.mastermind.JeuPm.Vue;

import fr.rasen.mastermind.JeuPm.Plateau;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class FenetreDef extends JPanel {

    private Fenetre fenetre;

    private GridLayout gl;
    private JLabel propo = new JLabel("Proposition");
    private JLabel indication = new JLabel("Indication");
    private JPanel panProp = new JPanel();
    private JPanel panIndic = new JPanel();
    private List<JLabel> listProp = new ArrayList<JLabel>();
    private List<JLabel> listIndic = new ArrayList<JLabel>();
    private JPanel panCenter = new JPanel();
    private Plateau plateau;

    private JPanel panNord = new JPanel();
    private JLabel titre = new JLabel("Jeu du +/-");
    private JLabel mode = new JLabel();
    private JLabel tours = new JLabel();

    private JPanel panEst = new JPanel();
    private JPanel panOuest = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JScrollPane jScrollPane = new JScrollPane();

    private JPanel panSud = new JPanel();
    private JTextField proposition = new JTextField();
    private JButton valider = new JButton("Valider");
    private int compteurTours = 0;
    private static final Logger logger = LogManager.getLogger();

    public FenetreDef(Fenetre f) {
        fenetre = f;
        plateau = new Plateau("def");

        JDialog jd = new JDialog();
        jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JTextField jtf = new JTextField();
        JLabel jl = new JLabel("Veuillez entrer une combinaison de " + plateau.getNbChiffre() + " chiffres");
        jd.setSize(new Dimension(300, 100));
        jd.setBackground(Color.white);
        jd.setLayout(new BorderLayout());
        jd.setLocationRelativeTo(null);
        jd.setTitle("Combinaison");
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (jtf.getText().length() == plateau.getNbChiffre()) {
                    plateau.getGmDef().setCombinaison(jtf.getText());
                    jd.setVisible(false);
                    initPan();
                } else {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Veuillez entrer une combinaison de " + plateau.getNbChiffre() + " chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
                    jtf.setText("");
                    logger.warn("Erreur de création du code à trouver.");
                }

            }
        });
        jtf.setPreferredSize(new Dimension(30,25));
        jd.add(jl,BorderLayout.NORTH);
        jd.add(jtf,BorderLayout.CENTER);
        jd.setVisible(true);
        proposition.addActionListener(new playListener());
        valider.addActionListener(new playListener());
        logger.trace("Initialisation de l'affichage du mode défenseur.");
        }

        public void initPan () {
            this.setLayout(new BorderLayout());
            gl = new GridLayout(plateau.getNbToursMax() + 1, 2);
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
            panCenter.setBackground(Color.white);
            panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
            panCenter.setLayout(new GridBagLayout());


            GridLayout nordLayout = new GridLayout(4, 1);
            ft = new Font("showcard gothic", Font.BOLD, 50);
            titre.setFont(ft);
            panNord.setPreferredSize(new Dimension(600, 200));
            panNord.setBackground(Color.white);
            panNord.setLayout(nordLayout);
            ft = new Font("showcard gothic", Font.BOLD, 25);
            mode.setText("Mode : Défenseur");
            mode.setFont(ft);
            tours.setFont(ft);
            ;
            tours.setText("Nombre de tours restant : " + (plateau.getNbToursMax()));
            tours.setHorizontalAlignment(JLabel.CENTER);
            JLabel combi = new JLabel("Combinaison rentré : " + plateau.getGmDef().getCombinaison());
            combi.setFont(ft);
            combi.setHorizontalAlignment(JLabel.CENTER);
            titre.setHorizontalAlignment(JLabel.CENTER);
            mode.setHorizontalAlignment(JLabel.CENTER);
            panNord.add(titre);
            panNord.add(mode);
            panNord.add(combi);
            panNord.add(tours);

            panEst.setPreferredSize(new Dimension(100, 400));
            panEst.setBackground(Color.white);
            panOuest.setPreferredSize(new Dimension(100, 400));
            panOuest.setBackground(Color.white);

            panSud.setPreferredSize(new Dimension(600, 200));
            panSud.setBackground(Color.white);
            proposition.setPreferredSize(new Dimension(300, 50));

            panSud.add(proposition);
            panSud.add(valider);

            this.add(panNord, BorderLayout.NORTH);
            this.add(panEst, BorderLayout.EAST);
            initTableau();
            this.add(jScrollPane, BorderLayout.CENTER);
            this.add(panOuest, BorderLayout.WEST);
            this.add(panSud, BorderLayout.SOUTH);
            this.getParent().repaint();
            this.getParent().revalidate();
            logger.trace("Affichage du mode Défenseur réussi.");
        }

        public void initTableau (){
            for (int i = 0; i < plateau.getNbToursMax() + 1; i++) {
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                if (i == 0) {
                    gbc.anchor = GridBagConstraints.LINE_START;
                    panCenter.add(panProp, gbc);
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.gridx = 1;
                    gbc.gridy = i;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                    panCenter.add(panIndic, gbc);
                } else if (i==1) {
                    JLabel propLab = new JLabel(plateau.defenseur());
                    Font ft = new Font("showcard gothic", Font.BOLD, 20);
                    propLab.setFont(ft);
                    propLab.setBorder(BorderFactory.createLineBorder(Color.black));
                    propLab.setHorizontalAlignment(JLabel.CENTER);
                    propLab.setPreferredSize(new Dimension(180, 30));
                    propLab.setMinimumSize(new Dimension(180, 30));
                    listProp.add(propLab);
                    gbc.anchor =GridBagConstraints.LINE_START;
                    panCenter.add(listProp.get(0), gbc);
                    gbc.gridy = i;
                    gbc.gridx = 1;
                    JLabel indicLab = new JLabel();
                    indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
                    indicLab.setHorizontalAlignment(JLabel.CENTER);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    indicLab.setPreferredSize(new Dimension(180, 30));
                    indicLab.setMinimumSize(new Dimension(180, 30));
                    panCenter.add(indicLab, gbc);
                } else{
                    JLabel propLab = new JLabel();
                    propLab.setBorder(BorderFactory.createLineBorder(Color.black));
                    propLab.setHorizontalAlignment(JLabel.CENTER);
                    propLab.setPreferredSize(new Dimension(180, 30));
                    propLab.setMinimumSize(new Dimension(180, 30));
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.anchor = GridBagConstraints.LINE_START;
                    panCenter.add(propLab, gbc);
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.gridx = 1;
                    gbc.gridy = i;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    JLabel indicLab = new JLabel();
                    indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
                    indicLab.setHorizontalAlignment(JLabel.CENTER);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                    indicLab.setPreferredSize(new Dimension(180, 30));
                    indicLab.setMinimumSize(new Dimension(180, 30));
                    panCenter.add(indicLab, gbc);
                }
            }
            jScrollPane.setViewportView(panCenter);
            jScrollPane.setBounds(100, 200, 400, 400);
        }

        public void majTableau () {
            panCenter.removeAll();
            panCenter.setBackground(Color.white);
            panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
            panCenter.setLayout(new GridBagLayout());
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            panCenter.add(panProp, gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.BASELINE_LEADING;
            panCenter.add(panIndic, gbc);
            panCenter.add(panProp);
            panCenter.add(panIndic);
            int ligne =1;
            for (int i = 0; i < listProp.size(); i++) {
                if(i == listProp.size()-1){
                    gbc.gridy = ligne;
                    gbc.gridx = 0;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.anchor =GridBagConstraints.LINE_START;
                    panCenter.add(listProp.get(i), gbc);
                    gbc.gridy = ligne;
                    gbc.gridx = 1;
                    JLabel indicLab = new JLabel();
                    indicLab.setBorder(BorderFactory.createLineBorder(Color.black));
                    indicLab.setHorizontalAlignment(JLabel.CENTER);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                    indicLab.setPreferredSize(new Dimension(200, 30));
                    indicLab.setMinimumSize(new Dimension(200, 30));
                    panCenter.add(indicLab, gbc);
                } else {
                    gbc.gridx = 0;
                    gbc.gridy = ligne;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.LINE_START;
                    panCenter.add(listProp.get(i), gbc);
                    gbc.gridx = 1;
                    gbc.gridy = ligne;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.BASELINE_LEADING;
                    panCenter.add(listIndic.get(i), gbc);
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
                propLab.setPreferredSize(new Dimension(200, 30));
                propLab.setMinimumSize(new Dimension(200, 30));
                gbc.fill = GridBagConstraints.BOTH;
                gbc.anchor = GridBagConstraints.LINE_START;
                panCenter.add(propLab, gbc);
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
                indicLab.setPreferredSize(new Dimension(200, 30));
                indicLab.setMinimumSize(new Dimension(200, 30));
                panCenter.add(indicLab, gbc);
                ligne ++;
            }
            panCenter.revalidate();
            tours.removeAll();
            tours.setText("Nombre de tours restant : " + (plateau.getNbToursMax() - compteurTours));
            tours.setHorizontalAlignment(JLabel.CENTER);
            tours.revalidate();
            this.add(jScrollPane, BorderLayout.CENTER);

        }

        public void gagne () {
            FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
            logger.trace("Fin de partie en mode Défenseur : Victoire du joueur");
        }

        public void perdu () {
            FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
            logger.trace("Fin de partie du mode Défenseur : Victoire de l'ordinateur");
        }


        public List<JLabel> getListProp () {
            return listProp;
        }

        public List<JLabel> getListIndic () {
            return listIndic;
        }

        public JTextField getProposition () {
            return proposition;
        }

        public void setProposition (JTextField proposition){
            this.proposition = proposition;
        }

        public Plateau getPlateau () {
            return plateau;
        }

        public void setPlateau (Plateau plateau){
            this.plateau = plateau;
        }

        public int getCompteurTours () {
            return compteurTours;
        }

        public JButton getValider () {
            return valider;
        }

        class playListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                if (plateau.getNbChiffre() == proposition.getText().length()) {
                    Font ft = new Font("showcard gothic", Font.BOLD, 20);
                    JLabel prop = new JLabel();
                    prop.setFont(ft);
                    prop.setBorder(BorderFactory.createLineBorder(Color.black));
                    prop.setMinimumSize(new Dimension(200,30));
                    prop.setText(proposition.getText());
                    prop.setHorizontalAlignment(JLabel.CENTER);
                    plateau.getjDef().setDerRep(prop.getText());
                    listIndic.add(compteurTours, prop);
                    proposition.setText("");
                    JLabel indic = new JLabel();
                    indic.setFont(ft);
                    indic.setBorder(BorderFactory.createLineBorder(Color.black));
                    indic.setText(plateau.defenseur());
                    indic.setHorizontalAlignment(JLabel.CENTER);
                    indic.setMinimumSize(new Dimension(200,30));
                    listProp.add(compteurTours + 1, indic);
                    majTableau();
                    if (prop.getText().equals(plateau.getEgalFinal())) {
                        perdu();
                    } else if (plateau.getNbToursMax() == compteurTours - 1) {
                        gagne();
                    } else if (plateau.getP().isModeDev()) {
                        proposition.setText(plateau.getGmDef().evalProp(indic.getText()));
                    }
                }
            }

        }
    }
