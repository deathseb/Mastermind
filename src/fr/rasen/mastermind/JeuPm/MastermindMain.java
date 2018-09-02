package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class MastermindMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plateau p;
		System.out.println("1 pour le mode challenger\n2 pour le mode duel");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch (i) {
		case 1: 
			p = new Plateau("chall");
			break;
		case 2:
			p = new Plateau("def");
			break;
		}
	}

}
