package fr.rasen.mastermind.Mastermind.vue;

import javax.swing.*;

public class Fenetre extends JFrame {

    private Challenger challenger = new Challenger(this);

    public Fenetre(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setTitle("Mastermind");
        this.getContentPane().add(challenger);
    }

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
        f.setVisible(true);
    }
}
