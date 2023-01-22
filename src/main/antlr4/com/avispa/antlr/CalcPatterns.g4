grammar CalcPatterns;

NEWLINE: '\r'?'\n';
NUM: ([0-9]*[.])?[0-9]+;
WS: [ \t]+ -> skip;

input: (line (NEWLINE line)* NEWLINE?)?;

line: expression;

expression: left=expression op=('+'|'-') right=expression # AddSub
          | left=expression op=('*'|'/') right=expression # MulDiv
          | left=expression '^' right=expression # Pow
          | signedAtom # SignedAtomElement;

signedAtom: '-' atom # UnaryMinus
          | atom #AtomElement;

atom: NUM # Number
    | '(' expression ')' # Parentheses;