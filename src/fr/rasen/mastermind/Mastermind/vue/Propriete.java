package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boite de dialogue permettant d'afficher les propriétés du jeu et de les modifier avant de démarrer une partie.
 */
public class Propriete extends JDialog{

    //-- OFA
    private JPanel panNord = new JPanel();
    private JPanel panOuest = new JPanel();
    private JPanel panEst = new JPanel();

    private JPanel panInfo = new JPanel();

    private JLabel textTours = new JLabel("Nombre de tours de jeu : ");
    private JPanel panTours = new JPanel();
    private JTextField donneeTours = new JTextField();

    private JLabel textChiffre = new JLabel("Taille de la combinaison : ");
    private JPanel panChiffre = new JPanel();
    private JTextField donneeChiffre = new JTextField();

    private JLabel textCouleurs = new JLabel("Nombre de couleurs différentes utilisable (de 4 à 10");
    private JPanel panCouleurs = new JPanel();
    private JTextField donneeCouleurs = new JTextField();

    private JLabel textDev = new JLabel("Mode Développeur activé ? ");
    private JCheckBox modeDev = new JCheckBox();

    private GridLayout gl = new GridLayout(7,2);

    private JPanel panSud = new JPanel();
    private JButton sauvegarder = new JButton("Sauvegarder");

    private JDialog jd = this;


    public Propriete (Plateau p) {

        this.setLayout(new BorderLayout());
        this.setTitle("Propriétés");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        //-- OFA : J'ajouter des bordures pour comprendre comment les placements s'effectuent.
        panInfo.setBorder(BorderFactory.createLineBorder(Color.red));
        panSud.setBorder(BorderFactory.createLineBorder(Color.yellow));
        panTours.setBorder(BorderFactory.createLineBorder(Color.blue));
        panChiffre.setBorder(BorderFactory.createLineBorder(Color.green));

        //-- OFA : Les panneaux Nord, Ouest et Est me permettent de compresser le panneau du centre afin de mieux le centrer.
        panNord.setPreferredSize(new Dimension(600, 50));
        panOuest.setPreferredSize(new Dimension(100, 400));
        panEst.setPreferredSize(new Dimension(100, 400));

        panInfo.setLayout(gl);

        donneeTours.setText(String.valueOf(p.getP().getNbTours()));
        donneeChiffre.setText(String.valueOf(p.getP().getNbChiffre()));
        modeDev.setSelected(p.getP().isModeDev());
        donneeCouleurs.setText(String.valueOf(p.getP().getNbCouleursMax()));

        donneeTours.setPreferredSize(new Dimension(60, 25));
        donneeChiffre.setPreferredSize(new Dimension(60,25));
        donneeCouleurs.setPreferredSize(new Dimension(60,25));

        textTours.setHorizontalAlignment(JLabel.CENTER);
        textChiffre.setHorizontalAlignment(JLabel.CENTER);
        textDev.setHorizontalAlignment(JLabel.CENTER);
        textCouleurs.setHorizontalAlignment(JLabel.CENTER);

        panTours.setAlignmentX(0.0F);
        panTours.add(donneeTours);
        panChiffre.add(donneeChiffre);
        panCouleurs.add(donneeCouleurs);

        sauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (Integer.parseInt(donneeCouleurs.getText())>=4 && Integer.parseInt(donneeCouleurs.getText())<=10){
                    p.getP().setModeDev(modeDev.isSelected());
                    p.getP().setNbChiffre(Integer.parseInt(donneeChiffre.getText()));
                    p.getP().setNbTours(Integer.parseInt(donneeTours.getText()));
                    p.getP().setNbCouleursMax(Integer.parseInt(donneeCouleurs.getText()));
                    p.getP().sauvegarde();
                    jd.setVisible(false);
                }
            }
        });

        panInfo.add(textTours);     panInfo.add(panTours);
        panInfo.add(textChiffre);   panInfo.add(panChiffre);
        panInfo.add(textCouleurs);  panInfo.add(panCouleurs);
        panInfo.add(textDev);       panInfo.add(modeDev);

        //-- OFA : Ajout de 3x2 JLabel pour pousser les 3 lignes précédentes vers le haut.
        // Cela permet de se direqu'on peut rajouter des paramèters plus tard.
        // Et cela permet de forcer l'alignement du cintenu des lignes du dessus.
        // Aparemment c'est plus simple d'aligner le contenu d'une grille de type GridBagLyout en utilisant un GridBagConstraints
        // cf https://stackoverflow.com/questions/11357720/java-vertical-alignment-within-jpanel
        panInfo.add(new JLabel());  panInfo.add(new JLabel());
        panInfo.add(new JLabel());  panInfo.add(new JLabel());
        panInfo.add(new JLabel());  panInfo.add(new JLabel());
        panSud.add(sauvegarder);

        //-- OFA : J'ai ajouté les panneaux Nors, Ouest et Est dans le BorderLayout.
        this.add(panNord, BorderLayout.NORTH);
        this.add(panOuest, BorderLayout.WEST);
        this.add(panInfo, BorderLayout.CENTER);
        this.add(panEst, BorderLayout.EAST);
        this.add(panSud, BorderLayout.SOUTH);

    }

}
