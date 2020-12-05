
package com.asl20;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%public
%final
%class ASL20SampleLexer
%cup
%implements ASL20SampleSymbol
%char
%line
%column

%{
    StringBuffer javaAsIs = new StringBuffer();
    StringBuffer string = new StringBuffer();
    ArrayList<Symbol> notYetClosed = new ArrayList<Symbol>();
    String notYetTerminated = "";
    ComplexSymbolFactory symbolFactory;
    int stateAroundString;
    int outerState = YYINITIAL;

  private Symbol symbol(String name, int sym) {
      return symbolFactory.newSymbol(name, sym, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
  }

  private Symbol symbol(String name, int sym, Object val) {
      Location left = new Location(yyline+1,yycolumn+1,yychar);
      Location right = new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
      return symbolFactory.newSymbol(name, sym, left, right,val);
  }
  private Symbol symbol(String name, int sym, Object val,int buflength) {
      Location left = new Location(yyline+1,yycolumn+yylength()-buflength,yychar+yylength()-buflength);
      Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
      return symbolFactory.newSymbol(name, sym, left, right,val);
  }

  private void error(String message) throws java.io.IOException {
      throw new java.io.IOException ("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message); }

  private int getNotYetClosedSym () throws java.io.IOException {
      if (0 == notYetClosed.size())
          error ("There is no not-yet-closed brace that should be closed");
      Symbol open = notYetClosed.get (notYetClosed.size() - 1);
      return open.sym;
  }

  private Symbol openBrace (Symbol b) { notYetClosed.add (b); return b; }

  private Symbol closeBrace (Symbol b, int expected_open_sym) throws java.io.IOException {
      if (getNotYetClosedSym() != expected_open_sym)
          error ("Mismatching brace");
      notYetClosed.remove (notYetClosed.size() - 1);
      return b;
  }

%}

%ctorarg ComplexSymbolFactory symbolFactory_

%init{
	symbolFactory = symbolFactory_;
%init}

%eofval{
     return symbolFactory.newSymbol("EOF", EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
%eofval}



ident = [a-zA-Z_][a-zA-Z0-9_]*([:]+[a-zA-Z0-9_]+)*
int_literal = 0 | [1-9][0-9]*

new_line = \r|\n|\r\n
white_space = {new_line} | [ \t\f]

%%

<YYINITIAL>{
  ";"               { return symbol("semicolon", _SEMICOLON); }
  "("               { return openBrace(symbol("(",_LPAR)); }
  ")"               { return closeBrace(symbol(")",_RPAR), _LPAR); }
  "="               { return symbol("=",_EQ); }
  "+"               { return symbol("plus",_PLUS); }
  "-"               { return symbol("minus",_MINUS); }
  "*"               { return symbol("mul",_STAR); }
  "/"               { return symbol("div",_SLASH); }
  ","               { return symbol("comma", _COMMA); }
  {int_literal}     { return symbol("Int Const",INTCONST, IntegerAtom.dadd(Integer.parseInt(yytext()))); }
  {white_space}     { /* ignore */ }
  \${ident}         { return symbol("Variable", VAR,  QName.dadd(yytext().substring (1))); }
}

/* error fallback */
.                   { error("Illegal character <"+ yytext()+">"); }
