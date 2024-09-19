package codigo;

import java_cup.runtime.*;

%%
%class LexerCup
%type java_cup.runtime.Symbol

%cup
%cupsym simbolos
%full
%line
%char
espacio=[ ,\t,\r\n]+
%{
    private Symbol symbol (int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
    tip="int"|"float"|"byte"|"short"
    iden=[a-zA-Z][a-zA-Z0-9]*

%%
    {tip} {return new Symbol(sym.tipo, yyline,yycolumn, yytext());}
    {iden} {return new Symbol(sym.identificador, yyline,yycolumn, yytext());}
    "," { return new Symbol(sym.coma, yyline,yycolumn, yytext());}
    ";" { return new Symbol(sym.puntoycoma, yyline,yycolumn, yytext());}
    " " { return new Symbol(sym.espacio, yyline,yycolumn, yytext());}
    . {return new Symbol(sym.error, yyline,yycolumn, yytext());}



    //{dv} {return new Symbol(sym.declaracion_de_variables, yyline,yycolumn, yytext());}
    //{w} {return new Symbol(sym.cadena, yyline,yycolumn, yytext());}

   //"a" { return new Symbol(sym.letra_a, yyline,yycolumn, yytext());}
   //"b" { return new Symbol(sym.letra_b, yyline,yycolumn, yytext());}


