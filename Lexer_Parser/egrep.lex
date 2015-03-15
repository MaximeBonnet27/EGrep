/* Declarations */
package cpa.parser;

%%
%byaccj

%{
private Parser yyparser;
public Yylex(java.io.Reader r, Parser yyparser){
	this(r);
	this.yyparser = yyparser;
}
%}


ORD_CHAR = [a-zA-Z0-9_]
SPACE = [ \n\r\t]

%%

"\\^" | "\\." | "\\[" | "\\$" | "\\(" | "\\)" | "\\|" | "\\*" | "\\+" | "\\+" | "\\?" | "\\{" | "\\\\" {yyparser.yylval = new ParserVal(yytext()); return Parser.QUOTED_CHAR;} 
{ORD_CHAR} {yyparser.yylval = new ParserVal(yytext()); return Parser.ORD_CHAR; }
"^" | "$" | "(" | ")" | "*" | "." | "|" | "+" | "?" | "{" | "}" | "," {return (int) yycharat(0);}
{SPACE} {}
[^] { System.err.println("Unexpected char : '" + yytext() + "'"); }
