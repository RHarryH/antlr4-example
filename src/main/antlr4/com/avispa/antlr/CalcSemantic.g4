grammar CalcSemantic;

NEWLINE: '\r'?'\n';
NUM: ([0-9]*[.])?[0-9]+;
WS: [ \t]+ -> skip;

input: (line (NEWLINE line)* NEWLINE?)?;

line returns [double result]: expression { System.out.println("Result: " + $expression.result); $result = $expression.result; };

expression returns [double result]
          : left=expression op=('+'|'-') right=expression { $result = ($op.text.equals("+") ? $left.result + $right.result : $left.result - $right.result); }
          | left=expression op=('*'|'/') right=expression { $result = ($op.text.equals("*") ? $left.result * $right.result : $left.result / $right.result); }
          | left=expression '^' right=expression { $result = Math.pow($left.result, $right.result); }
          | signedAtom { $result = $signedAtom.result; };

signedAtom returns [double result]: '-' atom { $result = -$atom.result; }
          | atom { $result = Double.parseDouble($atom.text); };

atom returns [double result]: NUM { $result = Double.parseDouble($NUM.text); }
    | '(' expression ')' { $result = $expression.result; };