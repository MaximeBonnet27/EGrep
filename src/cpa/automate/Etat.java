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
  
  
  
  public void addTransition(Transition t) {
    transitions.add(t);
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

  @Override
  public Object clone(){
    Etat clone = new Etat();
    clone.numero = numero;
    for(Transition t : transitions){
      clone.addTransition(new Transition(clone, (Etat) t.getArrivee().clone(), t.getEtiquette()));
    }
    return clone;
  }

  
  
  
}
