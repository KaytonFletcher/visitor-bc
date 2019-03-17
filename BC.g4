grammar BC;

//program: (line';'?)+;
program: block EOF;

block: statement*;

statement:
    methodDef           #defineCheck
    | RETURN expr       #returnCheck
    | BREAK             #breakCheck
    | CONTINUE          #continueCheck                
    
    | ifstate           #ifCheck
    | whilestate        #whileCheck
    | forstate          #forCheck
    
    | expr              #exprPrint
    | shorthand         #shorthandPrint
    | equation          #equationCheck
    | PRINT print       #printCheck

    | expr              #exprPrint 
    | COMMENT           #commentCheck
    | INLINE_COMMENT    #inlineCheck
;

methodDef:
    FUNCTION ID OPAREN methodDefArgs CPAREN functionblock
;

methodCall:
    ID OPAREN methodCallArgs CPAREN
;

methodDefArgs:
    //optional arguments
    | ID (',' ID)*
;

methodCallArgs:
    | expr (',' expr)*
;

whilestate: 
    WHILE expr actions
;

ifstate:
    IF OPAREN expr CPAREN actions (ELSE IF OPAREN expr CPAREN actions)* (ELSE actions)?
;

forstate:
    FOR OPAREN equation SEMIC expr SEMIC shorthand CPAREN actions
;

expr:
    methodCall                          #callExpr
    | MINUS expr                        #negateExpr 
    | OPAREN expr CPAREN                #parens 
    | el=expr op=POW er=expr            #powExpr
    | el=expr op=(MULT|DIV) er=expr     #multExpr
    | el=expr op=(PLUS|MINUS) er=expr   #addExpr

    | el=expr op=LTHAN er=expr          #lessExpr
    | el=expr op=LTHANEQ er=expr        #lessEqExpr
    | el=expr op=GTHAN er=expr          #greaterExpr
    | el=expr op=GTHANEQ er=expr        #greaterEqExpr
    | el=expr op=EQUAL er=expr          #equalExpr
    | el=expr op=NOTEQUAL er=expr       #notequalExpr
    
    | NOT expr                          #notExpr
    | el=expr op=AND er=expr            #andExpr
    | el=expr op=OR er=expr             #orExpr


    | SQRT expr CPAREN                  #sqrtExpr
    | SIN expr CPAREN                   #sinExpr
    | COS expr CPAREN                   #cosExpr
    | LOG expr CPAREN                   #logExpr 
    | EXP expr CPAREN                   #expExpr 
    
    | READ                              #readExpr
    
    | DOUBLE                            #double 
    | ID                                #idExpr
;

functionblock:
    OBRACE block CBRACE 
;

actions:
    OBRACE block CBRACE 
    | statement
;

shorthand:
    ID op=PP
    | op=PP ID
    | ID op=MM
    | op=MM ID
;

equation:
    ID op=ASSIGN expr
    | ID op=PLUSEQ expr 
    | ID op=MINUSEQ expr
    | ID op=MULTEQ expr 
    | ID op=DIVEQ expr
    | ID op=POWEQ expr 
    | ID op=ASSIGN READ 
;

print:
    '"'ID'"'  (',' print)? //{System.out.print($ID.text); }
    | expr  (',' print)? //{System.out.print($expr.val); }
;

//Math operators/functions
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
POW: '^';
ASSIGN: '=';
SQRT: 'sqrt(';
SIN: 's(';
COS: 'c(';
LOG: 'l(';
EXP: 'e(';

//Shorthand
PLUSEQ: '+=';
MINUSEQ: '-=';
MULTEQ: '*=';
DIVEQ: '/=';
POWEQ: '^=';
PP: '++';
MM: '--';

//Relational
LTHAN: '<';
LTHANEQ: '<=';
GTHAN: '>';
GTHANEQ: '>=';
EQUAL: '==';
NOTEQUAL: '!=';

//Boolean
NOT: '!';
AND: '&&';
OR: '||';


READ: 'read()';
PRINT: 'print';

//Key words
IF: 'if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
FUNCTION: 'define';
RETURN: 'return';
BREAK: 'break';
CONTINUE: 'continue';


//Characters
OPAREN: '(';
CPAREN: ')';
OBRACE: '{';
CBRACE: '}';
SEMIC: ';';


//NEWLINE:'\r'? '\n';
COMMENT: '/*' .*? '*/';
INLINE_COMMENT: '#' .*? '\n';
ID: [_A-Za-z]+;
DOUBLE: ([0-9]*[.])?[0-9]+;
WS : [ \t\n\r]+ -> skip ;
NEGATIVE_SQRT: SQRT'(''-'[0-9]+')';