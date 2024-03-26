## Grammar :
#### program → statement* EOF ;
#### declaration → funDecl | varDecl| classDecl | statement ;

#### varDecl     → "var" IDENTIFIER ( "=" expression )? ";" ;
#### classDecl   → "class" IDENTIFIER "{" function* "}" ;
#### funDecl     → "fun" function ;
#### function    → IDENTIFIER "(" parameters? ")" block ;
#### parameters  → IDENTIFIER ( "," IDENTIFIER )* ;
#### statement   → exprStmt| forStmt | printStmt|  | returnStmt | whileStmt | block ;
#### exprStmt    → expression ";" ;
#### forStmt     → "for" "(" ( varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement ;
#### printStmt   → "print" expression ";" ;
#### ifStmt      → "if" "(" expression ")" statement ( "else" statement )? ;
#### whileStmt   → "while" "(" expression ")" statement ;
#### block       → "{" declaration* "}" ;
#### returnStmt  → "return" expression? ";" ;

#### expression  → assignment ;
#### assignment  → ( call "." )? IDENTIFIER "=" assignment | logic_or ;
#### logic_or    → logic_and ( "or" logic_and )* ;
#### logic_and   → ternary ( "and" ternary )* ;
#### ternary     → equality (equality (?) equality (:) equality)* ;
#### equality    → comparison ( ( "!=" | "==" ) comparison )* ;
#### comparison  → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
#### term        → factor ( ( "-" | "+" ) factor )* ;
#### factor      → unary ( ( "/" | "*" ) unary )* ;
#### unary       → ( "!" | "-" ) unary | call ;
#### call        → primary ( "(" arguments? ")" | "." IDENTIFIER )* ;
#### primary     → NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")";
#### arguments   → expression ( "," expression )* ;

## Operator   ||   Associativity
#### Equality == != Left \
#### Comparison > >= < <= Left\
#### Term - + Left\
#### Factor / * Left\
#### Unary ! - Right


Generate AST command :  java src/main/java/com/interpreter/tools/GenerateAst.java "src/main/java/com/interpreter/pry/"