package fr.rasen.mastermind.Mastermind.vue;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JPanel {


    private JPanel panNord = new JPanel();
    private JLabel titre = new JLabel("Mastermind");

    private JButton chall = new JButton("Jouer en mode Challenger");
    private JButton def = new JButton("Jouer en mode Défenseur");
    private JButton duel = new JButton("Jouer en mode Duel");
    private JButton propriete = new JButton("Propriétés");
    private JButton apropos = new JButton("A Propos");
    private JPanel menu = new JPanel();
    private JPanel pan = this;

    public Accueil() {
        this.setLayout(new BorderLayout());
        Font ft = new Font("showcard gothic", Font.BOLD, 50);
        titre.setFont(ft);
        panNord.setPreferredSize(new Dimension(600, 200));
        panNord.setBackground(Color.white);
        panNord.add(titre);
        this.add(panNord, BorderLayout.NORTH);
        menu.setLayout(new GridLayout(5, 1));
        chall.setFont(new Font("showcard gothic", Font.ITALIC, 20));
        chall.setOpaque(false);
        chall.setContentAreaFilled(false);
        chall.setBorderPainted(false);
        JPanel panChall = new JPanel();
        panChall.setBackground(Color.white);
        panChall.add(chall);
        def.setFont(new Font("showcard gothic", Font.ITALIC, 20));
        def.setOpaque(false);
        def.setContentAreaFilled(false);
        def.setBorderPainted(false);
        JPanel panDef = new JPanel();
        panDef.setBackground(Color.white);
        panDef.add(def);
        duel.setFont(new Font("showcard gothic", Font.ITALIC, 20));
        duel.setOpaque(false);
        duel.setContentAreaFilled(false);
        duel.setBorderPainted(false);
        JPanel panDuel = new JPanel();
        panDuel.setBackground(Color.white);
        panDuel.add(duel);
        propriete.setFont(new Font("showcard gothic", Font.ITALIC, 20));
        propriete.setOpaque(false);
        propriete.setContentAreaFilled(false);
        propriete.setBorderPainted(false);
        JPanel panPropriete = new JPanel();
        panPropriete.setBackground(Color.white);
        panPropriete.add(propriete);
        apropos.setFont(new Font("showcard gothic", Font.ITALIC, 20));
        apropos.setOpaque(false);
        apropos.setContentAreaFilled(false);
        apropos.setBorderPainted(false);
        JPanel panPropos = new JPanel();
        panPropos.setBackground(Color.white);
        panPropos.add(apropos);
        menu.add(panChall);
        menu.add(panDef);
        menu.add(panDuel);
        menu.add(panPropriete);
        menu.add(panPropos);
        this.add(menu, BorderLayout.CENTER);

    }

    public JButton getChall() {
        return chall;
    }

    public JButton getDef() {
        return def;
    }

    public JButton getDuel() {
        return duel;
    }

    public JButton getPropriete() {
        return propriete;
    }

    public void setPropriete(JButton propriete) {
        this.propriete = propriete;
    }

    public JButton getApropos() {
        return apropos;
    }

}


