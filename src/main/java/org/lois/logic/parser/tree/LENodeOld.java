package org.lois.logic.parser.tree;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

public class LENodeOld {
    private final String expression;
    private BiPredicate<Boolean, Boolean> operator;
    private char operatorSymbol;
    private LENodeOld leftChild;
    private LENodeOld rightChild;

    public LENodeOld(String expression) {
        this.expression = expression;
    }

    @Override
    public LENodeOld getLeftChild() {
        return leftChild;
    }

    @Override
    public void setLeftChild(LENodeOld leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public LENodeOld getRightChild() {
        return rightChild;
    }

    @Override
    public void setRightChild(LENodeOld rightChild) {
        this.rightChild = rightChild;
    }

    public BiPredicate<Boolean, Boolean> getOperator() {
        return operator;
    }

    public char getOperatorSymbol() {
        return operatorSymbol;
    }

    public void setOperator(BiPredicate<Boolean, Boolean> operator, char operatorSymbol) {
        this.operator = operator;
        this.operatorSymbol = operatorSymbol;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public boolean calcValue(Map<String, Boolean> parameters) {
        if (leftChild == null && rightChild == null) {
            return parameters.get(expression);
        } else if (leftChild == null) {
            return operator.test(null, rightChild.calcValue(parameters));
        } else {
            return operator.test(leftChild.calcValue(parameters), rightChild.calcValue(parameters));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LENodeOld node = (LENodeOld) o;
        return Objects.equals(expression, node.expression);
    }
}
