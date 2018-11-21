package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.rasen.mastermind.JeuPm.Plateau;

public class Fenetre extends JFrame{

	private JPanel panNord = new JPanel();
	private JLabel titre = new JLabel("Jeu du +/-");
	
	private JButton chall = new JButton("Jouer en mode Challenger");
	private JButton def = new JButton("Jouer en mode Défenseur");
	private JButton duel = new JButton("Jouer en mode Duel");
	private JButton propriete = new JButton("Propriétés");
	private JButton apropos = new JButton("A Propos");
	private JPanel menu = new JPanel();
	
	private FenetreChal fc;
	private FenetreDef fd;
	private FenetreAccueil fa;
	private FenetreDuel fDuel;
	
	private Container pan = this.getContentPane();
	
	public Fenetre() {
		this.setTitle("P3 Mastermind");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initMenu();
	}
	
	public void initMenu() {
		fa = new FenetreAccueil();
		fa.getChall().addActionListener(new challListener());
		fa.getDef().addActionListener(new defListener());
		fa.getDuel().addActionListener(new duelListener());
		fa.getPropriete().addActionListener(new proprieteListener());
		this.getContentPane().add(fa, BorderLayout.CENTER);
	}
	
	public void initChall() {
		fc = new FenetreChal();
		this.getContentPane().add(fc, BorderLayout.CENTER);
		
	}
	
	public void initDef() {
		fd = new FenetreDef();
		this.getContentPane().add(fd, BorderLayout.CENTER);
	}
	
	public void initDuel() {
		fDuel = new FenetreDuel();
		this.getContentPane().removeAll();
		this.setSize(new Dimension(1200, 800));
		this.getContentPane().add(fDuel, BorderLayout.CENTER);
		this.revalidate();
	}
	
	class challListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			pan.removeAll();
			initChall();
			pan.repaint();
			pan.revalidate();
		}
	}
	
	class defListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pan.removeAll();
			initDef();
			pan.repaint();
			pan.revalidate();
		}
	}
	
	class duelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			pan.removeAll();
			initDuel();
			pan.repaint();
			pan.revalidate();
		}
	}
	
	class proprieteListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Plateau plateau = new Plateau();
			FenetreProperties fp = new FenetreProperties(plateau);
			fp.setVisible(true);
		}
		
	}
	
	
	public static void main (String[] args) {
		Fenetre f = new Fenetre();
		f.setVisible(true);
	}
}
