package fr.rasen.mastermind.JeuPm.Vue;

import fr.rasen.mastermind.JeuPm.Plateau;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FenetreChal extends JPanel {

	private Fenetre fenetre;

	private GridLayout gl;
	private JLabel propo = new JLabel("Proposition");
	private JLabel indication = new JLabel("Indication");
	private JPanel panProp = new JPanel();
	private JPanel panIndic = new JPanel();
	private ArrayList<JLabel> listProp = new ArrayList<JLabel>();
	private ArrayList<JLabel> listIndic = new ArrayList<JLabel>();
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

	private static final Logger logger = LogManager.getLogger();


	public FenetreChal(Fenetre f) {
		fenetre =f;
		this.setLayout(new BorderLayout());
		plateau = new Plateau("chall");


		gl = new GridLayout(plateau.getNbToursMax()+1,2);
		Font ft = new Font("showcard gothic", Font.BOLD, 20);
		propo.setFont(ft);
		propo.setHorizontalAlignment(JLabel.CENTER);
		indication.setFont(ft);
		indication.setHorizontalAlignment(JLabel.CENTER);
		panProp.setBorder(BorderFactory.createLineBorder(Color.black));
		panProp.add(propo);
		panIndic.setBorder(BorderFactory.createLineBorder(Color.black));
		panIndic.add(indication);
		panCenter.setBackground(Color.white);
		panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		panCenter.setLayout(gl);
		panCenter.add(panProp);
		panCenter.add(panIndic);

		ft = new Font("showcard gothic", Font.BOLD, 50);
		titre.setFont(ft);
		panNord.setPreferredSize(new Dimension(600,200));
		panNord.setBackground(Color.white);
		ft = new Font("showcard gothic", Font.BOLD, 25);
		mode.setText("Mode : Challenger");
		mode.setFont(ft);
		tours.setFont(ft);;
		tours.setText("Nombre de tours restant : " + (plateau.getNbToursMax()));
		tours.setHorizontalAlignment(JLabel.CENTER);
		titre.setHorizontalAlignment(JLabel.CENTER);
		mode.setHorizontalAlignment(JLabel.CENTER);
		if (plateau.getP().isModeDev()) {
			GridLayout nordLayout = new GridLayout(4,1);
			panNord.setLayout(nordLayout);
			JLabel combi = new JLabel("Combinaison : " + plateau.getGmChall().getCombinaison());
			combi.setFont(ft);
			combi.setHorizontalAlignment(JLabel.CENTER);
			panNord.add(titre);
			panNord.add(mode);
			panNord.add(tours);
			panNord.add(combi);
		} else {
			GridLayout nordLayout = new GridLayout(3,1);
			panNord.setLayout(nordLayout);
			panNord.add(titre);
			panNord.add(mode);
			panNord.add(tours);
		}

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

		this.add(panNord, BorderLayout.NORTH);
		this.add(panEst, BorderLayout.EAST);
		initTableau(plateau.getNbToursMax());
		this.add(panCenter, BorderLayout.CENTER);
		this.add(panOuest, BorderLayout.WEST);
		this.add(panSud, BorderLayout.SOUTH);
		logger.trace("Affichage mode challenger réussi");
	}

	public void initTableau (int nb) {
		for(int i =0; i< nb; i++) {
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

	public void majTableau() {
		panCenter.removeAll();
		panCenter.setBackground(Color.white);
		panCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		panCenter.setLayout(new GridLayout(plateau.getNbToursMax()+1, 2));
		panCenter.add(panProp);
		panCenter.add(panIndic);
		for(int i = 0; i < listProp.size(); i++) {
			panCenter.add(listProp.get(i));
			panCenter.add(listIndic.get(i));
		}
		compteurTours ++;
		for (int j = plateau.getNbToursMax(); j > compteurTours; j--) {
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
		tours.setText("Nombre de tours restant : " + (plateau.getNbToursMax()-compteurTours));
		tours.setHorizontalAlignment(JLabel.CENTER);
		tours.revalidate();
		this.add(panCenter, BorderLayout.CENTER);
	}

	public void gagne() {
		FinDePartie fp = new FinDePartie("Victoire", true, fenetre.getProjet3(), fenetre);
		logger.trace("Fin de partie en mode challenger : Victoire");
	}

	public void perdu() {
		FinDePartie fp = new FinDePartie("Défaite", false, fenetre.getProjet3(), fenetre);
		logger.trace("Fin de partie en mode challenger : Défaite");
	}

	public ArrayList<JLabel> getListProp() {
		return listProp;
	}

	public ArrayList<JLabel> getListIndic() {
		return listIndic;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public JTextField getProposition() {
		return proposition;
	}

	public void setProposition(JTextField proposition) {
		this.proposition = proposition;
	}

	public int getCompteurTours() {
		return compteurTours;
	}

	public JButton getValider() {
		return valider;
	}

	class playListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			try{
				Integer.parseInt(proposition.getText());
				if (plateau.getNbChiffre() == proposition.getText().length()) {
					Font ft = new Font("showcard gothic", Font.BOLD, 15);
					JLabel prop = new JLabel();
					prop.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
					prop.setFont(ft);
					prop.setHorizontalAlignment(JLabel.CENTER);
					prop.setBorder(BorderFactory.createLineBorder(Color.black));
					prop.setText(proposition.getText());
					listProp.add(compteurTours, prop);
					JLabel indic = new JLabel();
					indic.setFont(ft);
					indic.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
					indic.setHorizontalAlignment(JLabel.CENTER);
					indic.setBorder(BorderFactory.createLineBorder(Color.black));
					indic.setText(plateau.challenger(proposition.getText()));
					listIndic.add(compteurTours, indic);
					proposition.setText("");
					majTableau();
					String egalFinal = plateau.getEgalFinal();
					if (indic.getText().equals(egalFinal)) {
						gagne();
					}
					if(plateau.getNbToursMax() == compteurTours-1) {
						perdu();
					}
				}
			} catch (Exception e){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "Veuillez rentrer une combinaison de " + plateau.getNbChiffre() + " chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
			}


		}

	}

}
