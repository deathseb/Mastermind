package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AffichagePastille extends JLabel  implements MouseListener {
    private Pastille pastille;
    private Challenger challenger;

    public AffichagePastille(Pastille p, Challenger c){
        pastille = p;
        challenger = c;
        this.setIcon(new ImageIcon(pastille.getFichier()));
        this.addMouseListener(this);
    }

    public Pastille getPastille() {
        return pastille;
    }

    public void setPastille(Pastille pastille) {
        this.pastille = pastille;
    }

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        challenger.getProp().add(this.pastille);
        challenger.ajoutProp();

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
}
