## Grammar :
#### program → statement* EOF ;
#### statement → exprStmt | printStmt ;
#### exprStmt → expression ";" ;
#### printStmt → "print" expression ";" ;
#### expression → ternary ;
#### ternary -> equality (equality (?) equality (:) equality)* ;
#### equality → comparison ( ( "!=" | "==" ) comparison )* ;
#### comparison → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
#### term → factor ( ( "-" | "+" ) factor )* ;
#### factor → unary ( ( "/" | "*" ) unary )* ;
#### unary → ( "!" | "-" ) unary | ternary ;

#### primary → NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")"

## Operator   ||   Associativity
#### Equality == != Left \
#### Comparison > >= < <= Left\
#### Term - + Left\
#### Factor / * Left\
#### Unary ! - Right