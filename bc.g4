grammar BC;

program: (line';'?)+;


line:
    expr //{ if(!Double.isNaN($expr.val)){System.out.println("result: "+ Double.toString($expr.val));} } 
    | shorthand //{ System.out.println("result: "+ Double.toString($shorthand.val)); } 
    | equation  
    | PRINT print //{ System.out.println(); }
    | COMMENT
    | INLINE_COMMENT
    | NEWLINE
;

expr returns [Double val]: 
    MINUS expr #negateExpr //{ $val = $expr.val * -1; } 
    | OPAREN expr CPAREN #parens //{$val = $expr.val;}
    | el=expr op=POW er=expr  #powExpr //{ $val= Math.pow($el.val,$er.val);}
    | el=expr op=(MULT|DIV) er=expr #multExpr
    // { if($op.text.equals("*")){$val=$el.val*$er.val;} 
    //     else { if($er.val != 0){$val=$el.val/$er.val;}
    //             else{$val=Double.NaN; System.out.println("Runtime error: Divide by zero");}
    //         } }
    
    | el=expr op=(PLUS|MINUS) er=expr #addExpr
    //{ if($op.text.equals("+")){$val=$el.val+$er.val;} else {$val=$el.val-$er.val;} }

    | DOUBLE  #double //{ $val=Double.parseDouble($DOUBLE.text); } 
    | ID #idExpr //{ $val=hmap.getOrDefault($ID.text, 0.0);}

    | NOT expr #notExpr//{ if($expr.val == 0.0){$val = 1.0;} else {$val = 0.0;} }

    | el=expr op=AND er=expr #andExpr
    //{ if($el.val != 0.0 && $er.val != 0.0){$val = 1.0;} else{$val = 0.0;} } 

    | el=expr op=OR er=expr #orExpr
    //{ if($el.val != 0.0 || $er.val != 0.0){$val = 1.0;} else{$val = 0.0;} }

    | SQRT expr CPAREN #sqrtExpr
    // { if($expr.val < 0){$val = Double.NaN; 
    //     System.out.println("Runtime error: Square root of a negative number"); }
    //     else{$val = Math.sqrt($expr.val);} }

    | SIN expr CPAREN #sinExpr//{ $val = Math.sin($expr.val); }
    | COS expr CPAREN #cosExpr//{ $val = Math.cos($expr.val); }
    | LOG expr CPAREN #logExpr //{ $val = Math.log($expr.val); } 
    | EXP expr CPAREN #expExpr //{ $val = Math.exp($expr.val); }
    

    | READ #readExpr //{ $val = scnr.nextDouble(); }
    ;

shorthand returns [Double val]:
    ID op=PP
    | op=PP ID
    | ID op=MM
    | op=MM ID
;

equation returns [Double val]:
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


//Boolean
NOT: '!';
AND: '&&';
OR: '||';


READ: 'read()';
PRINT: 'print';

//Statements
IF: 'if';
WHILE: 'while';
FOR: 'for';
FUNCTION: 'define';

//Characters
OPAREN: '(';
CPAREN: ')';


NEWLINE:'\r'? '\n';
COMMENT: '/*' .*? '*/';
INLINE_COMMENT: '#' .*? '\n';
ID: [_A-Za-z]+;
DOUBLE: ([0-9]*[.])?[0-9]+;
WS : [ \t]+ -> skip ;
NEGATIVE_SQRT: SQRT'(''-'[0-9]+')';