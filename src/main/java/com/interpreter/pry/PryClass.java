package com.interpreter.pry;
import java.util.List;
import java.util.Map;
public class PryClass implements PryCallable {
    final String name;

    private final Map<String, PryFunction> methods;

    PryClass(String name, Map<String, PryFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int arity() {
        PryFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        PryInstance instance = new PryInstance(this);
        PryFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }
    PryFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }
}
