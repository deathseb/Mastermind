package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class MastermindMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plateau p, p1;
		Scanner sc = new Scanner(System.in);
		int i = 0;
		Propriete prop = new Propriete();
		boolean quitter = false;
		while (!quitter) {
			if(i == 0)
			System.out.println("1 pour le mode challenger\n2 pour le mode défenseur\n3 pour le mode duel\n4 Quitter");
			i = sc.nextInt();
			if(i == 4) 
				quitter = true;
			switch (i) {
			case 1: 
				p = new Plateau("chall");
				i=0;
				break;
			case 2:
				p = new Plateau("def");
				i=0;
				break;
			case 3:
				int tour = 0;
				p = new Plateau("chall", prop, tour);
				p1 = new Plateau("def", prop, tour);
				while(tour != prop.getNbTours()) {
					tour++;
					p.setTourActuel(tour);
					p1.setTourActuel(tour);
					p.challenger(p.getGmChall(), p.getjChall());
					p1.defenseur(p1.getGmDef(), p1.getjDef());
				}
				
				break;
			}
			
		}
	}

}
