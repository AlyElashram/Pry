package com.interpreter.pry;
import java.util.List;

public class PryFunction implements PryCallable{

    private final Stmt.Function declaration;
    private final Enviroment closure;

    PryFunction(Stmt.Function declaration, Enviroment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }
    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        Enviroment environment = new Enviroment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }
    @Override
    public int arity() {
        return declaration.params.size();
    }
    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
