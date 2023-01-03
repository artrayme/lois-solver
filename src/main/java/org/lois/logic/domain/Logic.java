package org.lois.logic.domain;

/**
 * Logic interface to use in Tree Solver
 */
public interface Logic {
    Value not(Value value);
    Value and(Value left, Value right);
    Value or(Value left, Value right);
    Value equal(Value left, Value right);
    Value implication(Value left, Value right);
    Value diamond(Value value);
    Value square(Value value);
}
