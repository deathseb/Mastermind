package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
	
	private Container pan = this.getContentPane();
	
	public Fenetre() {
		this.setTitle("P3 Mastermind");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initMenu();
	}
	
	public void initMenu() {
		Font ft = new Font("bauhaus 93", Font.BOLD, 50);
		titre.setFont(ft);
		panNord.setPreferredSize(new Dimension(600,200));
		panNord.setBackground(Color.white);
		panNord.add(titre);
		this.getContentPane().add(panNord, BorderLayout.NORTH);
		menu.setLayout(new GridLayout(5,1));
		menu.setBackground(Color.white);
		
		chall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pan.removeAll();
				initChall();
				pan.repaint();
				pan.revalidate();
			}
			
		});
		def.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pan.removeAll();
				initDef();
				pan.repaint();
				pan.revalidate();
			}
			
		});
		duel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pan.removeAll();
				initDuel();
				pan.repaint();
				pan.revalidate();
			}
			
		});
		JPanel panChall = new JPanel();
		panChall.setBackground(Color.white);
		panChall.add(chall);
		JPanel panDef = new JPanel();
		panDef.setBackground(Color.white);
		panDef.add(def);
		JPanel panDuel = new JPanel();
		panDuel.setBackground(Color.white);
		panDuel.add(duel);
		JPanel panPropriete = new JPanel();
		panPropriete.setBackground(Color.white);
		panPropriete.add(propriete);
		JPanel panPropos = new JPanel();
		panPropos.setBackground(Color.white);
		panPropos.add(apropos);
		menu.add(panChall);
		menu.add(panDef);
		menu.add(panDuel);
		menu.add(panPropriete);
		menu.add(panPropos);
		this.getContentPane().add(menu, BorderLayout.CENTER);
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
		
	}
	
	
	public static void main (String[] args) {
		Fenetre f = new Fenetre();
		f.setVisible(true);
	}
}
