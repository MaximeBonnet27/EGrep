#! /bin/bash

jflex Lexer_Parser/egrep.lex
mv Lexer_Parser/Yylex.java src/cpa/parser/
Lexer_Parser/byacc -Jpackage="cpa.parser" Lexer_Parser/egrep.y && mv Parser.java src/cpa/parser/ && mv ParserVal.java src/cpa/parser/

