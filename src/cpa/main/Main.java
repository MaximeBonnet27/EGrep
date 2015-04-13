/**
 * 
 */
package cpa.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import cpa.automate.Automate;
import cpa.factory.AutomateFactory;
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
		if(args.length < 1){
			System.err.println("Il faut au moins un argument!");
			return;
		}
		String expr = args[0];
		Parser parser = new Parser();
		Automate a =  (parser.getAutomateFromString(expr));
		a = AutomateFactory.traitement(a);
		Scanner sc = new Scanner(System.in);
		if(args.length >= 2){
			for(int i =1;i<args.length;i++){
				String filename = args[i];
				File f = new File(filename);
				if(f.isDirectory()){
					checkDirectory(a, f);
				}
				else{
					checkFile(a, f);
				}
			}
		}
		else {
			while(true){
				System.out.println("Entrez une chaine : ");
				String str = sc.nextLine();

				if(sc == null || sc.equals("")){
					break;
				}
				if(a.verifierMot(str)){
					System.out.println(str);
				}
			}
			sc.close();
		}
	}

	public static void checkFile(Automate a, File f){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
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
	public static void checkDirectory(Automate a, File d){
		File [] liste = d.listFiles();
		for(File f : liste){
			if(f.isFile()){
				checkFile(a, f);
			}
			else{
				checkDirectory(a, f);
			}
		}
	}


}
