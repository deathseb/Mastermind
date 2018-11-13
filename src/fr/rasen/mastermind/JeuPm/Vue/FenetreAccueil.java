package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreAccueil extends JPanel {

	private JPanel panNord = new JPanel();
	private JLabel titre = new JLabel("Jeu du +/-");

	private JButton chall = new JButton("Jouer en mode Challenger");
	private JButton def = new JButton("Jouer en mode Défenseur");
	private JButton duel = new JButton("Jouer en mode Duel");
	private JButton propriete = new JButton("Propriétés");
	private JButton apropos = new JButton("A Propos");
	private JPanel menu = new JPanel();

	private JPanel pan = this;

	public FenetreAccueil() {
		this.setLayout(new BorderLayout());
		Font ft = new Font("bauhaus 93", Font.BOLD, 50);
		titre.setFont(ft);
		panNord.setPreferredSize(new Dimension(600,200));
		panNord.setBackground(Color.white);
		panNord.add(titre);
		this.add(panNord, BorderLayout.NORTH);
		menu.setLayout(new GridLayout(5,1));
		menu.setBackground(Color.white);
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
		this.add(menu, BorderLayout.CENTER);

	}

	public JPanel getPanNord() {
		return panNord;
	}

	public void setPanNord(JPanel panNord) {
		this.panNord = panNord;
	}

	public JLabel getTitre() {
		return titre;
	}

	public void setTitre(JLabel titre) {
		this.titre = titre;
	}

	public JButton getChall() {
		return chall;
	}

	public void setChall(JButton chall) {
		this.chall = chall;
	}

	public JButton getDef() {
		return def;
	}

	public void setDef(JButton def) {
		this.def = def;
	}

	public JButton getDuel() {
		return duel;
	}

	public void setDuel(JButton duel) {
		this.duel = duel;
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

	public void setApropos(JButton apropos) {
		this.apropos = apropos;
	}

	public JPanel getMenu() {
		return menu;
	}

	public void setMenu(JPanel menu) {
		this.menu = menu;
	}

	public JPanel getPan() {
		return pan;
	}

	public void setPan(JPanel pan) {
		this.pan = pan;
	}

}
