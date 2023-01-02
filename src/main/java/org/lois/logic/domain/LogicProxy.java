package org.lois.logic.domain;

public class LogicProxy implements Logic {

    Logic original;

    public Logic getOriginal() {
        return original;
    }

    public void setOriginal(Logic original) {
        this.original = original;
    }

    @Override
    public Value not(Value value) {
        return original.not(value);
    }

    @Override
    public Value and(Value left, Value right) {
        return original.and(left, right);
    }

    @Override
    public Value or(Value left, Value right) {
        return original.or(left, right);
    }

    @Override
    public Value implication(Value left, Value right) {
        return original.implication(left, right);
    }

    @Override
    public Value diamond(Value value) {
        return original.diamond(value);
    }

    @Override
    public Value square(Value value) {
        return original.square(value);
    }

    @Override
    public Value equal(Value left, Value right) {
        return original.equal(left, right);
    }
}
