grammar BC;

//program: (line';'?)+;
program: block EOF;

block: statement*;

statement:
                    
    ifstate             #ifCheck
    | whilestate        #whileCheck
    | expr              #exprPrint //{ if(!Double.isNaN($expr.val)){System.out.println("result: "+ Double.toString($expr.val));} } 
    | shorthand         #shorthandCheck//{ System.out.println("result: "+ Double.toString($shorthand.val)); } 
    | equation          #equationCheck
    | PRINT print       #printCheck //{ System.out.println(); }

    | COMMENT           #commentCheck
    | INLINE_COMMENT    #inlineCheck
;

methodDef:
    FUNCTION ID OPAREN methodArgs CPAREN actions
;

methodCall:
    ID OPAREN methodArgs CPAREN
;

methodArgs:
    //optional arguments
    | expr (',' expr)*
;

whilestate: 
    WHILE expr actions
;

ifstate:
    IF OPAREN expr CPAREN actions (ELSE IF OPAREN expr CPAREN actions)* (ELSE actions)?
;

forstate:
    FOR equation SEMIC expr SEMIC
;

expr: 
    MINUS expr                          #negateExpr 
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
    ID op=ASSIGN expr //{ hmap.put($ID.text, $expr.val); }
    | ID op=PLUSEQ expr //{ hmap.put($ID.text, hmap.getOrDefault($ID.text,0.0) + $expr.val); }
    | ID op=MINUSEQ expr //{ hmap.put($ID.text, hmap.getOrDefault($ID.text,0.0) - $expr.val); }
    | ID op=MULTEQ expr //{ hmap.put($ID.text, hmap.getOrDefault($ID.text,0.0) * $expr.val); }
    | ID op=DIVEQ expr //{ hmap.put($ID.text, hmap.getOrDefault($ID.text,0.0) / $expr.val); }
    | ID op=POWEQ expr //{ hmap.put($ID.text, Math.pow(hmap.getOrDefault($ID.text,0.0), $expr.val)); }
    | ID op=ASSIGN READ  //{ double toAdd = scnr.nextDouble(); hmap.put($ID.text, toAdd); }
;

print:
    '"'ID'"' //{System.out.print($ID.text); } (',' print)? 
    | expr //{System.out.print($expr.val); } (',' print)? 
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

//Statements
IF: 'if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
FUNCTION: 'define';
RETURN: 'return';

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