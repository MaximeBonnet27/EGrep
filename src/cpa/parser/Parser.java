//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package cpa.parser;



//#line 3 "Lexer_Parser/egrep.y"
import java.io.*;
import java.util.*;
import cpa.automate.Automate;
import cpa.factory.AutomateFactory;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ORD_CHAR=257;
public final static short QUOTED_CHAR=258;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    2,    2,    2,    2,    2,    3,
    3,    3,    4,    4,    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    1,    3,    1,    2,    1,    1,    1,    3,    2,    1,
    1,    1,    1,    1,    1,    3,    4,    5,
};
final static short yydefred[] = {                         0,
   10,   11,    6,    7,    0,   12,    0,    0,    0,    5,
    0,    0,    0,   13,   14,   15,    0,    9,    8,    0,
    0,   16,    0,    0,   17,   18,
};
final static short yydgoto[] = {                          7,
    8,    9,   10,   18,
};
final static short yysindex[] = {                       -36,
    0,    0,    0,    0,  -36,    0, -116,  -36,  -31,    0,
  -35,  -36,  -31,    0,    0,    0, -248,    0,    0,  -36,
  -29,    0, -123, -112,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    5,    1,    0,
    0,    0,    3,    0,    0,    0,    0,    0,    0,    7,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                        11,
    8,    6,    0,    0,
};
final static int YYTABLESIZE=261;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                          4,
    3,   25,    4,    5,    1,   19,    2,   12,   21,    6,
   14,   15,   26,   13,   23,   11,    0,    0,    0,   20,
    0,    0,    0,    0,    0,   13,    0,    0,    0,    0,
    0,   16,    0,    0,    0,    0,    3,    0,    4,    0,
    3,    3,    4,    4,    0,    1,    3,    2,    4,    0,
    0,    0,    0,    0,    0,    0,    0,    3,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   12,    0,
    0,   17,    0,    0,    3,   22,    4,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    0,    4,    0,    1,    0,
    2,    0,    0,   24,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    3,    3,    4,
    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         36,
    0,  125,    0,   40,    0,   41,    0,  124,  257,   46,
   42,   43,  125,    8,   44,    5,   -1,   -1,   -1,   12,
   -1,   -1,   -1,   -1,   -1,   20,   -1,   -1,   -1,   -1,
   -1,   63,   -1,   -1,   -1,   -1,   36,   -1,   36,   -1,
   40,   41,   40,   41,   -1,   41,   46,   41,   46,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   94,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  124,   -1,
   -1,  123,   -1,   -1,   94,  125,   94,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  124,   -1,  124,   -1,  124,   -1,
  124,   -1,   -1,  257,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  257,
  258,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=258;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"'$'",null,null,null,"'('","')'","'*'","'+'",
"','",null,"'.'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,"'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,"'^'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'","'|'","'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ORD_CHAR","QUOTED_CHAR",
};
final static String yyrule[] = {
"$accept : extended_reg_exp",
"extended_reg_exp : ERE_branch",
"extended_reg_exp : extended_reg_exp '|' ERE_branch",
"ERE_branch : ERE_expression",
"ERE_branch : ERE_branch ERE_expression",
"ERE_expression : one_char_or_coll_elem_ERE",
"ERE_expression : '^'",
"ERE_expression : '$'",
"ERE_expression : '(' extended_reg_exp ')'",
"ERE_expression : ERE_expression ERE_dupl_symbol",
"one_char_or_coll_elem_ERE : ORD_CHAR",
"one_char_or_coll_elem_ERE : QUOTED_CHAR",
"one_char_or_coll_elem_ERE : '.'",
"ERE_dupl_symbol : '*'",
"ERE_dupl_symbol : '+'",
"ERE_dupl_symbol : '?'",
"ERE_dupl_symbol : '{' ORD_CHAR '}'",
"ERE_dupl_symbol : '{' ORD_CHAR ',' '}'",
"ERE_dupl_symbol : '{' ORD_CHAR ',' ORD_CHAR '}'",
};

//#line 58 "Lexer_Parser/egrep.y"

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

	
               
//#line 275 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 20 "Lexer_Parser/egrep.y"
{this.resultat = (Automate) ((Automate) val_peek(0).obj).clone();}
break;
case 2:
//#line 21 "Lexer_Parser/egrep.y"
{ resultat = AutomateFactory.createUnion((Automate) val_peek(2).obj, (Automate) val_peek(0).obj); }
break;
case 3:
//#line 23 "Lexer_Parser/egrep.y"
{yyval.obj = val_peek(0).obj;}
break;
case 4:
//#line 24 "Lexer_Parser/egrep.y"
{  yyval.obj = AutomateFactory.createConcatenation((Automate) val_peek(1).obj,(Automate) val_peek(0).obj); }
break;
case 5:
//#line 26 "Lexer_Parser/egrep.y"
{ yyval.obj = val_peek(0).obj;}
break;
case 6:
//#line 27 "Lexer_Parser/egrep.y"
{ yyval.obj = AutomateFactory.createAutomateDebut(); }
break;
case 7:
//#line 28 "Lexer_Parser/egrep.y"
{ yyval.obj = AutomateFactory.createAutomateFin(); }
break;
case 8:
//#line 29 "Lexer_Parser/egrep.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 9:
//#line 30 "Lexer_Parser/egrep.y"
{ 
                   	switch(val_peek(0).sval.charAt(0)){
                   	case '*' : yyval.obj = AutomateFactory.createEtoile((Automate) val_peek(1).obj); break;
                   	case '+' : yyval.obj = AutomateFactory.createPlus((Automate) val_peek(1).obj); break;
                   	case '?' : yyval.obj = AutomateFactory.createPointInterrogation((Automate) val_peek(1).obj); break;
                   	case 'm' : yyval.obj = AutomateFactory.createMultiplicateur((Automate) val_peek(1).obj, Integer.parseInt(val_peek(0).sval.substring(1, val_peek(0).sval.length()))); break;
                   	case 's' : yyval.obj = AutomateFactory.createMultiplicateurSuperieur((Automate) val_peek(1).obj, Integer.parseInt(val_peek(0).sval.substring(1, val_peek(0).sval.length()))); break;
                   	case 'i' : 
                   		    String[] tab = val_peek(0).sval.substring(1, val_peek(0).sval.length()).split(",");
                   		    int m = Integer.parseInt(tab[0]);
                   		    int n = Integer.parseInt(tab[1]);
                   		    yyval.obj = AutomateFactory.createMultiplicateurIntervalle((Automate) val_peek(1).obj, m, n); break;
                   		
                   	}
                    }
break;
case 10:
//#line 46 "Lexer_Parser/egrep.y"
{ yyval.obj =  AutomateFactory.createAutomate(val_peek(0).sval.charAt(0));}
break;
case 11:
//#line 47 "Lexer_Parser/egrep.y"
{ yyval.obj = AutomateFactory.createAutomate(val_peek(0).sval.charAt(1)); }
break;
case 12:
//#line 48 "Lexer_Parser/egrep.y"
{ yyval.obj = AutomateFactory.createAutomatePoint(); }
break;
case 13:
//#line 50 "Lexer_Parser/egrep.y"
{ yyval.sval = "*"; }
break;
case 14:
//#line 51 "Lexer_Parser/egrep.y"
{ yyval.sval = "+"; }
break;
case 15:
//#line 52 "Lexer_Parser/egrep.y"
{ yyval.sval = "?"; }
break;
case 16:
//#line 53 "Lexer_Parser/egrep.y"
{ yyval.sval = "m"+val_peek(1).sval; }
break;
case 17:
//#line 54 "Lexer_Parser/egrep.y"
{ yyval.sval = "s"+val_peek(2).sval; }
break;
case 18:
//#line 55 "Lexer_Parser/egrep.y"
{ yyval.sval = "i" + val_peek(3).sval + "," + val_peek(1).sval;}
break;
//#line 510 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
