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
	private ArrayList<Character> etiquettes;

	public Transition(Etat depart, Etat arrivee, char c) {
		this.depart = depart;
		this.arrivee = arrivee;
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
	public ArrayList<Character> getEtiquette() {
		return etiquettes;
	}
	public boolean isEpsilonTransition(){
		return etiquettes.get(0) == epsilon;
	}

	public boolean isPointTransition(){
		return etiquettes.get(0) == point;
	}

	public boolean isDebutTransition(){
		return etiquettes.get(0) == debut;
	}

	public boolean isFinTransition(){
		return etiquettes.get(0) == fin;
	}

	public String getAffichageEtiquette(){
			if(isEpsilonTransition()){
				return "£";
			}else if (isPointTransition()){
				return ".";
			}
			else if(isDebutTransition()){
				return "^";
			}
			else if(isFinTransition()){
				return "$";
			}
		StringBuilder sb = new StringBuilder();
		for(Character c : etiquettes){
		  if(c.equals('"')) sb.append("\\\" ");
		  else sb.append(c + " ");
		}
		return sb.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Transition((Etat) depart.clone(), (Etat) arrivee.clone(), (ArrayList<Character>) etiquettes.clone());
	}

	public boolean estDansEtiquette(char caractere) {
		return etiquettes.contains(caractere) || isPointTransition();
	}


}
