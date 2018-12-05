package fr.rasen.mastermind.JeuPm.Vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FenetreDuel extends JPanel {

    private FenetreChal fc;
    private FenetreDef fd;
    private static final Logger logger = LogManager.getLogger();

    public FenetreDuel(){
        fc = new FenetreChal();
        fd = new FenetreDef();
        fc.setBorder(BorderFactory.createLineBorder(Color.green, 3));
        fd.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        fd.getProposition().setEnabled(false);
        ActionListener [] al = fc.getProposition().getActionListeners();
        fc.getProposition().removeActionListener(al[0]);
        fc.getValider().removeActionListener(al[0]);
        fc.getProposition().addActionListener(new PlayDuelListenerChall());
        fc.getValider().addActionListener(new PlayDuelListenerChall());
        al = fd.getProposition().getActionListeners();
       fd.getValider().removeActionListener(al[0]);
        fd.getProposition().removeActionListener(al[0]);
        fd.getProposition().addActionListener(new PlayDuelListenerDef());
        fd.getValider().addActionListener(new PlayDuelListenerDef());

        this.setLayout(new BorderLayout());
        this.add(fd, BorderLayout.EAST);
        this.add(fc, BorderLayout.WEST);
        logger.trace("Affichage mode Duel réussi.");
    }

    public void transitionCD(){
        Thread t = new Thread(new ThreadFocusCD());
        t.start();
    }

    public void transitionDC(){
        Thread t = new Thread(new ThreadFocusDC());
        t.start();
    }


    class PlayDuelListenerChall implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if (fc.getPlateau().getNbChiffre() == fc.getProposition().getText().length()) {
                Font ft = new Font("showcard gothic", Font.BOLD, 15);
                JLabel prop = new JLabel();
                prop.setPreferredSize(new Dimension(180, 360/(fc.getPlateau().getNbToursMax()+1)));
                prop.setFont(ft);
                prop.setHorizontalAlignment(JLabel.CENTER);
                prop.setBorder(BorderFactory.createLineBorder(Color.black));
                prop.setText(fc.getProposition().getText());
                fc.getListProp().add(fc.getCompteurTours(), prop);
                JLabel indic = new JLabel();
                indic.setFont(ft);
                indic.setPreferredSize(new Dimension(180, 360/(fc.getPlateau().getNbToursMax()+1)));
                indic.setHorizontalAlignment(JLabel.CENTER);
                indic.setBorder(BorderFactory.createLineBorder(Color.black));
                indic.setText(fc.getPlateau().challenger(fc.getProposition().getText()));
                fc.getListIndic().add(fc.getCompteurTours(), indic);
                fc.getProposition().setText("");
                fc.majTableau();
                String egalFinal = fc.getPlateau().getEgalFinal();
                if (indic.getText().equals(egalFinal)) {
                    fc.gagne();
                }
                if(fc.getPlateau().getNbToursMax() == fc.getCompteurTours()-1) {
                    fc.perdu();
                }
                transitionCD();
            }
        }
    }

    class PlayDuelListenerDef implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if (fd.getPlateau().getNbChiffre() == fd.getProposition().getText().length()) {
                Font ft = new Font("showcard gothic", Font.BOLD, 15);
                JLabel prop = new JLabel();
                prop.setFont(ft);
                prop.setBorder(BorderFactory.createLineBorder(Color.black));
                prop.setText(fd.getProposition().getText());
                prop.setHorizontalAlignment(JLabel.CENTER);
                fd.getPlateau().getjDef().setDerRep(prop.getText());
                fd.getListIndic().add(fd.getCompteurTours(), prop);
                fd.getProposition().setText("");
                JLabel indic = new JLabel();
                indic.setFont(ft);
                indic.setBorder(BorderFactory.createLineBorder(Color.black));
                indic.setText(fd.getPlateau().defenseur());
                indic.setHorizontalAlignment(JLabel.CENTER);
                fd.getListProp().add(fd.getCompteurTours()+1, indic);
                fd.majTableau();
                if (prop.getText().equals(fd.getPlateau().getEgalFinal())) {
                    fd.perdu();
                } else if(fd.getPlateau().getNbToursMax() == fd.getCompteurTours()-1) {
                    fd.gagne();
                } else if(fd.getPlateau().getP().isModeDev()){
                    fd.getProposition().setText(fd.getPlateau().getGmDef().evalProp(indic.getText()));
                }
                transitionDC();
            }
        }
    }

    class ThreadFocusCD implements Runnable {
        public void run() {
            fc.getProposition().setEnabled(false);
            fc.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            Thread thread = new Thread();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Erreur lors du thread de pause.");
            }
            fd.setBorder(BorderFactory.createLineBorder(Color.green, 3));
            fd.getProposition().setEnabled(true);
        }
    }

    class ThreadFocusDC implements Runnable {
        public void run() {
            fd.getProposition().setEnabled(false);
            fd.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            Thread thread = new Thread();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Erreur lors du thread de pause.");
            }
            fc.setBorder(BorderFactory.createLineBorder(Color.green, 3));
            fc.getProposition().setEnabled(true);
        }
    }
}
