package fr.rasen.mastermind.JeuPm.Vue;

import fr.rasen.mastermind.JeuPm.Plateau;
import fr.rasen.mastermind.Projet3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame{

	private Projet3 projet3;

	private FenetreChal fc;
	private FenetreDef fd;
	private FenetreAccueil fa;
	private FenetreDuel fDuel;
	
	private Container pan = this.getContentPane();
	
	public Fenetre(Projet3 p) {
		projet3 = p;
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
		fc = new FenetreChal(this);
		this.getContentPane().add(fc, BorderLayout.CENTER);
		
	}
	
	public void initDef() {
		fd = new FenetreDef(this);
		this.getContentPane().add(fd, BorderLayout.CENTER);
	}
	
	public void initDuel() {
		fDuel = new FenetreDuel(this);
		this.getContentPane().removeAll();
		this.setSize(new Dimension(1228, 800));
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

	public Projet3 getProjet3() {
		return projet3;
	}
}
