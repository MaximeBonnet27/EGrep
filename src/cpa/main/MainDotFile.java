/**
 * 
 */
package cpa.main;

import cpa.algorithmes.Determinisation;
import cpa.algorithmes.EpsilonTransitions;
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
		Automate a = Determinisation.powerSetConstruction(EpsilonTransitions.eliminer(p.getAutomateFromFile("examples/regex-15.ere")));
		System.out.println(a.toDotFile());
	}
	
}
