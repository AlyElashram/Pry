## Grammar :
#### program → statement* EOF ;
#### declaration → varDecl | statement ;
#### varDecl → "var" IDENTIFIER ( "=" expression )? ";" ;
#### statement → exprStmt| forStmt | printStmt| ifStmt | whileStmt | block ;
#### exprStmt → expression ";" ;
#### forStmt → "for" "(" ( varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement ;
#### printStmt → "print" expression ";" ;
#### ifStmt → "if" "(" expression ")" statement ( "else" statement )? ;
#### whileStmt → "while" "(" expression ")" statement ;
#### block → "{" declaration* "}" ;

#### expression → assignment ;
#### assignment → IDENTIFIER "=" assignment | logic_or ;
#### logic_or → logic_and ( "or" logic_and )* ;
#### logic_and → ternary ( "and" ternary )* ;
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