package com.interpreter.pry;
import java.util.HashMap;
import java.util.Map;
public class PryInstance {
    private PryClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    PryInstance(PryClass klass) {
        this.klass = klass;
    }
    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }
        PryFunction method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RunTimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }
    void set(Token name,Object value){
        fields.put(name.lexeme,value);
    }



    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
