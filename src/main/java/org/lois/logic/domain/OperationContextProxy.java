package org.lois.logic.domain;

public class OperationContextProxy implements OperationContext{

    OperationContext original;

    public OperationContext getOriginal() {
        return original;
    }

    public void setOriginal(OperationContext original) {
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
    public Value impl(Value left, Value right) {
        return original.impl(left, right);
    }

    @Override
    public Value diamond(Value left, Value right) {
        return original.diamond(left, right);
    }

    @Override
    public Value square(Value left, Value right) {
        return original.square(left, right);
    }

    @Override
    public Value equal(Value left, Value right) {
        return original.equal(left, right);
    }
}
