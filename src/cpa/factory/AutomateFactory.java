package cpa.factory;

import java.util.ArrayList;

import cpa.automate.Automate;
import cpa.automate.Etat;
import cpa.automate.Transition;

public class AutomateFactory {

  public static Transition createEpsilonTransition(Etat depart, Etat arrivee){
    Transition t = new Transition(depart, arrivee, Transition.epsilon);
    depart.addTransition(t);
    return t;
  }

  public static Transition createPointTransition(Etat depart, Etat arrivee){
    Transition t = new Transition(depart, arrivee, Transition.point);
    depart.addTransition(t);
    return t;
  }

  public static Transition createDebutTransition(Etat depart, Etat arrivee){
    Transition t = new Transition(depart, arrivee, Transition.debut);
    depart.addTransition(t);
    return t;
  }
  
  public static Transition createFinTransition(Etat depart, Etat arrivee){
    Transition t = new Transition(depart, arrivee, Transition.fin);
    depart.addTransition(t);
    return t;
  }
  
  public static Transition createTransition(Etat depart, Etat arrivee, char c){
    Transition t = new Transition(depart, arrivee, c);
    depart.addTransition(t);
    return t;
  }
  
  public static Transition createTransitionListe(Etat depart, Etat arrivee, ArrayList<Character> l){
	    Transition t = new Transition(depart, arrivee, l);
	    depart.addTransition(t);
	    return t;
	  }


  public static Automate createAutomate(char c){
    Etat ei = new Etat();
    Etat ef = new Etat();
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
    createTransition(ei, ef, c);
    etats.add(ei);
    etats.add(ef);
    etatFinal.add(ef);
    return new Automate(ei, etatFinal, etats);
  }

  public static Automate createAutomateVide(){
    Etat ei = new Etat();
    Etat ef = new Etat();
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
    createEpsilonTransition(ei, ef);
    etats.add(ei);
    etats.add(ef);
    etatFinal.add(ef);
    return new Automate(ei, etatFinal, etats);
  }

  public static Automate createAutomatePoint(){
    Etat ei = new Etat();
    Etat ef = new Etat();
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
    createPointTransition(ei, ef);
    etats.add(ei);
    etats.add(ef);
    etatFinal.add(ef);
    return new Automate(ei, etatFinal, etats);
  }
  
  public static Automate createAutomateDebut(){
    Etat ei = new Etat();
    Etat ef = new Etat();
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
    createDebutTransition(ei, ef);
    etats.add(ei);
    etats.add(ef);
    etatFinal.add(ef);
    return new Automate(ei, etatFinal, etats);
  }
  
  public static Automate createAutomateFin(){
    Etat ei = new Etat();
    Etat ef = new Etat();
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
    createFinTransition(ei, ef);
    etats.add(ei);
    etats.add(ef);
    etatFinal.add(ef);
    return new Automate(ei, etatFinal, etats);
  }
  

  public static Automate createListeAutomate(ArrayList<Character> liste){
	    Etat ei = new Etat();
	    Etat ef = new Etat();
	    ArrayList<Etat> etats = new ArrayList<Etat>();
	    ArrayList<Etat> etatFinal = new ArrayList<Etat>();
	    createTransitionListe(ei, ef, liste);
	    etats.add(ei);
	    etats.add(ef);
	    etatFinal.add(ef);
	    return new Automate(ei, etatFinal, etats);
  }
  public static Automate createListeInverseAutomate(ArrayList<Character> liste){
	  ArrayList<Character> complementaire = new ArrayList<Character>();
	  for(char c = (char) 32; c <= (char) 126; c++){
		  if(!liste.contains(c)){
			  complementaire.add(c);
		  }
	  }
	  return createListeAutomate(complementaire);
  }
  
  public static Automate createConcatenation(Automate automateA, Automate automateB){
    for(Etat etatFinalA : automateA.getEtatsFinaux()){
      createEpsilonTransition(etatFinalA, automateB.getEtatInitial());
    }
    ArrayList<Etat> unionEtats = automateA.getEtats();
    unionEtats.addAll(automateB.getEtats());
    return new Automate(automateA.getEtatInitial(), automateB.getEtatsFinaux(), unionEtats);
  }

  public static Automate createUnion(Automate automateA, Automate automateB){
    Etat nouvelEtatInitial = new Etat();
    createEpsilonTransition(nouvelEtatInitial, automateA.getEtatInitial());
    createEpsilonTransition(nouvelEtatInitial, automateB.getEtatInitial());
    Etat nouvelEtatFinal = new Etat();
    for(Etat etatFinalA : automateA.getEtatsFinaux()){
      createEpsilonTransition(etatFinalA, nouvelEtatFinal);
    }
    for(Etat etatFinalB : automateB.getEtatsFinaux()){
      createEpsilonTransition(etatFinalB, nouvelEtatFinal);
    }
    ArrayList<Etat> unionEtats = automateA.getEtats();
    unionEtats.addAll(automateB.getEtats());
    unionEtats.add(nouvelEtatInitial);
    unionEtats.add(nouvelEtatFinal);
    ArrayList<Etat> etatsFinaux = new ArrayList<Etat>();
    etatsFinaux.add(nouvelEtatFinal);
    return new Automate(nouvelEtatInitial, etatsFinaux, unionEtats);

  }

  public static Automate createEtoile(Automate automate){
    Etat nouvelEtatFinal = new Etat();
    for(Etat ancienEtatFinal : automate.getEtatsFinaux()){
      createEpsilonTransition(ancienEtatFinal, automate.getEtatInitial());
      createEpsilonTransition(ancienEtatFinal, nouvelEtatFinal);
    }
    Etat nouvelEtatInitial = new Etat();
    createEpsilonTransition(nouvelEtatInitial, nouvelEtatFinal);
    createEpsilonTransition(nouvelEtatInitial, automate.getEtatInitial());
    ArrayList<Etat> etatsFinaux = new ArrayList<Etat>();
    etatsFinaux.add(nouvelEtatFinal);
    ArrayList<Etat> nouveauxEtats = automate.getEtats();
    nouveauxEtats.add(nouvelEtatInitial);
    nouveauxEtats.add(nouvelEtatFinal);
    return new Automate(nouvelEtatInitial, etatsFinaux, nouveauxEtats);

  }

  public static Automate createPlus(Automate automate){
    for(Etat etatFinal : automate.getEtatsFinaux()){
      createEpsilonTransition(etatFinal, automate.getEtatInitial());
    }
    return new Automate(automate.getEtatInitial(), automate.getEtatsFinaux(), automate.getEtats());
  }

  public static Automate createPointInterrogation(Automate automate){
    for(Etat etatFinal : automate.getEtatsFinaux()){
      createEpsilonTransition(automate.getEtatInitial(), etatFinal);
    }
    return new Automate(automate.getEtatInitial(), automate.getEtatsFinaux(), automate.getEtats());

  }

  public static Automate createMultiplicateur(Automate automate, int m){
    if(m == 0) return createAutomateVide();
    return createConcatenation((Automate) automate.clone(), createMultiplicateur(automate, m-1));
  }

  public static Automate createMultiplicateurSuperieur(Automate automate, int m){
    Automate premierePartie = createMultiplicateur(automate, m);
    return createConcatenation(premierePartie, createEtoile(automate));
  }

  public static Automate createMultiplicateurIntervalle(Automate automate, int m, int n){
    Automate premierePartie = createMultiplicateur(automate, m);
    Automate deuxiemePartie = createMultiplicateur(createPointInterrogation(automate), n - m);
    return createConcatenation(premierePartie, deuxiemePartie);
  }

  public static Automate createAutomateExempleA(){
    Etat ei = new Etat();
    Etat e2 = new Etat();
    Etat ef = new Etat();
    createTransition(ei, e2, 'a');
    createTransition(ei, ei, 'b');
    createTransition(e2, ef, 'b');
    createTransition(e2, ei, 'a');
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatsFinaux = new ArrayList<Etat>();
    etats.add(ei);
    etats.add(e2);
    etats.add(ef);
    etatsFinaux.add(ef);
    return new Automate(ei, etatsFinaux, etats);
  }

  public static Automate createAutomateExempleB(){
    Etat ei = new Etat();
    Etat e2 = new Etat();
    Etat e3 = new Etat();
    Etat e4 = new Etat();
    Etat ef = new Etat();
    createTransition(ei, e2, 'a');
    createTransition(ei, ei, 'b');
    createTransition(ei, e3, 'c');
    createTransition(e2, ei, 'a');
    createTransition(e2, e4, 'b');
    createTransition(e2, ei, 'c');
    createEpsilonTransition(e3, e4);
    createTransition(e3, ei, 'a');
    createTransition(e3, ei, 'b');
    createTransition(e3, ei, 'c');
    createTransition(e4, ef, 'a');
    createTransition(e4, ei, 'b');
    createTransition(e4, ei, 'c');
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatsFinaux = new ArrayList<Etat>();
    etats.add(ei);
    etats.add(e2);
    etats.add(e3);
    etats.add(e4);
    etats.add(ef);
    etatsFinaux.add(ef);
    return new Automate(ei, etatsFinaux, etats);

  }

  public static Automate createAutomateExempleC(){
    Etat eiA = new Etat();
    Etat efA = new Etat();
    createTransition(eiA, efA, 'a');
    createTransition(eiA, eiA, 'b');
    ArrayList<Etat> etatsA = new ArrayList<Etat>();
    ArrayList<Etat> etatsFinauxA = new ArrayList<Etat>();
    etatsA.add(eiA);
    etatsA.add(efA);
    etatsFinauxA.add(efA);
    Automate automateA = new Automate(eiA, etatsFinauxA, etatsA);
    Etat eiB = new Etat();
    Etat efB = new Etat();
    createTransition(eiB, efB, 'b');
    createTransition(eiB, eiB, 'a');
    ArrayList<Etat> etatsB = new ArrayList<Etat>();
    ArrayList<Etat> etatsFinauxB = new ArrayList<Etat>();
    etatsB.add(eiB);
    etatsB.add(efB);
    etatsFinauxB.add(efB);
    Automate automateB = new Automate(eiB, etatsFinauxB, etatsB);
    return createConcatenation(automateA, automateB);

  }

  public static Automate createAutomateExempleD(){
    return createUnion(createAutomateExempleB(), createAutomateExempleC());
  }

  public static Automate createAutomateExempleE(){
    Etat ei = new Etat();
    Etat ef = new Etat();
    createTransition(ei, ef, 'a');
    createTransition(ei, ei, 'b');
    ArrayList<Etat> etats = new ArrayList<Etat>();
    ArrayList<Etat> etatsFinaux = new ArrayList<Etat>();
    etats.add(ei);
    etats.add(ef);
    etatsFinaux.add(ef);
    Automate a = new Automate(ei, etatsFinaux, etats);
    return createEtoile(a);
  }

  public static Automate createAutomateExempleF(){
    return AutomateFactory.createAutomate('F');
  }

  public static Automate createAutomateExempleG(){
    return createConcatenation(createAutomate('A'),createPlus(createAutomate('D')));
  }

  public static Automate createAutomateExempleH(){
    return createConcatenation(createAutomate('A'),createPointInterrogation(createAutomate('D')));
  }

  public static Automate createAutomateExempleI(){
    return createConcatenation(createAutomate('B'),createAutomate('A'));
  }

  public static Automate createAutomateExempleJ(){
    Automate a = createAutomate('a');
    return createConcatenation(a, (Automate) a.clone());
  }
  public static Automate createAutomateExempleK(){
    return createMultiplicateur(createAutomate('M'), 3);
  }

  public static Automate createAutomateExempleL(){
    return createConcatenation(createMultiplicateurSuperieur(createAutomate('A'), 3), createAutomate('b'));
  }
  public static Automate createAutomateExempleM(){
    return createConcatenation(createMultiplicateurIntervalle(createAutomate('A'), 3,6), createAutomate('b'));
  }
  public static Automate createAutomateExempleN(){
    return createConcatenation(createConcatenation(createAutomate('A'), createAutomatePoint()), createAutomate('B'));
  }
  
  public static Automate createAutomateExempleO(){
    return createConcatenation(createAutomateDebut(), createAutomate('A'));
  }
  
  public static Automate createAutomateExempleP(){
    return createConcatenation(createAutomate('A'), createAutomateFin());
  }
  public static Automate createAutomateExempleQ(){
	  ArrayList<Character> l = new ArrayList<Character>();
	  l.add('a');
	  l.add('z');
	  l.add('e');
	  return createListeAutomate(l);
  }
  public static Automate createAutomateExempleR(){
	  ArrayList<Character> l = new ArrayList<Character>();
	  l.add('k');
	  l.add('e');
	  l.add('v');
	  l.add('z');
	  return createListeInverseAutomate(l);
  }

}
