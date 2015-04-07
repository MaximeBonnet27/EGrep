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

  public static Automate powerSetConstruction(Automate a){
    Etat nouvelEtatInitial = a.getEtatInitial();
    ArrayList<Etat> nouveauxEtats = new ArrayList<Etat>();
    ArrayList<Etat> etatsAConsiderer = new ArrayList<Etat>();
    nouveauxEtats.add(nouvelEtatInitial);
    etatsAConsiderer.add(nouvelEtatInitial);

    while(!etatsAConsiderer.isEmpty()){
      // Pop
      Etat popped = etatsAConsiderer.get(0);
      Etat x = new Etat(popped);
      etatsAConsiderer.remove(popped);

      ArrayList<Etat> etatsAccessibles = new ArrayList<Etat>();
      ArrayList<ArrayList<Character>> etiquettesCorrespondantes = new ArrayList<ArrayList<Character>>();
      for(Transition t : popped.getTransitions()){
        etatsAccessibles.add(t.getArrivee());
        etiquettesCorrespondantes.add(t.getEtiquette());
      }
      for(int i = 0; i < etatsAccessibles.size(); i++){
        x.addTransition(new Transition(x, etatsAccessibles.get(i), etiquettesCorrespondantes.get(i)));
      }
      for(Etat e : etatsAccessibles){
        if(nouveauxEtats.contains(e)) continue;
        etatsAConsiderer.add(e);
      }

      nouveauxEtats.addAll(etatsAccessibles);
    }
    ArrayList<Etat> F = new ArrayList<>();
    for(Etat e : nouveauxEtats){
      if(a.getEtatsFinaux().contains(e))
        F.add(e);
    }
    return new Automate(nouvelEtatInitial, F, nouveauxEtats);
  }

  public static Automate recopiageDuCours(Automate a){
    Etat nouvelEtatInitial = a.getEtatInitial();
    ArrayList<Etat> nouveauxEtats = new ArrayList<Etat>();
    ArrayList<Etat> etatsAConsiderer = new ArrayList<Etat>();
    ArrayList<Etat> nouveauxEtatsFinaux = new ArrayList<Etat>();
    ArrayList<Etat> etatsDejaVus = new ArrayList<Etat>();
    nouveauxEtats.add(nouvelEtatInitial);
    etatsAConsiderer.add(nouvelEtatInitial);

    while(!etatsAConsiderer.isEmpty()){
      System.out.println(etatsAConsiderer.size());
      // Pop
      Etat popped = etatsAConsiderer.get(0);
      Etat x = new Etat(popped);
      etatsAConsiderer.remove(popped);
      ArrayList<Etat> etatsAccessibles = new ArrayList<Etat>();
      ArrayList<ArrayList<Character>> etiquettesCorrespondantes = new ArrayList<ArrayList<Character>>();
      Etat nouvelEtatAPartirDeListe;
      for(char c = (char) 32; c <= (char) 126; c++){
        for(Transition t : popped.getTransitions()){
          if(t.getEtiquette().contains(c)){
            etatsAccessibles.add(t.getArrivee());
            etiquettesCorrespondantes.add(t.getEtiquette());
          }
        }

        nouvelEtatAPartirDeListe = new Etat(etatsAccessibles);

        x.addTransition(new Transition(x, nouvelEtatAPartirDeListe, c));
        // PUSH (...)
        etatsDejaVus.add(nouvelEtatAPartirDeListe);
        for(Etat e : etatsAccessibles){
          if(!etatsDejaVus.contains(nouvelEtatAPartirDeListe)){
            etatsAConsiderer.add(nouvelEtatAPartirDeListe);
          }
        }
      }
    }
    return new Automate(nouvelEtatInitial, nouveauxEtatsFinaux, nouveauxEtats);
  }

  public static Automate monEssaiAMoi(Automate a){
    Stack<Etat> etatsATraiter = new Stack<Etat>();
    ArrayList<Etat> nouveauxEtats = new ArrayList<Etat>();
    ArrayList<Etat> nouveauxEtatsFinaux = new ArrayList<Etat>();
    etatsATraiter.add(a.getEtatInitial());
    nouveauxEtats.add(a.getEtatInitial());
    while(!etatsATraiter.empty()){
      System.out.println(etatsATraiter.size());
      Etat e = etatsATraiter.pop();
      for(char c = (char) 32; c <= (char) 126; c++){
        Etat e2;
        ArrayList<Etat> etatsAccessibles = new ArrayList<Etat>();
        boolean estFinal = false;
        for(Transition t : e.getTransitions()){
          if(t.getEtiquette().contains(c)){
            etatsAccessibles.add(t.getArrivee());
            if(a.isEtatFinal(t.getArrivee())){
              estFinal = true;
            }
          }
        }
        e2 = new Etat(etatsAccessibles);
        e.addTransition(new Transition(e, e2, c));
        nouveauxEtats.add(e2);
        if(estFinal){
          nouveauxEtatsFinaux.add(e2);
        }
        if(!nouveauxEtats.contains(e2)){
          etatsATraiter.push(e2);
        }
      }

    }
    return new Automate(a.getEtatInitial(), nouveauxEtatsFinaux, nouveauxEtats);
  }

}