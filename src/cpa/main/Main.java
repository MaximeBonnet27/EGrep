/**
 * 
 */
package cpa.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import cpa.algorithmes.Determinisation;
import cpa.algorithmes.EpsilonTransitions;
import cpa.automate.Automate;
import cpa.parser.Parser;

/**
 * @author 3100381
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String expr = args[0];
		Parser parser = new Parser();
		Automate a =  Determinisation.compute(EpsilonTransitions.eliminer(parser.getAutomateFromString(expr)));
		if(args.length >= 2){
			for(int i =1;i<args.length;i++){
				String filename = args[i];
				BufferedReader br = null;
				System.out.println("Fichier "+ filename + " : ");
				try {
					br = new BufferedReader(new FileReader(filename));
					String line = br.readLine();
					do{
						if(a.verifierMot(line)){
							System.out.println(line);
						}
					}while((line = br.readLine())!=null);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			while(true){
				System.out.println("Entrez une chaine : ");
				Scanner sc = new Scanner(System.in);

				String str = sc.nextLine();
				
				if(sc == null || sc.equals("")){
					break;
				}
				if(a.verifierMot(str)){
					System.out.println(str);
				}
			}
		}
	}
}
