grammar Calc;

NEWLINE: '\r'?'\n';
NUM: ([0-9]*[.])?[0-9]+;
WS: [ \t]+ -> skip;

input: (line (NEWLINE line)* NEWLINE?)?;

line: expression;

expression: expression ('+'|'-') expression
          | expression ('*'|'/') expression
          | expression '^' expression
          | signedAtom;

signedAtom: '-' atom
          | atom;

atom: NUM
    | '(' expression ')';