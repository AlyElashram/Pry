package com.interpreter.pry;
import java.util.List;
public interface PryCallable {
int arity();
Object call(Interpreter interpreter, List<Object> arguments);



}
