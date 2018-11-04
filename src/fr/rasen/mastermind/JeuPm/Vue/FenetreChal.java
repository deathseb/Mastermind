package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.rasen.mastermind.JeuPm.Plateau;

public class FenetreChal extends JPanel {
	
	private GridLayout gl;
	private JLabel propo = new JLabel("Proposition");
	private JLabel indication = new JLabel("Indication");
	private List<JLabel> listProp = new ArrayList<JLabel>();
	private List<JLabel> listIndic = new ArrayList<JLabel>();
	private JPanel panCenter = new JPanel();
	private Plateau plateau;
	
	private JPanel panNord = new JPanel();
	private JLabel titre = new JLabel("Jeu du +/-");
	private JLabel mode = new JLabel();
	
	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();
	
	private JPanel panSud = new JPanel();
	private JTextField proposition = new JTextField();
	private JButton valider = new JButton("Valider");
	
	public FenetreChal() {
		this.setLayout(new BorderLayout());
		plateau = new Plateau("chall");
		
		gl = new GridLayout(plateau.getNbToursMax()+1,2);
		propo.setBorder(BorderFactory.createLineBorder(Color.black));
		indication.setBorder(BorderFactory.createLineBorder(Color.black));
		propo.setHorizontalAlignment(JLabel.CENTER);
		indication.setHorizontalAlignment(JLabel.CENTER);
		panCenter.setBackground(Color.white);
		panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		panCenter.setLayout(gl);
		panCenter.add(propo);
		panCenter.add(indication);
		
		GridLayout nordLayout = new GridLayout(2,1);
		Font ft = new Font("bauhaus 93", Font.BOLD, 50);
		titre.setFont(ft);
		panNord.setPreferredSize(new Dimension(600,200));
		panNord.setBackground(Color.white);
		panNord.setLayout(nordLayout);
		ft = new Font("bauhaus 93", Font.BOLD, 30);
		mode.setText("Mode : Challenger");
		mode.setFont(ft);
		titre.setHorizontalAlignment(JLabel.CENTER);
		mode.setHorizontalAlignment(JLabel.CENTER);
		panNord.add(titre);
		panNord.add(mode);
		
		panEst.setPreferredSize(new Dimension(100,400));
		panEst.setBackground(Color.white);
		panOuest.setPreferredSize(new Dimension(100,400));
		panOuest.setBackground(Color.white);
		
		panSud.setPreferredSize(new Dimension(600,200));
		panSud.setBackground(Color.white);
		proposition.setPreferredSize(new Dimension(300, 50));
		proposition.addActionListener(new playListener());
		panSud.add(proposition);
		panSud.add(valider);
		
		this.add(panNord, BorderLayout.NORTH);
		this.add(panEst, BorderLayout.EAST);
		initTableau(plateau.getNbToursMax());
		this.add(new JScrollPane(panCenter), BorderLayout.CENTER);
		this.add(panOuest, BorderLayout.WEST);
		this.add(panSud, BorderLayout.SOUTH);
		
	}
	
	public void initTableau (int nb) {
		for(int i =0; i< nb; i++) {
			JLabel prop = new JLabel();
			prop.setBorder(BorderFactory.createLineBorder(Color.black));
			prop.setHorizontalAlignment(JLabel.CENTER);
			JLabel indic = new JLabel();
			indic.setBorder(BorderFactory.createLineBorder(Color.black));
			indic.setHorizontalAlignment(JLabel.CENTER);
			listProp.add(prop);
			listIndic.add(indic);
			panCenter.add(prop);
			panCenter.add(indic);
		}
	}
	
	class playListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		
	}

}
