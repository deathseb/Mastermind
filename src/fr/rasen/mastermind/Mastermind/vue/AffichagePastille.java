package fr.rasen.mastermind.Mastermind.vue;

import fr.rasen.mastermind.Mastermind.Pastille;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AffichagePastille extends JLabel  implements MouseListener {
    private Pastille pastille;
    private Challenger challenger;
    private AffichagePastille affichagePastille = this;

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
        challenger.getProp().add(affichagePastille);
        challenger.ajoutProp();
    }


    public void mousePressed(MouseEvent e) {
        challenger.getProp().add(affichagePastille);
        challenger.ajoutProp();
    }


    public void mouseReleased(MouseEvent e) {
        challenger.getProp().add(affichagePastille);
        challenger.ajoutProp();
    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
}
