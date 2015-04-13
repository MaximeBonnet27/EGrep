package cpa.automate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Automate implements Serializable {

	private ArrayList<Etat> etatsInitiaux;
	private ArrayList<Etat> etatsFinaux;
	private ArrayList<Etat> etats;
	
	private HashMap<Integer, ArrayList<Etat>> verifies;
	
	public Automate(ArrayList<Etat> etatInitiaux, ArrayList<Etat> etatsFinaux,
			ArrayList<Etat> etats) {
		super();
		this.etatsInitiaux = etatInitiaux;
		this.etatsFinaux = etatsFinaux;
		this.etats = etats;
	}
	
	public Automate(Etat ei,ArrayList<Etat> etatsFinaux,
			ArrayList<Etat> etats) {
		
		this.etatsInitiaux = new ArrayList<Etat>();
		etatsInitiaux.add(ei);
		this.etatsFinaux = etatsFinaux;
		this.etats = etats;
	}

	public boolean verifierMot(String mot){
		verifies = new HashMap<>();
		boolean match = false;
		for(Etat ei : etatsInitiaux){
			match = match || verifierMotRecurence(mot, ei, 0);  
			if(match) return true;
		}
		return match;
	}

	private boolean verifierMotRecurence(String mot, Etat etatActuel, int indexActuel){
		if(verifies.get(indexActuel) == null){
			verifies.put(indexActuel, new ArrayList<Etat>());
		}
		verifies.get(indexActuel).add(etatActuel);
		if(isEtatFinal(etatActuel)){
			return true;
		}
		if(indexActuel == mot.length()){
			for(Transition transition : etatActuel.getTransitions()){
				if(transition.isEpsilonTransition()){
					return verifies.get(indexActuel).contains(transition.getArrivee()) ? false : verifierMotRecurence(mot, transition.getArrivee(), indexActuel);
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
					if(verifies.get(indexActuel + 1) == null){
						verifies.put(indexActuel + 1, new ArrayList<Etat>());
					}

					match = match || (verifies.get(indexActuel + 1).contains(transition.getArrivee()) ? match : verifierMotRecurence(mot, transition.getArrivee(), indexActuel + 1));
					if(match) return true;
				}
				else if(transition.isEpsilonTransition()){
					match = match || (verifies.get(indexActuel).contains(transition.getArrivee()) ? match : verifierMotRecurence(mot, transition.getArrivee(), indexActuel));
					if(match) return true;
				}
				else if(transition.isPointTransition()){
					if(verifies.get(indexActuel + 1) == null){
						verifies.put(indexActuel + 1, new ArrayList<Etat>());
					}
					match = match || (verifies.get(indexActuel + 1).contains(transition.getArrivee()) ? match : verifierMotRecurence(mot, transition.getArrivee(), indexActuel + 1));
					if(match) return true;
				}
				else if(transition.isDebutTransition()){
					match = match || (indexActuel == 0) && (verifies.get(indexActuel).contains(transition.getArrivee()) ? match : verifierMotRecurence(mot, transition.getArrivee(), indexActuel));
					if(match) return true;
				}
			}
			if (!match){
				if(verifies.get(indexActuel + 1) == null){
					verifies.put(indexActuel + 1, new ArrayList<Etat>());
				}
				boolean retour = false;
				for(Etat ei : etatsInitiaux){
					retour = retour || (verifies.get(indexActuel + 1).contains(ei) ? false : verifierMotRecurence(mot, ei, indexActuel+1));
				}
				return retour;
			}
			else return true;
		}
	}

	public ArrayList<Etat> getEtatsInitiaux() {
		return etatsInitiaux;
	}
	
	public Etat getEtatInitial(){
		return etatsInitiaux.get(0);
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


	public boolean isEtatInitial(Etat etat){
		return etatsInitiaux.contains(etat);
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Etat e : etats){
			if(isEtatInitial(e)){
				builder.append("-> ");
			}
			if(isEtatFinal(e)){
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
		Automate a = (Automate) obj;
		for(Etat e : a.etats){
		  e.setNumero(Etat.compteur++);
		}
		return a; 
       	
	}
	
	public Object clone2() {
		Object obj = null;
		try {
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
		Automate a = (Automate) obj;
		return a; 
       	
	}


	public String toDotFile(){
		StringBuilder sb = new StringBuilder();
		sb.append("digraph automate{\n");
		sb.append("size=\"8,10\";");
		for(Etat e : etats){
			sb.append(e.toDotFile());
		}
		sb.append("}\n");
		return sb.toString();

	}

	/**
	 * @param nouveauxFinaux
	 */
	public void setFinaux(ArrayList<Etat> nouveauxFinaux) {
		this.etatsFinaux = nouveauxFinaux;
	}

	/**
	 * @param nouveauxInitiaux
	 */
	public void setInitiaux(ArrayList<Etat> nouveauxInitiaux) {
		this.etatsInitiaux = nouveauxInitiaux;
	}


}
