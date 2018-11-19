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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.rasen.mastermind.JeuPm.Plateau;

public class FenetreDef extends JPanel {

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
	private JLabel tours = new JLabel();

	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();

	private JPanel panSud = new JPanel();
	private JTextField proposition = new JTextField();
	private JButton valider = new JButton("Valider");
	private int compteurTours = 0;

	public FenetreDef() {
		plateau = new Plateau("def");

		JDialog jd = new JDialog();
		JTextField jtf = new JTextField();
		JLabel jl = new JLabel("Veuillez entrer une combinaison de " + plateau.getNbChiffre() + " chiffres");
		jd.setSize(new Dimension(300,100));
		jd.setBackground(Color.white);
		jd.setLayout(new BorderLayout());
		jd.setLocationRelativeTo(null);
		jd.setTitle("Combinaison");
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		jtf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plateau.getGmDef().setCombinaison(jtf.getText());
				jd.setVisible(false);
				initPan();
			}
		});
		jtf.setPreferredSize(new Dimension(30,25));
		jd.add(jl, BorderLayout.NORTH);
		jd.add(jtf, BorderLayout.CENTER);
		jd.setVisible(true);
	}

	public void initPan() {
		this.setLayout(new BorderLayout());
		gl = new GridLayout(plateau.getNbToursMax()+1,2);
		Font ft = new Font("showcard gothic", Font.BOLD, 20);
		propo.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
		propo.setFont(ft);
		propo.setBorder(BorderFactory.createLineBorder(Color.black));
		indication.setBorder(BorderFactory.createLineBorder(Color.black));
		propo.setHorizontalAlignment(JLabel.CENTER);
		indication.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
		indication.setFont(ft);
		indication.setHorizontalAlignment(JLabel.CENTER);
		panCenter.setPreferredSize(new Dimension(360, 360));
		panCenter.setBackground(Color.white);
		panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		panCenter.setLayout(gl);
		panCenter.add(propo);
		panCenter.add(indication);

		GridLayout nordLayout = new GridLayout(4,1);
		ft = new Font("showcard gothic", Font.BOLD, 50);
		titre.setFont(ft);
		panNord.setPreferredSize(new Dimension(600,200));
		panNord.setBackground(Color.white);
		panNord.setLayout(nordLayout);
		ft = new Font("showcard gothic", Font.BOLD, 25);
		mode.setText("Mode : Défenseur");
		mode.setFont(ft);
		tours.setFont(ft);;
		tours.setText("Nombre de tours restant : " + String.valueOf(plateau.getNbToursMax()));
		tours.setHorizontalAlignment(JLabel.CENTER);
		JLabel combi = new JLabel("Combinaison rentré : " + plateau.getGmDef().getCombinaison());
		combi.setFont(ft);
		combi.setHorizontalAlignment(JLabel.CENTER);
		titre.setHorizontalAlignment(JLabel.CENTER);
		mode.setHorizontalAlignment(JLabel.CENTER);
		panNord.add(titre);
		panNord.add(mode);
		panNord.add(combi);
		panNord.add(tours);

		panEst.setPreferredSize(new Dimension(100,400));
		panEst.setBackground(Color.white);
		panOuest.setPreferredSize(new Dimension(100,400));
		panOuest.setBackground(Color.white);

		panSud.setPreferredSize(new Dimension(600,200));
		panSud.setBackground(Color.white);
		proposition.setPreferredSize(new Dimension(300, 50));
		proposition.addActionListener(new playListener());
		valider.addActionListener(new playListener());
		panSud.add(proposition);
		panSud.add(valider);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(panNord, BorderLayout.NORTH);
		this.add(panEst, BorderLayout.EAST);
		initTableau(plateau.getNbToursMax());
		this.add(panCenter, BorderLayout.CENTER);
		this.add(panOuest, BorderLayout.WEST);
		this.add(panSud, BorderLayout.SOUTH);
		this.getParent().repaint();
		this.getParent().revalidate();

	}

	public void initTableau (int nb) {
		for(int i =0; i< nb; i++) {
			if(i ==0) {
				JLabel prop = new JLabel();
				Font ft = new Font("showcard gothic", Font.BOLD, 15);
				prop.setFont(ft);
				prop.setBorder(BorderFactory.createLineBorder(Color.black));
				prop.setHorizontalAlignment(JLabel.CENTER);
				prop.setText(plateau.defenseur());
				listProp.add(compteurTours, prop);
				JLabel indic = new JLabel();
				indic.setBorder(BorderFactory.createLineBorder(Color.black));
				indic.setHorizontalAlignment(JLabel.CENTER);
				panCenter.add(prop);
				panCenter.add(indic);
			} else {
				JLabel prop = new JLabel();
				prop.setBorder(BorderFactory.createLineBorder(Color.black));
				prop.setHorizontalAlignment(JLabel.CENTER);
				JLabel indic = new JLabel();
				indic.setBorder(BorderFactory.createLineBorder(Color.black));
				indic.setHorizontalAlignment(JLabel.CENTER);
				panCenter.add(prop);
				panCenter.add(indic);
			}
		}
	}

	public void majTableau() {
		panCenter.removeAll();
		//panCenter = new JPanel();
		panCenter.setBackground(Color.white);
		panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		panCenter.setLayout(new GridLayout(plateau.getNbToursMax()+1, 2));
		panCenter.add(propo);
		panCenter.add(indication);
		for(int i = 0; i < listProp.size(); i++) {
			if(i == listProp.size()-1) {
				panCenter.add(listProp.get(i));
				JLabel indic = new JLabel();
				indic.setBorder(BorderFactory.createLineBorder(Color.black));
				indic.setHorizontalAlignment(JLabel.CENTER);
				panCenter.add(indic);
			} else {
				panCenter.add(listProp.get(i));
				panCenter.add(listIndic.get(i));
			}
		}
		compteurTours ++;
		for (int j = plateau.getNbToursMax(); j > compteurTours+1; j--) {
			JLabel prop = new JLabel();
			prop.setBorder(BorderFactory.createLineBorder(Color.black));
			prop.setHorizontalAlignment(JLabel.CENTER);
			JLabel indic = new JLabel();
			indic.setBorder(BorderFactory.createLineBorder(Color.black));
			indic.setHorizontalAlignment(JLabel.CENTER);
			panCenter.add(prop);
			panCenter.add(indic);
		}
		panCenter.revalidate();
		tours.removeAll();
		tours.setText("Nombre de tours restant : " + String.valueOf(plateau.getNbToursMax()-compteurTours));
		tours.setHorizontalAlignment(JLabel.CENTER);
		tours.revalidate();
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(panCenter, BorderLayout.CENTER);
		this.revalidate();
	}

	public void gagne() {
		JDialog jd = new JDialog();
		jd.setBackground(Color.white);
		Font ft = new Font("showcard gothic", Font.BOLD, 15);
		jd.setSize(new Dimension(600,300));
		JLabel jl = new JLabel("Félicitation, votre combinaison n'a pas été trouvé !");
		jl.setBackground(Color.white);
		jl.setFont(ft);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jd.setTitle("Victoire!");
		jd.setLocationRelativeTo(null);
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		proposition.setEnabled(false);
		jd.add(jl);
		jd.setVisible(true);
	}

	public void perdu() {
		JDialog jd = new JDialog();
		jd.setTitle("Perdu !!");
		jd.setBackground(Color.white);
		Font ft = new Font("showcard gothic", Font.BOLD, 15);
		jd.setLocationRelativeTo(null);
		jd.setSize(new Dimension(600,300));
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		proposition.setEnabled(false);
		JLabel jl = new JLabel("Dommage, votre combinaison a été trouvé");
		jl.setBackground(Color.white);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setFont(ft);
		jd.add(jl);
		jd.setVisible(true);
	}

	class playListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if (plateau.getNbChiffre() == proposition.getText().length()) {
				//System.out.print(listProp.get(0).getText());
				Font ft = new Font("showcard gothic", Font.BOLD, 15);
				JLabel prop = new JLabel();
				prop.setFont(ft);
				prop.setBorder(BorderFactory.createLineBorder(Color.black));
				prop.setText(proposition.getText());
				prop.setHorizontalAlignment(JLabel.CENTER);
				plateau.getjDef().setDerRep(prop.getText());
				listIndic.add(compteurTours, prop);
				proposition.setText("");
				JLabel indic = new JLabel();
				indic.setFont(ft);
				indic.setBorder(BorderFactory.createLineBorder(Color.black));
				indic.setText(plateau.defenseur());
				indic.setHorizontalAlignment(JLabel.CENTER);
				listProp.add(compteurTours+1, indic);
				majTableau();
				if (prop.getText().equals(plateau.getEgalFinal())) {
					perdu();
				}
				if(plateau.getNbToursMax() == compteurTours-1) {
					gagne();
				}
			}
		}

	}
}
