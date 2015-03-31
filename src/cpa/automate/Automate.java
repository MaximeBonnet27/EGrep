package cpa.automate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Automate implements Serializable {

  private Etat etatInitial;
  private ArrayList<Etat> etatsFinaux;
  private ArrayList<Etat> etats;

  public Automate(Etat etatInitial, ArrayList<Etat> etatsFinaux,
      ArrayList<Etat> etats) {
    super();
    this.etatInitial = etatInitial;
    this.etatsFinaux = etatsFinaux;
    this.etats = etats;
  }
  
  public boolean verifierMot(String mot){
    return verifierMotRecurence(mot, etatInitial, 0);  
  }

  private boolean verifierMotRecurence(String mot, Etat etatActuel, int indexActuel){
    if(isEtatFinal(etatActuel)){
      return true;
    }
    if(indexActuel == mot.length()){
      for(Transition transition : etatActuel.getTransitions()){
        if(transition.isEpsilonTransition()){
          return verifierMotRecurence(mot, transition.getArrivee(), indexActuel);
        }
        if(transition.isFinTransition()){
          return true;
        }
      }
      return false;
    }
    else{
      char caractere = mot.charAt(indexActuel);
      boolean match = false;
      for(Transition transition : etatActuel.getTransitions()){
        if(transition.estDansEtiquette(caractere)){
          match = match || verifierMotRecurence(mot, transition.getArrivee(), indexActuel + 1);
        }
        else if(transition.isEpsilonTransition()){
          match = match || verifierMotRecurence(mot, transition.getArrivee(), indexActuel);
        }
        else if(transition.isPointTransition()){
          match = match || verifierMotRecurence(mot, transition.getArrivee(), indexActuel + 1);
        }
        else if(transition.isDebutTransition()){
          match = match || (indexActuel == 0) && verifierMotRecurence(mot, transition.getArrivee(), indexActuel);
        }
      }
      if (!match){
        return verifierMotRecurence(mot, etatInitial, indexActuel+1);
      }
      else return true;
    }
  }

  public Etat getEtatInitial() {
    return etatInitial;
  }

  public ArrayList<Etat> getEtatsFinaux() {
    return etatsFinaux;
  }

  public ArrayList<Etat> getEtats() {
    return etats;
  }


  public boolean isEtatFinal(Etat etat){
    return etatsFinaux.contains(etat);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for(Etat e : etats){
      if(e == etatInitial){
        builder.append("-> ");
      }
      if(etatsFinaux.contains(e)){
        builder.append("* ");
      }
      builder.append(e.toString());
      builder.append("\n");
    }
    return builder.toString();
  }

  @Override
  public Object clone() {
    Object obj = null;
    try {
        // Write the object out to a byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
        out.flush();
        out.close();

        // Make an input stream from the byte array and read
        // a copy of the object back in.
        ObjectInputStream in = new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray()));
        obj = in.readObject();
    }
    catch(IOException e) {
        e.printStackTrace();
    }
    catch(ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
    }
    return obj;  }
  
  
  
}
