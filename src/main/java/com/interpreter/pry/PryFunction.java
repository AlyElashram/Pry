package com.interpreter.pry;
import java.util.List;

public class PryFunction implements PryCallable{

    private final Stmt.Function declaration;
    private final Enviroment closure;
    private final boolean isInitializer;

    PryFunction(Stmt.Function declaration, Enviroment closure , boolean isInitializer) {
        this.declaration = declaration;
        this.closure = closure;
        this.isInitializer = isInitializer;
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
            if (isInitializer) return closure.getAt(0, "this");
            return returnValue.value;
        }
        if (isInitializer) return closure.getAt(0, "this");
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

    PryFunction bind(PryInstance instance) {
        Enviroment environment = new Enviroment(closure);
        environment.define("this", instance);
        return new PryFunction(declaration, environment, isInitializer);
    }
}
