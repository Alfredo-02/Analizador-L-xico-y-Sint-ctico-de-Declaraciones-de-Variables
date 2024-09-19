package codigo;

import static codigo.tokens.*;

%%

%class lex
%type tokens


%{
public String lexeme;
%}
   tip="int"|"float"|"byte"|"short"
   iden=[a-zA-Z][a-zA-Z0-9]*

%%     
    {tip} {lexeme=yytext(); return tipo;} 
    {iden} {lexeme=yytext(); return identificador;}
    "," {lexeme=yytext(); return coma;}
    ";" {lexeme=yytext(); return puntoycoma;}
    " " {lexeme=yytext(); return espacio;}  
    . {return error;}

