package org.example.logic.domain;

public interface OperationContext {
    Value not(Value value);
    Value and(Value left, Value right);
    Value or(Value left, Value right);
    Value impl(Value left, Value right);
    Value diamond(Value left, Value right);
    Value square(Value left, Value right);
}
