package org.lois.logic.domain;

public interface Logic {
    Value not(Value value);
    Value and(Value left, Value right);
    Value or(Value left, Value right);
    Value impl(Value left, Value right);
    Value diamond(Value left, Value right);
    Value square(Value left, Value right);

    Value equal(Value left, Value right);
}