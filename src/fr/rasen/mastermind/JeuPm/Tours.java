package fr.rasen.mastermind.JeuPm;

public class Tours {

	private int tour;
	private String prop;
	private String indication;
	
	public Tours() {
		tour = 0;
		prop = "";
		indication = "";
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}
	
	public String toString() {
		return "Tour " + tour + " Proposition : " + prop + "  |  Indication : " + indication; 
	}
}
