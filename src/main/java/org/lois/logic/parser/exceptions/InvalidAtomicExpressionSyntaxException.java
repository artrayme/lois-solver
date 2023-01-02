package org.lois.logic.parser.exceptions;

public class InvalidAtomicExpressionSyntaxException extends Exception {
    private final String expression;
    public InvalidAtomicExpressionSyntaxException(String expression) {
        super("Expression \"" + expression + "\" is not valid");
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
