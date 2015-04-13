/**
 * 
 */
package cpa.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import cpa.algorithmes.EpsilonTransitions;
import cpa.automate.Automate;
import cpa.parser.Parser;

/**
 * @author 3100381
 *
 */
public class MainDotFile {

	public static void main(String[] args) throws IOException {
		Parser p = new Parser();
		if(args.length < 1){
			System.err.println("Il faut au moins un argument !");
		}
		Automate a = p.getAutomateFromString(args[0]);
		String option = "";
		if(args.length >= 2){
			option = args[1];
		}
		if(option.equals("EPSILON")){
			a = EpsilonTransitions.eliminer(a);
		}
		PrintWriter pw = new PrintWriter(new File("output.dot"));
		pw.print(a.toDotFile());
		pw.close();
		Runtime rt = Runtime.getRuntime();
		System.out.println("Generation de output.dot ...");
		rt.exec("dot -Tpdf output.dot -o output.pdf");
		System.out.println("Generation finie.");
		if(Desktop.isDesktopSupported()){
			File pdfFile = new File("output.pdf");
			System.out.println("Ouverture du fichier .pdf ...");
			Desktop.getDesktop().open(pdfFile);
		}
	}

}
