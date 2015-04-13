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
public class Minimisation {

	public static Automate brzozowski(Automate a){
		//    return Minimisation.transpose(Minimisation.transpose(a));
		return Determinisation.compute(Minimisation.transpose(Determinisation.compute(Minimisation.transpose(a))));
	}

	public static Automate transpose(Automate a){
		Automate b = (Automate) a.clone2();

		for(Etat e : a.getEtats()){
			e.setTransitions(new ArrayList<Transition>());
		}
		for(Etat e : b.getEtats()){
			ArrayList<Transition> nouvelleListe = new ArrayList<>();
			for(Transition t : e.getTransitions()){
				nouvelleListe.add(new Transition(t.getArrivee(), t.getDepart(), t.getEtiquette()));
			}
			for(Etat nouveauE : a.getEtats()){
				for(Transition nouvelleTransition : nouvelleListe){
					if(nouvelleTransition.getDepart().getNumero() == nouveauE.getNumero()){
						nouveauE.addTransition(nouvelleTransition);
					}
				}
			}
		}

		ArrayList<Etat> nouveauxFinaux = new ArrayList<>();
		nouveauxFinaux = a.getEtatsInitiaux();

		ArrayList<Etat> nouveauxInitiaux = new ArrayList<>();
		nouveauxInitiaux = a.getEtatsFinaux();
		a.setFinaux(nouveauxFinaux);
		a.setInitiaux(nouveauxInitiaux);


		return a;
	}

}
