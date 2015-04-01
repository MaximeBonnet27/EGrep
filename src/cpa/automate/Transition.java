package cpa.automate;

import java.io.Serializable;
import java.util.ArrayList;

public class Transition implements Serializable{

	public static final char epsilon = (char) -1;
	public static final char point = (char) -2;
	public static final char debut = (char) -3;
	public static final char fin = (char) -4;

	private Etat depart;
	private Etat arrivee;
	private char etiquette;

	private ArrayList<Character> etiquettes;

	public Transition(Etat depart, Etat arrivee, char c) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.etiquette = c;
		this.etiquettes = new ArrayList<Character>();
		etiquettes.add(c);
	}

	public Transition(Etat depart, Etat arrivee, ArrayList<Character> listeChar){
		this.depart = depart;
		this.arrivee = arrivee;
		this.etiquettes = listeChar;
	}

	public Etat getDepart() {
		return depart;
	}
	public Etat getArrivee() {
		return arrivee;
	}
	public char getEtiquette() {
		return etiquette;
	}
	public boolean isEpsilonTransition(){
		return etiquette == epsilon;
	}

	public boolean isPointTransition(){
		return etiquette == point;
	}

	public boolean isDebutTransition(){
		return etiquette == debut;
	}

	public boolean isFinTransition(){
		return etiquette == fin;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Transition((Etat) depart.clone(), (Etat) arrivee.clone(), etiquette);
	}

	public boolean estDansEtiquette(char caractere) {
		//System.out.print("LISTE : ");
//		for(Character c : etiquettes){
//			System.out.print(" " + c);
//		}
		//System.out.println();
		//System.out.println("CHAR : " + caractere);
		return etiquettes.contains(caractere) || etiquette == caractere;
	}


}
