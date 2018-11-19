package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.rasen.mastermind.JeuPm.Plateau;

public class FenetreProperties extends JDialog {

	private JPanel panInfo = new JPanel();

	private JLabel textTours = new JLabel("Nombre de tours de jeu : ");
	private JPanel panTours = new JPanel();
	private JTextField donneeTours = new JTextField();

	private JLabel textChiffre = new JLabel("Nombre de chiffre dans la combinaison : ");
	private JPanel panChiffre = new JPanel();
	private JTextField donneeChiffre = new JTextField();

	private JLabel textDev = new JLabel("Mode Développeur activé ? ");
	private JCheckBox modeDev = new JCheckBox();

	private GridLayout gl = new GridLayout(3,2);

	private JPanel panSud = new JPanel();
	private JButton sauvegarder = new JButton("Sauvegarder");
	
	private JDialog jd = this;

	
	public FenetreProperties(Plateau p) {
		
		this.setLayout(new BorderLayout());
		this.setTitle("Propriétés");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		panInfo.setLayout(gl);
		donneeTours.setText(String.valueOf(p.getP().getNbTours()));
		donneeChiffre.setText(String.valueOf(p.getP().getNbChiffre()));
		modeDev.setSelected(p.getP().isModeDev());
		donneeTours.setPreferredSize(new Dimension(200, 25));
		donneeChiffre.setPreferredSize(new Dimension(200,25));
		textTours.setHorizontalAlignment(JLabel.CENTER);
		textChiffre.setHorizontalAlignment(JLabel.CENTER);
		textDev.setHorizontalAlignment(JLabel.CENTER);
		panTours.add(donneeTours);
		panChiffre.add(donneeChiffre);	
		
		sauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.getP().setModeDev(modeDev.isSelected());
				p.getP().setNbChiffre(Integer.parseInt(donneeChiffre.getText()));
				p.getP().setNbTours(Integer.parseInt(donneeTours.getText()));
				p.getP().sauvegarde();
				jd.setVisible(false);
			}
		});
		
		panInfo.add(textTours);
		panInfo.add(panTours);
		panInfo.add(textChiffre);
		panInfo.add(panChiffre);
		panInfo.add(textDev);
		panInfo.add(modeDev);
		panSud.add(sauvegarder);
		
		this.add(panInfo, BorderLayout.CENTER);
		this.add(panSud, BorderLayout.SOUTH);
	}

}
