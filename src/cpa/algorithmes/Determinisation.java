/**
 * 
 */
package cpa.algorithmes;

import java.util.ArrayList;
import java.util.Stack;

import cpa.automate.Automate;
import cpa.automate.Etat;
import cpa.automate.Transition;

/**
 * @author 3100381
 * 
 */
public class Determinisation {

	public static Automate compute(Automate a) {
		if(estDeterministe(a)){
			System.out.println("DET => " + a);
			return a;
		}
		Stack<Etat> etatsATraiter = new Stack<Etat>();
		ArrayList<Etat> nouveauxEtats = new ArrayList<Etat>();
		ArrayList<Etat> nouveauxEtatsFinaux = new ArrayList<Etat>();
		etatsATraiter.addAll(a.getEtatsInitiaux());
		nouveauxEtats.addAll(a.getEtatsInitiaux());
		while (!etatsATraiter.empty()) {
			System.out.println(etatsATraiter.size());
			Etat e = etatsATraiter.pop();
			for (char c = (char) 32; c <= (char) 126; c++) {
				Etat e2;
				ArrayList<Etat> etatsAccessibles = new ArrayList<Etat>();
				boolean estFinal = false;
				for (Transition t : e.getTransitions()) {
					if (t.estDansEtiquette(c)) {
						etatsAccessibles.add(t.getArrivee());
						if (a.isEtatFinal(t.getArrivee())) {
							estFinal = true;
						}
					}
				}
				e2 = new Etat(etatsAccessibles);
				if (estFinal || !e2.getTransitions().isEmpty()) {
					e.addTransition(new Transition(e, e2, c));

					if (estFinal) {
						nouveauxEtatsFinaux.add(e2);
					}
					if (!nouveauxEtats.contains(e2)) {
						nouveauxEtats.add(e2);
						etatsATraiter.push(e2);
					}
				}
			}
		}
		
		// CLEANUP
		
		for(Etat e : nouveauxEtats){
			ArrayList<Transition> toRemove = new ArrayList<Transition>();
			for(Transition t : e.getTransitions()){
				if(!nouveauxEtats.contains(t.getArrivee())){
					toRemove.add(t);
				}
			}
			e.getTransitions().removeAll(toRemove);
		}
		
		Automate b = new Automate(a.getEtatsInitiaux(), nouveauxEtatsFinaux,
				nouveauxEtats);
		System.out.println("DET => " + b);
		return b;
	}
	
	public static boolean estDeterministe(Automate a){
		int cpt;
		for (char c = (char) 32; c <= (char) 126; c++) {
			for(Etat e : a.getEtats()){
				cpt = 0;
				for(Transition t : e.getTransitions()){
					if(t.estDansEtiquette(c)){
						cpt++;
					}
				}
				if(cpt > 1) return false;
			}
		}
		return true;
	}
}
