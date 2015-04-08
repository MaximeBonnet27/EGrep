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
public class Minimisation {

	public static Automate brzozowski(Automate a){
		return Minimisation.transpose(Minimisation.transpose(a));
		//return Minimisation.transpose(Determinisation.compute(Minimisation.transpose(Determinisation.compute(a))));
	}

	private static Automate transpose(Automate a) {
		ArrayList<Etat> nouveauxEtats = new ArrayList<Etat>();
		ArrayList<Etat> nouveauxFinaux = new ArrayList<Etat>();
		ArrayList<Etat> nouveauxInitiaux = new ArrayList<Etat>();
		
		System.out.println("ENTREE TRANSPOSE");
		System.out.println(a);
		System.out.println("*****************");
		
		Stack<Etat> etatsATraiter = new Stack<Etat>();
		etatsATraiter.addAll(a.getEtatsFinaux());
		while(!etatsATraiter.isEmpty()){
			System.out.println("TAILLE : " + etatsATraiter.size());
			Etat etatTraite = etatsATraiter.pop();
			Etat et = new Etat(etatTraite);
			nouveauxEtats.add(et);
			if(a.isEtatFinal(etatTraite)){
				System.out.println("FINAL");
				nouveauxInitiaux.add(et);
			}
			if(a.isEtatInitial(etatTraite)){
				System.out.println("INIT");
				nouveauxFinaux.add(et);
			}
			for(Etat e : a.getEtats()){
				for(Transition t : e.getTransitions()){
					if(t.getArrivee().equals(etatTraite)){
						Etat e2 = new Etat(e);

						//nouveauxEtats.add(e2);
						et.addTransition(new Transition(et, e2, t.getEtiquette()));
						if(!nouveauxEtats.contains(e)){
							etatsATraiter.push(e);
						}
					}
				}
			}
		}
		System.out.println(nouveauxInitiaux.size()+" "+nouveauxFinaux.size()+" "+nouveauxEtats.size());
		Automate b = new Automate(nouveauxInitiaux, nouveauxFinaux, nouveauxEtats); 
		System.out.println("MIN => " + b);
		return b;
	}

}
