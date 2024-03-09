package com.interpreter.pry;


abstract class Expr {
 interface Visitor<R> {
 R visitBinaryExpr(Binary expr);
 R visitGroupingExpr(Grouping expr);
 R visitLiteralExpr(Literal expr);
 R visitTernaryExpr(Ternary expr);
 R visitUnaryExpr(Unary expr);
 R visitVariableExpr(Variable expr);
 R visitAssignExpr(Assign expr);

 }
 abstract <R> R accept(Visitor<R> visitor);
 static class Binary extends Expr {
 Binary(Expr left, Token operator, Expr right) {
 this.left = left;
 this.operator = operator;
 this.right = right;
 }

 @Override
 <R> R accept(Visitor<R> visitor) {
 return visitor.visitBinaryExpr(this);
 }

 final Expr left;
 final Token operator;
 final Expr right;
 }
 static class Grouping extends Expr {
 Grouping(Expr expression) {
 this.expression = expression;
 }

 @Override
 <R> R accept(Visitor<R> visitor) {
 return visitor.visitGroupingExpr(this);
 }

 final Expr expression;
 }
 static class Literal extends Expr {
 Literal(Object value) {
 this.value = value;
 }

 @Override
 <R> R accept(Visitor<R> visitor) {
 return visitor.visitLiteralExpr(this);
 }

 final Object value;
 }
 static class Unary extends Expr {
 Unary(Token operator, Expr right) {
 this.operator = operator;
 this.right = right;
 }

 @Override
 <R> R accept(Visitor<R> visitor) {
 return visitor.visitUnaryExpr(this);
 }

 final Token operator;
 final Expr right;
 }
 static class Ternary extends Expr {

  final Expr condition;
  final Expr valid;
  final Expr invalid;

  Ternary(Expr condition, Expr valid, Expr invalid) {
   this.condition = condition;
   this.valid = valid;
   this.invalid = invalid;
  }

  @Override
  <R> R accept(Visitor<R> visitor) {
   return visitor.visitTernaryExpr(this);
  }
 }
 static class Variable extends Expr {
 Variable(Token name) {
 this.name = name;
 }

 @Override
 <R> R accept(Visitor<R> visitor) {
 return visitor.visitVariableExpr(this);
 }

 final Token name;
 }

 static class Assign extends Expr{
  final Token name;
  final Expr value;
  Assign(Token name,Expr value){
   this.name = name;
   this.value = value;
  }
  @Override
  <R> R accept(Visitor<R> visitor) {
   return visitor.visitAssignExpr(this);
  }

 }
}
