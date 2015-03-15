
%{
import java.io.*;
import java.util.*;
import cpa.automate.Automate;
import cpa.factory.AutomateFactory;
%}
%token <sval> ORD_CHAR 
%token <sval> QUOTED_CHAR

%type <obj> extended_reg_exp
%type <obj> ERE_branch
%type <obj> ERE_expression
%type <obj> one_char_or_coll_elem_ERE
%type <sval> ERE_dupl_symbol

%start  extended_reg_exp
%%

extended_reg_exp   : ERE_branch {this.resultat = (Automate) ((Automate) $1).clone();}
                   | extended_reg_exp '|' ERE_branch { resultat = AutomateFactory.createUnion((Automate) $1, (Automate) $3); }
                   ;
ERE_branch         : ERE_expression {$$ = $1;}
                   | ERE_branch ERE_expression {  $$ = AutomateFactory.createConcatenation((Automate) $1,(Automate) $2); }
                   ;
ERE_expression     : one_char_or_coll_elem_ERE { $$ = $1;}
				   | '^' { $$ = AutomateFactory.createAutomateDebut(); }
				   | '$' { $$ = AutomateFactory.createAutomateFin(); }
                   | '(' extended_reg_exp ')' { $$ = $2; }
                   | ERE_expression ERE_dupl_symbol { 
                   	switch($2.charAt(0)){
                   	case '*' : $$ = AutomateFactory.createEtoile((Automate) $1); break;
                   	case '+' : $$ = AutomateFactory.createPlus((Automate) $1); break;
                   	case '?' : $$ = AutomateFactory.createPointInterrogation((Automate) $1); break;
                   	case 'm' : $$ = AutomateFactory.createMultiplicateur((Automate) $1, Integer.parseInt($2.substring(1, $2.length()))); break;
                   	case 's' : $$ = AutomateFactory.createMultiplicateurSuperieur((Automate) $1, Integer.parseInt($2.substring(1, $2.length()))); break;
                   	case 'i' : 
                   		    String[] tab = $2.substring(1, $2.length()).split(",");
                   		    int m = Integer.parseInt(tab[0]);
                   		    int n = Integer.parseInt(tab[1]);
                   		    $$ = AutomateFactory.createMultiplicateurIntervalle((Automate) $1, m, n); break;
                   		
                   	}
                    } 
                   ;
one_char_or_coll_elem_ERE  : ORD_CHAR { $$ =  AutomateFactory.createAutomate($1.charAt(0));}
				   | QUOTED_CHAR { $$ = AutomateFactory.createAutomate($1.charAt(1)); }
				   | '.'  { $$ = AutomateFactory.createAutomatePoint(); }
                   ;
ERE_dupl_symbol    : '*' { $$ = "*"; }
				   | '+' { $$ = "+"; }
				   | '?' { $$ = "?"; }
				   | '{' ORD_CHAR '}' { $$ = "m"+$2; }
				   | '{' ORD_CHAR ',' '}' { $$ = "s"+$2; }
				   | '{' ORD_CHAR ',' ORD_CHAR '}' { $$ = "i" + $2 + "," + $4;}
				   ;
				   
				   
				   
				   
%%

private Yylex lexer;

private int yylex(){
	int yyl_return = -1;
	try{
		yylval = new ParserVal(0);
		yyl_return = lexer.yylex();
	}
	catch(IOException e){
		System.err.println("IO Error : " + e);
	}
	return yyl_return;
}

public void yyerror(String error){
	System.err.println("Error : " + error);
}


public Parser (Reader r){
	lexer = new Yylex(r, this);
}

public static Automate resultat;

public Automate getAutomateFromFile(String filename){
	Parser yyparser = null;
	try{
	yyparser = new Parser(new FileReader(filename));
	}
	catch(FileNotFoundException e){
		System.err.println("Not found");
		return null;
	}
	yyparser.yyparse();
	return resultat;
}

	
               