/**
 * 
 */
package cpa.main;

import cpa.algorithmes.Determinisation;
import cpa.algorithmes.EpsilonTransitions;
import cpa.algorithmes.Minimisation;
import cpa.automate.Automate;
import cpa.factory.AutomateFactory;
import cpa.parser.Parser;

/**
 * @author 3100381
 *
 */
public class MainDotFile {

	public static void main(String[] args) {
		Parser p = new Parser();
		Automate a = p.getAutomateFromString("F");
//	 AutomateFactory.traitement(a);
		System.out.println(a);
		System.out.println("******************************");
		System.out.println(Minimisation.transpose(a));
    System.out.println(Minimisation.transpose(a));
	}
	
}
