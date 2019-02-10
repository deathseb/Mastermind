package fr.rasen.mastermind;

import fr.rasen.mastermind.Mastermind.vue.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Projet3 extends JFrame {
    private JPanel panTitre = new JPanel();
    private JLabel titre = new JLabel("Projet 3");
    private JPanel panPM = new JPanel();
    private JButton buttonPM = new JButton("Jouer au jeu du +/-");
    private JPanel panMaster = new JPanel();
    private JButton buttonMaster = new JButton("Jouer au Mastermind");
    private JPanel panButton = new JPanel();
    private GridLayout gridButton = new GridLayout(5,1);
    private Projet3 p3;

    public Projet3(){
        this.setSize(new Dimension(600,800));
        this.setTitle("Projet 3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initFrame();
        p3 = this;
    }


    public void initFrame(){
        this.setLayout(new BorderLayout());
        panTitre.setBackground(Color.white);
        this.setBackground(Color.white);
        titre.setPreferredSize(new Dimension(600,100));
        titre.setFont(new Font("showcard gothic", Font.BOLD, 50));
        titre.setBackground(Color.white);
        titre.setHorizontalAlignment(JLabel.CENTER);
        panTitre.add(titre);
        this.add(panTitre, BorderLayout.NORTH);
        buttonPM.setFont(new Font("shocard gothic", Font.CENTER_BASELINE,20));
        buttonPM.setForeground(Color.blue);
        buttonPM.setBackground(Color.white);
        buttonPM.setOpaque(false);
        buttonPM.setContentAreaFilled(false);
        buttonPM.setBorderPainted(false);
        buttonPM.addActionListener(new PMListener());
        buttonMaster.setFont(new Font("shocard gothic", Font.CENTER_BASELINE,20));
        buttonMaster.setForeground(Color.red);
        buttonMaster.setBackground(Color.white);
        buttonMaster.setOpaque(false);
        buttonMaster.setContentAreaFilled(false);
        buttonMaster.setBorderPainted(false);
        buttonMaster.addActionListener(new MastermindListener());
        panPM.setBackground(Color.white);
        panPM.add(buttonPM);
        panMaster.setBackground(Color.white);
        panMaster.add(buttonMaster);
        panButton.setLayout(gridButton);
        panButton.add(panPM);
        panButton.add(panMaster);
        for(int i=0; i<gridButton.getRows()-2; i++){
            JPanel pan = new JPanel();
            pan.setBackground(Color.white);
            panButton.add(pan);
        }
        this.add(panButton, BorderLayout.CENTER);
    }

    class MastermindListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            p3.setVisible(false);
            Fenetre fenetreMastermind = new Fenetre(p3);
            fenetreMastermind.setVisible(true);
        }
    }

    class PMListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            p3.setVisible(false);
            fr.rasen.mastermind.JeuPm.Vue.Fenetre fenetrePM = new fr.rasen.mastermind.JeuPm.Vue.Fenetre(p3);
            fenetrePM.setVisible(true);
        }
    }



    public static void main(String[] args) {
        Projet3 projet3 = new Projet3();
        projet3.setVisible(true);
    }
}
