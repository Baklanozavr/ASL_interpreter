package asl.input;

import asl.model.core.*;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

import static asl.input.sym.*;

/* -----------------Options and Declarations Section----------------- */
%%

// The name of the class JFlex will create.
%public
%final
%class ASLLexer

// Defines the set of characters the scanner will work on. For scanning text files, %unicode should always be used.
// If no version is specified, the most recent supported Unicode version will be used (Unicode 12.1 in JFlex 1.8.2).
%unicode

// Switches to CUP compatibility mode to interface with a CUP generated parser.
%cup

// Turns character counting on. The long member variable yychar contains the number of characters
// (starting with 0) from the beginning of input to the beginning of the current token.
%char

// Switches line counting on (the current line number can be accessed via the variable yyline).
%line

// Switches column counting on (the current column is accessed via yycolumn)
%column
   
/*
  Declarations

  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.
*/
%{
    ComplexSymbolFactory symbolFactory;
    ArrayList<Symbol> openedBrackets = new ArrayList<>();

    private Location left() {
        return new Location(yyline + 1, yycolumn + 1, (int) yychar);
    }

    private Location right() {
        return new Location(yyline + 1, yycolumn + yylength(), (int) (yychar + yylength()));
    }

    private Symbol symbol(String name, int sym) {
        return symbol(name, sym, null);
    }

    // To create a new java_cup.runtime.Symbol with information about the current token
    private Symbol symbol(String name, int sym, Object val) {
        return symbolFactory.newSymbol(name, sym, left(), right(), val);
    }

    private void error(String message) throws java.io.IOException {
        throw new java.io.IOException ("Error at line " + (yyline + 1) + ", column " + (yycolumn + 1) + " : " + message);
    }

    private int getSymBracketToClose() throws java.io.IOException {
        if (0 == openedBrackets.size())
            error ("There is no opened bracket");
        Symbol openedBracket = openedBrackets.get(openedBrackets.size() - 1);
        return openedBracket.sym;
    }

    private Symbol openBracket(Symbol b) {
        openedBrackets.add(b);
        return b;
    }

    private Symbol closeBracket(Symbol b, int expected_open_sym) throws java.io.IOException {
        if (getSymBracketToClose() != expected_open_sym)
            error ("Mismatching bracket");
        openedBrackets.remove(openedBrackets.size() - 1);
        return b;
    }

    private String formatString(String input) {
        return input.substring(1, input.length() - 1);
    }
%}

%init{
	symbolFactory = new ComplexSymbolFactory();
%init}

%eofval{
     return symbolFactory.newSymbol("EOF", EOF, left(), new Location(yyline + 1, yycolumn + 1, (int) (yychar + 1)));
%eofval}

/*
  Macro Declarations
  
  These declarations are regular expressions that will be used latter in the Lexical Rules Section.
*/
   
new_line = \r|\n|\r\n
/* White space is a line terminator, space, tab, or line feed. */
white_space = {new_line} | [ \t\f]

int_lit = 0 | [1-9][0-9]*
double_lit = [0-9]+\.[0-9]+
string_lit = \".*\"
qname_lit = [a-zA-Z_][a-zA-Z0-9_]*([:]+[a-zA-Z0-9_]+)*

true_lit = true
false_lit = false
undef_lit = undef

//dec_int_id = [A-Za-z_][A-Za-z_0-9]*
   
%%
/* ------------------------Lexical Rules Section---------------------- */
   
/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */
   
   /* YYINITIAL is the state at which the lexer begins scanning.  So
   these regular expressions will only be matched if the scanner is in
   the start state YYINITIAL. */
<YYINITIAL> {
  {white_space}     { /* ignore */ }

  {int_lit}         { return symbol("Int Const", INTCONST, IntegerAtom.of(Integer.parseInt(yytext()))); }
  {double_lit}      { return symbol("Double Const", DOUBLECONST, DoubleAtom.of(Double.parseDouble(yytext()))); }
  {string_lit}      { return symbol("String Const", STRING, new StringAtom(formatString(yytext()))); }

  {true_lit}        { return symbol("TrueAtom", TRUE, BooleanAtom.TRUE); }
  {false_lit}       { return symbol("FalseAtom", FALSE, BooleanAtom.FALSE); }
  {undef_lit}       { return symbol("Undef", UNDEF, Undef.UNDEF); }

  {qname_lit}       { return symbol("QName", QNAME, QNameAtom.create(yytext())); }
  \${qname_lit}     { return symbol("Variable", VAR, new Variable(yytext().substring(1))); }
  \#{qname_lit}     { return symbol("AttrVariable", ATTRVAR, new AttrVariable(yytext().substring(1))); }

  "("               { return openBracket(symbol("(", _LPAR)); }
  ")"               { return closeBracket(symbol(")", _RPAR), _LPAR); }
  "{"               { return openBracket(symbol("{", _LCURL)); }
  "}"               { return closeBracket(symbol("}", _RCURL), _LCURL); }
  ";"               { return symbol("semicolon", _SEMICOLON); }
  "="               { return symbol("=", _EQ); }
  ">"               { return symbol(">", _GT); }
  ">="              { return symbol(">=", _GTE); }
  "<"               { return symbol("<", _LT); }
  "<="              { return symbol("<=", _LTE); }
  "+"               { return symbol("plus", _PLUS); }
  "-"               { return symbol("minus", _MINUS); }
  "*"               { return symbol("mul", _STAR); }
  "/"               { return symbol("div", _SLASH); }
  "%"               { return symbol("mod", _PERCENT); }
  ","               { return symbol("comma", _COMMA); }
  "!"               { return symbol("not", _NOT); }
  "!="              { return symbol("!=", _NEQ); }
  "=="              { return symbol("==", _EQQ); }
  "&&"              { return symbol("and", _AND); }
  "||"              { return symbol("or", _OR); }
  "."               { return symbol("dot", _DOT); }
}

/* error fallback */
.   { error("Illegal character `" + yytext() + "`"); }
