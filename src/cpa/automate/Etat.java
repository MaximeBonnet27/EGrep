package cpa.automate;

import java.io.Serializable;
import java.util.ArrayList;

public class Etat implements Serializable{

	private static int compteur = 0;
	private int numero;
	private ArrayList<Transition> transitions;

	public Etat() {
		numero = compteur++;
		transitions = new ArrayList<Transition>();
	}

	public Etat(Etat autre){
		numero = autre.numero;
		transitions = new ArrayList<Transition>();
	}

	public Etat(ArrayList<Etat> liste){
		numero = compteur++;
		transitions = new ArrayList<Transition>();
		for(Etat e : liste){
			for(Transition t : e.getTransitions()){
				transitions.add(new Transition(this, t.getArrivee(), t.getEtiquette()));
			}
		}
	}


	public void addTransition(Transition t) {
		transitions.add(t);
	}

	public void supprimerTransition(Transition t) {
		transitions.remove(t);
	}


	public ArrayList<Transition> getTransitions() {
		return transitions;
	}  

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(numero + " {");
		for(Transition transition : transitions){
			if(transition.isEpsilonTransition()){
				builder.append(" £=>" + transition.getArrivee().getNumero() );
			}
			else if(transition.isPointTransition()){
				builder.append(" .=>" + transition.getArrivee().getNumero() );

			}
			else if(transition.isDebutTransition()){
				builder.append(" ^=>" + transition.getArrivee().getNumero() );
			}
			else if(transition.isFinTransition()){
				builder.append(" $=>" + transition.getArrivee().getNumero() );
			}
			else{
				builder.append(" " + transition.getEtiquette() + "=>" + transition.getArrivee().getNumero() );
			}
		}
		builder.append(" }");
		return builder.toString();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero){
		this.numero = numero;
	}

	@Override
	public Object clone(){
		Etat clone = new Etat();
		clone.numero = numero;
		for(Transition t : transitions){
			clone.addTransition(new Transition(clone, (Etat) t.getArrivee().clone(), (ArrayList<Character>) t.getEtiquette()));
		}
		return clone;
	}

	public String toDotFile(){
		StringBuilder sb = new StringBuilder();
		for(Transition t : transitions){
			sb.append("S"+numero+" -> S" +t.getArrivee().getNumero()+ "[color=black, style=solid, label=\""+t.getAffichageEtiquette()+"\"];\n");
		}
		return sb.toString();
	}





}
