/**
 * 
 */
package cpa.algorithmes;

import java.util.ArrayList;

import cpa.automate.Automate;
import cpa.automate.Etat;
import cpa.automate.Transition;

/**
 * @author 3100381
 *
 */
public class Determinisation {

	public static Automate powerSetConstruction(Automate a){
		// CRAQUAGE
		Etat q02 = a.getEtatInitial();
		ArrayList<Etat> Q2 = new ArrayList<Etat>();
		ArrayList<Etat> etatsAConsiderer = new ArrayList<Etat>();
		Q2.add(q02);
		etatsAConsiderer.add(q02);

		while(!etatsAConsiderer.isEmpty()){
			Etat x = etatsAConsiderer.get(0);
			ArrayList<Etat> petitQ2 = new ArrayList<Etat>();
			ArrayList<Object> sigma = new ArrayList<Object>();
			for(Transition t : x.getTransitions()){
				petitQ2.add(t.getArrivee());
				sigma.add(t.getEtiquette());
			}
			for(int i = 0; i < petitQ2.size(); i++){
				if(sigma.get(i) instanceof ArrayList<?>){
					x.addTransition(new Transition(x, petitQ2.get(i), (ArrayList<Character>)sigma.get(i)));
				}else{
					x.addTransition(new Transition(x, petitQ2.get(i), (char) sigma.get(i)));
				}
			}

			for(Etat e : petitQ2){
				if(Q2.contains(e)) continue;
				etatsAConsiderer.add(e);
			}

			Q2.addAll(petitQ2);
			
			etatsAConsiderer.remove(x);
		}
		ArrayList<Etat> F = new ArrayList<>();
		for(Etat e : Q2){
			if(a.getEtatsFinaux().contains(e))
				F.add(e);
		}
		return new Automate(q02, F, Q2);
	}

}
