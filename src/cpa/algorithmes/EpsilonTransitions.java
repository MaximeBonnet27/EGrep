
package cpa.algorithmes;
import java.util.ArrayList;
import cpa.automate.Automate;
import cpa.automate.Etat;
import cpa.automate.Transition;
public class EpsilonTransitions {
	public static Automate eliminer(Automate a){
		Etat k = null;
		ArrayList<Etat> listeQ;
		while(true){
			k = null;
			listeQ = new ArrayList<Etat>();
			for(Etat e : a.getEtats()){
				for(Transition t : e.getTransitions()){
					if(t.isEpsilonTransition()){
						k = t.getArrivee();
					}
				}
			}
			if(k == null){
				return a;
			}
			for(Etat q : a.getEtats()){
				if(q.equals(k)) continue;
				for(Transition t : q.getTransitions()){
					if(t.isEpsilonTransition() && t.getArrivee().equals(k)){
						listeQ.add(q);
					}
				}
			}
			for(Etat r : a.getEtats()){
				for(Transition t : r.getTransitions()){
					if(t.getDepart().equals(k)){
						for(Etat q : listeQ){
							q.addTransition(new Transition(q,r,t.getEtiquette()));
							if(a.isEtatFinal(k)){
								a.getEtatsFinaux().add(q);
							}
						}
					}
				}
			}
			for(Etat e : a.getEtats()){
				ArrayList<Transition> aSupprimer = new ArrayList<Transition>();
				for(Transition t : e.getTransitions()){
					if(t.isEpsilonTransition() && t.getArrivee().equals(k)){
						aSupprimer.add(t);
					}
				}
				e.getTransitions().removeAll(aSupprimer);
			}
		}
	}
	public static boolean chercherEpsilon(Automate a){
		for(Etat e : a.getEtats()){
			for(Transition t : e.getTransitions()){
				if(t.isEpsilonTransition()) return true;
			}
		}
		return false;
	}
}