package fr.rasen.mastermind.JeuPm.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.rasen.mastermind.JeuPm.Plateau;

public class FenetreChal extends JPanel {

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


	public FenetreChal() {
		this.setLayout(new BorderLayout());
		plateau = new Plateau("chall");


		gl = new GridLayout(plateau.getNbToursMax()+1,2);
		Font ft = new Font("showcard gothic", Font.BOLD, 20);
		//propo.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
		propo.setFont(ft);
		//propo.setBorder(BorderFactory.createLineBorder(Color.black));
		//indication.setBorder(BorderFactory.createLineBorder(Color.black));
		propo.setHorizontalAlignment(JLabel.CENTER);
		//ndication.setPreferredSize(new Dimension(180, 360/(plateau.getNbToursMax()+1)));
		indication.setFont(ft);
		indication.setHorizontalAlignment(JLabel.CENTER);
		panProp.setBorder(BorderFactory.createLineBorder(Color.black));
		panProp.add(propo);
		panIndic.setBorder(BorderFactory.createLineBorder(Color.black));
		panIndic.add(indication);
		//panCenter.setPreferredSize(new Dimension(360, 360));
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
		tours.setText("Nombre de tours restant : " + String.valueOf(plateau.getNbToursMax()));
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
		//panCenter = new JPanel();
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
		//panCenter.repaint();
		tours.removeAll();
		tours.setText("Nombre de tours restant : " + String.valueOf(plateau.getNbToursMax()-compteurTours));
		tours.setHorizontalAlignment(JLabel.CENTER);
		tours.revalidate();
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(panCenter, BorderLayout.CENTER);
		//this.revalidate();
		//this.repaint();
	}

	public void gagne() {
		JDialog jd = new JDialog();
		jd.setBackground(Color.white);
		Font ft = new Font("showcard gothic", Font.BOLD, 15);
		jd.setSize(new Dimension(600,300));
		JLabel jl = new JLabel("Félicitation, vous avez gagner");
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
		JLabel jl = new JLabel("Dommage, vous n'avez pas trouvez la combinaison. N'hésitez pas à réessayer!");
		jl.setBackground(Color.white);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setFont(ft);
		jd.add(jl);
		jd.setVisible(true);
	}

	public JLabel getPropo() {
		return propo;
	}

	public void setPropo(JLabel propo) {
		this.propo = propo;
	}

	public JLabel getIndication() {
		return indication;
	}

	public void setIndication(JLabel indication) {
		this.indication = indication;
	}

	public ArrayList<JLabel> getListProp() {
		return listProp;
	}

	public void setListProp(ArrayList<JLabel> listProp) {
		this.listProp = listProp;
	}

	public ArrayList<JLabel> getListIndic() {
		return listIndic;
	}

	public void setListIndic(ArrayList<JLabel> listIndic) {
		this.listIndic = listIndic;
	}

	public JPanel getPanCenter() {
		return panCenter;
	}

	public void setPanCenter(JPanel panCenter) {
		this.panCenter = panCenter;
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

	public void setCompteurTours(int compteurTours) {
		this.compteurTours = compteurTours;
	}

	public JButton getValider() {
		return valider;
	}

	public void setValider(JButton valider) {
		this.valider = valider;
	}

	class playListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
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

		}

	}

}
