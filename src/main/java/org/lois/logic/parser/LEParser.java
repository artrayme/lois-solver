package org.lois.logic.parser;

import org.lois.logic.parser.exceptions.InvalidBracketsException;
import org.lois.logic.parser.tree.LENodeOld;
import org.lois.logic.parser.tree.LETree;
import org.lois.logic.parser.exceptions.InvalidAtomicExpressionSyntaxException;
import org.lois.logic.parser.exceptions.InvalidOperatorException;
import org.lois.logic.parser.exceptions.InvalidSyntaxCharacterException;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class LEParser {


    private LEParser() {
    }

    public static LETree valueOf(String expression) throws InvalidBracketsException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidOperatorException {
        LEParser LEParser = new LEParser();
        if (checkBrackets(expression) == -1) {
            throw new InvalidBracketsException();
        }
        Optional<Character> invalidSymbol = checkSymbolsValidity(expression);
        if (invalidSymbol.isPresent()) {
            throw new InvalidSyntaxCharacterException(invalidSymbol.get());
        }
        LETree leTree = new LETree(LEParser.parseRecursive(expression));
        return leTree;
    }

    public static int checkBrackets(String expression) {
        if (expression.isEmpty())
            return 0;

        int maxDepth = 0;
        int counter = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (counter > maxDepth)
                maxDepth = counter;
            char c = expression.charAt(i);
            if (c == Constants.OPEN_BRACKET)
                counter++;
            if (c == Constants.CLOSE_BRACKET)
                counter--;
            if (counter < 0)
                return -1;
            if (counter == 0 && i < expression.length() - 1)
                return -1;
        }
        if (counter != 0) {
            return -1;
        }

        return maxDepth;
    }

    public static Optional<Character> checkSymbolsValidity(String expression) {
        for (char c : expression.toCharArray()) {
            if (!isAtomicExpressionSymbol(c)
                    && !isOperatorSymbol(c)
                    && c != Constants.OPEN_BRACKET
                    && c != Constants.CLOSE_BRACKET
            ) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public static boolean checkAtomicSyntax(String expression) {
        if (expression.length() != 1)
            return false;
        return isAtomicExpressionSymbol(expression.charAt(0));
    }

    private static boolean isOperatorSymbol(Character symbol) {
        return symbol == Constants.CONJUNCTION
                || symbol == Constants.DISJUNCTION
                || symbol == Constants.NEGATION
                || symbol == Constants.EQUALITY
                || symbol == Constants.RHOMBUS
                || symbol == Constants.SQUARE
                || symbol == Constants.IMPLICIT;
    }

    private static boolean isAtomicExpressionSymbol(char symbol) {
        return ((symbol >= 'A' && symbol <= 'Z') || symbol == Constants.TRUE || symbol == Constants.FALSE);
    }

    private LENodeOld parseRecursive(String expressionPart) throws InvalidAtomicExpressionSyntaxException, InvalidOperatorException, InvalidBracketsException {
        if (expressionPart.isEmpty())
            throw new InvalidAtomicExpressionSyntaxException(expressionPart);
        if (expressionPart.charAt(0) != Constants.OPEN_BRACKET) {
            if (!checkAtomicSyntax(expressionPart)) {
                throw new InvalidAtomicExpressionSyntaxException(expressionPart);
            }
            return new LENodeOld(expressionPart);
        }
        if (expressionPart.length() < 3)
            throw new InvalidAtomicExpressionSyntaxException(expressionPart);

        LENodeOld node = new LENodeOld(expressionPart);
        if (expressionPart.charAt(1) == Constants.NEGATION) {
            node.setOperator(convertToOperator(Constants.NEGATION), Constants.NEGATION);
            node.setRightChild(parseRecursive(expressionPart.substring(2, expressionPart.length() - 1)));
        } else if (checkBrackets(expressionPart) == 1) {
            flatExpression(expressionPart.substring(1, expressionPart.length() - 1), node);
        } else {
            deepExpression(expressionPart, node);
        }
        return node;

    }

    private String getPartByRule(AtomicInteger index, String expressionPart, Predicate<Character> rule) {
        StringBuilder part = new StringBuilder();
        while (index.get() < expressionPart.length() && rule.test(expressionPart.charAt(index.get()))) {
            part.append(expressionPart.charAt(index.get()));
            index.incrementAndGet();
        }
        return part.toString();
    }

    private void flatExpression(String expressionPart, LENodeOld node) throws InvalidOperatorException, InvalidAtomicExpressionSyntaxException {
        AtomicInteger index = new AtomicInteger();
        String firstPart = getPartByRule(index, expressionPart, LEParser::isAtomicExpressionSymbol);
        if (!checkAtomicSyntax(firstPart)) {
            throw new InvalidAtomicExpressionSyntaxException(firstPart);
        }
        String operator = getPartByRule(index, expressionPart, e -> !isAtomicExpressionSymbol(e) && e != Constants.NEGATION);
        if (operator.length() > 1)
            throw new InvalidOperatorException(operator);

        if (index.get() >= expressionPart.length())
            throw new InvalidOperatorException(expressionPart);

        String secondPart = expressionPart.substring(index.get());
        if (!checkAtomicSyntax(secondPart)) {
            throw new InvalidAtomicExpressionSyntaxException(secondPart);
        }

        node.setLeftChild(new LENodeOld(firstPart));
        node.setOperator(convertToOperator(operator.charAt(0)), operator.charAt(0));
        node.setRightChild(new LENodeOld(secondPart));
    }

    private void deepExpression(String expressionPart, LENodeOld node) throws InvalidOperatorException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        LEParsedEntity entity = splitExpression(expressionPart.substring(1, expressionPart.length() - 1));
        if (entity.getOperator().length() != 1)
            throw new InvalidOperatorException(entity.getOperator());
        node.setOperator(convertToOperator(entity.getOperator().charAt(0)), entity.getOperator().charAt(0));
        node.setLeftChild(parseRecursive(entity.getFirstPart()));
        node.setRightChild(parseRecursive(entity.getSecondPart()));
    }

    private LEParsedEntity splitExpression(String expression) throws InvalidOperatorException, InvalidBracketsException {
        LEParsedEntity result = new LEParsedEntity();
        StringBuilder operator = new StringBuilder();
        AtomicInteger index = new AtomicInteger(0);
        result.setFirstPart(extractExpressionPart(expression, index));
        if (index.get() == expression.length())
            throw new InvalidOperatorException(expression);
        if (expression.charAt(index.get()) == Constants.NEGATION)
            throw new InvalidOperatorException(Constants.NEGATION);

        while (LEParser.isOperatorSymbol(expression.charAt(index.get()))) {
            operator.append(expression.charAt(index.get()));
            index.incrementAndGet();
            if (index.get() == expression.length())
                throw new InvalidOperatorException(expression);
        }
        result.setOperator(operator.toString());

        result.setSecondPart(extractExpressionPart(expression, index));
        if (!(result.getFirstPart() + result.getOperator() + result.getSecondPart()).equals(expression))
            throw new InvalidOperatorException(expression.substring(index.get()));
        return result;
    }

    private String extractExpressionPart(String expression, AtomicInteger index) throws InvalidBracketsException {
        int counter = 0;
        StringBuilder part = new StringBuilder();
        do {
            char c = expression.charAt(index.get());
            if (c == Constants.OPEN_BRACKET)
                counter++;
            if (c == Constants.CLOSE_BRACKET)
                counter--;
            part.append(c);
            index.incrementAndGet();
            if (counter < 0)
                throw new InvalidBracketsException();
        } while (counter != 0);
        return part.toString();
    }

    private BiPredicate<Boolean, Boolean> convertToOperator(Character sign) throws InvalidOperatorException {
        return switch (sign) {
            case Constants.CONJUNCTION -> (a, b) -> a && b;
            case Constants.DISJUNCTION -> (a, b) -> a || b;
            case Constants.EQUALITY -> (a, b) -> a == b;
            case Constants.NEGATION -> (a, b) -> !b;
            case Constants.IMPLICIT -> (a, b) -> (!a) || b;
            case Constants.RHOMBUS -> (a, b) -> a || b;
            case Constants.SQUARE -> (a, b) -> a || b;
            default -> throw new InvalidOperatorException(sign);
        };
    }
}
